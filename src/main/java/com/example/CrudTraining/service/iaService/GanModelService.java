package com.example.CrudTraining.service.iaService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;






/**
 * Service pour manipuler le modèle de Génération d'image GAN.
 *
 */
public interface GanModelService {



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

