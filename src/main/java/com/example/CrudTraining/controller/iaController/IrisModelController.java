package com.example.CrudTraining.controller.iaController;

import com.example.CrudTraining.bo.IrisModelResponse;
import com.example.CrudTraining.service.iaService.IrisModelService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.CrudTraining.bo.IrisModelRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;






/**
 * Controller pour manipuler le modèle de classification des Iris.
 *
 */
@RestController
@RequestMapping("/api/ia/iris")
public class IrisModelController {





    // *************************** Attributs *************************** //
    private final IrisModelService irisModelService;





    // *************************** Constructeur *************************** //
    @Autowired
    public IrisModelController(IrisModelService irisModelService) { this.irisModelService = irisModelService; }





    // *************************** Méthodes *************************** //

    /**
     * Méthode qui initialise le modèle de Machine Learning qui classe les Iris
     * @return String : Message de succès.
     *
     */
    @GetMapping("/load-predict-in-model")
    public boolean initializeModelPrediction() {
        return irisModelService.initializeModelPrediction();
    }



    /**
     * Méthode qui interroge le modèle de machine Learning de prédiction du type d'Iris
     * @param IrisModelRequest request : Paramètres du modèle de Machine Learning envoyé depuis le Front.
     * @return Message de succès.
     *
     */
    @PostMapping("/predict")
    public String getIrisModelPrediction(@RequestBody IrisModelRequest request) {
        String prediction = irisModelService.getIrisModelPrediction(request);
        return prediction;
    }



    /**
     * Méthode qui sauvegarde les prédictions du modèle d'Iris
     * @param IrisModelRequest request : Paramètres du modèle de Machine Learning envoyé depuis le Front.
     * @return Message de succès.
     *
     */
    @PostMapping("/save-predict")
    public IrisModelResponse saveIrisModelPrediction(@RequestBody IrisModelResponse irisModelResponse) throws JsonProcessingException {
        // ************************ TEST **************************** //
        System.out.println(
                        irisModelResponse.getPrediction() + " " +
                        irisModelResponse.getSepalLength() + " " +
                        irisModelResponse.getSepalWidth() + " " +
                        irisModelResponse.getPetalLength() + " " +
                        irisModelResponse.getPetalWidth()
        );
        // ************************ TEST **************************** //
        return irisModelService.saveIrisModelPrediction(irisModelResponse);
    }



    /**
     * Méthode qui renvoie la liste des prédictions générées par le modèle de Machine Learning Iris.
     * @return La liste des prédictions calculées par les utilisateurs.
     *
     */
    @GetMapping("/all-predict")
    public List<IrisModelResponse> getAllIrisModelPrediction() {
        return irisModelService.getAllIrisModelPrediction();
    }



    /**
     * Controller qui génère un fichier Excel qui contient les prédictions du modèle de classification des Iris.
     * @return boolean : succès/échec de l'exécution.
     *
     */
    @GetMapping("/generate-excel")
    public boolean generateExcelFile() {
        try {
            return irisModelService.generateExcel();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }



    /**
     * Controller qui génère un fichier Csv qui contient les prédictions du modèle de classification des Iris.
     * @return boolean : succès/échec de l'exécution
     *
     */
    @GetMapping("/generate-csv")
    public boolean generateCsvFile() {
        try {
            return irisModelService.generateCsv();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return false;
        }
    }











    // *********************************** TEST EXCEL *********************************** //
    // *********************************** TEST EXCEL *********************************** //
    // *********************************** TEST EXCEL *********************************** //
    /**
     * Controller qui génère un fichier Excel pour intégrer le jeu de données qui entraine
     * le modèle de classification des Iris.
     *
     * @return String : Message de succès et génère le fichier excel pour importer les données.
     */
    @GetMapping("/generate-template-excel-dataset")
    public boolean generateExcelForDataset() throws IOException {
        return irisModelService.generateExcelForDataset();
    }



    /**
     * Controller qui importe un jeu de données pour entrainer
     * le modèle de classification des Iris.
     * @param MultipartFile file : Fichier Excel qui contient le jeu de données.
     * @return String : Message de succès.
     *
     */
    @PostMapping("/load-dataset-excel")
    public boolean importExcelIrisDataSetFile(MultipartFile file){
        return irisModelService.importExcelIrisDataSetFile(file);
    }
    // *********************************** TEST EXCEL *********************************** //
    // *********************************** TEST EXCEL *********************************** //
    // *********************************** TEST EXCEL *********************************** //










    // *********************************** TEST CSV *********************************** //
    // *********************************** TEST CSV *********************************** //
    // *********************************** TEST CSV *********************************** //
    /**
     * Controller qui génère un fichier Csv pour intégrer le jeu de données qui entraine
     * le modèle de classification des Iris.
     * @return String : Message de succès et génère le fichier excel pour importer les données.
     *
     */
    @GetMapping("/generate-template-csv-dataset")
    public boolean generateCsvForDataset()  throws IOException {
        return irisModelService.generateCsvForDataset();
    }



    /**
     * Controller qui importe un jeu de données Csv pour entrainer
     * le modèle de classification des Iris.
     * @param MultipartFile file : Fichier Csv qui contient le jeu de données.
     * @return String : Message de succès.
     *
     */
    @PostMapping("/load-dataset-csv")
    public boolean importCsvIrisDataSetFile(MultipartFile file){
        return irisModelService.importCsvIrisDataSetFile(file);
    }
    // *********************************** TEST CSV *********************************** //
    // *********************************** TEST CSV *********************************** //
    // *********************************** TEST CSV *********************************** //












}

