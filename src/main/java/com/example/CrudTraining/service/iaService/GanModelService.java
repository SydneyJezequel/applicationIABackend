package com.example.CrudTraining.service.iaService;

import org.springframework.web.multipart.MultipartFile;






/**
 * Service pour manipuler le modèle de Génération d'image GAN.
 *
 */
public interface GanModelService {



    /**
     * Méthode pour charger le fichier de paramètres du Générateur.
     * @param MultipartFile : Fichier de paramètres du Générateur.
     * @return boolean : succès/échec de l'exécution.
     *
     */
    public boolean loadParametersGenFile(MultipartFile parameterGenFile);



    /**
     * Méthode pour générer des images avec le modèle GAN.
     * @return boolean : succès/échec de l'exécution.
     *
     */
    public boolean generateImage();



    /**
     * Méthode pour entrainer le modèle GAN.
     * @param nbEpochs : Nombre d'itération sur l'ensemble du jeu de test.
     * @param batchSize : Taille des lots d'images.
     * @param lr : Taux d'apprentissage.
     * @param zDim : Dimensions de l'Espace Latent.
     * @param device : Hardware utilisé.
     * @param showStep : Etapes affichées.
     * @param saveStep : Etapes sauvegardées.
     * @return boolean : succès/échec de l'exécution.
     *
     */
    public boolean trainGanModel(int nbEpochs, int batchSize, double lr, int zDim, String device, int showStep, int saveStep);






}

