package com.example.CrudTraining.controller.iaController;

import com.example.CrudTraining.bo.ia.embeddingModel.QuestionInput;
import com.example.CrudTraining.bo.ia.embeddingModel.SelectCategoryDataSet;
import com.example.CrudTraining.bo.ia.embeddingModel.SelectDataSet;
import com.example.CrudTraining.service.iaService.EmbeddingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;






/**
 * Controller pour manipuler le modèle LLM et l'em.
 *
 */
@RestController
@RequestMapping("/api/ia/embedding")
public class EmbeddingController {





    // *************************** Attributs *************************** //
    private final EmbeddingService embeddingService;





    // *************************** Constructeur *************************** //
    @Autowired
    public EmbeddingController(EmbeddingService embeddingService) { this.embeddingService = embeddingService; }





    // *************************** Méthodes *************************** //


    /**
     * Méthode qui génère le fichier Jsonl pour charger le DataSet dans la BDD Vectorielle.
     * Par défaut, le DataSet utilisé est celui du Camélia Yvon Jézéquel.
     * @return boolean : succès/échec de l'exécution.
     *
     */
    @GetMapping("/generate-template-jsonl-dataset")
    public boolean generateJsonlFileTemplateForDataset() {
        return embeddingService.generateJsonlFileTemplateForDataset();
    }



    /**
     * Méthode qui charge le dataSet d'entrainement du modèle au format .jsonl.
     * @param MultipartFile file : Fichier .jsonl contenant le dataSet d'entrainement.
     * @return boolean : succès/échec de l'exécution.
     *
     */
    @PostMapping("/process-jsonl-dataset")
    public boolean importJsonlTemplateDataSetFile(@RequestParam("file") MultipartFile file){
        return embeddingService.importJsonlTemplateDataSetFile(file);
    }



    /**
     * Méthode qui initialise le dataset par défaut dans la BDD Vectorielle.
     * Le DataSet par défaut est le DataSet : "camelia_yvon_jezequel_dataset.jsonl".
     * @return boolean : succès/échec de l'exécution.
     *
     */
    @GetMapping("/load-default-dataset")
    public boolean initializeDefaultDataset(){
        return embeddingService.initializeDefaultDataset();
    }



    /**
     * Méthode qui génère un fichier ".jsonl" contenant le dataset par défaut.
     * Le DataSet par défaut est le DataSet : "camelia_yvon_jezequel_dataset.jsonl".
     * @return boolean : succès/échec de l'exécution.
     *
     */
    @GetMapping("/generate-jsonl-default-dataset")
    public boolean generateJsonlFileDefaultDataset(){
        return embeddingService.generateJsonlFileDefaultDataset();
    }



    /**
     * Méthode qui charge le dataset dans la BDD Vectorielle à partir du fichier .jsonl.
     * @param SelectDataSet : filePath
     * @return boolean : succès/échec de l'exécution.
     *
     */
    @GetMapping("/load-dataset")
    public boolean loadFileIntoDataset(){
        return embeddingService.loadFileIntoDataset();
    }



    /**
     * Méthode qui sélectionne des données du dataset en fonction de leur catégorie.
     * @param SelectCategoryDataSet : catégorie.
     * @return boolean : succès/échec de l'exécution.
     *
     */
    @PostMapping("/select-category")
    public boolean selectCategory(@RequestBody String selectCategoryDataSet){
        return embeddingService.selectCategory(selectCategoryDataSet);
    }



    /**
     * Méthode qui récupère la liste des catégories de données
     * contenu dans le DataSet d'Embedding.
     * @return  List<String> : liste des catégories.
     *
     */
    @GetMapping("/get-categories-list")
    public List<String> getListDataCategories(){
        return embeddingService.getListDataCategories();
    }



    /**
     * Méthode qui récupère une réponse à partir de la question posée.
     * @param QuestionInput : question.
     * @return String : réponse.
     *
     */
    @PostMapping("/get-llm-embedding-answer")
    public String getLlmEmbeddingAnswer(@RequestBody String question){
        return embeddingService.getLlmEmbeddingAnswer(question);
    }






}

