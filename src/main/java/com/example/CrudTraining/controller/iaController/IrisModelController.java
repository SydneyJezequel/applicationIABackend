package com.example.CrudTraining.controller.iaController;

import com.example.CrudTraining.bo.ia.randomForestmodel.IrisModelResponse;
import com.example.CrudTraining.service.iaService.IrisModelService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.CrudTraining.bo.ia.randomForestmodel.IrisModelRequest;
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
     * Méthode qui initialise le modèle de Machine Learning Random Forest.
     * Par défaut, ce modèle est utilisé sur le dataset de classification du type d'Iris.
     * @return boolean : succès/échec de l'exécution.
     *
     */
    @GetMapping("/load-predict-in-model")
    public boolean initializeModelPrediction() {
        return irisModelService.initializeModelPrediction();
    }



    /**
     * Méthode qui exécute le modèle de machine Learning Random Forest.
     * Par défaut, ce modèle est utilisé pour prédire du type d'Iris
     * @param IrisModelRequest request : Paramètres du modèle.
     * @return Message de succès.
     *
     */
    @PostMapping("/predict")
    public String getIrisModelPrediction(@RequestBody IrisModelRequest request) {
        String prediction = irisModelService.getIrisModelPrediction(request);
        return prediction;
    }



    /**
     * Méthode qui sauvegarde les prédictions du modèle.
     * Par défaut, le modèle Random Forest est utilisé sur le dataset de classification du type d'Iris.
     * @param IrisModelResponse : Paramètres passés aux modèles et prédiction réalisée.
     * @return IrisModelResponse : Paramètres passés aux modèles et prédiction réalisée.
     *
     */
    @PostMapping("/save-predict")
    public IrisModelResponse saveIrisModelPrediction(@RequestBody IrisModelResponse irisModelResponse) throws JsonProcessingException {
        return irisModelService.saveIrisModelPrediction(irisModelResponse);
    }



    /**
     * Méthode qui renvoie la liste des prédictions générées par le modèle.
     * Par défaut, le modèle Random Forest est utilisé sur le dataset de classification du type d'Iris.
     * @return List<IrisModelResponse> : La liste des prédictions.
     *
     */
    @GetMapping("/all-predict")
    public List<IrisModelResponse> getAllIrisModelPrediction() {
        return irisModelService.getAllIrisModelPrediction();
    }



    /**
     * Méthode qui génère un fichier Excel contenant les prédictions du modèle.
     * Par défaut, le modèle Random Forest est utilisé sur le dataset de classification du type d'Iris.
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
     * Méthode qui génère un fichier Csv qui contient les prédictions du modèle.
     * Par défaut, le modèle Random Forest est utilisé sur le dataset de classification du type d'Iris.
     * @return boolean : succès/échec de l'exécution.
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



    /**
     * Méthode qui génère le fichier Excel de chargement du dataSet d'entrainement.
     * Par défaut, le modèle Random Forest est utilisé sur le dataset de classification du type d'Iris.
     * @return boolean : succès/échec de l'exécution.
     */
    @GetMapping("/generate-template-excel-dataset")
    public boolean generateExcelForDataset() throws IOException {
        return irisModelService.generateExcelForDataset();
    }



    /**
     * Méthode qui charge le dataSet d'entrainement du modèle au format Excel.
     * Par défaut, le modèle Random Forest est utilisé sur le dataset de classification du type d'Iris.
     * @param MultipartFile file : Fichier Excel contenant le dataSet d'entrainement.
     * @return boolean : succès/échec de l'exécution.
     *
     */
    @PostMapping("/load-dataset-excel")
    public boolean importExcelIrisDataSetFile(MultipartFile file){
        return irisModelService.importExcelIrisDataSetFile(file);
    }



    /**
     * Méthode qui génère le fichier csv de chargement du dataSet d'entrainement.
     * Par défaut, le modèle Random Forest est utilisé sur le dataset de classification du type d'Iris.
     * @return boolean : succès/échec de l'exécution.
     *
     */
    @GetMapping("/generate-template-csv-dataset")
    public boolean generateCsvForDataset()  throws IOException {
        return irisModelService.generateCsvForDataset();
    }



    /**
     * Méthode qui charge le dataSet d'entrainement du modèle au format Csv.
     * Par défaut, le modèle Random Forest est utilisé sur le dataset de classification du type d'Iris.
     * @param MultipartFile file : Fichier Csv contenant le dataSet d'entrainement.
     * @return boolean : succès/échec de l'exécution.
     *
     */
    @PostMapping("/load-dataset-csv")
    public boolean importCsvIrisDataSetFile(MultipartFile file){
        return irisModelService.importCsvIrisDataSetFile(file);
    }



    /**
     * Méthode qui génère le fichier Excel contenant les données du DataSet Iris.
     *
     */
    @GetMapping("/generate-iris-dataset-excel")
    public boolean generateIrisDataSetExcel() {
        return irisModelService.generateIrisDataSetExcel();
    }






}

