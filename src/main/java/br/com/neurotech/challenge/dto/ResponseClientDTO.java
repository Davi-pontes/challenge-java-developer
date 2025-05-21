package br.com.neurotech.challenge.dto;

import br.com.neurotech.challenge.entity.NeurotechClient;

public record ResponseClientDTO(
        String name,
        Integer age,
        Double income
) {
    public ResponseClientDTO(NeurotechClient neurotechClient){
        this(
                neurotechClient.getName(),
                neurotechClient.getAge(),
                neurotechClient.getIncome()
        );
    }
}
