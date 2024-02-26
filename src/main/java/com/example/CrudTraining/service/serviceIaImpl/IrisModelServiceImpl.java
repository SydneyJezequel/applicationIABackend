package com.example.CrudTraining.service.serviceIaImpl;

import com.example.CrudTraining.bo.ia.randomForestmodel.IrisModelDataSet;
import com.example.CrudTraining.bo.ia.randomForestmodel.IrisModelRequest;
import com.example.CrudTraining.bo.ia.randomForestmodel.IrisModelResponse;
import com.example.CrudTraining.repository.IrisModelRepository;
import com.example.CrudTraining.service.iaService.IrisModelService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;






/**
 * Implémentation du Service pour manipuler le modèle Random Forest.
 * Par défaut, ce modèle est entrainé et exécuté sur le dataSet de classement des Iris.
 *
 */
@Service
public class IrisModelServiceImpl implements IrisModelService {





    // *************************** Attributs *************************** //

    @Autowired
    private IrisModelRepository irisModelRepository;
    private final String initializeModelIrisApiUrl = "http://localhost:8008/initialize-model";
    private final String executeModelIrisApiUrl = "http://localhost:8008/predict";
    private final String loadUserPredictionsInModelIrisApiUrl = "http://localhost:8008/load-predict-in-model";
    private final String getIrisModelDataSet = "http://localhost:8008/get-iris-dataset";





    // ************************** Implémentation des logs ************************** //
    private static final Logger logger = Logger.getLogger(IrisModelServiceImpl.class.getName());





    // ************************** Méthodes ************************** //

