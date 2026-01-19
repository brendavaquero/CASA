package org.casa.backend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.casa.backend.enums.EstadoActividad;
import org.casa.backend.enums.TipoActividad;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor

public class TallerDiplomadoDto extends ActividadDto{
    private Integer cupo;
    private String objetivoGeneral;
    private String objetivosEspecificos;
    private String temas;
    private String materialSol;
    private String criteriosSeleccion;
    private String notas;
    private Integer numSesiones;
    private String idPrograma;
    private String idDocente;


    public TallerDiplomadoDto(
            String idActividad,
            String titulo,
            String descripcion,
            TipoActividad tipo,
            LocalDate fechaInicio,
            LocalDate fechaCierre,
            LocalDate fechaResultados,
            Instant fechaCreacion,
            String requisitos,
            EstadoActividad estado,
            String imagen,
            boolean requiereMuestraTrabajo,
            boolean visible,
            boolean infantil,
            Integer cupo,
            String objetivoGeneral,
            String objetivosEspecificos,
            String temas,
            String materialSol,
            String criteriosSeleccion,
            String notas,
            Integer numSesiones,
            String idPrograma,
            String idDocente
    ) {
        super(idActividad, titulo, descripcion, tipo, fechaInicio, fechaCierre, fechaResultados, fechaCreacion, requisitos, estado, imagen, requiereMuestraTrabajo, visible, infantil);
        this.cupo = cupo;
        this.objetivoGeneral = objetivoGeneral;
        this.objetivosEspecificos = objetivosEspecificos;
        this.temas = temas;
        this.materialSol = materialSol;
        this.criteriosSeleccion = criteriosSeleccion;
        this.notas = notas;
        this.numSesiones = numSesiones;
        this.idPrograma = idPrograma;
        this.idDocente = idDocente;
    }
}
