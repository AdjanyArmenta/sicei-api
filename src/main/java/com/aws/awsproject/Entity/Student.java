package com.aws.awsproject.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    private Integer id;
    private String nombres;
    private String apellidos;
    private String matricula;
    private Double promedio;

}
