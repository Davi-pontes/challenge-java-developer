package br.com.neurotech.challenge.service;

import br.com.neurotech.challenge.dto.CreateClientDTO;

import br.com.neurotech.challenge.dto.ResponseClientDTO;
import br.com.neurotech.challenge.dto.ResponseClientEligibleForHatch;

import java.util.List;

public interface ClientService {
	
	/**
	 * Salva um novo cliente
	 * 
	 * @return ID do cliente recém-salvo
	 */
	String save(CreateClientDTO client);
	
	/**
	 * Recupera um cliente baseado no seu ID
	 */
	ResponseClientDTO get(String id);

	/**
	 * Recupera todos os clientes com idade entre 23 e 25 anos, que possuem crédito com juros fixos
	 * e estão aptos a adquirir crédito automotivo para veículos do tipo Hatch (renda entre R$ 5.000,00 e R$ 15.000,00).
	 *
	 */
	List<ResponseClientEligibleForHatch> getEligibleClientsForHatchWithFixedCredit();
}
