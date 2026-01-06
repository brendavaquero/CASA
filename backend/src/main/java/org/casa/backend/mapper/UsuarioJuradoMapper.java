package org.casa.backend.mapper;

import org.casa.backend.dto.UsuarioJurado;
import org.casa.backend.enums.Rol;

public class UsuarioJuradoMapper {
    public static UsuarioJurado toDto(Object[] row) {
        UsuarioJurado dto = new UsuarioJurado();
        dto.setIdJurado(((String) row[0]));
        dto.setIdUsuario(((String) row[1]));
        dto.setIdConvocatoria(((String) row[2]));
        dto.setNombre((String) row[3]);
        dto.setApellidos((String) row[4]);
        dto.setCorreo(((String) row[5]));
        dto.setContrasenia(((String) row[6]));
        dto.setRol(((Rol) row[7]));
        return dto;
    }
}
