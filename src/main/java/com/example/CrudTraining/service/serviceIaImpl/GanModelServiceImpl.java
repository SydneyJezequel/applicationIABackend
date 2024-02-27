package com.example.CrudTraining.service.serviceIaImpl;

import com.example.CrudTraining.service.iaService.GanModelService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;






/**
 * Implémentation du Service pour manipuler le modèle de Génération d'image GAN.
 *
 */
@Service
public class GanModelServiceImpl implements GanModelService {





    // *************************** Urls pour manipuler le modèle Gan *************************** //
    private final String urlGenerateGanImage = "http://localhost:8010/generate-faces";
    private final String urlTrainGanImage = "http://localhost:8010/train-gan-model";





    // ************************** Implémentation des logs ************************** //
    private static final Logger logger = Logger.getLogger(GanModelServiceImpl.class.getName());





    // ************************************ Méthodes ************************************ //

    public boolean loadParametersGenFile(MultipartFile parameterGenFile){
        try {
            // Dossier ou est stocké le fichier de paramètres du Générateur :
            String folderName = "config";
            // Chemin du dossier :
            String folderPath = "/Users/sjezequel/PycharmProjects/GanExecution/" + folderName;
            Path path = Paths.get(folderPath);
            // Création du dossier :
            if (!Files.exists(path)) {
                try {
                    Files.createDirectories(path);
                    logger.info("Dossier './config' créé avec succès à la source du projet !");
                } catch (IOException e) {
                    logger.info("Échec de la création du dossier : " + e.getMessage());
                }
            } else {
                logger.info("Le dossier existe déjà.");
            }
            // Chargement du fichier de paramètres dans le dossier "/config" :
            String fileName = "G-latest.pkl";
            Path destinationFilePath = Paths.get(folderPath, fileName);
            try (OutputStream outputStream = Files.newOutputStream(destinationFilePath)) {
                outputStream.write(parameterGenFile.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return true;
        } catch(RuntimeException e){
            e.printStackTrace();
            return false;
        }
    }



    @Override
    public boolean generateImage() {
        try {
            // Classe pour manipuler la requête Http :
            RestTemplate restTemplate = new RestTemplate();
            // En-tête de la requête Http :
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> httpEntity = new HttpEntity<>(headers);
            // Exécution de la requête Http :
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



    @Override
    public boolean trainGanModel (int nbEpochs, int batchSize, double lr, int zDim, String device, int showStep, int saveStep) {
        try {
            // En-tête de la requête Http :
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            // Contenu de la requête Http :
            Map<String, Object> requestBodyMap = new HashMap<>();
            requestBodyMap.put("n_epochs", nbEpochs);
            requestBodyMap.put("batch_size", batchSize);
            requestBodyMap.put("lr", lr);
            requestBodyMap.put("z_dim", zDim);
            requestBodyMap.put("device", device);
            requestBodyMap.put("show_step", showStep);
            requestBodyMap.put("save_step", saveStep);
            // Conversion du contenu en JSON :
            ObjectMapper objectMapper = new ObjectMapper();
            String requestBody = objectMapper.writeValueAsString(requestBodyMap);
            HttpEntity<String> httpEntity = new HttpEntity<>(requestBody, headers);
            // Exécution de la requête Http :
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    urlTrainGanImage,
                    HttpMethod.POST,
                    httpEntity,
                    String.class
            );
            // Récupération de la réponse :
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

