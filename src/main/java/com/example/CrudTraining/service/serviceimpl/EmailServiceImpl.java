package com.example.CrudTraining.service.serviceimpl;

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
// import javax.mail.internet.MimeMessage;





/**
 * Implémentation du service pour Envoyer les Emails
 *
 */
@Service
public class EmailServiceImpl implements EmailService {








    // ********************* Attributs *********************

    private JavaMailSender emailSender;






    // ********************* Constructeur *********************






    // ********************* Méthodes *********************

    @Override
    public String envoyerEmail(EmailRequest emailRequest) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("destinataire@example.com");
        message.setSubject("Nouveau message de contact");
        message.setText("Email: " + emailRequest.getEmail() + "\n\nMessage: " + emailRequest.getMessage());

        JavaMailSender javaMailSender = new JavaMailSenderImpl();

        javaMailSender.send(message);

        return "Email envoyé avec succès";
    }





    @Bean
    public JavaMailSender getJavaMailSender() {
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

        return mailSender;
    }



    @Override
    public void sendMessageWithAttachment(
            String to, String subject, String text, String pathToAttachment) throws MessagingException {
        // ...

        javax.mail.internet.MimeMessage message = emailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("noreply@baeldung.com");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text);

        FileSystemResource file
                = new FileSystemResource(new File(pathToAttachment));
        helper.addAttachment("Invoice", file);

        emailSender.send(message);

    }





}

