package com.example.CrudTraining.service.serviceimpl;

import com.example.CrudTraining.bo.IrisModelRequest;
import com.example.CrudTraining.bo.IrisModelResponse;
import com.example.CrudTraining.controller.dto.ChatGptRequest;
import com.example.CrudTraining.controller.dto.ChatGptResponse;
import com.example.CrudTraining.repository.IrisModelRepository;
import com.example.CrudTraining.service.IaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;






/**
 * Implémentation pour appeler des modèles de Machines Learning
 *
 */
@Service
public class IaServiceImpl implements IaService {





    // *************************** Attributs Modèle Iris *************************** //

    @Autowired
    private IrisModelRepository irisModelRepository;

    // Url pour exécuter le modèle Iris :
    private final String executeModelIrisApiUrl = "http://localhost:8008/predict";

    // ************** TEST ************** //
    // Url pour entrainer le modèle Iris :
    private final String loadUserPredictionsInModelIrisApiUrl = "http://localhost:8008/load-predict-in-model";
    // ************** TEST ************** //





    // *************************** Attributs ChatGpt *************************** //

    // Template Rest :
    @Autowired
    private RestTemplate restTemplate;

    // Version de ChatGpt :
    @Value("${openai.model}")
    private String model;

    // Url de l'Api ChatGpt :
    @Value("${openai.api.url}")
    private String apiUrl;





    // ************************** Méthodes ************************** //
    @Override
    public String getIrisModelPrediction(IrisModelRequest request){
        // Attribut :
        String prediction;
        // Création de l'en-tête de la requête Http :
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // Création du contenu de la requête Http (corps + en-tête) :
        HttpEntity<IrisModelRequest> httpEntity = new HttpEntity<>(request, headers);
        // Exécution de la requête vers l'API du modèle de Machine Learning :
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(executeModelIrisApiUrl, httpEntity, String.class);
        // Récupération et renvoie de la réponse :
        prediction = responseEntity.getBody();
        return prediction ;
    }



    @Override
    public IrisModelResponse saveIrisModelPrediction(IrisModelResponse result){
        System.out.println(result.getPrediction());
        System.out.println(result.getPetalLength());
        System.out.println(result.getSepalLength());
        return irisModelRepository.save(result);
    }



    @Override
    public List<IrisModelResponse> getAllIrisModelPrediction(){
        return irisModelRepository.findAll();
    }



    // *********************************** TEST *********************************** //
    // *********************************** TEST *********************************** //
    // *********************************** TEST *********************************** //
    @Override
    public void loadUsersPredictionsInModel(){
        // Récupération des données de la BDD :
        List<IrisModelResponse> userPredictions = getAllIrisModelPrediction();

        // Intégration des données dans un ArrayList à 2 niveaux :
        ArrayList<ArrayList<Object>> loadData = new ArrayList<>();
        for(IrisModelResponse prediction : userPredictions){
            ArrayList<Object> ligneData = new ArrayList<Object>();
            ligneData.add(prediction.getSepalLength());
            ligneData.add(prediction.getSepalWidth());
            ligneData.add(prediction.getPetalLength());
            ligneData.add(prediction.getPetalWidth());
            ligneData.add(prediction.getPrediction());
        }

        // Envoi des données dans le modèle de machine Learning Iris :
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> httpEntity = new HttpEntity<>(loadData, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(executeModelIrisApiUrl, httpEntity, String.class);
    }
    // *********************************** TEST *********************************** //
    // *********************************** TEST *********************************** //
    // *********************************** TEST *********************************** //



    @Override
    public String chat(@RequestBody String prompt){
        ChatGptRequest request = new ChatGptRequest(model, prompt); // Création d'une requête contenant le message.
        ChatGptResponse chatGptResponse = restTemplate.postForObject(apiUrl, request, ChatGptResponse.class); // Récupération de la réponse générée par chatGPT.
        return chatGptResponse.getChoices().get(0).getMessage().getContent(); // Renvoie de la réponse.
    }





}

