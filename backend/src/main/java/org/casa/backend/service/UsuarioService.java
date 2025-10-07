package org.casa.backend.service;

import java.util.List;

/*
import java.util.List;
import java.util.Optional;*/

import org.casa.backend.dto.UsuarioDto;
/* 
import org.casa.backend.entity.Usuario;
import org.casa.backend.repository.UsuarioRepository;*/
import org.springframework.stereotype.Service;


@Service
/*
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> obtenerPorId(String idUsuario) {
        return usuarioRepository.findById(idUsuario);
    }

    public Usuario guardar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void eliminar(String idUsuario) {
        usuarioRepository.deleteById(idUsuario);
    }
}*/
public interface UsuarioService {

    UsuarioDto createUsuario(UsuarioDto usuarioDto);
    UsuarioDto getUsuarioById(String usuario_id);
    List<UsuarioDto> getAllUsuarios();
    UsuarioDto updateUsuario(String usuarioId, UsuarioDto updatedUsuario);
    void deleteUsuario(String usuarioId);

}
