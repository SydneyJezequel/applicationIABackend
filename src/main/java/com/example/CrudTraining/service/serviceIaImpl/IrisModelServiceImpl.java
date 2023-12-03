package com.example.CrudTraining.service.serviceIaImpl;

import com.example.CrudTraining.bo.IrisModelRequest;
import com.example.CrudTraining.bo.IrisModelResponse;
import com.example.CrudTraining.repository.IrisModelRepository;
import com.example.CrudTraining.service.iaService.IrisModelService;
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
    public boolean initializeModelPrediction(){
        try {
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
            logger.info(messageSucces);
            return true;
        }catch (RuntimeException e){
            logger.info(e.toString());
            return false;
        }
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



    @Override
    public boolean generateExcelForDataset() throws IOException {
        try {
            // Création du fichier Excel et la feuille qui contient les données :
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("iris-new-dataset");
            // Création ligne d'en-tête du fichier Excel :
            Row headerRow = sheet.createRow(0); // La nouvelle ligne fait partie de la feuille du fichier Excel.
            headerRow.createCell(0).setCellValue("sepal length");
            headerRow.createCell(1).setCellValue("sepal width");
            headerRow.createCell(2).setCellValue("petal length");
            headerRow.createCell(3).setCellValue("petal width");
            headerRow.createCell(4).setCellValue("prediction");
            // Fichier Excel ou sont chargées les données :
            String filePath = "/Users/sjezequel/Desktop/Iris_new_dataset_excel";
            // Écriture des données dans le fichier Excel :
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            }
            workbook.close();
            logger.info("Fichier Excel avec succès.");
            return true;
        } catch (RuntimeException e)
        {
            logger.warning("Erreur : " + e);
            return false;
        }
    }



    @Override
    public boolean importExcelIrisDataSetFile(MultipartFile file) {
        // DataSet à charger dans le modèle :
        List<IrisModelResponse> irisDataSet = new ArrayList<>();
        try {
            Workbook workbook = WorkbookFactory.create(file.getInputStream()); // Conversion du fichier en workbook.
            Sheet dataSheet = workbook.getSheetAt(0); // Récupération de la feuille 1.
            for (int rowIndex = 1; rowIndex <= dataSheet.getLastRowNum(); rowIndex++) { // On parcourt le fichier.
                Row row = dataSheet.getRow(rowIndex);
                if (row != null) {
                    // Récupération des valeurs de la cellule :
                    double sepalLength = Double.parseDouble(row.getCell(0).getStringCellValue());
                    double sepalWidth = Double.parseDouble(row.getCell(1).getStringCellValue());
                    double petalLength = Double.parseDouble(row.getCell(2).getStringCellValue());
                    double petalWidth = Double.parseDouble(row.getCell(3).getStringCellValue());
                    String prediction = row.getCell(4).getStringCellValue();
                    // Création de la ligne du dataset :
                    IrisModelResponse dataLine = new IrisModelResponse();
                    dataLine.setSepalLength(sepalLength);
                    dataLine.setSepalWidth(sepalWidth);
                    dataLine.setPetalLength(petalLength);
                    dataLine.setPetalWidth(petalWidth);
                    dataLine.setPrediction(prediction);
                    // Ajout de la ligne dans le dataSet :
                    irisDataSet.add(dataLine);
                }
            }
            // Appelle de l'Api pour charger les données dans le modèle de classification des Iris :
            loadAndTrainModel(irisDataSet);
            logger.info("Données Excel intégrées avec succès.");
            return true;
        }
        catch (IOException e) { // Gestion des erreurs :
            logger.warning("Erreur : " + e);
            return false;
        }
    }



    @Override
    public boolean generateCsvForDataset() throws IOException{
        try {
            // Path du fichier template CSV  :
            String filePath = "/Users/sjezequel/Desktop/iris_new_dataset_csv";
            // Création du fichier Csv :
            CSVWriter writer = new CSVWriter(new FileWriter(filePath));
            // Injection de l'en-tête dans le fichier :
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
    public boolean importCsvIrisDataSetFile(MultipartFile file){
        try {
            // Lecture des données du fichier et stockage en mémoire tampon :
            BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            // Objet pour lire les données csv du Buffer :
            CSVReader csvReader = new CSVReader(reader);
            // Ligne du fichier CSV :
            String[] ligne;
            // DataSet à charger dans le modèle :
            List<IrisModelResponse> irisDataSet = new ArrayList<>();
            // Saut de la ligne d'en-tête :
            csvReader.readNext();
            // Lecture du fichier ligne par ligne :
            while ((ligne = csvReader.readNext()) != null) { // S'il y a une ligne dans le fichier, on l'affecte à ligne.
                // Création d'un objet Personne :
                IrisModelResponse dataLine = new IrisModelResponse();
                dataLine.setSepalLength(Double.parseDouble(ligne[0])); // Sepal length
                dataLine.setSepalWidth(Double.parseDouble(ligne[1])); // Sepal width
                dataLine.setPetalLength(Double.parseDouble(ligne[2])); // Petal length
                dataLine.setPetalWidth(Double.parseDouble(ligne[3])); // Petal width
                dataLine.setPrediction(ligne[4]); // prediction
                // Ajout de la ligne dans le dataSet :
                irisDataSet.add(dataLine);
            }
            loadAndTrainModel(irisDataSet);
            logger.info("Données Csv intégrées avec succès.");
            return true;
        } catch (IOException | CsvValidationException e ) {
            logger.warning("Erreur : " + e);
            return false;
        }
    }



    @Override
    public String loadAndTrainModel(List<IrisModelResponse> irisDataSet){
        // Conversion des données en Mapper :
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
        // Envoi des données dans le modèle de machine Learning Iris :
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(modelDataSet, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(loadUserPredictionsInModelIrisApiUrl, httpEntity, String.class);
        // Renvoie du message généré par l'Api :
        return responseEntity.getBody();
    }





}

