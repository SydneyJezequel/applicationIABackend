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
 * Implémentation des méthodes pour manipuler le modèle de Génération d'image GAN.
 *
 */
@Service
public class GanModelServiceImpl implements GanModelService {





    // *************************** Urls pour manipuler le modèle Gan *************************** //
    private final String urlGenerateGanImage = "http://localhost:8009/generate-faces";
    private final String urlTrainGanImage = "http://localhost:8009/train-gan-model";





    // ************************** Implémentation des logs ************************** //
    private static final Logger logger = Logger.getLogger(GanModelServiceImpl.class.getName());






    // ************************************ Méthodes ************************************ //

    /**
     * Méthode qui charge le fichier de paramètres du Générateur dans le projet Python.
     * Ce fichier de paramètres est ensuite utilisé par le modèle pour générer des images.
     * @param parameterGenFile : fichier de paramètres du Générateur
     * @return : Renvoie un booléen.
     *
     */
    public boolean loadParametersGenFile(MultipartFile parameterGenFile){
        try {

            // Nom du dossier de stockage des paramètres du Générateur :
            String nomDuDossier = "config";
            // Chemin du dossier de stockage :
            String cheminDuDossier = "/Users/sjezequel/PycharmProjects/GanExecution/" + nomDuDossier;
            Path cheminPath = Paths.get(cheminDuDossier);

            // Création du dossier :
            if (!Files.exists(cheminPath)) {
                try {
                    Files.createDirectories(cheminPath);
                    logger.info("Dossier './config' créé avec succès à la source du projet !");
                } catch (IOException e) {
                    logger.info("Échec de la création du dossier : " + e.getMessage());
                }
            } else {
                logger.info("Le dossier existe déjà.");
            }

            // Chargement du fichier de paramètres dans le dossier "/config" :
            String nomDuFichier = "G-latest.pkl";
            Path cheminFichierDestination = Paths.get(cheminDuDossier, nomDuFichier);
            try (OutputStream outputStream = Files.newOutputStream(cheminFichierDestination)) {
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
            // Création de l'en-tête de la requête Http :
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            // Création du contenu de la requête Http :
            Map<String, Object> requestBodyMap = new HashMap<>();
            requestBodyMap.put("n_epochs", nbEpochs);
            requestBodyMap.put("batch_size", batchSize);
            requestBodyMap.put("lr", lr);
            requestBodyMap.put("z_dim", zDim);
            requestBodyMap.put("device", device);
            requestBodyMap.put("show_step", showStep);
            requestBodyMap.put("save_step", saveStep);
            // Convertir la map en JSON :
            ObjectMapper objectMapper = new ObjectMapper();
            String requestBody = objectMapper.writeValueAsString(requestBodyMap);
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




