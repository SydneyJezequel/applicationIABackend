package com.example.CrudTraining.controller.iaController;

import com.example.CrudTraining.service.iaService.FaceRecognizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;


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
    // @PostMapping("/encode-known-faces")
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
    // @PostMapping("/validate")
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
    //@PostMapping("/recognize-face")
    @GetMapping("/use-recognize-face")
    public boolean reconnaissanceFaciale() {
        return faceRecognizerService.reconnaissanceFaciale();
    }























































































































    /**
     * Méthode qui XXXXXXXXXX
     * @return
     *
     */
    @GetMapping("/test-1")
    public String test1() {
        return faceRecognizerService.test1();
    }



    /**
     * Méthode qui XXXXXXXXXX
     * @return
     *
     */
    @PostMapping("/test-3")
    public boolean test3(@RequestBody String test3) {
        String test = faceRecognizerService.test3(test3);
        System.out.println(test);
        return true;
    }




}

