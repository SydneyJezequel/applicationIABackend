package com.example.CrudTraining.service.serviceImpl;

import com.example.CrudTraining.bo.EmailRequest;
import com.example.CrudTraining.service.EmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import java.io.File;
import java.util.Properties;
import java.util.logging.Logger;
// import javax.mail.internet.MimeMessage;






/**
 * Implémentation du service pour Envoyer les Emails
 *
 */
@Service
public class EmailServiceImpl implements EmailService {





    // ************************** Attributs ************************** //
    private JavaMailSender emailSender;





    // ************************** Implémentation des logs ************************** //
    private static final Logger logger = Logger.getLogger(EmailServiceImpl.class.getName());





    // *************************** Méthodes *************************** //
    @Override
    public String sendEmail(EmailRequest emailRequest) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo("destinataire@example.com");
            message.setSubject("Nouveau message de contact");
            message.setText("Email: " + emailRequest.getEmail() + "\n\nMessage: " + emailRequest.getMessage());

            JavaMailSender javaMailSender = new JavaMailSenderImpl();
            javaMailSender.send(message);

            logger.info("Méthode envoyerMail() exécutée avec succès.");
            return "Email envoyé avec succès";
        } catch (RuntimeException e) {
            logger.warning("Méthode envoyerEmail() en erreur : " + e);
        }
            return null;
    }



    @Bean
    public JavaMailSender getJavaMailSender() {
        try {
            JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
            mailSender.setHost("smtp.gmail.com");
            mailSender.setPort(587);

            mailSender.setUsername("my.gmail@gmail.com");
            mailSender.setPassword("password");

            Properties props = mailSender.getJavaMailProperties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.debug", "true");

            logger.info("Méthode getJavaMailSender() exécutée avec succès.");
            return mailSender;
        } catch (RuntimeException e) {
            logger.warning("Méthode getJavaMailSender() en erreur : " + e);
        }
        return null;
    }



    @Override
    public void sendEmailWithAttachment(String to, String subject, String text, String pathToAttachment) throws MessagingException {

        try {
            javax.mail.internet.MimeMessage message = emailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("noreply@baeldung.com");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);

            FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
            helper.addAttachment("Invoice", file);

            emailSender.send(message);
            logger.info("Méthode sendMessageWithAttachment() exécutée avec succès.");

        } catch (RuntimeException e) {
            logger.warning("Méthode sendMessageWithAttachment() en erreur : " + e);
        }
    }






}

