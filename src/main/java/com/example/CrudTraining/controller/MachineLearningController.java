package com.example.CrudTraining.controller;

import com.example.CrudTraining.bo.IrisModelResponse;
import com.example.CrudTraining.service.IaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.CrudTraining.bo.IrisModelRequest;
import java.util.List;






@RestController
@RequestMapping("/api/ia")
public class MachineLearningController {





    // *************************** Attributs *************************** //
    private final IaService iaService;





    // *************************** Constructeur *************************** //
    @Autowired
    public MachineLearningController(IaService iaService) { this.iaService = iaService; }





    // *************************** Méthodes *************************** //

    /**
     * Méthode qui interroge le modèle de machine Learning de prédiction du type d'Iris
     * @param IrisModelRequest request : Paramètres du modèle de Machine Learning envoyé depuis le Front.
     * @return Message de succès.
     *
     */
    @PostMapping("/iris/predict")
    public String getIrisModelPrediction(@RequestBody IrisModelRequest request) {
        String prediction = iaService.getIrisModelPrediction(request);
        return prediction;
    }



    /**
     * Méthode qui sauvegarde les prédictions du modèle d'Iris
     * @param IrisModelRequest request : Paramètres du modèle de Machine Learning envoyé depuis le Front.
     * @return Message de succès.
     *
     */
    @PostMapping("/iris/save-predict")
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

        return iaService.saveIrisModelPrediction(irisModelResponse);
    }



    /**
     * Méthode qui renvoie la liste des prédictions générées par le modèle de Machine Learning Iris.
     * @return La liste des prédictions calculées par les utilisateurs.
     *
     */
    @GetMapping("/iris/all-predict")
    public List<IrisModelResponse> getAllIrisModelPrediction() {
        return iaService.getAllIrisModelPrediction();
    }



    // *********************************** TEST *********************************** //
    // *********************************** TEST *********************************** //
    // *********************************** TEST *********************************** //
    /**
     * Méthode qui charge la liste des prédictions du modèle de Machine Learning Iris pour l'entrainer.
     * @return String : Message de succès.
     *
     */
    @GetMapping("/iris/load-predict-in-model")
    public String loadUsersPredictionsInModel() {
        iaService.loadUsersPredictionsInModel();
        return "Le modèle a été entrainé avec les données";
    }
    // *********************************** TEST *********************************** //
    // *********************************** TEST *********************************** //
    // *********************************** TEST *********************************** //



    /**
     * Méthode du controller qui envoie la requête du front à chatGpt et Récupère la réponse.
     * @param prompt : Contenu de la requête.
     * @return String de la réponse.
     *
     */
    @PostMapping("/chat-gpt")
    public String chat(@RequestBody String prompt){
        return iaService.chat(prompt);
    }





}

