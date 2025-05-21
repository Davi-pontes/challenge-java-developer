package br.com.neurotech.challenge.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record CreateClientDTO(
        @NotBlank(message = "O nome é obrigatório")
        String name,

        @Min(value = 0, message = "A idade deve ser maior ou igual a 0")
        Integer age,

        @DecimalMin(value = "0.0", inclusive = false, message = "A renda deve ser maior que 0")
        Double income
) {
}