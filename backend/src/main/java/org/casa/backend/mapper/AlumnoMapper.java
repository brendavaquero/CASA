package org.casa.backend.mapper;

import org.casa.backend.dto.AlumnoDto;
import org.casa.backend.entity.Alumno;
import org.casa.backend.entity.Postulacion;

public class AlumnoMapper {

    public static AlumnoDto mapToAlumnoDto(Alumno alumno) {
        AlumnoDto dto = new AlumnoDto();
        dto.setIdAlumno(alumno.getIdAlumno());
        dto.setIdPostulacion(alumno.getPostulacion().getIdPostulacion());
        dto.setConstancia(alumno.getConstancia());
        
        return dto;
    }

    public static Alumno mapToAlumno(AlumnoDto alumnoDto, Postulacion postulacion) {
        Alumno alumno = new Alumno();
        alumno.setIdAlumno(alumnoDto.getIdAlumno());
        alumno.setPostulacion(postulacion);
        alumno.setConstancia(alumnoDto.getConstancia() != null ? alumnoDto.getConstancia() : true);

        return alumno;
    }
}