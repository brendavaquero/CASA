package org.casa.backend.controllers;

import org.casa.backend.dto.UsuarioDto;
import org.casa.backend.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    private  UsuarioService usuarioService;

    //Crear un usuario nuevo
    @PostMapping
    public ResponseEntity<UsuarioDto> createUsuario(@RequestBody UsuarioDto usuarioDto){
        UsuarioDto savedUsuario =  usuarioService.createUsuario(usuarioDto);
        return new ResponseEntity<>(savedUsuario, HttpStatus.CREATED);
    }

    //Obtener un usuario
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> getUsuarioById(@PathVariable("id") String usuarioId){
        UsuarioDto usuarioDto = usuarioService.getUsuarioById(usuarioId);
        return ResponseEntity.ok(usuarioDto);
    }
    //Obtener todos los usuarios
    @GetMapping
    public ResponseEntity<List<UsuarioDto>> getAllUsuarios(){
        List<UsuarioDto> usuarios = usuarioService.getAllUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    //Actualizar un empleado
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDto> updateUsuario(@PathVariable("id") String usuarioId, @RequestBody UsuarioDto updatedUsuario){
        UsuarioDto usuarioDto = usuarioService.updateUsuario(usuarioId, updatedUsuario);
        return ResponseEntity.ok(usuarioDto);
    }

    //Eliminar el usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUsuario(@PathVariable("id") String usuarioId) {
        usuarioService.deleteUsuario(usuarioId);
        return ResponseEntity.ok("Usuario eliminado correctamente");
    }
/*
    @GetMapping
    public List<Usuario> listar() {
        return usuarioService.listarUsuarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtener(@PathVariable String id) {
        return usuarioService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Usuario crear(@RequestBody Usuario usuario) {
        if (usuario.getUltimo_acceso() == null) {
            usuario.setUltimo_acceso(LocalDateTime.now());
        }
        return usuarioService.guardar(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizar(@PathVariable String id, @RequestBody Usuario usuario) {
        return usuarioService.obtenerPorId(id).map(u -> {
            u.setNombre(usuario.getNombre());
            u.setApellidos(usuario.getApellidos());
            u.setCorreo(usuario.getCorreo());
            u.setRol(usuario.getRol());
            u.setActivo(usuario.isActivo());
            if (usuario.getUltimo_acceso() != null) {
                u.setUltimo_acceso(usuario.getUltimo_acceso());
            }
            //usuario.setIdUsuario(id);
            return ResponseEntity.ok(usuarioService.guardar(usuario));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        if (usuarioService.obtenerPorId(id).isPresent()) {
            usuarioService.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }*/
}
