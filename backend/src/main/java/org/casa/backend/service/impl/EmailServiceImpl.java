package org.casa.backend.service.impl;

import org.casa.backend.service.EmailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService{
    private final JavaMailSender mailSender;

    @Override
    @Async
    public void enviarCorreoTexto(String to, String subject, String message) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom("casasaoax@gmail.com");
        mail.setTo(to);
        mail.setSubject(subject);
        mail.setText(message);
        mailSender.send(mail);
    }

    @Override
    @Async
    public void enviarCorreoHtml(String to, String subject, String html) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(html, true);

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Error enviando correo", e);
        }
    }
    @PostConstruct
    public void debugMailUser() {
        System.out.println("MAIL USER -> " + System.getenv("MAIL_USERNAME"));
    }
    @PostConstruct
public void debugMailPass() {
    System.out.println("MAIL PASS LEN -> " + 
        (System.getenv("MAIL_PASSWORD") != null 
        ? System.getenv("MAIL_PASSWORD").length() 
        : "NULL"));
}
}
