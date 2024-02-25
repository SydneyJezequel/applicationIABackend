package com.example.CrudTraining.controller.iaController;

import com.example.CrudTraining.service.iaService.GanModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;






/**
 * Controller pour manipuler le modèle de Génération d'image.
 *
 */
@RestController
@RequestMapping("/api/ia/gan-image-generation")
public class GanModelController {





    // *************************** Attributs *************************** //
    private final GanModelService ganModelService;





    // *************************** Constructeur *************************** //
    @Autowired
    public GanModelController(GanModelService ganModelService) { this.ganModelService = ganModelService; }





    // ************************************ Méthodes ************************************ //

    /**
     * Méthode pour charger le fichier de paramètres du modèle GAN.
     * @param MultipartFile parameterGenFile : Fichier de paramètres du Générateur.
     * @return booléen : succès/échec de l'exécution.
     *
     */
    @PostMapping("/process-parameters-gen-file")
    public boolean parametersGenFileController(@RequestParam("file") MultipartFile parameterGenFile) {
        return ganModelService.loadParametersGenFile(parameterGenFile);
    }



    /**
     * Méthode pour générer des images avec le modèle GAN.
     * @return booléen : succès/échec de l'exécution.
     *
     */
    @GetMapping("/generate-faces")
    public boolean generateImage(){
        return ganModelService.generateImage();
    }



    /**
     * Méthode pour entrainer le modèle GAN.
     * @return booléen : succès/échec de l'exécution.
     *
     */
    @PostMapping("/train-gan-model")
    public boolean trainGanModel(@RequestParam int nbEpochs, @RequestParam int batchSize, @RequestParam double lr, @RequestParam int zDim, @RequestParam String device, @RequestParam int showStep, @RequestParam int saveStep){
        return ganModelService.trainGanModel(nbEpochs, batchSize, lr, zDim, device, showStep, saveStep);
    }






}

