package com.example.CrudTraining.controller.iaController;

import com.example.CrudTraining.service.iaService.FaceRecognizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;






/**
 * Controller pour manipuler le modèle de reconnaissance faciale.
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





    // *************************** Méthodes pour charger les photos *************************** //

    /**
     * Méthode pour charger un fichier .zip contenant les photos d'entrainement du modèle.
     * @return boolean : succès/échec de l'exécution.
     *
     */
    @PostMapping("/process-training-set-file-image-zip")
    public boolean loadTrainingDataSetZip(@RequestParam("file") MultipartFile imageZip) {
        return faceRecognizerService.loadTrainingDataSetZip(imageZip);
    }



    /**
     * Méthode pour charger un fichier .zip contenant les photos de validation du modèle.
     * @return boolean : succès/échec de l'exécution.
     *
     */
    @PostMapping("/process-validation-set-file-image-zip")
    public boolean loadValidationDataSetZip(@RequestParam("file") MultipartFile imageZip) {
        return faceRecognizerService.loadValidationDataSetZip(imageZip);
    }



    /**
     * Méthode pour charger une photo à identifier.
     * @param MultipartFile : faceIdentifyFile
     * @return booléen : succès/échec de l'exécution.
     *
     */
    @PostMapping("/process-identify-face-image")
    public boolean loadFaceIdentifyFile(@RequestParam("file") MultipartFile faceIdentifyFile) {
        return faceRecognizerService.loadFaceIdentifyFile(faceIdentifyFile);
    }





    // *************************** Méthodes pour utiliser le modèle *************************** //

    /**
     * Méthode pour encoder les photos et entrainer le modèle.
     * @return boolean : succès/échec de l'exécution.
     *
     */
    @GetMapping("/recognize-face-training")
    public boolean trainFaceRecognizerModel() {
        return faceRecognizerService.trainFaceRecognizerModel();
    }



    /**
     * Méthode qui teste le modèle.
     * @return boolean : succès/échec de l'exécution.
     *
     */
    @GetMapping("/recognize-face-test")
    public boolean validateFaceRecognizerModel() {
        return faceRecognizerService.validateFaceRecognizerModel();
    }



    /**
     * Méthode qui exécute le modèle pour identifier un visage.
     * @return boolean : succès/échec de l'exécution.
     *
     */
    @GetMapping("/use-recognize-face")
    public boolean executeFaceRecognizerModel() {
        return faceRecognizerService.executeFaceRecognizerModel();
    }



    /**
     * Méthode qui initialise le modèle.
     * Le modèle de reconnaissance utilisé par défaut est le modèle "HOG".
     * @return boolean : succès/échec de l'exécution.
     *
     */
    @GetMapping("/initialize")
    public boolean initializeFaceRecognizerModel(){
        return faceRecognizerService.initializeFaceRecognizerModel();
    }



    /**
     * Méthode qui sélectionne le modèle de machine learning.
     * @return boolean : succès/échec de l'exécution.
     *
     */
    @PostMapping("/model-selection")
    public boolean selectModel(@RequestBody String selectedModel) {
        return faceRecognizerService.selectModel(selectedModel);
    }



    /**
     * Méthode qui renvoie la liste des modèles (HOG, CNN).
     * @return List<String> : liste des modèles.
     *
     */
    @GetMapping("/models-list")
    public List<String> getListModel() {
        return faceRecognizerService.getListModel();
    }






}

