package org.casa.backend.mapper;

import org.casa.backend.dto.TallerDiplomadoDto;
import org.casa.backend.entity.TallerDiplomado;
import org.casa.backend.entity.Docente;
import org.casa.backend.entity.Programa;

public class TallerDiplomadoMapper {
    public static TallerDiplomadoDto mapToTallerDiplomadoDto(TallerDiplomado tallerDiplomado) {
        return new TallerDiplomadoDto(
                tallerDiplomado.getIdActividad(),
                tallerDiplomado.getTitulo(),
                tallerDiplomado.getDescripcion(),
                tallerDiplomado.getTipo(),
                tallerDiplomado.getFechaInicio(),
                tallerDiplomado.getFechaCierre(),
                tallerDiplomado.getFechaResultados(),
                tallerDiplomado.getFechaCreacion(),
                tallerDiplomado.getRequisitos(),
                tallerDiplomado.getEstado(),
                tallerDiplomado.getCupo(),
                tallerDiplomado.getObjetivoGeneral(),
                tallerDiplomado.getObjetivosEspecificos(),
                tallerDiplomado.getTemas(),
                tallerDiplomado.getMaterialSol(),
                tallerDiplomado.getCriteriosSeleccion(),
                tallerDiplomado.getNotas(),
                tallerDiplomado.getNumSesiones(),
                tallerDiplomado.getPrograma().getIdPrograma(),
                tallerDiplomado.getDocente().getIdUsuario()
        );
    }

    public static TallerDiplomado mapToTallerDiplomado(TallerDiplomadoDto tallerDiplomadoDto,Programa programa, Docente docente ) {
        return new TallerDiplomado(
                tallerDiplomadoDto.getIdActividad(),
                tallerDiplomadoDto.getTitulo(),
                tallerDiplomadoDto.getDescripcion(),
                tallerDiplomadoDto.getTipo(),
                tallerDiplomadoDto.getFechaInicio(),
                tallerDiplomadoDto.getFechaCierre(),
                tallerDiplomadoDto.getFechaResultados(),
                tallerDiplomadoDto.getFechaCreacion(),
                tallerDiplomadoDto.getRequisitos(),
                tallerDiplomadoDto.getEstado(),
                tallerDiplomadoDto.getCupo(),
                tallerDiplomadoDto.getObjetivoGeneral(),
                tallerDiplomadoDto.getObjetivosEspecificos(),
                tallerDiplomadoDto.getTemas(),
                tallerDiplomadoDto.getMaterialSol(),
                tallerDiplomadoDto.getCriteriosSeleccion(),
                tallerDiplomadoDto.getNotas(),
                tallerDiplomadoDto.getNumSesiones(),
                programa,
                docente
        );
    }
}
