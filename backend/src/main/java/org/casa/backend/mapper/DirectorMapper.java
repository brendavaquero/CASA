package org.casa.backend.mapper;

import org.casa.backend.dto.DirectorDto;
import org.casa.backend.entity.Director;

public class DirectorMapper {
    public static DirectorDto toDTO(Director director) {
        DirectorDto dto = new DirectorDto();
        dto.setId(director.getId());
        dto.setNombre(director.getNombre());
        dto.setFirma(director.getFirma());
        return dto;
    }

    public static Director toEntity(DirectorDto dto) {
        Director director = new Director();
        director.setId(dto.getId());
        director.setNombre(dto.getNombre());
        director.setFirma(dto.getFirma());
        return director;
    }
}
