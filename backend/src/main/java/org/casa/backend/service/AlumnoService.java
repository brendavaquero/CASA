package org.casa.backend.service;

import java.util.List;
import org.casa.backend.dto.AlumnoDto;

public interface AlumnoService {
    List<AlumnoDto> getAllAlumnos();
    AlumnoDto getAlumnoById(String idAlumno);
    AlumnoDto createAlumno(AlumnoDto alumnoDto);
    void deleteAlumno(String idAlumno);
    List<AlumnoDto> getAlumnosByPostulacion(String idPostulacion);
}
