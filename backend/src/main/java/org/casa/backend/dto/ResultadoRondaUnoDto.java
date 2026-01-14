package org.casa.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResultadoRondaUnoDto {

    private String idResultado;

    private String idConvocatoria;
    private String idPostulacion;
    //private String idEvaluacion;

    private Double promedio;
    private Integer totalEvaluaciones;
    private Integer posicion;

    private boolean finalista;
    private boolean mencionHonorifica;

    private LocalDateTime fechaCalculo;
}
