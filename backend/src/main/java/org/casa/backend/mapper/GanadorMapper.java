package org.casa.backend.mapper;

import org.casa.backend.dto.GanadorDto;
import org.casa.backend.entity.Evaluacion;
import org.casa.backend.entity.Ganador;

public class GanadorMapper {
    public static GanadorDto mapToGanadorDto(Ganador ganador){
        if(ganador == null) return null;

        return new GanadorDto(
            ganador.getIdGanador(),
            ganador.getEvaluacion().getIdEvaluacion(),
            ganador.getSemblanza(),
            ganador.getFoto()
        );
    }

    public static Ganador mapToGanador(GanadorDto dto, Evaluacion evaluacion){
        if(dto == null) return null;

        Ganador ganador = new Ganador();
        ganador.setEvaluacion(evaluacion);
        ganador.setSemblanza(dto.getSemblanza());
        ganador.setFoto(dto.getFoto());

        return ganador;
    }
}
