package org.casa.backend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.casa.backend.enums.EstadoActividad;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor

public class TallerDiplomadoDto extends ActividadDto{
    private String tipo;
    private Integer cupo;
    private String objetivoGeneral;
    private String objetivosEspecificos;
    private String temas;
    private String materialSol;
    private String criteriosSeleccion;
    private String notas;
    private Integer numSesiones;

    public TallerDiplomadoDto(
            String idActividad,
            String titulo,
            String descripcion,
            LocalDate fechaInicio,
            LocalDate fechaCierre,
            LocalDate fechaResultados,
            //Instant fechaCreacion,
            String requisitos,
            EstadoActividad estado,
            String tipo,
            Integer cupo,
            String objetivoGeneral,
            String objetivosEspecificos,
            String temas,
            String materialSol,
            String criteriosSeleccion,
            String notas,
            Integer numSesiones
    ) {
        super(idActividad, titulo, descripcion, fechaInicio, fechaCierre, fechaResultados, requisitos, estado);
        this.tipo = tipo;
        this.cupo = cupo;
        this.objetivoGeneral = objetivoGeneral;
        this.objetivosEspecificos = objetivosEspecificos;
        this.temas = temas;
        this.materialSol = materialSol;
        this.criteriosSeleccion = criteriosSeleccion;
        this.notas = notas;
        this.numSesiones = numSesiones;
    }
}
