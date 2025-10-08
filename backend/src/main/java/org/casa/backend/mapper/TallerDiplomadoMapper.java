package org.casa.backend.mapper;

import org.casa.backend.dto.TallerDiplomadoDto;
import org.casa.backend.entity.TallerDiplomado;

public class TallerDiplomadoMapper {
    public static TallerDiplomadoDto mapToTallerDiplomadoDto(TallerDiplomado tallerDiplomado) {
        return new TallerDiplomadoDto(
                tallerDiplomado.getIdActividad(),
                tallerDiplomado.getTitulo(),
                tallerDiplomado.getDescripcion(),
                tallerDiplomado.getFechaInicio(),
                tallerDiplomado.getFechaCierre(),
                tallerDiplomado.getFechaResultados(),
                //tallerDiplomado.getFechaCreacion(),
                tallerDiplomado.getRequisitos(),
                tallerDiplomado.getEstado(),
                tallerDiplomado.getTipo(),
                tallerDiplomado.getCupo(),
                tallerDiplomado.getObjetivoGeneral(),
                tallerDiplomado.getObjetivosEspecificos(),
                tallerDiplomado.getTemas(),
                tallerDiplomado.getMaterialSol(),
                tallerDiplomado.getCriteriosSeleccion(),
                tallerDiplomado.getNotas(),
                tallerDiplomado.getNumSesiones()
        );
    }

    public static TallerDiplomado mapToTallerDiplomado(TallerDiplomadoDto tallerDiplomadoDto) {
        return new TallerDiplomado(
                tallerDiplomadoDto.getIdActividad(),
                tallerDiplomadoDto.getTitulo(),
                tallerDiplomadoDto.getDescripcion(),
                tallerDiplomadoDto.getFechaInicio(),
                tallerDiplomadoDto.getFechaCierre(),
                tallerDiplomadoDto.getFechaResultados(),
                //tallerDiplomadoDto.getFechaCreacion(),
                tallerDiplomadoDto.getRequisitos(),
                tallerDiplomadoDto.getEstado(),
                tallerDiplomadoDto.getTipo(),
                tallerDiplomadoDto.getCupo(),
                tallerDiplomadoDto.getObjetivoGeneral(),
                tallerDiplomadoDto.getObjetivosEspecificos(),
                tallerDiplomadoDto.getTemas(),
                tallerDiplomadoDto.getMaterialSol(),
                tallerDiplomadoDto.getCriteriosSeleccion(),
                tallerDiplomadoDto.getNotas(),
                tallerDiplomadoDto.getNumSesiones()
        );
    }
}
