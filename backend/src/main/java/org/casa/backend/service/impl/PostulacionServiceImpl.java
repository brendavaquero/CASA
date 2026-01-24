package org.casa.backend.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.casa.backend.dto.*;
import org.casa.backend.entity.*;
import org.casa.backend.enums.EstadoPost;
import org.casa.backend.exception.ResourceNotFoundException;
import org.casa.backend.mapper.ParticipanteMapper;
import org.casa.backend.mapper.PostulacionMapper;
import org.casa.backend.repository.*;
import org.casa.backend.service.PostulacionService;
import org.springframework.dao.DataIntegrityViolationException;
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
    private ArchivoRepository archivoRepository;

    @Override
    public PostulacionDto createPostulacion(PostulacionDto dto) {
        System.out.println("Ejectuando postulación de taller");
        Participante participante = participanteRepository.findById(dto.getIdUsuario())
            .orElseThrow(() -> new ResourceNotFoundException("Participante no encontrado"));

        Actividad actividad = tallerDiplomadoRepositoryR.findById(dto.getIdActividad())
            .orElseThrow(() -> new ResourceNotFoundException("Actividad no encontrada"));

        if (actividad.isInfantil()) {

            validarPostulacionInfantil(
                    actividad.getIdActividad(),
                    dto.getPostulante()
            );

        } else {

            validarPostulacionAdulto(
                    participante.getIdUsuario(),
                    actividad.getIdActividad()
            );
        }
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
        System.out.println("Ejectuando postulación de conv");
        Participante participante = participanteRepository.findById(dto.getIdUsuario())
            .orElseThrow(() -> new ResourceNotFoundException("Participante no encontrado"));

        Actividad actividad = convocatoriaResidenciaRepository.findById(dto.getIdActividad())
            .orElseThrow(() -> new ResourceNotFoundException("Convocatoria no encontrada"));

        if (actividad.isInfantil()) {

            validarPostulacionInfantil(
                    actividad.getIdActividad(),
                    dto.getPostulante()
            );

        } else {

            validarPostulacionAdulto(
                    participante.getIdUsuario(),
                    actividad.getIdActividad()
            );
        }

        Postulacion postulacion = new Postulacion();
        postulacion.setParticipante(participante);
        postulacion.setActividad(actividad);
        // normalizar nombre para no diferenciar mayúsculas y minúsculas
        String postulante = dto.getPostulante().trim().toLowerCase();
        postulacion.setPostulante(postulante);
        //postulacion.setMotivo(dto.getMotivo());
        postulacion.setEstadoPos(dto.getEstadoPos());
        postulacion.setFechaPostulacion(dto.getFechaPostulacion());
        postulacion.setNombreObra(dto.getNombreObra());

//        Postulacion saved = postulacionRepository.save(postulacion);
//        return PostulacionMapper.mapToPostulacionDto(saved);
        try {
            Postulacion saved = postulacionRepository.save(postulacion);
            return PostulacionMapper.mapToPostulacionDto(saved);

        } catch (DataIntegrityViolationException e) {

            if (actividad.isInfantil()) {
                throw new IllegalStateException(
                        "Este postulante ya está registrado en esta actividad"
                );
            }

            throw e; // otros errores reales
        }

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
            throw new ResourceNotFoundException("No puedes seleccionar más participantes del cupo");
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
    public List<PostulacionPendienteJuradoDto> getPendientesParaJurado(
            String idJurado,
            Integer ronda
    ) {

        return postulacionRepository.findPendientesParaJurado(
                //EstadoPost.PENDIENTE,
                idJurado,
                ronda
        );
    }
    @Override
    @Transactional
    public Postulacion registrarPostulacionPostal(RegistroPostalPostulacionDto dto) {

        Participante participante = participanteRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new IllegalArgumentException("Participante no encontrado"));

        Actividad actividad = convocatoriaResidenciaRepository.findById(dto.getIdActividad())
                .orElseThrow(() -> new IllegalArgumentException("Actividad no encontrada"));

        // 1. Crear Postulación
        Postulacion postulacion = new Postulacion();
        postulacion.setParticipante(participante);
        postulacion.setActividad(actividad);
        postulacion.setNombreObra(dto.getNombreObra());
        postulacion.setEstadoPos(EstadoPost.PENDIENTE);

        Postulacion postulacionGuardada = postulacionRepository.save(postulacion);

        // 2. Crear Archivos
        for (ArchivoDto archivoDto : dto.getArchivos()) {

            Archivo archivo = new Archivo();
            archivo.setNombre(archivoDto.getNombre());
            archivo.setRuta(archivoDto.getRuta());
            archivo.setTipo(archivoDto.getTipo());
            archivo.setActividad(actividad);
            archivo.setPostulacion(postulacionGuardada);

            archivoRepository.save(archivo);
        }

        return postulacionGuardada;
    }

    //obtener las postulacion pero con todo lo de participante
    @Override
    public List<PostulacionParticipanteDto> getParticipantesByActividad(String idActividad) {
        List<Postulacion> participantes =
                postulacionRepository.findByActividad_IdActividad(idActividad);

        return participantes.stream().map(p -> {
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

    private void validarPostulacionAdulto(
            String idUsuario,
            String idActividad
    ) {
        if (postulacionRepository
                .existsByParticipante_IdUsuarioAndActividad_IdActividad(
                        idUsuario, idActividad
                )) {

            throw new IllegalStateException(
                    "Ya estás registrado en esta actividad"
            );
        }
    }

    private void validarPostulacionInfantil(
            String actividadId,
            String postulanteNombre
    ) {
        if (postulacionRepository
                .existsByActividad_IdActividadAndPostulante(
                        actividadId, postulanteNombre
                )) {

            throw new IllegalStateException(
                    "Este postulante ya está registrado"
            );
        }
    }

    @Override
    public boolean existePostulacion(String idUsuario, String idActividad) {
        return postulacionRepository
                .existsByParticipante_IdUsuarioAndActividad_IdActividad(idUsuario, idActividad);
    }


}
