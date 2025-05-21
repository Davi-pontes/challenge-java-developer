package br.com.neurotech.challenge.service;

import br.com.neurotech.challenge.dto.CreateClientDTO;

import br.com.neurotech.challenge.entity.NeurotechClient;

public interface ClientService {
	
	/**
	 * Salva um novo cliente
	 * 
	 * @return ID do cliente recém-salvo
	 */
	NeurotechClient save(CreateClientDTO client);
	
	/**
	 * Recupera um cliente baseado no seu ID
	 */
	NeurotechClient get(String id);
}
