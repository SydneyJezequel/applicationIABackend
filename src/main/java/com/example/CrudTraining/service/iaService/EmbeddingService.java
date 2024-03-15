package com.example.CrudTraining.service.iaService;

import com.example.CrudTraining.bo.ia.embeddingModel.QuestionInput;
import com.example.CrudTraining.bo.ia.embeddingModel.SelectCategoryDataSet;
import com.example.CrudTraining.bo.ia.embeddingModel.SelectDataSet;
import org.springframework.web.multipart.MultipartFile;






/**
 * Service pour manipuler le modèle d'Embedding.
 *
 */
public interface EmbeddingService {



    /**
     * Méthode qui charge le dataSet d'entrainement du modèle au format .jsonl.
     * @param MultipartFile file : Fichier .jsonl contenant le dataSet d'entrainement.
     * @return boolean : succès/échec de l'exécution.
     *
     */
    public boolean importJsonlTemplateDataSetFile(MultipartFile file);



    /**
     * Méthode qui charge le dataset dans la BDD Vectorielle à partir du fichier .jsonl
     * @return boolean : succès/échec de l'exécution.
     *
     */
    public boolean loadDataset(SelectDataSet path);



    /**
     * Méthode qui sélectionne des données du dataset en fonction de leur catégorie.
     * @param String : catégorie.
     * @return boolean : succès/échec de l'exécution.
     *
     */
    public boolean selectCategory(SelectCategoryDataSet selectCategoryDataSet);



    /**
     * Méthode qui récupère une réponse à partir de la question posée.
     * @param QuestionInput : question.
     * @return String : réponse.
     *
     */
    public String getLlmEmbeddingAnswer(QuestionInput question);






}

