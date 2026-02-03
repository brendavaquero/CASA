package org.casa.backend.mapper;

import org.casa.backend.dto.ActividadDto;
import org.casa.backend.dto.ActividadInstitucionDTO;
import org.casa.backend.entity.Actividad;

public class ActividadMapper {

    public static ActividadDto mapToActividadDto(Actividad actividad) {
        ActividadDto dto = new ActividadDto();

        dto.setIdActividad(actividad.getIdActividad());
        dto.setTitulo(actividad.getTitulo());
        dto.setDescripcion(actividad.getDescripcion());
        dto.setTipo(actividad.getTipo());
        dto.setFechaInicio(actividad.getFechaInicio());
        dto.setFechaCierre(actividad.getFechaCierre());
        dto.setFechaResultados(actividad.getFechaResultados());
        dto.setFechaCreacion(actividad.getFechaCreacion());
        dto.setRequisitos(actividad.getRequisitos());
        dto.setEstado(actividad.getEstado());
        dto.setImagen(actividad.getImagen());
        dto.setRequiereMuestraTrabajo(actividad.isRequiereMuestraTrabajo());
        dto.setVisible(actividad.isVisible());
        dto.setInfantil(actividad.isInfantil());

        // map instituciones
        dto.setInstituciones(
                actividad.getInstituciones().stream()
                        .map(ai -> new ActividadInstitucionDTO(
                                ai.getInstitucion().getId(),
                                ai.getInstitucion().getNombre(),
                                ai.getInstitucion().getLogoUrl(),
                                ai.getOrden(),
                                ai.isPrincipal()
                        ))
                        .toList()
        );

        return dto;
    }

}