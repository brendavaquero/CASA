package org.casa.backend.dto;

import java.time.LocalDate;

import org.casa.backend.enums.EstadoActividad;
import org.casa.backend.enums.TipoActividad;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JuradoConvocatoriaDto {
    private String idJurado;
    private String idUsuario;
    private String nombre;
    private String apellidos;
    private String correo;
    private String titulo;
    private String descripcion;
    private LocalDate fechaInicio;
    private LocalDate fechaCierre;
    private LocalDate fechaResultados;
    private String requisitos;
    private TipoActividad tipo;
    private String imagen;
    private String idActividad;
    private String bases;
    private String premio;
    private String convocantes;
    private LocalDate fechaInicioR1; 
    private LocalDate fechaLimiteR1;
    private EstadoActividad estado;
}
