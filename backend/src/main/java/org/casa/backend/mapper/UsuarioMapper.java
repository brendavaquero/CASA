package org.casa.backend.mapper;

import org.casa.backend.dto.UsuarioDto;
import org.casa.backend.entity.Usuario;

public class UsuarioMapper {
    public static UsuarioDto mapToUsuarioDto(Usuario usuario){
        return new UsuarioDto(
            usuario.getNombre(),
            usuario.getApellidos(),
            usuario.getCorreo(),
            usuario.getContrasenia(),
            usuario.getRol()
        );
    }

    public static Usuario mapToUsuario(UsuarioDto usuarioDto){
        return new Usuario(
            usuarioDto.getNombre(),
            usuarioDto.getApellidos(),
            usuarioDto.getCorreo(),
            usuarioDto.getContrasenia(),
            usuarioDto.getRol()
        );
    }
}
