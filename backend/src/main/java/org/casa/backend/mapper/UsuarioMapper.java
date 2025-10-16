package org.casa.backend.mapper;

import org.casa.backend.dto.UsuarioDto;
import org.casa.backend.entity.Usuario;

public class UsuarioMapper {
    public static UsuarioDto mapToUsuarioDto(Usuario usuario){
        return new UsuarioDto(
                usuario.getIdUsuario(),
                usuario.getNombre(),
                usuario.getApellidos(),
                usuario.getCorreo(),
                usuario.getContrasenia(),
                usuario.getRol(),
                usuario.isActivo(),
                usuario.getFecha_registro(),
                usuario.getUltimo_acceso()
        );
    }

    public static Usuario mapToUsuario(UsuarioDto usuarioDto){
        return new Usuario(
                usuarioDto.getIdUsuario(),
                usuarioDto.getNombre(),
                usuarioDto.getApellidos(),
                usuarioDto.getCorreo(),
                usuarioDto.getContrasenia(),
                usuarioDto.getRol(),
                usuarioDto.isActivo(),
                usuarioDto.getFecha_registro(),
                usuarioDto.getUltimo_acceso()
        );
    }
}
