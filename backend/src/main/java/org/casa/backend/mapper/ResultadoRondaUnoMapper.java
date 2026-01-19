package org.casa.backend.mapper;

import org.casa.backend.dto.ResultadoRondaUnoDto;
import org.casa.backend.entity.ResultadoRondaUno;

public class ResultadoRondaUnoMapper {

    public static ResultadoRondaUnoDto mapToResultadoRondaUnoDto(ResultadoRondaUno resultado) {
        return new ResultadoRondaUnoDto(
                resultado.getIdResultado(),
                resultado.getIdConvocatoria(),
                resultado.getIdPostulacion(),
                resultado.getPromedio() != null ? resultado.getPromedio().doubleValue() : null,
                resultado.getTotalEvaluaciones(),
                resultado.getPosicion(),
                resultado.getFinalista(),
                resultado.getMencionHonorifica(),
                resultado.getFechaCalculo()
        );
    }

    public static ResultadoRondaUno mapToResultadoRondaUno(ResultadoRondaUnoDto dto, ResultadoRondaUno res) {
        if (dto == null) return null;

        ResultadoRondaUno resultado = new ResultadoRondaUno();
        resultado.setIdResultado(dto.getIdResultado());
        resultado.setIdConvocatoria(dto.getIdConvocatoria());
        resultado.setIdPostulacion(dto.getIdPostulacion());

        if (dto.getPromedio() != null) {
            resultado.setPromedio(
                    java.math.BigDecimal.valueOf(dto.getPromedio())
            );
        }

        resultado.setTotalEvaluaciones(dto.getTotalEvaluaciones());
        resultado.setPosicion(dto.getPosicion());
        resultado.setFinalista(dto.isFinalista());
        resultado.setMencionHonorifica(dto.isMencionHonorifica());
        // fechaCalculo no se setea

        return resultado;
    }
}
