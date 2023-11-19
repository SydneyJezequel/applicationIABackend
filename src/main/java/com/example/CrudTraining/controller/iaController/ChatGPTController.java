package com.example.CrudTraining.controller.iaController;

import com.example.CrudTraining.service.iaService.ChatGptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;






/**
 * Controller pour échanger avec chatGPT.
 *
 */
@RestController
@RequestMapping("/api/ia")
public class ChatGPTController {





    // *************************** Attributs *************************** //
    private final ChatGptService chatGptService;





    // *************************** Constructeur *************************** //
    @Autowired
    public ChatGPTController(ChatGptService chatGptService) { this.chatGptService = chatGptService; }




    // *************************** Méthodes *************************** ///**
    /**
     * Méthode du controller qui envoie la requête du front à chatGpt et Récupère la réponse.
     * @param prompt : Contenu de la requête.
     * @return String de la réponse.
     *
     */
    @PostMapping("/chat-gpt")
    public String chat(@RequestBody String prompt){
        return chatGptService.chat(prompt);
    }





}

