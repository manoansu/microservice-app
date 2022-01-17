package pt.amane.hrpayroll.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import pt.amane.hrpayroll.entities.Payment;
import pt.amane.hrpayroll.entities.Worker;

@Service
public class PaymentService {

	/**
	 * pega o valor da propriedade hr-worker..
	 */
	@Value("${hr-worker.host}")
	private String hrWorkerHost;

	/**
	 * esse objeto restTemplate, vai fazer a chamada no meu webservice hr-worker..
	 */
	@Autowired
	private RestTemplate restTemplate;

	// Metodo pagamento de serviço..
	public Payment getPayment(Long workerId, int qtDays) {

		/**
		 * para passar parametro cria o Map<String, Sring> que o http só é aceita string
		 */
		Map<String, String> uriVariables = new HashMap<>();
		// "" + workerId = converte long para string..
		uriVariables.put("id", "" + workerId);

		/**
		 * Para fazer a requisicao externa, tem q criar nova classe que quer chamar
		 * nesse projeto, por ex: Worker de projeto hr-worker. OBS: como essa classe nao
		 * tem relacao com bd nao e ecessario fazer o mapeamento e chama o.
		 * 
		 * como nos queremos saber o o id de funcionario vamos usar endpoint de
		 * findById, nesse caso o id ja esta nesse metodo que é workerId.
		 * onde:
		 * hrWorkerHost + "/workers/{id}", Worker.class, uriVariables
		 * 
		 * hrWorkerHost =é o link de worker (http://localhost:8001)
		 *  + "/workers/{id}" => concatena o endpoint com link
		 *  , Worker.class => passa a classe worker, 
		 *  uriVariables = pega parametro da requisicao..
		 */
		Worker worker = restTemplate.getForObject(hrWorkerHost + "/workers/{id}", Worker.class, uriVariables);
		
		/**
		 * o worker vai receber o resultado da requisicao, e apenas 
		 * preenche os dados dinamicamente no return..
		 */

		return new Payment(worker.getName(), worker.getDailyIncome(), qtDays);
	}

}
