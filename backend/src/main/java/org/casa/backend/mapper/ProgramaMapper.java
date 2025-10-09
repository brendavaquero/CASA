package org.casa.backend.mapper;

import org.casa.backend.dto.ProgramaDto;
import org.casa.backend.entity.Programa;
import org.casa.backend.entity.TallerDiplomado;
import org.casa.backend.entity.Usuario;

public class ProgramaMapper {
    public static ProgramaDto mapProgramaToDto(Programa programa){
        return new ProgramaDto(
            programa.getNombre(),
            programa.getDescripcion(),
            programa.getIdActividad().getIdActividad(),
            programa.getIdUsuario().getIdUsuario()
        );
    }

    public static Programa mapToPrograma(ProgramaDto dto, TallerDiplomado tD, Usuario usuario){
        return new Programa(
            dto.getNombre(),
            dto.getDescripcion(),
            tD,
            usuario
        );
    }

}
