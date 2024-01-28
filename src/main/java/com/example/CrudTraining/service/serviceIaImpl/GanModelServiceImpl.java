package com.example.CrudTraining.service.serviceIaImpl;

import com.example.CrudTraining.service.iaService.GanModelService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;






/**
 * Implémentation des méthodes pour manipuler le modèle de Génération d'image GAN.
 *
 */
@Service
public class GanModelServiceImpl implements GanModelService {





    // *************************** Urls pour manipuler le modèle Gan *************************** //
    private final String urlGenerateGanImage = "http://localhost:8008/generate-faces";
    private final String urlTrainGanImage = "http://localhost:8008/train-gan-model";





    // ************************** Implémentation des logs ************************** //
    private static final Logger logger = Logger.getLogger(GanModelServiceImpl.class.getName());






    // ************************************ Méthodes ************************************ //

    /**
     * Méthode pour générer des images avec le modèle GAN.
     *
     */
    @Override
    public boolean generateImage() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            // Création de l'en-tête de la requête Http :
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> httpEntity = new HttpEntity<>(headers);
            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    urlGenerateGanImage,
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


        /**
         * Méthode pour générer des images avec le modèle GAN.
         *
         */
        @Override
        public boolean trainGanModel (int nbEpochs, int batchSize, double lr, int zDim, String device, int showStep, int saveStep) {
            try {
                // ********************** TEST ********************** //
                nbEpochs = 10000;
                batchSize = 128;
                lr = 1e-4;
                zDim = 200;
                device = "cpu";
                showStep = 35;
                saveStep = 35;
                // ********************** TEST ********************** //
                // Création de l'en-tête de la requête Http :
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                // Création du contenu de la requête Http :
                Map<String, Object> requestBodyMap = new HashMap<>();
                requestBodyMap.put("nb_epochs", nbEpochs);
                requestBodyMap.put("batch_size", batchSize);
                requestBodyMap.put("lr", lr);
                requestBodyMap.put("z_dim", zDim);
                requestBodyMap.put("device", device);
                requestBodyMap.put("show_step", showStep);
                requestBodyMap.put("save_step", saveStep);

                // Convertir la map en JSON :
                String requestBody = new ObjectMapper().writeValueAsString(requestBodyMap);
                HttpEntity<String> httpEntity = new HttpEntity<>(requestBody, headers);
                // Exécution de la requête vers l'API du modèle de Machine Learning :
                RestTemplate restTemplate = new RestTemplate();
                ResponseEntity<String> responseEntity = restTemplate.exchange(
                            urlTrainGanImage,
                            HttpMethod.POST,
                            httpEntity,
                            String.class
                    );
                    // Récupération et renvoie de la réponse :
                    logger.info(responseEntity.getBody());
                    return true;
                }catch (RuntimeException e){
                    logger.info(e.toString());
                    return false;
                } catch (JsonProcessingException e) {
                    logger.info(e.toString());
                    return false;
                }
            }










}













