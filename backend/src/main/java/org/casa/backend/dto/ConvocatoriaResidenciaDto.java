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
    private LocalDate fechaInicioR1;
    private LocalDate fechaLimiteR1;

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
            boolean infantil,
            String bases,
            String premio,
            String convocantes,
            LocalDate fechaInicioR1,
            LocalDate fechaLimiteR1
    ){
        super(idActividad, titulo, descripcion, tipo, fechaInicio, fechaCierre, fechaResultados, fechaCreacion, requisitos, estado, imagen, requiereMuestraTrabajo, visible, infantil);
        this.bases = bases;
        this.premio = premio;
        this.convocantes = convocantes;
        this.fechaInicioR1=fechaInicioR1;
        this.fechaLimiteR1=fechaLimiteR1;
    }
}
