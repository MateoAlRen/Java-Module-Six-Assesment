package com.modulesixassesment.assesment.infrastructure.adapters.in.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record RequestRequestDTO(
        @NotBlank(message = "The document is necessary")
       String documentAffiliated,

       @NotNull(message = "Amount is necessary")
       @Positive(message = "Amount must be positive")
       Double amount,

       @NotNull(message = "Months are mandatory")
       @Positive(message = "Months are required")
       Integer months,

       @NotNull(message = "The amount is necessary!")
       @PositiveOrZero(message = "The propose can not be null!")
       Double amountPropose
) {
}
