package com.example.CrudTraining.service.iaService;






/**
 * Service pour communiquer avec l'Api de chatGPT.
 *
 */
public interface ChatGptService {



    /**
     * Méthode qui envoie une requête à l'Api de chatGpt et renvoie sa réponse.
     * @param prompt : Contenu de la requête.
     * @return String de la réponse.
     *
     */
    public String chat(String prompt);






}

