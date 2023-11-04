package com.example.CrudTraining.controller;

import com.example.CrudTraining.bo.EmailRequest;
import com.example.CrudTraining.service.EmailService;
import com.example.CrudTraining.service.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;






/**
 * Controller pour gérer l'envoi des mails via le service SMTP.
 *
 */
@RestController
@RequestMapping("/api/email")
public class EmailController {






    // ********************* Attributs *********************
    private final EmailService emailService;






    // ********************* Constructeur *********************
    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }







    // ********************* Méthodes *********************


    /**
     * Méthode qui gère l'envoie des mails.
     * @param emailRequest emailRequest.
     * @return Message de succès.
     *
     */
    @PostMapping("/envoyer-email")
    public String envoyerEmail(@RequestBody EmailRequest emailRequest) {
        return emailService.envoyerEmail(emailRequest);
    }





}