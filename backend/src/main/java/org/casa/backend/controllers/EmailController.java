package org.casa.backend.controllers;

import org.casa.backend.dto.EmailRequestDto;
import org.casa.backend.service.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
public class EmailController {
    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/enviar")
    public ResponseEntity<String> enviarCorreo(
            @RequestBody EmailRequestDto dto) {

        emailService.enviarCorreoTexto(
                dto.getCorreo(),
                dto.getAsunto(),
                dto.getMensaje()
        );

        return ResponseEntity.ok("Correo enviado correctamente");
    }
}
