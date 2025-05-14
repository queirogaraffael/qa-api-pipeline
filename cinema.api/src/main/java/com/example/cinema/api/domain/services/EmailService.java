package com.example.cinema.api.domain.services;

import com.example.cinema.api.domain.entities.Purchase;
import com.example.cinema.api.shared.exceptions.EmailSendException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);
    private final JavaMailSender mailSender;
    private final String from;

    public EmailService(JavaMailSender mailSender,
                        @Value("${spring.mail.username}") String from) {
        this.mailSender = mailSender;
        this.from = from;
    }

    public void sendWelcomeEmail(String to, String userName) {
        sendSimpleMessage(
                to,
                "Bem‑vindo(a)",
                String.format("Bem‑vindo(a) %s, seu cadastro foi concluído!", userName)
        );
    }

    public void sendPasswordReset(String to, String newPassword) {
        sendSimpleMessage(
                to,
                "Redefinição de senha",
                String.format("Sua nova senha é: %s%nNão compartilhe com ninguém!", newPassword)
        );
    }

    private void sendSimpleMessage(String to, String subject, String text) {
        try {
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setFrom(from);
            msg.setTo(to);
            msg.setSubject(subject);
            msg.setText(text);
            mailSender.send(msg);
        } catch (MailException e) {
            logger.error("Erro enviando e‑mail para {}", to, e);
            throw new EmailSendException("Não foi possível enviar e‑mail para " + to, e);
        }
    }

    public void sendPurchaseNotificationEmail(String to, Purchase purchase) {
        String subject = "Compra Realizada com Sucesso!";
        String text = String.format(
                "Olá %s,%n%nSua compra foi realizada com sucesso!%n%nDetalhes da compra:%n" +
                        "Sessão de Filme: %s%n" +
                        "Valor Total: %s%n" +
                        "Data da Compra: %s%n%n" +
                        "Obrigado por comprar conosco!",
                purchase.getUser().getName(),
                purchase.getMovieSession().getMovie().getTitle(),
                purchase.getTotalPrice(),
                purchase.getPurchaseDate()
        );

        sendSimpleMessage(to, subject, text);
    }
}
