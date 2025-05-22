package br.com.neurotech.challenge.service.impl;

import br.com.neurotech.challenge.dto.CreateClientDTO;
import br.com.neurotech.challenge.dto.ResponseClientDTO;
import br.com.neurotech.challenge.entity.CreditType;
import br.com.neurotech.challenge.entity.NeurotechClient;
import br.com.neurotech.challenge.repository.ClientRepository;
import br.com.neurotech.challenge.service.ClientService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CreditEvaluatorServiceImpl creditEvaluatorService;

    @Override
    public String save(CreateClientDTO client) {

        CreditType creditType = creditEvaluatorService.evaluate(client.age(),client.income());

        NeurotechClient neurotechClient = new NeurotechClient(client,creditType);

        clientRepository.save(neurotechClient);

        return neurotechClient.getId().toString();
    }

    @Override
    public ResponseClientDTO get(String id) {
        NeurotechClient client = clientRepository.findById(Long.parseLong(id))
                .orElseThrow(() -> new EntityNotFoundException("Client não encontrado."));

        return new ResponseClientDTO(client);
    }
}
