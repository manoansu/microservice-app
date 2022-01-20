package pt.amane.hrworker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pt.amane.hrworker.entities.Worker;

public interface WorkerRepository extends JpaRepository<Worker, Long>{

}
