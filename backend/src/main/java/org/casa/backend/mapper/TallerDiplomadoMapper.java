package org.casa.backend.mapper;

import org.casa.backend.dto.TallerDiplomadoDto;
import org.casa.backend.entity.TallerDiplomado;
import org.casa.backend.entity.Docente;
import org.casa.backend.entity.Programa;

public class TallerDiplomadoMapper {

    public static TallerDiplomadoDto mapToTallerDiplomadoDto(TallerDiplomado t) {

        String programaId = (t.getPrograma() != null)
                ? t.getPrograma().getIdPrograma()
                : null;

        String docenteId = (t.getDocente() != null)
                ? t.getDocente().getIdUsuario()
                : null;

        return new TallerDiplomadoDto(
                t.getIdActividad(),
                t.getTitulo(),
                t.getDescripcion(),
                t.getTipo(),
                t.getFechaInicio(),
                t.getFechaCierre(),
                t.getFechaResultados(),
                t.getFechaCreacion(),
                t.getRequisitos(),
                t.getEstado(),
                t.getImagen(),
                t.isRequiereMuestraTrabajo(),
                t.getCupo(),
                t.getObjetivoGeneral(),
                t.getObjetivosEspecificos(),
                t.getTemas(),
                t.getMaterialSol(),
                t.getCriteriosSeleccion(),
                t.getNotas(),
                t.getNumSesiones(),
                programaId,
                docenteId
        );
    }

    public static TallerDiplomado mapToTallerDiplomado(
            TallerDiplomadoDto dto,
            Programa programa,
            Docente docente
    ) {
        return new TallerDiplomado(
                dto.getIdActividad(),
                dto.getTitulo(),
                dto.getDescripcion(),
                dto.getTipo(),
                dto.getFechaInicio(),
                dto.getFechaCierre(),
                dto.getFechaResultados(),
                dto.getFechaCreacion(),
                dto.getRequisitos(),
                dto.getEstado(),
                dto.getImagen(),
                dto.isRequiereMuestraTrabajo(),
                dto.getCupo(),
                dto.getObjetivoGeneral(),
                dto.getObjetivosEspecificos(),
                dto.getTemas(),
                dto.getMaterialSol(),
                dto.getCriteriosSeleccion(),
                dto.getNotas(),
                dto.getNumSesiones(),
                programa,
                docente
        );
    }
}
