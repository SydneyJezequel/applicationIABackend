package com.example.CrudTraining.service.serviceIaImpl;

import com.example.CrudTraining.service.iaService.FineTuningModelService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;






/**
 * Implémentation du Service pour FineTuner et utiliser un modèle Llm.
 *
 */
@Service
public class FineTuningModelServiceImpl implements FineTuningModelService {





    // *************************** Attributs *************************** //

    private final String urlFineTuneLlm = "http://localhost:8012/fine-tune-model";
    private final String urlExecuteLlm = "http://localhost:8013/execute-model";





    // ************************** Implémentation des logs ************************** //
    private static final Logger logger = Logger.getLogger(EmbeddingServiceImpl.class.getName());





    // ************************** Méthodes ************************** //

    @Override
    public boolean fineTuneModel(int numEpochs, int trainDatasetSize, int validationDatasetSize, int trainBatchSize, int evalBatchSize) {
        try {
            // En-tête de la requête Http :
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            // Contenu de la requête Http :
            Map<String, Object> requestBodyMap = new HashMap<>();
            requestBodyMap.put("num_epochs", numEpochs);
            requestBodyMap.put("train_dataset_size", trainBatchSize);
            requestBodyMap.put("validation_dataset_size", validationDatasetSize);
            requestBodyMap.put("train_batch_size", trainBatchSize);
            requestBodyMap.put("eval_batch_size", evalBatchSize);
            // Conversion du contenu en JSON :
            ObjectMapper objectMapper = new ObjectMapper();
            String requestBody = objectMapper.writeValueAsString(requestBodyMap);
            HttpEntity<String> httpEntity = new HttpEntity<>(requestBody, headers);
            // Exécution de la requête Http :
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    urlFineTuneLlm,
                    HttpMethod.POST,
                    httpEntity,
                    String.class
            );
            // Récupération de la réponse :
            logger.info(responseEntity.getBody());
            return true;
        } catch (RuntimeException e) {
            logger.info(e.toString());
            return false;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public String executeModel(String question, String context) {
        try {
            // En-tête de la requête Http :
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            // Contenu de la requête Http :
            Map<String, Object> requestBodyMap = new HashMap<>();
            requestBodyMap.put("question", question);
            requestBodyMap.put("context", context);
            // Conversion du contenu en JSON :
            ObjectMapper objectMapper = new ObjectMapper();
            String requestBody = objectMapper.writeValueAsString(requestBodyMap);
            HttpEntity<String> httpEntity = new HttpEntity<>(requestBody, headers);
            // Exécution de la requête Http :
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    urlExecuteLlm,
                    HttpMethod.POST,
                    httpEntity,
                    String.class
            );
            // Récupération de la réponse :
            logger.info(responseEntity.getBody());
            return responseEntity.getBody();
        }catch (RuntimeException e){
            logger.info(e.toString());
            throw e;
        } catch (JsonProcessingException e) {
            logger.info(e.toString());
            throw new RuntimeException("Error processing JSON", e);
        }
    }





}














/*
    public boolean fineTuneModel() {
        try {
            // Classe pour manipuler la requête Http :
            RestTemplate restTemplate = new RestTemplate();
            // En-tête de la requête Http :
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> httpEntity = new HttpEntity<>(headers);
            // Exécution de la requête Http :
            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    urlFineTuneLlm,
                    HttpMethod.GET,
                    httpEntity,
                    String.class
            );
            return true;
        } catch (RuntimeException e) {
            logger.info(e.toString());
            return false;
        }
    }
*/



