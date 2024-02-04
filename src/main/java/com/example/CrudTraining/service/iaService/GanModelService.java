package com.example.CrudTraining.service.iaService;

import org.springframework.web.multipart.MultipartFile;






/**
 * Service pour manipuler le modèle de Génération d'image GAN.
 *
 */
public interface GanModelService {



    /**
     * Méthode pour déposer le fichier de paramètres du Générateur
     * dans le projet du modèle.
     * @param MultipartFile : parameterGenFile
     * @return booléen : Action réussie ou non.
     *
     */
    public boolean loadParametersGenFile(MultipartFile parameterGenFile);



    /**
     * Méthode pour générer des images avec le modèle GAN.
     *
     */
    public boolean generateImage();



    /**
     * Méthode pour générer des images avec le modèle GAN.
     *
     */
    public boolean trainGanModel(int nbEpochs, int batchSize, double lr, int zDim, String device, int showStep, int saveStep);





}

