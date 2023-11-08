package com.example.CrudTraining.service;

import com.example.CrudTraining.bo.EmailRequest;
import javax.mail.MessagingException;






/**
 * Service pour Envoyer les emails.
 *
 */
public interface EmailService {


    /**
     * Service qui gère l'envoie des mails.
     *
     * @param emailRequest emailRequest.
     * @return Message de succès.
     */
    public String envoyerEmail(EmailRequest emailRequest);

    void sendMessageWithAttachment(
            String to, String subject, String text, String pathToAttachment) throws MessagingException;
}