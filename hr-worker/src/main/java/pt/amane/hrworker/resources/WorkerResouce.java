package pt.amane.hrworker.resources;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pt.amane.hrworker.DTO.WorkerDTO;
import pt.amane.hrworker.services.WorkerService;

@RefreshScope
@RestController
@RequestMapping(value = "/workers")
public class WorkerResouce {
	
	private static Logger logger = org.slf4j.LoggerFactory.getLogger(WorkerResouce.class);
	
	/* pega qq valor nesse caso o teste criado 
	*no git para testar configuração de server
	* e acessar anossa configuraçao do gi.
	*/
	@Value("${test.config}")
	private String testConfig;
	
	
	@Autowired
	private Environment env;
	
	
	@Autowired
	private WorkerService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<WorkerDTO> findById(@PathVariable Long id) {
	
		/*
		try {
			Thread.sleep(3000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		logger.info("PORT = " + env.getProperty("local.server.port"));
		
		WorkerDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@GetMapping(value = "/configs")
	public ResponseEntity<Void> getConfigs(){
		logger.info("CONFIG = " + testConfig);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping
	public ResponseEntity<List<WorkerDTO>> findAll(){
		List<WorkerDTO> dtos = service.findAll();
		return ResponseEntity.ok().body(dtos);
	}
	

}
