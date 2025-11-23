package org.casa.backend.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.casa.backend.dto.AlumnoDto;
import org.casa.backend.dto.AlumnoTallerDto;
import org.casa.backend.dto.PostulacionDto;
import org.casa.backend.entity.Alumno;
import org.casa.backend.entity.Postulacion;
import org.casa.backend.exception.ResourceNotFoundException;
import org.casa.backend.mapper.AlumnoMapper;
import org.casa.backend.repository.AlumnoRepository;
import org.casa.backend.repository.PostulacionRepository;
import org.casa.backend.service.AlumnoService;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class AlumnoServiceImpl implements AlumnoService {

    private AlumnoRepository alumnoRepository;
    private PostulacionRepository postulacionRepository;

    @Override
    public AlumnoDto createAlumno(AlumnoDto alumnoDto) {
        if (alumnoDto.getIdPostulacion() == null) {
            throw new IllegalArgumentException("ID de postulación es requerido");
        }

        Postulacion postulacion = postulacionRepository.findById(alumnoDto.getIdPostulacion())
            .orElseThrow(() -> new ResourceNotFoundException("Postulación no encontrada con id: " + alumnoDto.getIdPostulacion()));

        
        if (!postulacion.getEstadoPos().name().equals("APROBADA")) {
            throw new IllegalArgumentException("Solo se pueden crear alumnos de postulaciones ACEPTADAS");
        }
        Alumno alumno = AlumnoMapper.mapToAlumno(alumnoDto, postulacion);
        //alumno.setPostulacion(postulacion);
        

        Alumno saved = alumnoRepository.save(alumno);
        return AlumnoMapper.mapToAlumnoDto(saved);
    }

    @Override
    public List<AlumnoDto> getAllAlumnos() {
        return alumnoRepository.findAll()
            .stream()
            .map(AlumnoMapper::mapToAlumnoDto)
            .collect(Collectors.toList());
    }

    @Override
    public AlumnoDto getAlumnoById(String idAlumno) {
        Alumno alumno = alumnoRepository.findById(idAlumno)
            .orElseThrow(() -> new ResourceNotFoundException("Alumno no encontrado con id: " + idAlumno));
        return AlumnoMapper.mapToAlumnoDto(alumno);
    }

    @Override
    public void deleteAlumno(String idAlumno) {
        Alumno alumno = alumnoRepository.findById(idAlumno)
            .orElseThrow(() -> new ResourceNotFoundException("Alumno no encontrado con id: " + idAlumno));
        alumnoRepository.delete(alumno);
    }

    @Override
    public List<AlumnoDto> getAlumnosByPostulacion(String idPostulacion) {
        return alumnoRepository.findByPostulacionIdPostulacion(idPostulacion)
            .stream()
            .map(AlumnoMapper::mapToAlumnoDto)
            .collect(Collectors.toList());
    }

    @Override
    public List<AlumnoTallerDto> obtenerTalleresDeAlumno(String idUsuario) {
        List<Object[]> rows = alumnoRepository.obtenerTalleresDeAlumno(idUsuario);

        return rows.stream().map(row -> new AlumnoTallerDto(
                (String) row[0],  // id_usuario
                (String) row[1],  // nombre
                (String) row[2],  // apellidos
                (String) row[3],  // id_alumno
                (String) row[4],  // id_actividad
                (String) row[5]   // titulo
        )).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void crearAlumnosDesdePostulaciones(List<PostulacionDto> postulacionesAprobadasDto) {

        if (postulacionesAprobadasDto == null || postulacionesAprobadasDto.isEmpty()) {
            return;
        }

        for (PostulacionDto pDto : postulacionesAprobadasDto) {
            // Asumimos que el DTO contiene el idPostulacion
            String idPost = pDto.getIdPostulacion();
            if (idPost == null) continue;

            // Intentar usar existsByPostulacion_IdPostulacion primero (evita cargar entidad)
            boolean existsById = alumnoRepository.existsByPostulacion_IdPostulacion(idPost);
            if (existsById) {
                // ya existe alumno para esta postulacion -> saltar
                continue;
            }

            // Cargar la entidad Postulacion (necesaria para la relación)
            Postulacion postulacion = postulacionRepository.findById(idPost)
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Postulacion no encontrada: " + idPost));

            // doble chequeo por si otro proceso la creó entre tanto
            if (alumnoRepository.existsByPostulacion(postulacion)) {
                continue;
            }

            Alumno alumno = new Alumno();
            alumno.setPostulacion(postulacion);
            // Puedes cambiar el valor por default que quieras:
            alumno.setConstancia(false);

            alumnoRepository.save(alumno);
        }
    }
}