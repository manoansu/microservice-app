package pt.amane.hrpayroll.services;

import org.springframework.stereotype.Service;

import pt.amane.hrpayroll.entities.Payment;


@Service
public class PaymentService {
	
	// Metodo pagamento de serviço..
	public Payment getPayment(Long workerId, int qtDays) {
		return new Payment("Bob", 200.00, qtDays);
	}

}
