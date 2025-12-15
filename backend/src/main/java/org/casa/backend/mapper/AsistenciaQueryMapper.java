package org.casa.backend.mapper;

import org.casa.backend.dto.AsistenciaAlumnoDto;

public class AsistenciaQueryMapper {

    public static AsistenciaAlumnoDto toDto(Object[] row) {
        AsistenciaAlumnoDto dto = new AsistenciaAlumnoDto();
        dto.setIdUsuario(((String) row[0]));
        dto.setNombre((String) row[1]);
        dto.setApellidos((String) row[2]);
        dto.setIdAlumno(((String) row[3]));
        dto.setAsistenciasPresentes(((Number) row[4]).longValue());
        dto.setPorcentajeAsistencia(((Number) row[5]).doubleValue());
        return dto;
    }
}

