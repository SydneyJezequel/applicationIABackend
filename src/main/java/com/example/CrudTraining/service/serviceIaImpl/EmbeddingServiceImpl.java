package com.example.CrudTraining.service.serviceIaImpl;

import com.example.CrudTraining.bo.ia.embeddingModel.DataCategories;
import com.example.CrudTraining.bo.ia.embeddingModel.QuestionInput;
import com.example.CrudTraining.bo.ia.embeddingModel.SelectCategoryDataSet;
import com.example.CrudTraining.bo.ia.embeddingModel.SelectDataSet;
import com.example.CrudTraining.service.iaService.EmbeddingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Value;
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


    // Chemins :
    @Value("${embedding.project.new.dataset.template}")
    private String pathGenerateJsonlFileTemplateForDataset;

    @Value("${embedding.project.new.dataset}")
    private String pathImportJsonlTemplateDataSetFile;

    @Value("${embedding.project.initialize.default.dataset}")
    private String pathInitializeDefaultDataset;

    @Value("${embedding.project.generate.default.dataset.origin}")
    private String pathGenerateDefaultDatasetOrigin;

    @Value("${embedding.project.generate.default.dataset.destination}")
    private String pathGenerateDefaultDatasetDestination;

    @Value("${embedding.project.load.new.dataset}")
    private String pathLoadFileIntoDataset;


    // Urls :
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
            String filePath = pathGenerateJsonlFileTemplateForDataset;

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
            // Chemin du dossier :
            String folderPath = pathImportJsonlTemplateDataSetFile;
            Path path = Paths.get(folderPath);
            // Création du dossier :
            if (!Files.exists(path)) {
                try {
                    Files.createDirectories(path);
                    logger.info("Dossier 'loaded_embedded_file' créé avec succès !");
                } catch (IOException e) {
                    logger.info("Échec de la création du dossier : " + e.getMessage());
                }
            } else {
                logger.info("Le dossier existe déjà.");
            }
            // Charge la photo à identifier dans le dossier :
            String fileName = "embedded_file.jsonl";
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
    public boolean initializeDefaultDataset(){
        try {
            // Définition du chemin du fichier chargé :
            SelectDataSet path = new SelectDataSet();
            path.setPath(pathInitializeDefaultDataset);
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
            logger.info("Dataset par défaut chargé.");
            String response = responseEntity.getBody();
            return Boolean.parseBoolean(response);
        } catch (RuntimeException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public boolean generateJsonlFileDefaultDataset(){
        try {
            // Chemin d'origine du fichier :
            String sourcePath = pathGenerateDefaultDatasetOrigin;
            // Chemin de destination :
            String destinationPath = pathGenerateDefaultDatasetDestination;
            // Copie du fichier :
            Path source = Paths.get(sourcePath);
            Path destination = Paths.get(destinationPath);
            Files.copy(source, destination);
            logger.info("Le fichier a été transféré avec succès.");
            return true;
        } catch (Exception e) {
            logger.info("Une erreur s'est produite lors du transfert du fichier : " + e.getMessage());
            return false;
        }
    }



    @Override
    public boolean loadFileIntoDataset() {
        try {
            // Définition du chemin du fichier chargé :
            SelectDataSet path = new SelectDataSet();
            path.setPath(pathLoadFileIntoDataset);
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
            logger.info("Dataset chargé.");
            String response = responseEntity.getBody();
            return Boolean.parseBoolean(response);
        } catch (RuntimeException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }



     @Override
    public boolean selectCategory(String selectCategoryDataSet) {
        try {
            // Création de la catégorie :
            SelectCategoryDataSet selectedCategoryDataSet = new SelectCategoryDataSet();
            selectedCategoryDataSet.setCategory(selectCategoryDataSet);
            // En-tête de la requête Http :
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            // Contenu de la requête Http :
            HttpEntity<SelectCategoryDataSet> httpEntity = new HttpEntity<>(selectedCategoryDataSet, headers);
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
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public String getLlmEmbeddingAnswer(String question) {
        try {
            // Création du paramètre envoyé
            QuestionInput questionInput = new QuestionInput();
            questionInput.setQuestion(question);
            // En-tête de la requête Http :
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            // Contenu de la requête Http :
            HttpEntity<QuestionInput> httpEntity = new HttpEntity<>(questionInput, headers);
            // Exécution de la requête :
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(getLlmEmbeddingAnswerUrl, httpEntity, String.class);
            // Récupération de la réponse :
            logger.info("Réponse récupérée.");
            return responseEntity.getBody();
        } catch (RuntimeException e) {
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

