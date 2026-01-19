package org.casa.backend.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.casa.backend.dto.UsuarioDto;
import org.casa.backend.entity.Usuario;
import org.casa.backend.exception.ResourceNotFoundException;
import org.casa.backend.mapper.UsuarioMapper;
import org.casa.backend.repository.UsuarioRepository;
import org.casa.backend.service.UsuarioService;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService{
    private UsuarioRepository usuarioRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public UsuarioDto createUsuario(UsuarioDto usuarioDto) {
        Usuario usuario = UsuarioMapper.mapToUsuario(usuarioDto);
        //cifrar la contraseña
        usuario.setContrasenia(
            passwordEncoder.encode(usuario.getContrasenia())
        );

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
        usuario.setActivo(updatedUsuario.isActivo());
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

    @Override
    public UsuarioDto updateContrasenia(String idUsuario, UsuarioDto updatedUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
        .orElseThrow(() ->
            new ResourceNotFoundException("Usuario no encontrado con ese id " + idUsuario)
        );

        if (updatedUsuario.getContrasenia() != null && !updatedUsuario.getContrasenia().isBlank()) {

            usuario.setContrasenia(
                passwordEncoder.encode(updatedUsuario.getContrasenia())
            );
        }

        Usuario updatedUsuarioObj = usuarioRepository.save(usuario);
        return UsuarioMapper.mapToUsuarioDto(updatedUsuarioObj);
    }

    @Override
    public void actualizarUltimoAcceso(String correo) {
         Usuario usuario = usuarioRepository
                .findByCorreo(correo)
                .orElseThrow(() ->
                        new RuntimeException("Usuario no encontrado")
                );

        usuario.setUltimo_acceso(LocalDateTime.now());
        usuarioRepository.save(usuario);
    }


}
