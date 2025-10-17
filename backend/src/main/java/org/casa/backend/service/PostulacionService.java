package org.casa.backend.service;

import java.util.List;

import org.casa.backend.dto.AlumnoActividadDto;
import org.casa.backend.dto.PostulacionDto;

public interface PostulacionService {
    PostulacionDto createPostulacion(PostulacionDto dto);
    List<PostulacionDto> getAllPostulaciones();
    PostulacionDto getPostulacionById(String id);
    void deletePostulacion(String id);
    List<AlumnoActividadDto> getAlumnosPorActividad(String idActividad);
}
