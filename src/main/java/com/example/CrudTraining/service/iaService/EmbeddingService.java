package com.example.CrudTraining.service.iaService;

import com.example.CrudTraining.bo.ia.embeddingModel.QuestionInput;
import com.example.CrudTraining.bo.ia.embeddingModel.SelectCategoryDataSet;
import com.example.CrudTraining.bo.ia.embeddingModel.SelectDataSet;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;






/**
 * Service pour manipuler le modèle d'Embedding.
 *
 */
public interface EmbeddingService {



    /**
     * Méthode qui génère le fichier Jsonl pour charger le DataSet dans la BDD Vectorielle.
     * Par défaut, le DataSet utilisé est celui du Camélia Yvon Jézéquel.
     * @return boolean : succès/échec de l'exécution.
     *
     */
    public boolean generateJsonlFileTemplateForDataset();



    /**
     * Méthode qui charge le dataSet d'entrainement du modèle au format .jsonl.
     * @param MultipartFile file : Fichier .jsonl contenant le dataSet d'entrainement.
     * @return boolean : succès/échec de l'exécution.
     *
     */
    public boolean importJsonlTemplateDataSetFile(MultipartFile file);



    /**
     * Méthode qui charge le dataset dans la BDD Vectorielle à partir du fichier .jsonl.
     * @return boolean : succès/échec de l'exécution.
     *
     */
    public boolean loadFileIntoDataset();



    /**
     * Méthode qui initialise le dataset par défaut dans la BDD Vectorielle.
     * Le DataSet par défaut est le DataSet : "camelia_yvon_jezequel_dataset.jsonl".
     * @return boolean : succès/échec de l'exécution.
     *
     */
    public boolean initializeDefaultDataset();



    /**
     * Méthode qui génère un fichier ".jsonl" contenant le dataset par défaut.
     * Le DataSet par défaut est le DataSet : "camelia_yvon_jezequel_dataset.jsonl".
     * @return boolean : succès/échec de l'exécution.
     *
     */
    public boolean generateJsonlFileDefaultDataset();



    /**
     * Méthode qui sélectionne des données du dataset en fonction de leur catégorie.
     * @param String : catégorie.
     * @return boolean : succès/échec de l'exécution.
     *
     */
    public boolean selectCategory(String  selectCategoryDataSet);



    /**
     * Méthode qui récupère une réponse à partir de la question posée.
     * @param QuestionInput : question.
     * @return String : réponse.
     *
     */
    public String getLlmEmbeddingAnswer(String question);



    /**
     * Méthode qui récupère la liste des catégories de données
     * contenu dans le DataSet d'Embedding.
     * @return  List<String> : liste des catégories.
     *
     */
    public List<String> getListDataCategories();






}

