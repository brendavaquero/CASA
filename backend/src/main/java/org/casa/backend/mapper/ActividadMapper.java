package org.casa.backend.mapper;

import org.casa.backend.dto.ActividadDto;
import org.casa.backend.entity.Actividad;

public class ActividadMapper {

    public static ActividadDto mapToDto(Actividad actividad) {
        return new ActividadDto(
            actividad.getIdActividad(),
            actividad.getTitulo(),
            actividad.getDescripcion(),
            actividad.getFechaInicio(),
            actividad.getFechaCierre(),
            actividad.getFechaResultados(),
            actividad.getFechaCreacion(),
            actividad.getRequisitos(),
            actividad.getEstado(),
            actividad.getTipoActividad()
        );
    }
}
