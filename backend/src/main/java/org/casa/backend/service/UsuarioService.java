package org.casa.backend.service;

import java.util.List;
import org.casa.backend.dto.UsuarioDto;
import org.springframework.stereotype.Service;


@Service
public interface UsuarioService {

    UsuarioDto createUsuario(UsuarioDto usuarioDto);
    UsuarioDto getUsuarioById(String usuario_id);
    List<UsuarioDto> getAllUsuarios();
    UsuarioDto updateUsuario(String usuarioId, UsuarioDto updatedUsuario);
    UsuarioDto updateUsuarioGeneral(String usuarioId, UsuarioDto updatedUsuario);
    UsuarioDto updateContrasenia(String idUsuario, UsuarioDto updatedUsuario);
    void deleteUsuario(String usuarioId);
    //Exclusivo para editar el ultimo acceso
    void actualizarUltimoAcceso(String correo);
}
