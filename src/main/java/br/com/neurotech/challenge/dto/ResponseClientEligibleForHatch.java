package br.com.neurotech.challenge.dto;

import br.com.neurotech.challenge.entity.NeurotechClient;

public record ResponseClientEligibleForHatch(
        String name,
        Double income
) {
    public ResponseClientEligibleForHatch(NeurotechClient neurotechClient){
        this(
                neurotechClient.getName(),
                neurotechClient.getIncome()
        );
    }
}
