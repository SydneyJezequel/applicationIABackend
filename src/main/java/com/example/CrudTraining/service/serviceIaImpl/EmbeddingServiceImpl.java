package com.example.CrudTraining.service.serviceIaImpl;

import com.example.CrudTraining.bo.ia.embeddingModel.DataCategories;
import com.example.CrudTraining.bo.ia.embeddingModel.QuestionInput;
import com.example.CrudTraining.bo.ia.embeddingModel.SelectCategoryDataSet;
import com.example.CrudTraining.bo.ia.embeddingModel.SelectDataSet;
import com.example.CrudTraining.service.iaService.EmbeddingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
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
    public boolean generateJsonlFileTemplateForDataset(){
        try {
            // Définition des Catégories du fichier ".jsonl" :
            List<String> categories = new ArrayList<>();
            categories.add(DataCategories.closed_qa.name());
            categories.add(DataCategories.classification.name());
            categories.add(DataCategories.summarization.name());
            categories.add(DataCategories.brainstorming.name());

            // Configuration de l'ObjectMapper pour formater les données JSON :
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

            // Emplacement du fichier généré :
            String filePath = "/Users/sjezequel/Desktop/Embedding_dataset";

            // Création d'un BufferedWriter pour écrire dans le fichier :
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                // Écriture de chaque ligne dans le fichier ".jsonl" :
                for (String category : categories) {
                    String jsonLine = "{\"instruction\": \"\", \"context\": \"\", \"response\": \"\", \"category\": \"" + category + "\"}";
                    writer.write(jsonLine);
                    writer.newLine();
                }
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }



    @Override
    public boolean importJsonlTemplateDataSetFile(MultipartFile file) {
        try {
            // Nom du dossier de stockage de l'image :
            String folderName = "embedded_file";
            // Chemin du dossier :
            String folderPath = "/Users/sjezequel/PycharmProjects/EmbeddingDocuments/" + folderName + "/";
            Path path = Paths.get(folderPath);
            // Création du dossier :
            if (!Files.exists(path)) {
                try {
                    Files.createDirectories(path);
                    logger.info("Dossier " + folderName + " créé avec succès !");
                } catch (IOException e) {
                    logger.info("Échec de la création du dossier : " + e.getMessage());
                }
            } else {
                logger.info("Le dossier existe déjà.");
            }
            // Charge la photo à identifier dans le dossier :
            String fileName = "camelia_yvon_jezequel_dataset.jsonl";
            Path destinationFilePath = Paths.get(folderPath, fileName);
            try (OutputStream outputStream = Files.newOutputStream(destinationFilePath)) {
                outputStream.write(file.getBytes());
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
    public boolean loadFileIntoDataset(SelectDataSet path) {
        try {
            // En-tête de la requête Http :
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            // Contenu de la requête Http :
            ObjectMapper objectMapper = new ObjectMapper();
            String requestBody = objectMapper.writeValueAsString(path);
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
    public String
    getLlmEmbeddingAnswer(QuestionInput question) {
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



    @Override
    public List<String> getListDataCategories(){
        String closed_qa = DataCategories.closed_qa.name();
        String brainstorming = DataCategories.brainstorming.name();
        String classification = DataCategories.classification.name();
        String summarization = DataCategories.summarization.name();
        List<String> dataCategoriesList = new ArrayList<>();
        dataCategoriesList.add(closed_qa);
        dataCategoriesList.add(brainstorming);
        dataCategoriesList.add(classification);
        dataCategoriesList.add(summarization);
        return dataCategoriesList;
    }






}

