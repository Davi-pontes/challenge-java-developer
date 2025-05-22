package br.com.neurotech.challenge.service.impl;

import br.com.neurotech.challenge.entity.CreditType;
import br.com.neurotech.challenge.service.CreditEvaluatorService;
import org.springframework.stereotype.Service;

@Service
public class CreditEvaluatorServiceImpl implements CreditEvaluatorService {

    @Override
    public CreditType evaluate(int age, double income) {
        if (isYoungAdult(age)) {
            return CreditType.JUROS_FIXOS;
        } else if (isSenior(age)) {
            return CreditType.CONSIGNADO;
        } else if (isAdultWithStableIncome(age, income)) {
            return CreditType.JUROS_VARIAVEIS;
        }

        throw new IllegalArgumentException("Cliente não se enquadra em nenhuma modalidade de crédito.");
    }

    private boolean isYoungAdult(int age) {
        return age >= 18 && age <= 25;
    }

    private boolean isSenior(int age) {
        return age > 65;
    }

    private boolean isAdultWithStableIncome(int age, double income) {
        return age >= 21 && age <= 65 && income >= 5000 && income <= 15000;
    }
}