package org.casa.backend.dto;

import java.sql.Date;
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
    private Date fechaInicioR1; 
    private Date fechaLimiteR1; 
    private Date fechaInicioR2; 
    private Date fechaLimiteR2;

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
            String convocantes,
            Date fechaInicioR1, 
            Date fechaLimiteR1, 
            Date fechaInicioR2, 
            Date fechaLimiteR2
    ){
        super(idActividad, titulo, descripcion, tipo, fechaInicio, fechaCierre, fechaResultados, fechaCreacion, requisitos, estado, imagen, requiereMuestraTrabajo, visible);
        this.bases = bases;
        this.premio = premio;
        this.convocantes = convocantes;
        this.fechaInicioR1=fechaInicioR1;
        this.fechaLimiteR1=fechaLimiteR1;
        this.fechaInicioR2=fechaInicioR2;
        this.fechaLimiteR2=fechaLimiteR2;
    }
}
