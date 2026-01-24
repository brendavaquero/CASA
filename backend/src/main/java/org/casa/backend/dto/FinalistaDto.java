package org.casa.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FinalistaDto {
    private String idConvocatoria;
    private boolean infantil;
    private String idResultado;
    private String idPostulacion;
    private String nombre;
    private String apellidos;
    private String correo;
    private String postulante;
    private String nombreObra;
    private String ruta;
    private BigDecimal promedio;
    private Integer posicion;
}

