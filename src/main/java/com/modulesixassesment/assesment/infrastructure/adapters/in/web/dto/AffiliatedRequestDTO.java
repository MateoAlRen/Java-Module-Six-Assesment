package com.modulesixassesment.assesment.infrastructure.adapters.in.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record AffiliatedRequestDTO(
        @NotBlank(message = "Documento es obligatorio")
        String document,

        @NotBlank(message = "name is necessary")
        String name,

        @NotNull(message = "salary is necessary")
        @Positive(message = "You need to put positive numbers!")
        Double salary,

        @NotNull(message = "Affiliated date is necessary")
        LocalDate affiliatedDate,

        @NotBlank(message = "Status is mandatory")
        String status

) {

}
