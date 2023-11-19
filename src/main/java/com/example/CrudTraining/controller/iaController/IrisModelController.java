package com.example.CrudTraining.controller.iaController;

import com.example.CrudTraining.bo.IrisModelResponse;
import com.example.CrudTraining.service.iaService.IrisModelService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.CrudTraining.bo.IrisModelRequest;
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
    public String initializeModelPrediction() {
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



    // ********************************************** TEST ********************************************** //
    // ********************************************** TEST ********************************************** //
    // ********************************************** TEST ********************************************** //
    /**
     * Méthode qui charge la liste des prédictions du modèle de Machine Learning Iris pour l'entrainer.
     * @return String : Message de succès.
     *
     */
    @GetMapping("/load-predicts-in-model")
    public String loadUsersPredictionsInModel() {
        irisModelService.loadUsersPredictionsInModel();
        return "Le modèle a été entrainé avec les données";
    }
    // ********************************************** TEST ********************************************** //
    // ********************************************** TEST ********************************************** //
    // ********************************************** TEST ********************************************** //



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





}

