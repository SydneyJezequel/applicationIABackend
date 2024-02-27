package com.example.CrudTraining.controller.dto;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;






/**
 * Requête envoyée à ChatGpt.
 *
 */
@Data
public class ChatGptRequest {





    // *************************** Attributs *************************** //

    private String model;

    private List<Message> messages;





    // *************************** Constructeur *************************** //
    public ChatGptRequest(String model, String prompt) {
        // Version de chatGpt :
        this.model = model;
        this.messages = new ArrayList<>(); //
        // Intégration des rôles et messages dans la requête :
        this.messages.add(new Message("user", prompt));
     }






}

