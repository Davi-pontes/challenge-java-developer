package br.com.neurotech.challenge.service.impl;

import br.com.neurotech.challenge.dto.CreateClientDTO;
import br.com.neurotech.challenge.dto.ResponseClientDTO;
import br.com.neurotech.challenge.dto.ResponseClientEligibleForHatch;
import br.com.neurotech.challenge.entity.CreditType;
import br.com.neurotech.challenge.entity.NeurotechClient;
import br.com.neurotech.challenge.repository.ClientRepository;
import br.com.neurotech.challenge.service.ClientService;
import br.com.neurotech.challenge.service.CreditEvaluatorService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CreditEvaluatorService creditEvaluatorService;

    @Override
    public String save(CreateClientDTO client) {
        CreditType creditType = creditEvaluatorService.evaluate(client.age(), client.income());

        NeurotechClient neurotechClient = new NeurotechClient(client, creditType);

        NeurotechClient savedClient = clientRepository.save(neurotechClient);

        return savedClient.getId().toString();
    }

    @Override
    public ResponseClientDTO get(String id) {
        NeurotechClient client = clientRepository.findById(Long.parseLong(id))
                .orElseThrow(() -> new EntityNotFoundException("Client não encontrado."));

        return new ResponseClientDTO(client);
    }

    @Override
    public List<ResponseClientEligibleForHatch> getEligibleClientsForHatchWithFixedCredit() {
        List<NeurotechClient> clients = clientRepository
                .findByAgeBetweenAndIncomeBetweenAndCreditType(23, 25, 5000.0, 15000.0, CreditType.JUROS_FIXOS);

        return clients.stream()
                .map(ResponseClientEligibleForHatch::new)
                .collect(Collectors.toList());
    }
}
