package com.example.CrudTraining.service;

import com.example.CrudTraining.bo.IrisModelRequest;
import com.example.CrudTraining.bo.IrisModelResponse;
import java.util.List;






/**
 * Service pour appeler des modèles de Machines Learning
 *
 */
public interface IaService {



    /**
     * Méthode qui initialise le modèle de Machine Learning qui classe les Iris
     * @return String : Message de succès.
     *
     */
    public String initializeModelPrediction();



    /**
     * Méthode qui appelle le modèle de Machine Learning qui classe les Iris
     * @return Le type d'Iris
     *
     */
    public String getIrisModelPrediction(IrisModelRequest request);



    /**
     * Méthode qui sauvegarde les prédictions du modèle de Machine Learning Iris.
     * @return La liste des prédictions calculées par les utilisateurs.
     *
     */
    public IrisModelResponse saveIrisModelPrediction(IrisModelResponse result);



    /**
     * Méthode qui renvoie la liste des prédictions générées par le modèle de Machine Learning Iris.
     * @return La liste des prédictions calculées par les utilisateurs.
     *
     */
    public List<IrisModelResponse> getAllIrisModelPrediction();



    // *********************************** TEST *********************************** //
    // *********************************** TEST *********************************** //
    // *********************************** TEST *********************************** //
    public void loadUsersPredictionsInModel();
    // *********************************** TEST *********************************** //
    // *********************************** TEST *********************************** //
    // *********************************** TEST *********************************** //



    /**
     * Méthode qui envoie une requête à chatGpt et Récupère la réponse.
     * @param prompt : Contenu de la requête.
     * @return String de la réponse.
     *
     */
    public String chat(String prompt);





}

