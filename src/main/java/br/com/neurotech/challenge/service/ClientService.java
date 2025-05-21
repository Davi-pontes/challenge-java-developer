package br.com.neurotech.challenge.service;

import br.com.neurotech.challenge.dto.CreateClientDTO;

import br.com.neurotech.challenge.dto.ResponseClientDTO;

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
}
