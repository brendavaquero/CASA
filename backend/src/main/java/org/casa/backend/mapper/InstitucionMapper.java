package org.casa.backend.mapper;

import org.casa.backend.dto.InstitucionDTO;
import org.casa.backend.entity.Institucion;

public class InstitucionMapper {

    public static Institucion mapToInstitucion(InstitucionDTO dto) {
        Institucion institucion = new Institucion();
        institucion.setId(dto.getId());
        institucion.setNombre(dto.getNombre());
        institucion.setLogoUrl(dto.getLogoUrl());
        institucion.setActivo(dto.isActivo());

        return institucion;
    }

    public static InstitucionDTO mapToDto(Institucion institucion) {
        InstitucionDTO dto = new InstitucionDTO();
        dto.setId(institucion.getId());
        dto.setNombre(institucion.getNombre());
        dto.setLogoUrl(institucion.getLogoUrl());
        dto.setActivo(institucion.isActivo());

        return dto;
    }
}