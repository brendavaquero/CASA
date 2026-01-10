package org.casa.backend.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.EntityNotFoundException;
import org.casa.backend.dto.EvaluacionDto;
import org.casa.backend.dto.EvaluacionPostulacionDto;
import org.casa.backend.entity.Evaluacion;
import org.casa.backend.entity.Jurado;
import org.casa.backend.entity.Postulacion;
import org.casa.backend.exception.ResourceNotFoundException;
import org.casa.backend.mapper.EvaluacionMapper;
import org.casa.backend.repository.EvaluacionRepository;
import org.casa.backend.repository.JuradoRepository;
import org.casa.backend.repository.PostulacionRepository;
import org.casa.backend.service.EvaluacionService;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EvaluacionServiceImpl implements EvaluacionService{

    private EvaluacionRepository evaluacionRepository;
    private JuradoRepository juradoRepository;
    private PostulacionRepository postulacionRepository;
    @Override
    public List<EvaluacionDto> getAllEvaluaciones() {
        return evaluacionRepository.findAll()
            .stream()
            .map(EvaluacionMapper::mapToEvalucionDto)
            .collect(Collectors.toList());
    }

    @Override
    public EvaluacionDto getEvaluacionById(String idEvaluacion) {
        Evaluacion evaluacion = evaluacionRepository.findById(idEvaluacion)
            .orElseThrow(() -> new ResourceNotFoundException("No existe la evaluacion con id: "+idEvaluacion));
        return EvaluacionMapper.mapToEvalucionDto(evaluacion);
    }

    @Override
    public EvaluacionDto createEvaluacion(EvaluacionDto evaluacionDto) {
        Postulacion postulacion = postulacionRepository.findById(evaluacionDto.getIdPostulacion())
            .orElseThrow(() -> new ResourceNotFoundException("Postulacion no encontrada"));

        Jurado jurado = juradoRepository.findById(evaluacionDto.getIdJurado())
            .orElseThrow(() -> new ResourceNotFoundException("Jurado no encontrado"));

        Evaluacion evaluacion = new Evaluacion();
        evaluacion.setJurado(jurado);
        evaluacion.setPostulacion(postulacion);
        evaluacion.setRonda(evaluacionDto.getRonda());
        evaluacion.setSemifinalista(evaluacionDto.isSemifinalista());
        evaluacion.setCalificacion(evaluacionDto.getCalificacion());
        evaluacion.setJustificacion(evaluacionDto.getJustificacion());
        evaluacion.setFinalista(evaluacionDto.isFinalista());

        Evaluacion saved = evaluacionRepository.save(evaluacion);
        return EvaluacionMapper.mapToEvalucionDto(saved);
    }

    @Override
    public EvaluacionDto updateEvaluacion(String idEvaluacion, EvaluacionDto evaluacionDto) {
        Evaluacion evaluacion = evaluacionRepository.findById(idEvaluacion)
            .orElseThrow(() -> new ResourceNotFoundException("Evaluacion no encontrada con id: "+idEvaluacion));
        
        evaluacion.setRonda(evaluacionDto.getRonda());
        evaluacion.setSemifinalista(evaluacionDto.isSemifinalista());
        evaluacion.setCalificacion(evaluacionDto.getCalificacion());
        evaluacion.setJustificacion(evaluacionDto.getJustificacion());
        evaluacion.setFinalista(evaluacionDto.isFinalista());

        Evaluacion updated = evaluacionRepository.save(evaluacion);

        return EvaluacionMapper.mapToEvalucionDto(updated);
    }

    @Override
    public EvaluacionDto evaluarRondaUno(EvaluacionDto dto) {

        if (dto.getIdJurado() == null) {
            throw new IllegalArgumentException("El idJurado es obligatorio");
        }

        if (dto.getIdPostulacion() == null) {
            throw new IllegalArgumentException("El idPostulacion es obligatorio");
        }


        // 1. Validar que no exista ya una evaluación del mismo jurado
        boolean yaEvaluado = evaluacionRepository
                .existsByJurado_IdJuradoAndPostulacion_IdPostulacionAndRonda(
                        dto.getIdJurado(),
                        dto.getIdPostulacion(),
                        1
                );

        if (yaEvaluado) {
            throw new IllegalStateException(
                    "El jurado ya evaluó esta postulación en la ronda 1"
            );
        }

        // 2. Obtener entidades relacionadas
        Jurado jurado = juradoRepository.findById(dto.getIdJurado())
                .orElseThrow(() -> new IllegalArgumentException("Jurado no encontrado"));

        Postulacion postulacion = postulacionRepository.findById(dto.getIdPostulacion())
                .orElseThrow(() -> new IllegalArgumentException("Postulación no encontrada"));

        // 3. Forzar ronda 1 (no confiar en frontend)
        dto.setRonda(1);
        dto.setSemifinalista(false);
        dto.setFinalista(false);

        // 4. Mapear DTO → Entity
        Evaluacion evaluacion = EvaluacionMapper
                .mapToEvaluacion(dto, jurado, postulacion);

        // 5. Guardar
        Evaluacion evaluacionGuardada = evaluacionRepository.save(evaluacion);

        // 6. Retornar DTO
        return EvaluacionMapper.mapToEvalucionDto(evaluacionGuardada);
    }

    @Override
    public EvaluacionPostulacionDto obtenerPostulacionParaEvaluacionRonda1(String idPostulacion) {

        EvaluacionPostulacionDto dto =
                postulacionRepository.obtenerParaEvaluacion(idPostulacion);

        if (dto == null) {
            throw new EntityNotFoundException(
                    "No se encontró la postulación con id " + idPostulacion
            );
        }

        return dto;
    }

}
