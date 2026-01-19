package org.casa.backend.service;

import java.util.List;

import org.casa.backend.dto.*;
import org.casa.backend.entity.Postulacion;
import org.casa.backend.enums.EstadoPost;

public interface PostulacionService {
    PostulacionDto createPostulacion(PostulacionDto dto);
    PostulacionDto createPostulacionConvocatoria(PostulacionDto dto);
    List<PostulacionDto> getAllPostulaciones();
    PostulacionDto getPostulacionById(String id);
    void deletePostulacion(String id);
    List<AlumnoActividadDto> getAlumnosPorActividad(String idActividad);
    PostulacionDto updateEstadoPos(String postulacionId, EstadoPost estado);
    List<PostulacionDto> getPostulacionesByActividad(String idActividad);
    void seleccionarPostulantes(String idActividad, List<String> postulacionesAprobadas);
    List<PostulacionDto> getPostulacionesPendientes(String idActividad);
    List<PostulacionParticipanteDto> getPostulacionesPendientesParticipante(String idActividad);
    //List<PostulacionDto> getPendientesParaJuradoIds(String idJurado, Integer ronda);
    List<PostulacionPendienteJuradoDto> getPendientesParaJurado(String idJurado,Integer ronda);
    Postulacion registrarPostulacionPostal(RegistroPostalPostulacionDto dto);
}
