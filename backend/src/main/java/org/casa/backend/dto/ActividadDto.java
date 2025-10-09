package org.casa.backend.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActividadDto {
    private String idActividad;
    private String titulo;
    private String descripcion;
    private LocalDate fechaInicio;
    private LocalDate fechaCierre;
    private LocalDate fechaResultados;
    private LocalDateTime fechaCreacion;
    private String requisitos;
    private String estado;
    private String tipoActividad;
}
