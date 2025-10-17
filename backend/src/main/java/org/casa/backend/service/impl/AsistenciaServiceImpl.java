package org.casa.backend.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.casa.backend.dto.AsistenciaDto;
import org.casa.backend.entity.Alumno;
import org.casa.backend.entity.Asistencia;
import org.casa.backend.exception.ResourceNotFoundException;
import org.casa.backend.mapper.AsistenciaMapper;
import org.casa.backend.repository.AlumnoRepository;
import org.casa.backend.repository.AsistenciaRepository;
import org.casa.backend.service.AsistenciaService;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AsistenciaServiceImpl implements AsistenciaService {

    private final AsistenciaRepository asistenciaRepository;
    private final AlumnoRepository alumnoRepository;

    @Override
    public AsistenciaDto registrarAsistencia(AsistenciaDto dto) {
        Alumno alumno = alumnoRepository.findById(dto.getIdAlumno())
                .orElseThrow(() -> new ResourceNotFoundException("Alumno no encontrado: " + dto.getIdAlumno()));

        Asistencia asistencia = AsistenciaMapper.mapToEntity(dto, alumno);
        return AsistenciaMapper.mapToDto(asistenciaRepository.save(asistencia));
    }

    @Override
    public AsistenciaDto obtenerPorId(String idAsistencia) {
        Asistencia asistencia = asistenciaRepository.findById(idAsistencia)
                .orElseThrow(() -> new ResourceNotFoundException("Asistencia no encontrada: " + idAsistencia));
        return AsistenciaMapper.mapToDto(asistencia);
    }

    @Override
    public List<AsistenciaDto> listarAsistencias() {
        return asistenciaRepository.findAll().stream()
                .map(AsistenciaMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void eliminarAsistencia(String idAsistencia) {
        asistenciaRepository.deleteById(idAsistencia);
    }

    public List<AsistenciaDto> obtenerAsistenciasPorAlumno(String idAlumno) {
        return asistenciaRepository.findByAlumno_IdAlumno(idAlumno)
                .stream()
                .map(a -> new AsistenciaDto(
                        a.getIdAsistencia(),
                        a.getAlumno().getIdAlumno(),
                        a.getFecha(),
                        a.getPresente()
                ))
                .collect(Collectors.toList());
    }
}
