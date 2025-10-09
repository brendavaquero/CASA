package org.casa.backend.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.casa.backend.dto.AlumnoDto;
import org.casa.backend.entity.Alumno;
import org.casa.backend.entity.Postulacion;
import org.casa.backend.exception.ResourceNotFoundException;
import org.casa.backend.mapper.AlumnoMapper;
import org.casa.backend.repository.AlumnoRepository;
import org.casa.backend.repository.PostulacionRepository;
import org.casa.backend.service.AlumnoService;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

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

        /*
        if (!postulacion.getEstadoPos().name().equals("APROBADA")) {
            throw new IllegalArgumentException("Solo se pueden crear alumnos de postulaciones ACEPTADAS");
        }*/
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
}