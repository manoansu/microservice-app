package pt.amane.hrpayroll.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import pt.amane.hrpayroll.DTO.WorkerDTO;


/**
 * esse compnent vai se injetado noutra classe..
 * FeignClient(name = "hr-worker", path = "/workers")
 * com uso de ribbon vamos tirar url = "localhost:8001" 
 * e usar a lista de instancias/clientes que vai rodar configurando a em
 * application.properties.. 
 * @author manoansu
 *
 */

@Component
@FeignClient(name = "hr-worker", path = "/workers")
public interface WorkerFeignClient {
	
	/**
	 * declaro a interface que vou chamar.. 
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/{id}")
	ResponseEntity<WorkerDTO> findById(@PathVariable Long id);

}
