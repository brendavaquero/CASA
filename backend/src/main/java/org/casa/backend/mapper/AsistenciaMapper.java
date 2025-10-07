package org.casa.backend.mapper;

import org.casa.backend.dto.AsistenciaDto;
import org.casa.backend.entity.Alumno;
import org.casa.backend.entity.Asistencia;

public class AsistenciaMapper {

    public static AsistenciaDto mapToDto(Asistencia asistencia) {
        return new AsistenciaDto(
            asistencia.getIdAsistencia(),
            asistencia.getAlumno() != null ? asistencia.getAlumno().getIdAlumno() : null,
            asistencia.getFecha(),
            asistencia.getPresente()
        );
    }

    public static Asistencia mapToEntity(AsistenciaDto dto, Alumno alumno) {
        return new Asistencia(
            dto.getIdAsistencia(),
            alumno,
            dto.getFecha(),
            dto.getPresente()
        );
    }
}
