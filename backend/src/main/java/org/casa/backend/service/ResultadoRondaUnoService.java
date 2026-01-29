package org.casa.backend.service;

import org.casa.backend.dto.FinalistaDto;
import org.casa.backend.entity.ResultadoRondaUno;

import java.util.List;

public interface ResultadoRondaUnoService {
    void cerrarRondaUno(String idConvocatoria);
    List<ResultadoRondaUno> obtenerResultados(String idConvocatoria);
    List<ResultadoRondaUno> obtenerFinalistas(String idConvocatoria);
    //void asignarGanador(String idConvocatoria, String idPostulacion);
    List<FinalistaDto> obtenerFinalistasDTO(String idConvocatoria);
    List<FinalistaDto> prepararRondaFinal(String idConvocatoria);
}
