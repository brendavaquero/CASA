package org.casa.backend.mapper;

import org.casa.backend.dto.SesionDto;
import org.casa.backend.entity.Sesion;
import org.casa.backend.entity.TallerDiplomado;

public class SesionMapper {
    public static SesionDto mapToSesionDto(Sesion sesion) {
        return new SesionDto(
                sesion.getIdSesion(),
                sesion.getTallerDiplomado() != null ? sesion.getTallerDiplomado().getIdActividad() : null,
                sesion.getFechaInicio(),
                sesion.getFechaFin(),
                sesion.getHoraInicio(),
                sesion.getHoraFin(),
                sesion.getAula()
        );
    }

    public static Sesion mapToSesion(SesionDto dto, TallerDiplomado tallerDiplomado) {
        Sesion sesion = new Sesion();
        sesion.setTallerDiplomado(tallerDiplomado);
        sesion.setFechaInicio(dto.getFechaInicio());
        sesion.setFechaFin(dto.getFechaFin());
        sesion.setHoraInicio(dto.getHoraInicio());
        sesion.setHoraFin(dto.getHoraFin());
        sesion.setAula(dto.getAula());
        return sesion;
    }
}
