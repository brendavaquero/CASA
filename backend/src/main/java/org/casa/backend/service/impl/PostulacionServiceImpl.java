package org.casa.backend.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.casa.backend.dto.AlumnoActividadDto;
import org.casa.backend.dto.ParticipanteDto;
import org.casa.backend.dto.PostulacionDto;
import org.casa.backend.dto.PostulacionParticipanteDto;
import org.casa.backend.entity.*;
import org.casa.backend.enums.EstadoPost;
import org.casa.backend.exception.ResourceNotFoundException;
import org.casa.backend.mapper.ParticipanteMapper;
import org.casa.backend.mapper.PostulacionMapper;
import org.casa.backend.repository.AlumnoRepository;
import org.casa.backend.repository.ConvocatoriaResidenciaRepository;
import org.casa.backend.repository.ParticipanteRepository;
import org.casa.backend.repository.PostulacionRepository;
import org.casa.backend.repository.TallerDiplomadoRepository;
import org.casa.backend.service.PostulacionService;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class PostulacionServiceImpl implements PostulacionService {

    private PostulacionRepository postulacionRepository;
    private ParticipanteRepository participanteRepository;
    private TallerDiplomadoRepository tallerDiplomadoRepositoryR;
    private ConvocatoriaResidenciaRepository convocatoriaResidenciaRepository;
    private AlumnoRepository alumnoRepository;

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
    public PostulacionDto createPostulacionConvocatoria(PostulacionDto dto) {
        Participante participante = participanteRepository.findById(dto.getIdUsuario())
            .orElseThrow(() -> new ResourceNotFoundException("Participante no encontrado"));

        Actividad actividad = convocatoriaResidenciaRepository.findById(dto.getIdActividad())
            .orElseThrow(() -> new ResourceNotFoundException("Convocatoria no encontrada"));

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

    @Override
    public List<PostulacionDto> getPostulacionesByActividad(String idActividad) {
        List<Postulacion> postulaciones =
                postulacionRepository.findByActividad_IdActividad(idActividad);

        return postulaciones
                .stream()
                .map(PostulacionMapper::mapToPostulacionDto)
                .toList();
    }

    @Override
    @Transactional
    public void seleccionarPostulantes(String idActividad, List<String> postulacionesAprobadas) {

        // 1. Obtener la actividad
        TallerDiplomado taller = tallerDiplomadoRepositoryR.findById(idActividad)
                .orElseThrow(() -> new ResourceNotFoundException("Actividad no encontrada"));

        int cupo = taller.getCupo();
        if (postulacionesAprobadas.size() > cupo) {
            throw new IllegalArgumentException("No puedes seleccionar más participantes del cupo");
        }

        // 2. Obtener TODAS las postulaciones de esa actividad
        List<Postulacion> todas = postulacionRepository.findByActividad_IdActividad(idActividad);

        // 3. Procesar
        for (Postulacion p : todas) {

            boolean aprobada = postulacionesAprobadas.contains(p.getIdPostulacion());

            if (aprobada) {
                p.setEstadoPos(EstadoPost.APROBADA);

                // Si no existe alumno para esa postulación, crearlo
                if (!alumnoRepository.existsByPostulacion(p)) {
                    Alumno alumno = new Alumno();
                    alumno.setPostulacion(p);
                    alumno.setConstancia(false);
                    alumnoRepository.save(alumno);
                }

            } else {
                p.setEstadoPos(EstadoPost.RECHAZADA);
            }

            postulacionRepository.save(p);
        }
    }

    @Override
    public List<PostulacionDto> getPostulacionesPendientes(String idActividad) {
        List<Postulacion> postulaciones = postulacionRepository.findPendientesByActividad(idActividad);

        return postulaciones.stream()
                .map(PostulacionMapper::mapToPostulacionDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PostulacionParticipanteDto> getPostulacionesPendientesParticipante(String idActividad) {

        List<Postulacion> postulaciones = postulacionRepository.findPendientesByActividad(idActividad);

        return postulaciones.stream().map(p -> {
            Participante participante = participanteRepository.findById(p.getParticipante().getIdUsuario())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Participante no encontrado: " + p.getParticipante().getIdUsuario()));

            ParticipanteDto participanteDto = ParticipanteMapper.mapToParticipanteDto(participante);

            return new PostulacionParticipanteDto(
                    p.getIdPostulacion(),
                    p.getParticipante().getIdUsuario(),
                    p.getActividad().getIdActividad(),
                    p.getPostulante(),
                    p.getMotivo(),
                    p.getEstadoPos(),
                    p.getFechaPostulacion(),
                    participanteDto
            );
        }).collect(Collectors.toList());
    }

    @Override
    public List<PostulacionDto> getPendientesParaJurado(
            //String idActividad,
            String idJurado,
            Integer ronda
    ) {

        return postulacionRepository
                .findPendientesParaJurado(
                        EstadoPost.PENDIENTE,
                        idJurado,
                        ronda
                )
                .stream()
                .map(PostulacionMapper::mapToPostulacionDto)
                .toList();
    }

}
