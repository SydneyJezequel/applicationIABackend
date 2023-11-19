package com.example.CrudTraining.service.iaService;

import com.example.CrudTraining.bo.IrisModelRequest;
import com.example.CrudTraining.bo.IrisModelResponse;
import java.io.IOException;
import java.util.List;






/**
 * Service pour appeler des modèles de Machines Learning
 *
 */
public interface IrisModelService {



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
    /**
     * Méthode qui entraine le modèle de machine learning avec les prédictions générées.
     *
     */
    public void loadUsersPredictionsInModel();
    // *********************************** TEST *********************************** //
    // *********************************** TEST *********************************** //
    // *********************************** TEST *********************************** //



    /**
     * Méthode qui génère un fichier Excel qui contient les prédictions du modèle de classification des Iris.
     * @return boolean : succès/échec de l'exécution.
     * @throws IOException
     *
     */
    public boolean generateExcel() throws IOException;



    /**
     * Méthode qui génère un fichier Csv qui contient les prédictions du modèle de classification des Iris.
     * @return boolean : succès/échec de l'exécution.
     *
     */
    public boolean generateCsv();





}

