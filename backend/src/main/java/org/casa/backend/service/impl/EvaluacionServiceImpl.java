package org.casa.backend.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.casa.backend.dto.EvaluacionDto;
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
    
}
