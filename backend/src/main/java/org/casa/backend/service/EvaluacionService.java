package org.casa.backend.service;

import java.util.List;

import org.casa.backend.dto.EvaluacionDto;
import org.casa.backend.dto.EvaluacionPostulacionDto;

public interface EvaluacionService {
    List<EvaluacionDto> getAllEvaluaciones();
    EvaluacionDto getEvaluacionById(String idEvaluacion);
    EvaluacionDto createEvaluacion(EvaluacionDto evaluacionDto);
    EvaluacionDto updateEvaluacion(String idEvaluacion, EvaluacionDto evaluacionDto);
    EvaluacionDto evaluarRondaUno(EvaluacionDto dto);
    EvaluacionPostulacionDto obtenerPostulacionParaEvaluacionRonda1(String idPostulacion);
}
