package org.casa.backend.service.impl;


import java.time.LocalDateTime;
import org.casa.backend.service.PasswordResetService;
import org.casa.backend.entity.PasswordResetToken;
import org.casa.backend.entity.Usuario;
import org.casa.backend.repository.PasswordResetTokenRepository;
import org.casa.backend.repository.UsuarioRepository;
import org.casa.backend.service.EmailService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PasswordResetServiceImpl implements PasswordResetService{
    private final UsuarioRepository usuarioRepository;
    private final PasswordResetTokenRepository tokenRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    public PasswordResetServiceImpl(
            UsuarioRepository usuarioRepository,
            PasswordResetTokenRepository tokenRepository,
            EmailService emailService,
            PasswordEncoder passwordEncoder
    ) {
        this.usuarioRepository = usuarioRepository;
        this.tokenRepository = tokenRepository;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
    }

    public void enviarCorreoRecuperacion(String correo) {
        Usuario usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Correo no registrado"));

        tokenRepository.deleteByUsuario_IdUsuario(usuario.getIdUsuario());

        String token = UUID.randomUUID().toString();

        PasswordResetToken resetToken = new PasswordResetToken(
                null,
                token,
                usuario,
                LocalDateTime.now().plusMinutes(15)
        );

        tokenRepository.save(resetToken);

        String link = "http://localhost:3000/reset-password?token=" + token;

        emailService.enviarCorreoTexto(
                correo,
                "Recuperación de contraseña",
                "Hola " + usuario.getNombre() +
                "\n\nDa clic en el siguiente enlace para restablecer tu contraseña:\n"
                + link +
                "\n\nEste enlace expira en 15 minutos."
        );
    }

    public void resetearContrasenia(String token, String nuevaContrasenia) {
        PasswordResetToken resetToken = tokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Token inválido"));

        if (resetToken.getFechaExpiracion().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Token expirado");
        }

        Usuario usuario = resetToken.getUsuario();
        usuario.setContrasenia(passwordEncoder.encode(nuevaContrasenia));
        usuarioRepository.save(usuario);

        tokenRepository.delete(resetToken);
    }
}
