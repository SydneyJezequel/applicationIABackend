package com.example.CrudTraining.service.iaService;






/**
 * Service pour appeler échanger avec chatGPT.
 *
 */
public interface ChatGptService {



    /**
     * Méthode qui envoie une requête à chatGpt et Récupère la réponse.
     * @param prompt : Contenu de la requête.
     * @return String de la réponse.
     *
     */
    public String chat(String prompt);





}

