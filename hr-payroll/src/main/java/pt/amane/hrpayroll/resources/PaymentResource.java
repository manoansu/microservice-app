package pt.amane.hrpayroll.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import pt.amane.hrpayroll.entities.Payment;
import pt.amane.hrpayroll.services.PaymentService;

@RestController
@RequestMapping(value = "/payments")
public class PaymentResource {
	
	@Autowired
	private PaymentService service;
	
	/*  Funcionario quer saber, o pagamento de nº de dias que 
	* um trabalhador q trabalhaonde:
	* {workerId} -> é o id de funcionario..
	* days -> é o dias que funcionario trabalhou(esse parametro pde ser dia, mes, ano, 
	* ou qq ouca coisa para associar esse endpoint)
	* {day} -> é qtdade de dias que ele trabalha para ser pago..
	* esse endpoint precisa chamr worker e pode ter problema de comuicação
	* serviço esta em baixo ou time out.
	*/
	
	@HystrixCommand(fallbackMethod = "getPeymentAlternative")
	@GetMapping(value = "/{workerId}/days/{day}")
	public ResponseEntity<Payment> getPeyment(@PathVariable Long workerId, @PathVariable Integer day) {
		Payment payment = service.getPayment(workerId, day);
		return ResponseEntity.ok(payment);
	}
	
	/**
	 * Cria o serviço alternativo assim que o serviço chamado 
	 * sai do ar ou esta dar time out essa vantagem é muito bom usando hystrix..
	 * @param workerId
	 * @param day
	 * @return
	 */
	public ResponseEntity<Payment> getPeymentAlternative(Long workerId, Integer day) {
		Payment payment = new Payment("Brann", 400.0, day);
		return ResponseEntity.ok(payment);
	}
}
