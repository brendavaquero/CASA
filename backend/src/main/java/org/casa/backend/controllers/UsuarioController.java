package org.casa.backend.controllers;

import org.casa.backend.dto.UsuarioDto;
import org.casa.backend.service.UsuarioService;
import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import java.util.List;


@CrossOrigin("*")
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

    //Actualizar un usuario
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDto> updateUsuario(@PathVariable("id") String usuarioId, @RequestBody UsuarioDto updatedUsuario){
        UsuarioDto usuarioDto = usuarioService.updateUsuario(usuarioId, updatedUsuario);
        return ResponseEntity.ok(usuarioDto);
    }
    //Actualizar un usuario general
    @PutMapping("/general/{id}")
    public ResponseEntity<UsuarioDto> updateUsuarioGen(@PathVariable("id") String usuarioId, @RequestBody UsuarioDto updatedUsuario){
        UsuarioDto usuarioDto = usuarioService.updateUsuarioGeneral(usuarioId, updatedUsuario);
        return ResponseEntity.ok(usuarioDto);
    }
    @PutMapping("/activo/{id}")
    public ResponseEntity<UsuarioDto> actualizarActivo(
            @PathVariable("id") String idUsuario,
            @RequestParam boolean activo
    ) {
        UsuarioDto usuarioActualizado =
            usuarioService.updateActivo(idUsuario, activo);

        return ResponseEntity.ok(usuarioActualizado);
    }

    /*
    @PutMapping("/me/ultimo-acceso")
    public ResponseEntity<Void> actualizarUltimoAcceso(
            Authentication authentication
    ) {
        String correo = authentication.getName();
        usuarioService.actualizarUltimoAcceso(correo);
        return ResponseEntity.ok().build();
    }*/

    //Eliminar el usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUsuario(@PathVariable("id") String usuarioId) {
        usuarioService.deleteUsuario(usuarioId);
        return ResponseEntity.ok("Usuario eliminado correctamente");
    }

}
