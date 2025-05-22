package br.com.neurotech.challenge.service;

import br.com.neurotech.challenge.entity.CreditType;

public interface CreditEvaluatorService {
    CreditType evaluate(int age, double income);
}
