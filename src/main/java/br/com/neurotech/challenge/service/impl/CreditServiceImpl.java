package br.com.neurotech.challenge.service.impl;

import br.com.neurotech.challenge.dto.ResponseClientDTO;
import br.com.neurotech.challenge.dto.ResponseCreditEligibility;
import br.com.neurotech.challenge.entity.VehicleModel;
import br.com.neurotech.challenge.service.ClientService;
import br.com.neurotech.challenge.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditServiceImpl implements CreditService {

    private final ClientService clientService;

    public CreditServiceImpl(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public ResponseCreditEligibility checkCredit(String clientId, VehicleModel model) {

        ResponseClientDTO clientDTO = clientService.get(clientId);

        switch (model) {
            case HATCH:
                if (clientDTO.income() >= 5000 && clientDTO.income() <= 15000) {
                    return new ResponseCreditEligibility(true, "Cliente apto para financiamento do modelo Hatch.");
                } else {
                    return new ResponseCreditEligibility(false, "Cliente não atende aos critérios para o modelo Hatch.");
                }
            case SUV:
                if (clientDTO.income() > 8000 && clientDTO.age() > 20) {
                    return new ResponseCreditEligibility(true, "Cliente apto para financiamento do modelo SUV.");
                } else {
                    return new ResponseCreditEligibility(false, "Cliente não atende aos critérios para o modelo SUV.");
                }
            default:
                return new ResponseCreditEligibility(false, "Modelo de carro inválido.");
        }
    }
}
