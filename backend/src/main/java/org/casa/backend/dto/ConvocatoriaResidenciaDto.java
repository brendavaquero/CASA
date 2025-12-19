package org.casa.backend.dto;

import java.time.Instant;
import java.time.LocalDate;

import org.casa.backend.enums.EstadoActividad;
import org.casa.backend.enums.TipoActividad;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ConvocatoriaResidenciaDto extends ActividadDto{
    private String bases;
    private String premio;
    private String convocantes;

    public ConvocatoriaResidenciaDto(
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
            String bases,
            String premio,
            String convocantes
    ){
        super(idActividad, titulo, descripcion, tipo, fechaInicio, fechaCierre, fechaResultados, fechaCreacion, requisitos, estado, imagen, requiereMuestraTrabajo, visible);
        this.bases = bases;
        this.premio = premio;
        this.convocantes = convocantes;
    }
}
