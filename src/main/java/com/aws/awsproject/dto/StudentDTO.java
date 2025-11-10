package com.aws.awsproject.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StudentDTO {

    private Integer id;

    @NotBlank(message = "Nombres is required")
    private String nombres;

    @NotBlank(message = "Apellidos is required")
    private String apellidos;

    @NotBlank(message = "Matricula is required")
    private String matricula;

    @NotNull(message = "Promedio is required")
    @DecimalMin(value = "0.0", message = "Promedio must be greater than or equal to 0")
    @DecimalMax(value = "10.0", message = "Promedio must be less than or equal to 10")
    private Double promedio;

}
