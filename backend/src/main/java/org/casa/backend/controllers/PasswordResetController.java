package org.casa.backend.controllers;

import org.casa.backend.dto.ForgotPasswordRequestDto;
import org.casa.backend.dto.ResetPasswordRequestDto;
import org.casa.backend.service.PasswordResetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/authPs")
public class PasswordResetController {

    private final PasswordResetService service;

    public PasswordResetController(PasswordResetService service) {
        this.service = service;
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(
            @RequestBody ForgotPasswordRequestDto dto) {

        service.enviarCorreoRecuperacion(dto.getCorreo());
        return ResponseEntity.ok("Correo de recuperación enviado");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(
            @RequestBody ResetPasswordRequestDto dto) {

        service.resetearContrasenia(
                dto.getToken(),
                dto.getNuevaContrasenia()
        );
        return ResponseEntity.ok("Contraseña actualizada correctamente");
    }
}
