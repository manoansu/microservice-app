package pt.amane.hrworker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pt.amane.hrworker.DTO.WorkerDTO;
import pt.amane.hrworker.entities.Worker;
import pt.amane.hrworker.repositories.WorkerRepository;
import pt.amane.hrworker.services.exception.ObjectNotFoundException;

@Service
public class WorkerService {

	@Autowired
	private WorkerRepository repository;

	public WorkerDTO findById(Long id) {
		Optional<Worker> workerId = repository.findById(id);
		Worker worker = workerId.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! id: " + id + ", Type: " + WorkerDTO.class.getName()));
		return new WorkerDTO(worker);
	}
	

	@Transactional(readOnly = true)
	public List<WorkerDTO> findAll() {
		List<Worker> workers = repository.findAll();
		return workers.stream().map(x -> new WorkerDTO(x)).toList();
	}

}
