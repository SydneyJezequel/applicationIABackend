package com.example.CrudTraining.service.iaService;

import com.example.CrudTraining.bo.ia.randomForestmodel.IrisModelRequest;
import com.example.CrudTraining.bo.ia.randomForestmodel.IrisModelResponse;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;






/**
 * Service pour manipuler le modèle Random Forest.
 *
 */
public interface IrisModelService {



    /**
     * Méthode qui initialise le modèle Random Forest.
     * Par défaut, le modèle est entrainé sur le dataSet de classement des Iris.
     * @return boolean : succès/échec de l'exécution.
     *
     */
    public boolean initializeModelPrediction();



    /**
     * Méthode qui génère une prédiction à partir du modèle Random Forest.
     * Par défaut, le modèle est entrainé et réalise des prédictions sur le dataSet de classement des Iris.
     * @return String : Le type d'Iris
     *
     */
    public String getIrisModelPrediction(IrisModelRequest request);



    /**
     * Méthode qui sauvegarde les prédictions du modèle Random Forest.
     * Par défaut, le modèle est entrainé et réalise des prédictions sur le dataSet de classement des Iris.
     * @return IrisModelResponse : Prédiction générée.
     *
     */
    public IrisModelResponse saveIrisModelPrediction(IrisModelResponse result);



    /**
     * Méthode qui renvoie la liste des prédictions générées par le modèle Random Forest.
     * Par défaut, le modèle est entrainé et réalise des prédictions sur le dataSet de classement des Iris.
     * @return List<IrisModelResponse> : Liste des prédictions.
     *
     */
    public List<IrisModelResponse> getAllIrisModelPrediction();



    /**
     * Méthode qui génère un fichier Excel contenant les prédictions du modèle Random Forest.
     * Par défaut, le modèle est entrainé et réalise des prédictions sur le dataSet de classement des Iris.
     * @return boolean : succès/échec de l'exécution.
     * @throws IOException
     *
     */
    public boolean generateExcel() throws IOException;



    /**
     * Méthode qui génère un fichier Csv contenant les prédictions du modèle Random Forest.
     * Par défaut, le modèle est entrainé et réalise des prédictions sur le dataSet de classement des Iris.
     * @return boolean : succès/échec de l'exécution.
     *
     */
    public boolean generateCsv();



    /**
     * Méthode qui génère un fichier Excel pour intégrer le dataSet d'entrainement.
     * @return boolean : succès/échec de l'exécution.
     *
     */
    public boolean generateExcelForDataset() throws IOException;



    /**
     * Méthode qui charge un dataSet d'entrainement au format Excel.
     * @param MultipartFile : DataSet.
     * @return boolean : succès/échec de l'exécution.
     *
     */
    public boolean importExcelIrisDataSetFile(MultipartFile file);



    /**
     * Méthode qui génère un fichier Csv pour intégrer le dataSet d'entrainement.
     * @return boolean : succès/échec de l'exécution.
     *
     */
    public boolean generateCsvForDataset() throws IOException;



    /**
     * Méthode qui charge un dataSet d'entrainement au format Csv.
     * @param MultipartFile : DataSet.
     * @return boolean : succès/échec de l'exécution.
     *
     */
    public boolean importCsvIrisDataSetFile(MultipartFile file);



    /**
     * Méthode qui charge les données et entraine le modèle.
     * Par défaut, le modèle est entrainé et réalise des prédictions sur le dataSet de classement des Iris.
     * @return String : Message de succès/échec de l'exécution.
     *
     */
    public String loadAndTrainModel(List<IrisModelResponse> irisDataSet);



    /**
     * Méthode qui génére un fichier Excel contenant le DataSet de classement des Iris.
     * @return boolean : succès/échec de l'exécution.
     *
     */
    public boolean generateIrisDataSetExcel();



    /**
     * Méthode qui renvoie le DataSet de classement des Iris depuis le modèle.
     * @return List<IrisModelResponse> : Lignes du dataSet.
     *
     */
    public List<IrisModelResponse> getIrisDataSet();






}

