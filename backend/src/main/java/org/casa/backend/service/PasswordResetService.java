package org.casa.backend.service;

public interface PasswordResetService {
    void enviarCorreoRecuperacion(String correo);
    void resetearContrasenia(String token, String nuevaContrasenia);
}
