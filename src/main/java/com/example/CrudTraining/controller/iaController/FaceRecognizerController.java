package com.example.CrudTraining.controller.iaController;

import com.example.CrudTraining.service.iaService.FaceRecognizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;






/**
 * Controller pour manipuler le modèle de Reconnaissance.
 *
 */
@RestController
@RequestMapping("/api/ia/face-recognizer")
public class FaceRecognizerController {






    // *************************** Attributs *************************** //
    private final FaceRecognizerService faceRecognizerService;






    // *************************** Constructeur *************************** //
    @Autowired
    public FaceRecognizerController(FaceRecognizerService faceRecognizerService) { this.faceRecognizerService = faceRecognizerService; }






    // *************************** Méthodes de chargement des images *************************** //

    /**
     * Méthode qui intègre un fichier .zip qui contient les images
     * d'entrainement du modèle de Machine Learning FaceRecognizer.
     * @return boolean
     *
     */
    @PostMapping("/process-training-set-file-image-zip")
    public boolean trainingSetController(@RequestParam("file") MultipartFile imageZip) {
        return faceRecognizerService.loadTrainingSetZip(imageZip);
    }



    /**
     * Méthode qui intègre un fichier .zip qui contient les images
     * de validation du modèle de Machine Learning FaceRecognizer.
     * @return boolean
     *
     */
    @PostMapping("/process-validation-set-file-image-zip")
    public boolean validationSetController(@RequestParam("file") MultipartFile imageZip) {
        return faceRecognizerService.loadValidationSetZip(imageZip);
    }



    /**
     * Méthode pour déposer une image à reconnaitre
     * dans le projet du modèle.
     * @param MultipartFile : faceIdentifyFile
     * @return booléen : Action réussie ou non.
     *
     */
    @PostMapping("/process-identify-face-image")
    public boolean faceIdentifyFileController(@RequestParam("file") MultipartFile faceIdentifyFile) {
        return faceRecognizerService.loadFaceIdentifyFile(faceIdentifyFile);
    }





    // *************************** Méthodes de manipulation du modèle *************************** //

    /**
     * Méthode qui intègre un fichier .zip qui contient les images
     * de validation du modèle de Machine Learning FaceRecognizer.
     * @return boolean
     *
     */
    @GetMapping("/recognize-face-training")
    public boolean encodageDesVisages() {
        return faceRecognizerService.encodageDesVisages();
    }



    /**
     * Méthode qui intègre un fichier .zip qui contient les images
     * de validation du modèle de Machine Learning FaceRecognizer.
     * @return boolean
     *
     */
    @GetMapping("/recognize-face-test")
    public boolean validationDuModel() {
        return faceRecognizerService.validationDuModel();
    }



    /**
     * Méthode qui intègre un fichier .zip qui contient les images
     * de validation du modèle de Machine Learning FaceRecognizer.
     * @return boolean
     *
     */
    @GetMapping("/use-recognize-face")
    public boolean reconnaissanceFaciale() {
        return faceRecognizerService.reconnaissanceFaciale();
    }



    /**
     * Méthode qui initialise le modèle de machine learning
     * "hog" comme modèle par défaut pour la reconnaissance faciale.
     * @return boolean : Opération réussie ou non.
     */
    @GetMapping("/initialize")
    public boolean initializeFaceRecognizerModel(){
        return faceRecognizerService.initializeFaceRecognizerModel();
    }



    /**
     * Méthode qui met à jour le modèle de machine learning
     * en BDD. Ce choix sera utilisé lors de l'exécution du modèle
     * de Machine Learning.
     * @return boolean : Opération réussie ou non.
     *
     */
    @PostMapping("/selection-modele")
    public boolean selectModel(@RequestBody String modeleSelectionne) {
        System.out.println(modeleSelectionne);
        return faceRecognizerService.selectModel(modeleSelectionne);
    }



    /**
     * Méthode qui renvoie la liste des modèles de
     * Machine Learning vers le front
     * pour le menu déroulant.
     *
     */
    @GetMapping("/liste-modele")
    public List<String> getListModel() {
        return faceRecognizerService.getListModele();
    }







}

