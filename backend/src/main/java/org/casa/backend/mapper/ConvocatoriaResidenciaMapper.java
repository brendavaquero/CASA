package org.casa.backend.mapper;

import org.casa.backend.dto.ConvocatoriaResidenciaDto;
import org.casa.backend.entity.ConvocatoriaResidencia;

public class ConvocatoriaResidenciaMapper {
    public static ConvocatoriaResidenciaDto mapToConvocatoriaResidenciaDto(ConvocatoriaResidencia c){
        return new ConvocatoriaResidenciaDto(
                c.getIdActividad(),
                c.getTitulo(),
                c.getDescripcion(),
                c.getTipo(),
                c.getFechaInicio(),
                c.getFechaCierre(),
                c.getFechaResultados(),
                c.getFechaCreacion(),
                c.getRequisitos(),
                c.getEstado(),
                c.getImagen(),
                c.isRequiereMuestraTrabajo(),
                c.isVisible(),
                c.isInfantil(),
                c.getBases(),
                c.getPremio(),
                c.getConvocantes(),
                c.getFechaInicioR1(),
                c.getFechaLimiteR1(),
                c.getFechaInicioR2(),
                c.getFechaLimiteR2()
        );
    }

    public static ConvocatoriaResidencia mapConvocatoriaResidencia(
            ConvocatoriaResidenciaDto dto
    ){
        return new ConvocatoriaResidencia(
                dto.getIdActividad(),
                dto.getTitulo(),
                dto.getDescripcion(),
                dto.getTipo(),
                dto.getFechaInicio(),
                dto.getFechaCierre(),
                dto.getFechaResultados(),
                dto.getFechaCreacion(),
                dto.getRequisitos(),
                dto.getEstado(),
                dto.getImagen(),
                dto.isRequiereMuestraTrabajo(),
                dto.isVisible(),
                dto.isInfantil(),
                dto.getBases(),
                dto.getPremio(),
                dto.getConvocantes(),
                dto.getFechaInicioR1(),
                dto.getFechaLimiteR1(),
                dto.getFechaInicioR2(),
                dto.getFechaLimiteR2()
        );
    }
}