package org.casa.backend.mapper;

import org.casa.backend.dto.AlumnoDto;
import org.casa.backend.entity.Alumno;

public class AlumnoMapper {

    public static AlumnoDto mapToAlumnoDto(Alumno alumno) {
        AlumnoDto dto = new AlumnoDto();
        dto.setIdAlumno(alumno.getIdAlumno());
        dto.setConstancia(alumno.getConstancia());
        
        // Mapear postulación si existe
        if (alumno.getPostulacion() != null) {
            dto.setPostulacion(PostulacionMapper.mapToPostulacionDto(alumno.getPostulacion()));
        }
        
        return dto;
    }

    // Para creación - solo mapea los campos necesarios
    public static Alumno mapToAlumno(AlumnoDto alumnoDto) {
        Alumno alumno = new Alumno();
        alumno.setConstancia(alumnoDto.getConstancia() != null ? alumnoDto.getConstancia() : true);
        // postulacion se establece en el servicio
        return alumno;
    }
}