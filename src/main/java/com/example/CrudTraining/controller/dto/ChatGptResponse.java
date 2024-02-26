package com.example.CrudTraining.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;






/**
 * Réponse envoyée par ChatGpt.
 *
 */
@Data // Getter & Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatGptResponse {





    // *************************** Attributs *************************** //

    // Liste des messages de la réponse :
    public List<Choice> choices;



    // Classe qui encapsule le message de la réponse :
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Choice{
        private int index;
        private Message message;
    }






}

