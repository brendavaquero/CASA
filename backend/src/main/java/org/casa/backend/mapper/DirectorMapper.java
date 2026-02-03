package org.casa.backend.mapper;

import org.casa.backend.dto.DirectorDto;
import org.casa.backend.entity.Director;

public class DirectorMapper {

    public static DirectorDto toDTO(Director director) {
        if (director == null) return null;

        DirectorDto dto = new DirectorDto();
        dto.setId(director.getId());
        dto.setNombre(director.getNombre());
        dto.setFirma(director.getFirma());
        dto.setActivo(director.getActivo());
        dto.setFechaInicio(director.getFechaInicio());
        dto.setFechaFin(director.getFechaFin());
        return dto;
    }

    public static Director toEntity(DirectorDto dto) {
        if (dto == null) return null;

        Director director = new Director();
        director.setId(dto.getId());
        director.setNombre(dto.getNombre());
        director.setFirma(dto.getFirma());
        director.setActivo(dto.getActivo());
        director.setFechaInicio(dto.getFechaInicio());
        director.setFechaFin(dto.getFechaFin());
        return director;
    }
}
