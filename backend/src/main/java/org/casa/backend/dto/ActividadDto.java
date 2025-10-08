package org.casa.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.casa.backend.enums.EstadoActividad;
//import org.casa.backend.entity.ConvocatoriaResidencia;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActividadDto {
    private String idActividad;
    private String titulo;
    private String descripcion;
    private LocalDate fechaInicio;
    private LocalDate fechaCierre;
    private LocalDate fechaResultados;
    private String requisitos;
    private EstadoActividad estado;


}
