package com.example.CrudTraining.service.serviceIaImpl;

import com.example.CrudTraining.bo.IrisModelRequest;
import com.example.CrudTraining.bo.IrisModelResponse;
import com.example.CrudTraining.repository.IrisModelRepository;
import com.example.CrudTraining.service.iaService.IrisModelService;
import com.opencsv.CSVWriter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;






/**
 * Implémentation mes méthodes pour manipuler le modèle de classification des Iris.
 *
 */
@Service
public class IrisModelServiceImpl implements IrisModelService {





    // *************************** Attributs Modèle Iris *************************** //

    @Autowired
    private IrisModelRepository irisModelRepository;
    // Url pour initialiser le modèle Iris :
    private final String initializeModelIrisApiUrl = "http://localhost:8008/initialize-model";
    // Url pour exécuter le modèle Iris :
    private final String executeModelIrisApiUrl = "http://localhost:8008/predict";
    // ************** TEST ************** //
    // Url pour entrainer le modèle Iris :
    private final String loadUserPredictionsInModelIrisApiUrl = "http://localhost:8008/load-predict-in-model";
    // ************** TEST ************** //





    // ************************** Implémentation des logs ************************** //
    private static final Logger logger = Logger.getLogger(IrisModelServiceImpl.class.getName());




    // ************************** Méthodes ************************** //

    @Override
    public String initializeModelPrediction(){
        // Attribut :
        String messageSucces;

        // Création de l'en-tête de la requête Http :
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Création du contenu de la requête Http :
        HttpEntity<Void> httpEntity = new HttpEntity<>(headers);

        // Exécution de la requête vers l'API du modèle de Machine Learning :
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                initializeModelIrisApiUrl,
                HttpMethod.GET,
                httpEntity,
                String.class
        );

        // Récupération et renvoie de la réponse :
        messageSucces = responseEntity.getBody();
        return messageSucces;
    }



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
            loadData.add(ligneData);
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
    public boolean generateCsv() {
        try {
            // Récupération des données de la BDD :
            List<IrisModelResponse> irisModelResponses = getAllIrisModelPrediction();
            // Path du fichier CSV ou sont chargées les données :
            String filePath = "/Users/sjezequel/Desktop/Predictions_classification_iris_csv";
            // Création du fichier Csv :
            CSVWriter writer = new CSVWriter(new FileWriter(filePath));
            // Injection de l'en-tête dans le fichier CSV :
            String[] entete = {"no_prediction", "sepal_length", "sepal_width", "petal_length", "petal_width", "prediction"};
            writer.writeNext(entete);
            // Injection des données dans le fichier CSV :
            for(IrisModelResponse irisModelResponse : irisModelResponses){
                String no_securite_sociale = String.valueOf(irisModelResponse.getNo_prediction());
                String[] ligne = {String.valueOf(irisModelResponse.getSepalLength()), String.valueOf(irisModelResponse.getSepalWidth()), String.valueOf(irisModelResponse.getPetalLength()), String.valueOf(irisModelResponse.getPetalWidth()), irisModelResponse.getPrediction()};
                writer.writeNext(ligne);
            }
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }



    @Override
    public boolean generateExcel() throws IOException {
        try {
            // Récupération des données de la BDD :
            List<IrisModelResponse> irisModelResponses = getAllIrisModelPrediction();
            // Création du fichier Excel et la feuille qui contient les données :
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Predictions");
            // Création ligne d'en-tête du fichier Excel :
            Row headerRow = sheet.createRow(0); // La nouvelle ligne fait partie de la feuille du fichier Excel.
            headerRow.createCell(0).setCellValue("no_prediction");
            headerRow.createCell(1).setCellValue("sepal_length");
            headerRow.createCell(2).setCellValue("sepal_width");
            headerRow.createCell(3).setCellValue("petal_length");
            headerRow.createCell(4).setCellValue("petal_width");
            headerRow.createCell(5).setCellValue("prediction");
            // Remplir chaque ligne du fichier Excel avec une Personne :
            int rowNum = 1;
            for (IrisModelResponse irisModelResponse : irisModelResponses) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(irisModelResponse.getNo_prediction());
                row.createCell(1).setCellValue(irisModelResponse.getSepalLength());
                row.createCell(2).setCellValue(irisModelResponse.getSepalWidth());
                row.createCell(3).setCellValue(irisModelResponse.getPetalLength());
                row.createCell(4).setCellValue(irisModelResponse.getPetalWidth());
                row.createCell(5).setCellValue(irisModelResponse.getPrediction());
            }
            // Fichier Excel ou sont chargées les données :
            String filePath = "/Users/sjezequel/Desktop/Predictions_classification_iris";
            // Écriture des données dans le fichier Excel :
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            }
            workbook.close();
            return true;
        } catch (RuntimeException e)
        {
            return false;
        }
    }





}

