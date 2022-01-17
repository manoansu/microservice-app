package pt.amane.hrpayroll.DTO;

import java.io.Serializable;

import pt.amane.hrpayroll.entities.Worker;

public class WorkerDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private Double dailyIncome;

	public WorkerDTO() {
	}

	public WorkerDTO(Long id, String name, Double dailyIncome) {
		this.id = id;
		this.name = name;
		this.dailyIncome = dailyIncome;
	}

	public WorkerDTO(Worker worker) {
		id = worker.getId();
		name = worker.getName();
		dailyIncome = worker.getDailyIncome();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getDailyIncome() {
		return dailyIncome;
	}

	public void setDailyIncome(Double dailyIncome) {
		this.dailyIncome = dailyIncome;
	}

}
