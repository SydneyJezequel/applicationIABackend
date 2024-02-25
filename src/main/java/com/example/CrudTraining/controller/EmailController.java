package com.example.CrudTraining.controller;

import com.example.CrudTraining.bo.EmailRequest;
import com.example.CrudTraining.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;






/**
 * Controller pour gérer des mails via service SMTP.
 *
 */
@RestController
@RequestMapping("/api/email")
public class EmailController {





    // *************************** Attributs *************************** //
    private final EmailService emailService;





    // *************************** Constructeur *************************** //
    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }





    // *************************** Méthodes *************************** //

    /**
     * Méthode qui gère l'envoi des mails.
     * @param emailRequest emailRequest.
     * @return Message de succès.
     *
     */
    @PostMapping("/envoyer-email")
    public String envoyerEmail(@RequestBody EmailRequest emailRequest) {
        return emailService.envoyerEmail(emailRequest);
    }






}

