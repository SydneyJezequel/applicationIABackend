package com.example.CrudTraining.service.iaService;

import com.example.CrudTraining.bo.ia.irismodele.IrisModelRequest;
import com.example.CrudTraining.bo.ia.irismodele.IrisModelResponse;
import org.springframework.web.multipart.MultipartFile;
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
    public boolean initializeModelPrediction();



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



    /**
     * Méthode qui génère un fichier Excel pour intégrer le jeu de données qui entraine
     * le modèle de classification des Iris.
     * @return String : Message de succès et génère le fichier excel pour importer les données.
     *
     */
    public boolean generateExcelForDataset() throws IOException;



    /**
     * Méthode qui importe un jeu de données Excel pour entrainer
     * le modèle de classification des Iris.
     * @param MultipartFile file : Contient le jeu de données.
     * @return String : Message de succès.
     *
     */
    public boolean importExcelIrisDataSetFile(MultipartFile file);



    /**
     * Méthode qui génère un fichier Csv pour intégrer le jeu de données qui entraine
     * le modèle de classification des Iris.
     * @return String : Message de succès et génère le fichier excel pour importer les données.
     *
     */
    public boolean generateCsvForDataset() throws IOException;



    /**
     * Méthode qui importe un jeu de données sous format Csv pour entrainer
     * le modèle de classification des Iris.
     * @param MultipartFile file : Contient le jeu de données.
     * @return String : Message de succès.
     *
     */
    public boolean importCsvIrisDataSetFile(MultipartFile file);



    /**
     * Méthode charge un fichier de données Excel et entraine le modèle
     * de classification des Iris.
     *
     */
    public String loadAndTrainModel(List<IrisModelResponse> irisDataSet);



    /**
     * Méthode pour générer un fichier Excel qui
     * contient les données du DataSet Iris.
     *
     */
    public boolean generateIrisDataSetExcel();



    /**
     * Méthode pour récupérer les données du DataSet Iris
     * depuis le modèle.
     *
     */
    public List<IrisModelResponse> getIrisDataSet();






}

