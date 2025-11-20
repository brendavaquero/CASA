package org.casa.backend.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.casa.backend.dto.AlumnoActividadDto;
import org.casa.backend.dto.PostulacionDto;
import org.casa.backend.entity.Actividad;
import org.casa.backend.entity.Participante;
import org.casa.backend.entity.Postulacion;
import org.casa.backend.enums.EstadoPost;
import org.casa.backend.exception.ResourceNotFoundException;
import org.casa.backend.mapper.PostulacionMapper;
import org.casa.backend.repository.ParticipanteRepository;
import org.casa.backend.repository.PostulacionRepository;
import org.casa.backend.repository.TallerDiplomadoRepository;
import org.casa.backend.service.PostulacionService;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PostulacionServiceImpl implements PostulacionService {

    private PostulacionRepository postulacionRepository;
    private ParticipanteRepository participanteRepository;
    private TallerDiplomadoRepository tallerDiplomadoRepositoryR;

    @Override
    public PostulacionDto createPostulacion(PostulacionDto dto) {
        Participante participante = participanteRepository.findById(dto.getIdUsuario())
            .orElseThrow(() -> new ResourceNotFoundException("Participante no encontrado"));

        Actividad actividad = tallerDiplomadoRepositoryR.findById(dto.getIdActividad())
            .orElseThrow(() -> new ResourceNotFoundException("Actividad no encontrada"));

        Postulacion postulacion = new Postulacion();
        postulacion.setParticipante(participante);
        postulacion.setActividad(actividad);
        postulacion.setPostulante(dto.getPostulante());
        postulacion.setMotivo(dto.getMotivo());
        postulacion.setEstadoPos(dto.getEstadoPos());
        postulacion.setFechaPostulacion(dto.getFechaPostulacion());

        Postulacion saved = postulacionRepository.save(postulacion);
        return PostulacionMapper.mapToPostulacionDto(saved);
    }

    @Override
    public List<PostulacionDto> getAllPostulaciones() {
        return postulacionRepository.findAll()
            .stream()
            .map(PostulacionMapper::mapToPostulacionDto)
            .collect(Collectors.toList());
    }

    @Override
    public PostulacionDto getPostulacionById(String id) {
        Postulacion postulacion = postulacionRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("No existe la postulacion con id: " + id));
        return PostulacionMapper.mapToPostulacionDto(postulacion);
    }

    @Override
    public void deletePostulacion(String id) {
        Postulacion postulacion = postulacionRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("No existe la postulacion con id: " + id));
        postulacionRepository.delete(postulacion);
    }

    @Override
    public List<AlumnoActividadDto> getAlumnosPorActividad(String idActividad) {
        List<Object[]> resultados = postulacionRepository.alumnosByActividad(idActividad);
        return resultados.stream()
                .map(r -> new AlumnoActividadDto(
                        (String) r[0],  // nombre
                        (String) r[1],  // apellidos
                        (String) r[2],  // idAlumno
                        (String) r[3],  // idPostulacion
                        (String) r[4]   // tituloActividad
                ))
                .collect(Collectors.toList());
    }

    //Editar el estado de la postulacion
    @Override
    public PostulacionDto updateEstadoPos(String postulacionId, EstadoPost estado) {
        Postulacion postulacion = postulacionRepository.findById(postulacionId)
        .orElseThrow(() -> new ResourceNotFoundException("Postulación no encontrada con id " + postulacionId));

        postulacion.setEstadoPos(estado);
        Postulacion updated = postulacionRepository.save(postulacion);

        return PostulacionMapper.mapToPostulacionDto(updated);
    }
}
