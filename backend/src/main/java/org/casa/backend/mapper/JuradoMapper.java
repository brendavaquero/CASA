package org.casa.backend.mapper;

import org.casa.backend.dto.JuradoDto;
import org.casa.backend.entity.ConvocatoriaResidencia;
import org.casa.backend.entity.Jurado;
import org.casa.backend.entity.Usuario;

public class JuradoMapper {
    public static JuradoDto mapToDto(Jurado jurado){
        if (jurado == null) return null;
        return new JuradoDto(
                jurado.getIdJurado(),
                jurado.getUsuario().getIdUsuario(),
                jurado.getConvocatoria().getIdActividad()
        );
    }

    public static Jurado mapToJurado(JuradoDto dto, Usuario usuario, ConvocatoriaResidencia convoResi){
        if(dto == null) return null;

        Jurado jurado = new Jurado();
        jurado.setIdJurado(dto.getIdJurado());
        jurado.setUsuario(usuario);
        jurado.setConvocatoria(convoResi);

        return jurado;
    }
}
