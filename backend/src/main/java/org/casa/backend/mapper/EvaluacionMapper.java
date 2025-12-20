package org.casa.backend.mapper;

import org.casa.backend.dto.EvaluacionDto;
import org.casa.backend.entity.Evaluacion;
import org.casa.backend.entity.Jurado;
import org.casa.backend.entity.Postulacion;

public class EvaluacionMapper {
    public static EvaluacionDto mapToEvalucionDto(Evaluacion evaluacion){
        if(evaluacion == null) return null;
        return new EvaluacionDto(
            evaluacion.getIdEvaluacion(),
            evaluacion.getJurado().getIdJurado(),
            evaluacion.getPostulacion().getIdPostulacion(),
            evaluacion.getRonda(),
            evaluacion.isSemifinalista(),
            evaluacion.getCalificacion(),
            evaluacion.getJustificacion(),
            evaluacion.isFinalista(),
            evaluacion.getFechaHora()
        );
    }

    public static Evaluacion mapToEvaluacion(EvaluacionDto dto, Jurado jurado, Postulacion postulacion){
        if(dto == null) return null;

        Evaluacion evaluacion = new Evaluacion();
        evaluacion.setIdEvaluacion(dto.getIdEvaluacion());
        evaluacion.setJurado(jurado);
        evaluacion.setPostulacion(postulacion);
        evaluacion.setRonda(dto.getRonda());
        evaluacion.setSemifinalista(dto.isSemifinalista());
        evaluacion.setCalificacion(dto.getCalificacion());
        evaluacion.setJustificacion(dto.getJustificacion());
        evaluacion.setFinalista(dto.isFinalista());
        evaluacion.setFechaHora(dto.getFechaHora());

        return evaluacion;
    }
}
