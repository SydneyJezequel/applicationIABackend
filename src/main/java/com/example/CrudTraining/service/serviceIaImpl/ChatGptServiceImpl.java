package com.example.CrudTraining.service.serviceIaImpl;

import com.example.CrudTraining.controller.dto.ChatGptRequest;
import com.example.CrudTraining.controller.dto.ChatGptResponse;
import com.example.CrudTraining.service.iaService.ChatGptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import java.util.logging.Logger;






/**
 * Implémentation du Service pour communiquer avec l'Api de chatGPT.
 *
 */
@Service
public class ChatGptServiceImpl implements ChatGptService {





    // ************************** Implémentation des logs ************************** //
    private static final Logger logger = Logger.getLogger(ChatGptServiceImpl.class.getName());





    // *************************** Attributs *************************** //

    // Template Rest :
    @Autowired
    private RestTemplate restTemplate;

    // Version de ChatGpt :
    @Value("${openai.model}")
    private String model;

    // Url de l'Api ChatGpt :
    @Value("${openai.api.url}")
    private String apiUrl;





    // *************************** Méthodes *************************** //
    @Override
    public String chat(@RequestBody String prompt){
        // Création d'une requête :
        ChatGptRequest request = new ChatGptRequest(model, prompt);
        // Réponse de l'Api de chatGPT :
        ChatGptResponse chatGptResponse = restTemplate.postForObject(apiUrl, request, ChatGptResponse.class);
        logger.info(chatGptResponse.getChoices().get(0).getMessage().getContent());
        return chatGptResponse.getChoices().get(0).getMessage().getContent();
    }






}

