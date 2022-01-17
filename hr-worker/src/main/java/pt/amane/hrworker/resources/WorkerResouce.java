package pt.amane.hrworker.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pt.amane.hrworker.DTO.WorkerDTO;
import pt.amane.hrworker.services.WorkerService;

@RestController
@RequestMapping(value = "/workers")
public class WorkerResouce {
	
	@Autowired
	private WorkerService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<WorkerDTO> findById(@PathVariable Long id) {
		WorkerDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@GetMapping
	public ResponseEntity<List<WorkerDTO>> findAll(){
		List<WorkerDTO> dtos = service.findAll();
		return ResponseEntity.ok().body(dtos);
	}
	

}
