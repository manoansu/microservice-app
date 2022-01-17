package pt.amane.hrpayroll.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import pt.amane.hrpayroll.DTO.WorkerDTO;


/**
 * esse compnent vai se injetado noutra classe..
 * @author manoansu
 *
 */

@Component
@FeignClient(name = "hr-worker", url = "localhost:8001" ,path = "/workers")
public interface WorkerFeignClient {
	
	/**
	 * declaro a interface que vou chamar.. 
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/{id}")
	ResponseEntity<WorkerDTO> findById(@PathVariable Long id);

}
