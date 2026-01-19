package org.casa.backend.service;

import org.springframework.stereotype.Service;

@Service
public interface EmailService {
    void enviarCorreoTexto(String to, String subject, String message);
    void enviarCorreoHtml(String to, String subject, String html);
} 
