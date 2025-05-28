package br.com.neurotech.challenge.service;

import br.com.neurotech.challenge.entity.CreditType;

public interface CreditEvaluatorService {
    /**
     * Avalia a melhor modalidade de crédito para um cliente com base em sua idade e renda.
     *
     */
    CreditType evaluate(int age, double income);
}
