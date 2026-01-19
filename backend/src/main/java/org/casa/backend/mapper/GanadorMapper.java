package org.casa.backend.mapper;

import org.casa.backend.dto.GanadorDto;
import org.casa.backend.entity.Archivo;
import org.casa.backend.entity.Evaluacion;
import org.casa.backend.entity.Ganador;
import org.casa.backend.entity.ResultadoRondaUno;

public class GanadorMapper {
    public static GanadorDto mapToGanadorDto(Ganador ganador){
        if(ganador == null) return null;

        return new GanadorDto(
            ganador.getIdGanador(),
            ganador.getSemblanza(),
            ganador.getFoto(),
            ganador.getArchivo().getIdArchivo(),
            ganador.getResultado().getIdResultado()
        );
    }

    public static Ganador mapToGanador(GanadorDto dto, ResultadoRondaUno resultado, Archivo archivo){
        if(dto == null) return null;

        Ganador ganador = new Ganador();
        ganador.setResultado(resultado);
        ganador.setSemblanza(dto.getSemblanza());
        ganador.setFoto(dto.getFoto());
        ganador.setArchivo(archivo);

        return ganador;
    }
}
