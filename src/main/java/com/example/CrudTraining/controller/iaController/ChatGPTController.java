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
@RequestMapping("/api/ia/chat-gpt")
public class ChatGPTController {





    // *************************** Attributs *************************** //
    private final ChatGptService chatGptService;





    // *************************** Constructeur *************************** //
    @Autowired
    public ChatGPTController(ChatGptService chatGptService) { this.chatGptService = chatGptService; }





    // *************************** Méthodes *************************** //

    /**
     * Méthode qui envoie le prompt à l'Api de chatGpt.
     * Elle renvoie la réponse.
     * @param prompt : Contenu du prompt.
     * @return String : Réponse.
     *
     */
    @PostMapping("/send-prompt")
    public String chat(@RequestBody String prompt){
        return chatGptService.chat(prompt);
    }






}

