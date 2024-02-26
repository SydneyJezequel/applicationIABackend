package com.example.CrudTraining.service;

import com.example.CrudTraining.bo.EmailRequest;
import javax.mail.MessagingException;






/**
 * Service pour Envoyer les Emails.
 *
 */
public interface EmailService {



    /**
     * Service qui gère l'envoie des mails.
     * @param emailRequest emailRequest.
     * @return Message de succès.
     *
     */
    public String sendEmail(EmailRequest emailRequest);



    /**
     * Service qui gère l'envoie des mails.
     * @param to
     * @param subject
     * @param text
     * @param pathToAttachment
     * @throws MessagingException
     *
     */
    public void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment) throws MessagingException;






}

