package org.casa.backend.service;

import java.util.List;
import org.casa.backend.dto.AlumnoDto;
import org.casa.backend.dto.AlumnoTallerDto;
import org.casa.backend.dto.PostulacionDto;

public interface AlumnoService {
    List<AlumnoDto> getAllAlumnos();
    AlumnoDto getAlumnoById(String idAlumno);
    AlumnoDto createAlumno(AlumnoDto alumnoDto);
    void deleteAlumno(String idAlumno);
    List<AlumnoDto> getAlumnosByPostulacion(String idPostulacion);
    List<AlumnoTallerDto> obtenerTalleresDeAlumno(String idUsuario);
    void crearAlumnosDesdePostulaciones(List<PostulacionDto> postulacionesAprobadas);
    //boolean existsByPostulacion(PostulacionDto postulacion);
}
