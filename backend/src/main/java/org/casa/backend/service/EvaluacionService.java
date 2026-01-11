package org.casa.backend.service;

import java.util.List;

import org.casa.backend.dto.EvaluacionConvoDto;
import org.casa.backend.dto.EvaluacionDto;

public interface EvaluacionService {
    List<EvaluacionDto> getAllEvaluaciones();
    EvaluacionDto getEvaluacionById(String idEvaluacion);
    EvaluacionDto createEvaluacion(EvaluacionDto evaluacionDto);
    EvaluacionDto updateEvaluacion(String idEvaluacion, EvaluacionDto evaluacionDto);
    EvaluacionDto evaluarRondaUno(EvaluacionDto dto);
    List<EvaluacionConvoDto> obtenerEvaluacionesByConvo(String idActividad);
}
