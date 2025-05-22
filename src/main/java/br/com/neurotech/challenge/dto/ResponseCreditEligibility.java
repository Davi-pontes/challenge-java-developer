package br.com.neurotech.challenge.dto;

public record ResponseCreditEligibility(
        Boolean eligible,
        String message
) {
}
