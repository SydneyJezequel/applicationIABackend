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
 * Implémentation des méthodes pour échanger avec chatGPT.
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
        ChatGptRequest request = new ChatGptRequest(model, prompt); // Création d'une requête contenant le message.
        ChatGptResponse chatGptResponse = restTemplate.postForObject(apiUrl, request, ChatGptResponse.class); // Récupération de la réponse générée par chatGPT.
        return chatGptResponse.getChoices().get(0).getMessage().getContent(); // Renvoie de la réponse.
    }





}

