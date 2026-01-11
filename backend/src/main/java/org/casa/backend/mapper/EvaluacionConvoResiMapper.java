package org.casa.backend.mapper;

import java.time.LocalDateTime;

import org.casa.backend.dto.EvaluacionConvoDto;

public class EvaluacionConvoResiMapper {
    public static EvaluacionConvoDto toDto(Object[] row){
        EvaluacionConvoDto dto = new EvaluacionConvoDto();
        dto.setIdEvaluacion(((String) row[0]));
        dto.setIdJurado(((String) row[1]));
        dto.setIdPostulacion(((String) row[2]));
        dto.setRonda(((Integer) row[3]));
        dto.setSemifinalista(((boolean) row[4]));
        dto.setCalificacion(((Double) row[5]));
        dto.setJustificacion(((String) row[6]));
        dto.setFinalista(((boolean) row[7]));
        dto.setFechaHora(((LocalDateTime) row[8]));
        dto.setIdUsuario(((String) row[9]));
        dto.setPostulante(((String) row[10]));
        dto.setNombre(((String) row[11]));
        dto.setApellidos(((String) row[12]));
        dto.setNombreJ(((String) row[13]));
        dto.setApellidosJ(((String) row[14]));

        return dto;
    }
    
}
