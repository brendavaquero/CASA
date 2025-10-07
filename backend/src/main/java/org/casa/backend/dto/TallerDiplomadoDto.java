package org.casa.backend.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TallerDiplomadoDto{
    private String idActividad;
    private String titulo;
    private String descripcion;
    private LocalDate fechaInicio;
    private LocalDate fechaCierre;
    private LocalDate fechaResultados;
    private LocalDateTime fechaCreacion;
    private String requisitos;
    private String estado;

    private String tipo;
    private Integer cupo;
    private String objetivoGeneral;
    private String objetivosEspecificos;
    private String temas;
    private String materialSol;
    private String criteriosSeleccion;
    private String notas;
    private Integer numSesiones;
}
