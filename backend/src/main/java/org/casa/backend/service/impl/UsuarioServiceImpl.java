package org.casa.backend.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.casa.backend.dto.UsuarioDto;
import org.casa.backend.entity.Usuario;
import org.casa.backend.exception.ResourceNotFoundException;
import org.casa.backend.mapper.UsuarioMapper;
import org.casa.backend.repository.UsuarioRepository;
import org.casa.backend.service.UsuarioService;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService{
    private UsuarioRepository usuarioRepository;

    @Override
    public UsuarioDto createUsuario(UsuarioDto usuarioDto) {
        Usuario usuario = UsuarioMapper.mapToUsuario(usuarioDto);
        Usuario savedUsuario = usuarioRepository.save(usuario);
        return UsuarioMapper.mapToUsuarioDto(savedUsuario);
    }

    @Override
    public UsuarioDto getUsuarioById(String usuario_id) {
        Usuario usuario = usuarioRepository.findById(usuario_id)
            .orElseThrow(() -> new ResourceNotFoundException("El usuario con este id no existe: "+ usuario_id));
        return UsuarioMapper.mapToUsuarioDto(usuario);
    }

    @Override
    public List<UsuarioDto> getAllUsuarios() {
        List<Usuario> usuarios =  usuarioRepository.findAll();
        return usuarios.stream().map((usuario) -> UsuarioMapper.mapToUsuarioDto(usuario))
            .collect(Collectors.toList());
    }

    @Override
    public UsuarioDto updateUsuario(String usuarioId, UsuarioDto updatedUsuario) {
       Usuario usuario =  usuarioRepository.findById(usuarioId).orElseThrow(
        () -> new ResourceNotFoundException("Usuario no encontrado con ese id "+ usuarioId)
       );
        usuario.setNombre(updatedUsuario.getNombre());
        usuario.setApellidos(updatedUsuario.getApellidos());
        usuario.setCorreo(updatedUsuario.getCorreo());
        usuario.setContrasenia(updatedUsuario.getContrasenia());
        usuario.setRol(updatedUsuario.getRol());
        Usuario updatedUsuarioObj =  usuarioRepository.save(usuario);

        return UsuarioMapper.mapToUsuarioDto(updatedUsuarioObj);
    }

    @Override
    public void deleteUsuario(String usuarioId) {
        Usuario usuario =  usuarioRepository.findById(usuarioId).orElseThrow(
        () -> new ResourceNotFoundException("Usuario no encontrado con ese id "+ usuarioId)
       );
       usuarioRepository.deleteById(usuarioId);
    }


}
