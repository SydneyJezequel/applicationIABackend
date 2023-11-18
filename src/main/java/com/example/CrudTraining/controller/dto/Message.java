package com.example.CrudTraining.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;






/**
 * Message contenu dans une Requête ou une Réponse chatGpt.
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {




    // *************************** Attributs *************************** //
    private String role;
    private String content;





}