    @Override
    public boolean initializeModelPrediction() {
        try {
            // Attributs :
            String messageSucces;
            // En-tête de la requête Http :
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            // Contenu de la requête Http :
            HttpEntity<Void> httpEntity = new HttpEntity<>(headers);
            // Exécution de la requête :
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    initializeModelIrisApiUrl,
                    HttpMethod.GET,
                    httpEntity,
                    String.class
            );
            // Récupération de la réponse :
            messageSucces = responseEntity.getBody();
            logger.info(messageSucces);
            return true;
        } catch (RuntimeException e) {
            logger.info(e.toString());
            return false;
        }
    }



    @Override
    public String getIrisModelPrediction(IrisModelRequest request) {
        // Attributs :
        String prediction;
        // En-tête de la requête Http :
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // Contenu de la requête Http (corps + en-tête) :
        HttpEntity<IrisModelRequest> httpEntity = new HttpEntity<>(request, headers);
        // Exécution de la requête :
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(executeModelIrisApiUrl, httpEntity, String.class);
        // Récupération de la réponse :
        prediction = responseEntity.getBody();
        return prediction;
    }



    @Override
    public IrisModelResponse saveIrisModelPrediction(IrisModelResponse result) {
        return irisModelRepository.save(result);
    }



    @Override
    public List<IrisModelResponse> getAllIrisModelPrediction() {
        return irisModelRepository.findAll();
    }



    @Override
    public boolean generateCsv() {
        try {
            // Récupération des données en BDD :
            List<IrisModelResponse> irisModelResponses = getAllIrisModelPrediction();
            // Path du fichier Csv :
            String filePath = "/Users/sjezequel/Desktop/Predictions_classification_iris_csv";
            // Création du fichier Csv :
            CSVWriter writer = new CSVWriter(new FileWriter(filePath));
            // Injection de l'en-tête dans le fichier Csv :
            String[] entete = {"no_prediction", "sepal_length", "sepal_width", "petal_length", "petal_width", "prediction"};
            writer.writeNext(entete);
            // Injection des lignes dans le fichier Csv :
            for (IrisModelResponse irisModelResponse : irisModelResponses) {
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
            // Récupération des données en BDD :
            List<IrisModelResponse> irisModelResponses = getAllIrisModelPrediction();
            // Création du fichier et de la feuille Excel :
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Predictions");
            // Création de la ligne d'en-tête :
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("no_prediction");
            headerRow.createCell(1).setCellValue("sepal_length");
            headerRow.createCell(2).setCellValue("sepal_width");
            headerRow.createCell(3).setCellValue("petal_length");
            headerRow.createCell(4).setCellValue("petal_width");
            headerRow.createCell(5).setCellValue("prediction");
            // Injection des lignes dans le fichier Excel :
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
            // Chemin du Fichier Excel :
            String filePath = "/Users/sjezequel/Desktop/Predictions_classification_iris";
            // Écriture des données dans le fichier :
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            }
            workbook.close();
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }



    @Override
    public boolean generateExcelForDataset() throws IOException {
        try {
            // Création du fichier et de la feuille Excel :
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("iris-new-dataset");
            // Création de la ligne d'en-tête du fichier Excel :
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("sepal length");
            headerRow.createCell(1).setCellValue("sepal width");
            headerRow.createCell(2).setCellValue("petal length");
            headerRow.createCell(3).setCellValue("petal width");
            headerRow.createCell(4).setCellValue("prediction");
            // Chemin du Fichier Excel :
            String filePath = "/Users/sjezequel/Desktop/Iris_new_dataset_excel";
            // Écriture de la ligne d'en-tête dans le fichier :
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            }
            workbook.close();
            logger.info("Fichier Excel avec succès.");
            return true;
        } catch (RuntimeException e) {
            logger.warning("Erreur : " + e);
            return false;
        }
    }



    @Override
    public boolean importExcelIrisDataSetFile(MultipartFile file) {
        // Création du dataSet :
        List<IrisModelResponse> irisDataSet = new ArrayList<>();
        try {
            // Conversion du fichier et en fichier Excel :
            Workbook workbook = WorkbookFactory.create(file.getInputStream());
            // Récupération de la feuille Excel :
            Sheet dataSheet = workbook.getSheetAt(0);
            // Parcourt de la feuille Excel :
            for (int rowIndex = 1; rowIndex <= dataSheet.getLastRowNum(); rowIndex++) {
                Row row = dataSheet.getRow(rowIndex);
                if (row != null) {
                    // Récupération des valeurs de chaque cellule :
                    double sepalLength = Double.parseDouble(row.getCell(0).getStringCellValue());
                    double sepalWidth = Double.parseDouble(row.getCell(1).getStringCellValue());
                    double petalLength = Double.parseDouble(row.getCell(2).getStringCellValue());
                    double petalWidth = Double.parseDouble(row.getCell(3).getStringCellValue());
                    String prediction = row.getCell(4).getStringCellValue();
                    // Création de chaque ligne du dataSet :
                    IrisModelResponse dataLine = new IrisModelResponse();
                    dataLine.setSepalLength(sepalLength);
                    dataLine.setSepalWidth(sepalWidth);
                    dataLine.setPetalLength(petalLength);
                    dataLine.setPetalWidth(petalWidth);
                    dataLine.setPrediction(prediction);
                    // Ajout de chaque ligne dans le dataSet :
                    irisDataSet.add(dataLine);
                }
            }
            // Chargement du dataSet dans le modèle :
            loadAndTrainModel(irisDataSet);
            logger.info("Données Excel intégrées avec succès.");
            return true;
        } catch (IOException e) {
            logger.warning("Erreur : " + e);
            return false;
        }
    }



    @Override
    public boolean generateCsvForDataset() throws IOException {
        try {
            // Chemin du fichier de template Csv  :
            String filePath = "/Users/sjezequel/Desktop/iris_new_dataset_csv";
            // Création du fichier de template Csv :
            CSVWriter writer = new CSVWriter(new FileWriter(filePath));
            // Injection de la ligne d'en-tête dans le fichier :
            String[] entete = {"sepal length", "sepal width", "petal length", "petal width", "prediction"};
            writer.writeNext(entete);
            writer.close();
            logger.info("Fichier Csv généré avec succès.");
            return true;
        } catch (IOException e) {
            logger.warning("Erreur : " + e);
            return false;
        }
    }



    @Override
    public boolean importCsvIrisDataSetFile(MultipartFile file) {
        try {
            // Stockage du fichier en mémoire tampon :
            BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            // Lecture des données Csv :
            CSVReader csvReader = new CSVReader(reader);
            // Ligne du fichier Csv :
            String[] ligne;
            // DataSet à charger :
            List<IrisModelResponse> irisDataSet = new ArrayList<>();
            // Saut de la ligne d'en-tête :
            csvReader.readNext();
            // Lecture du fichier ligne par ligne :
            while ((ligne = csvReader.readNext()) != null) {
                // Création d'une ligne :
                IrisModelResponse dataLine = new IrisModelResponse();
                dataLine.setSepalLength(Double.parseDouble(ligne[0]));
                dataLine.setSepalWidth(Double.parseDouble(ligne[1]));
                dataLine.setPetalLength(Double.parseDouble(ligne[2]));
                dataLine.setPetalWidth(Double.parseDouble(ligne[3]));
                dataLine.setPrediction(ligne[4]);
                // Ajout de la ligne dans le dataSet :
                irisDataSet.add(dataLine);
            }
            loadAndTrainModel(irisDataSet);
            logger.info("Données Csv intégrées avec succès.");
            return true;
        } catch (IOException | CsvValidationException e) {
            logger.warning("Erreur : " + e);
            return false;
        }
    }



    @Override
    public String loadAndTrainModel(List<IrisModelResponse> irisDataSet) {
        // Mise en forme du dataSet :
        List<Map<String, Object>> loadData = new ArrayList<>();
        for (IrisModelResponse dataLine : irisDataSet) {
            Map<String, Object> lineData = new HashMap<>();
            lineData.put("sepalLength", dataLine.getSepalLength());
            lineData.put("sepalWidth", dataLine.getSepalWidth());
            lineData.put("petalLength", dataLine.getPetalLength());
            lineData.put("petalWidth", dataLine.getPetalWidth());
            lineData.put("prediction", dataLine.getPrediction());
            loadData.add(lineData);
        }
        Map<String, Object> modelDataSet = new HashMap<>();
        modelDataSet.put("data_lines", loadData);
        // Chargement des données dans le modèle :
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(modelDataSet, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(loadUserPredictionsInModelIrisApiUrl, httpEntity, String.class);
        // Récupération de la réponse :
        return responseEntity.getBody();
    }



    @Override
    public boolean generateIrisDataSetExcel() {
        try{
            // Récupération du dataSet :
            List<IrisModelResponse> irisDataSet = getIrisDataSet();
            // Création du fichier et de la feuille Excel :
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("DataSet");
            // Création de la ligne d'en-tête :
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("sepal_length");
            headerRow.createCell(1).setCellValue("sepal_width");
            headerRow.createCell(2).setCellValue("petal_length");
            headerRow.createCell(3).setCellValue("petal_width");
            headerRow.createCell(4).setCellValue("prediction");
            // Chargement de chaque ligne du dataSet :
            int rowNum = 1;
            for (IrisModelResponse irisModelResponse : irisDataSet) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(irisModelResponse.getSepalLength());
                row.createCell(1).setCellValue(irisModelResponse.getSepalWidth());
                row.createCell(2).setCellValue(irisModelResponse.getPetalLength());
                row.createCell(3).setCellValue(irisModelResponse.getPetalWidth());
                row.createCell(4).setCellValue(irisModelResponse.getPrediction());
            }
            // Chemin du fichier :
            String filePath = "/Users/sjezequel/Desktop/iris_dataset";
            // Écriture des données dans le fichier :
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
                workbook.close();
                return true;
        } catch (RuntimeException | IOException e) {
            return false;
        }
    }



    @Override
    public List<IrisModelResponse> getIrisDataSet() {
        try {
            // En-tête de la requête Http :
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            // Contenu de la requête Http :
            HttpEntity<Void> httpEntity = new HttpEntity<>(headers);
            // Exécution de la requête :
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    getIrisModelDataSet,
                    HttpMethod.GET,
                    httpEntity,
                    String.class
            );
            // Récupération de la réponse :
            ObjectMapper objectMapper = new ObjectMapper();
            List<IrisModelResponse> irisDataSet = objectMapper.readValue(responseEntity.getBody(), IrisModelDataSet.class).getDataLines();
            logger.info("DataSet récupéré avec succès.");
            return irisDataSet;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }






}

