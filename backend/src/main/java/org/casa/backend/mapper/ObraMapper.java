package org.casa.backend.mapper;

import org.casa.backend.dto.ObraDto;
import org.casa.backend.entity.Archivo;
import org.casa.backend.entity.Ganador;
import org.casa.backend.entity.Obra;

public class ObraMapper {
    public static ObraDto mapToDto(Obra obra){
        if(obra == null) return null;
        return new ObraDto(
            obra.getIdObra(),
            obra.getGanador().getIdGanador(),
            obra.getArchivo().getIdArchivo(),
            obra.getComentarios(),
            obra.isVersionActual()
        );
    }

    public static Obra mapToObra(ObraDto dto, Ganador ganador, Archivo archivo){
        if(dto == null) return null;

        Obra obra = new Obra();
        obra.setIdObra(dto.getIdObra());
        obra.setGanador(ganador);
        obra.setArchivo(archivo);
        obra.setComentarios(dto.getComentarios());
        obra.setVersionActual(dto.isVersionActual());

        return obra;
    }
}
