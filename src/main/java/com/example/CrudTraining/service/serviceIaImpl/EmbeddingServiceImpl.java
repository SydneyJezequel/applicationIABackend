package com.example.CrudTraining.service.serviceIaImpl;

import com.example.CrudTraining.bo.ia.embeddingModel.QuestionInput;
import com.example.CrudTraining.bo.ia.embeddingModel.SelectCategoryDataSet;
import com.example.CrudTraining.bo.ia.embeddingModel.SelectDataSet;
import com.example.CrudTraining.service.iaService.EmbeddingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import java.util.logging.Logger;






/**
 * Implémentation du Service pour manipuler le modèle d'Embedding.
 *
 */
@Service
public class EmbeddingServiceImpl implements EmbeddingService {





    // *************************** Attributs *************************** //

    // ==> REVOIR LES URL (NON : coucou_cest_moi --> OUI : coucou-cest-moi) :
    private final String loadDataSetFileUrl = "http://localhost:8011/process-jsonl-dataset";
    private final String loadDataSetInVectorDbUrl = "http://localhost:8011/load-dataset";
    private final String selectDataByCategoryUrl = "http://localhost:8011/select-category";
    private final String getLlmEmbeddingAnswerUrl = "http://localhost:8011/get-llm-embedding-answer";





    // ************************** Implémentation des logs ************************** //
    private static final Logger logger = Logger.getLogger(EmbeddingServiceImpl.class.getName());





    // ************************** Méthodes ************************** //

    @Override
    public boolean importJsonlDataSetFile(MultipartFile file) {
        return false;
    }



    @Override
    public boolean loadDataset(SelectDataSet file_path) {
        try {
            // En-tête de la requête Http :
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            // Contenu de la requête Http :
            ObjectMapper objectMapper = new ObjectMapper();
            String requestBody = objectMapper.writeValueAsString(file_path);
            HttpEntity<String> httpEntity = new HttpEntity<>(requestBody, headers);
            // Exécution de la requête :
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    loadDataSetInVectorDbUrl,
                    HttpMethod.POST,
                    httpEntity,
                    String.class
            );
            // Récupération de la réponse :
            logger.info("Dataset chargé");
            String response = responseEntity.getBody();
            return Boolean.parseBoolean(response);
        } catch (RuntimeException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public boolean selectCategory(SelectCategoryDataSet selectCategoryDataSet) {
        try {
            // En-tête de la requête Http :
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            // Contenu de la requête Http :
            ObjectMapper objectMapper = new ObjectMapper();
            String requestBody = objectMapper.writeValueAsString(selectCategoryDataSet);
            HttpEntity<String> httpEntity = new HttpEntity<>(requestBody, headers);
            // Exécution de la requête :
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    selectDataByCategoryUrl,
                    HttpMethod.POST,
                    httpEntity,
                    String.class
            );
            // Récupération de la réponse :
            logger.info("Catégorie sélectionnée.");
            String response = responseEntity.getBody();
            return Boolean.parseBoolean(response);
        } catch (RuntimeException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public String getLlmEmbeddingAnswer(QuestionInput question) {
        try {
            // En-tête de la requête Http :
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            // Contenu de la requête Http :
            ObjectMapper objectMapper = new ObjectMapper();
            String requestBody = objectMapper.writeValueAsString(question);
            HttpEntity<String> httpEntity = new HttpEntity<>(requestBody, headers);
            // Exécution de la requête :
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    getLlmEmbeddingAnswerUrl,
                    HttpMethod.POST,
                    httpEntity,
                    String.class
            );
            // Récupération de la réponse :
            logger.info("Réponse récupérée.");
            return responseEntity.getBody();
        } catch (RuntimeException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }






}

