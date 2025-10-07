package org.casa.backend.mapper;

import org.casa.backend.dto.TallerDiplomadoDto;
import org.casa.backend.entity.TallerDiplomado;

public class TallerDiplomadoMapper {

    public static TallerDiplomadoDto mapToDto(TallerDiplomado taller) {
        TallerDiplomadoDto dto = new TallerDiplomadoDto();
        
        
        dto.setIdActividad(taller.getIdActividad());
        dto.setTitulo(taller.getTitulo());
        dto.setDescripcion(taller.getDescripcion());
        dto.setFechaInicio(taller.getFechaInicio());
        dto.setFechaCierre(taller.getFechaCierre());
        dto.setFechaResultados(taller.getFechaResultados());
        dto.setFechaCreacion(taller.getFechaCreacion());
        dto.setRequisitos(taller.getRequisitos());
        dto.setEstado(taller.getEstado());
        
        dto.setTipo(taller.getTipo());
        dto.setCupo(taller.getCupo());
        dto.setObjetivoGeneral(taller.getObjetivoGeneral());
        dto.setObjetivosEspecificos(taller.getObjetivosEspecificos());
        dto.setTemas(taller.getTemas());
        dto.setMaterialSol(taller.getMaterialSol());
        dto.setCriteriosSeleccion(taller.getCriteriosSeleccion());
        dto.setNotas(taller.getNotas());
        dto.setNumSesiones(taller.getNumSesiones());
        
        return dto;
    }
}