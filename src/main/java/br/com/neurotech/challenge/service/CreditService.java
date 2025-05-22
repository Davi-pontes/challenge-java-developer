package br.com.neurotech.challenge.service;

import br.com.neurotech.challenge.dto.ResponseCreditEligibility;
import br.com.neurotech.challenge.entity.VehicleModel;

public interface CreditService {
	
	/**
	 * Efetua a checagem se o cliente está apto a receber crédito
	 * para um determinado modelo de veículo
	 */
	ResponseCreditEligibility checkCredit(String clientId, VehicleModel model);
	
}
