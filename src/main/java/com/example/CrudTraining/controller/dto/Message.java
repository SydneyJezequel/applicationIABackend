package com.example.CrudTraining.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;






/**
 * Contenu d'une Requête / Réponse chatGpt.
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

