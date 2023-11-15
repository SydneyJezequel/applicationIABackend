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
        this.model = model; // Version de chatGpt.
        this.messages = new ArrayList<>(); //
        this.messages.add(new Message("user", prompt)); // Intégration des rôles et messages dans la requête.
     }





}

