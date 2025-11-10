package com.aws.awsproject.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Professor {

    private Integer id;
    private Integer numeroEmpleado;
    private String nombres;
    private String apellidos;
    private Integer horasClase;

}
