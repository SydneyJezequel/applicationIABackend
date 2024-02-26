package com.example.CrudTraining.service.iaService;

import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.util.List;
import java.util.zip.ZipInputStream;






/**
 * Service pour manipuler le modèle de Reconnaissance Faciale.
 *
 */
public interface FaceRecognizerService {





    // ************************** Méthodes de chargement des images ************************** //

    /**
     * Méthode pour charger le dossier contenant les images d'entrainement.
     * @param MultipartFile : Dossier contenant la liste des images.
     * @return boolean : succès/échec de l'exécution.
     *
     */
    public boolean loadTrainingSetZip(MultipartFile imageZip);



    /**
     * Méthode pour charger le dossier contenant les images de validation.
     * @param MultipartFile : Dossier contenant la liste des images.
     * @return boolean : succès/échec de l'exécution.
     *
     */
    public boolean loadValidationSetZip(MultipartFile imageZip);



    /**
     * Méthode pour charger une image à identifier.
     * @param MultipartFile : Image à identifier.
     * @return boolean : succès/échec de l'exécution.
     *
     */
    public boolean loadFaceIdentifyFile(MultipartFile faceIdentifyFile);



    /**
     * Méthode qui dézipe le fichier contenant les images d'entrainement du modèle.
     * @param inputStream : Dossier à déziper.
     * @param destDezipFile : Chemin du dossier dézipé.
     *
     */
    public void unzipTrainingFile(InputStream zipInputStream, String destDezipFile);



    /**
     * Méthode qui dézipe le fichier contenant les images de validation du modèle.
     * @param inputStream : Dossier à déziper.
     * @param destDezipFile : Chemin du dossier dézipé.
     *
     */
    public void unzipValidationFile(InputStream inputStream, String destDezipFile);



    /**
     * Méthode qui extrait chaque image contenue dans un fichier .zip;
     * @param zipInputStream : Fichier zip.
     * @param filePath : Emplacement des images chargées.
     * @throws FileNotFoundException
     *
     */
    public void extractFile(ZipInputStream zipInputStream, String filePath) throws FileNotFoundException;






    // ************************** Méthodes de manipulation du modèle ************************** //

    /**
     * Méthode qui entraine le modèle de Reconnaissance Faciale.
     * @return boolean : succès/échec de l'exécution.
     *
     */
    public boolean trainFaceRecognizer();



    /**
     * Méthode qui exécute le modèle de Reconnaissance Faciale.
     * @return boolean : succès/échec de l'exécution.
     *
     */
    public boolean executeFaceRecognizer();



    /**
     * Méthode qui valide le fonctionnement du modèle de Reconnaissance Faciale.
     * @return boolean : succès/échec de l'exécution.
     *
     */
    public boolean validateFaceRecognizer();



    /**
     * Méthode créant le fichier d'encodage des visages et le dossier le contenant.
     *
     */
    public void createEncodingFile();



    /**
     * Méthode qui initialise le modèle de Reconnaissance Faciale.
     * Le modèle initialisé par défaut est le modèle "HOG".
     * @return boolean : succès/échec de l'exécution.
     *
     */
    public boolean initializeFaceRecognizerModel();



    /**
     * Méthode qui modifie le modèle en BDD.
     * @param : String : Modèle sélectionnée.
     * @return boolean : succès/échec de l'exécution.
     *
     */
    public boolean selectModel(String selectedModel);



    /**
     * Méthode qui récupère le modèle de Reconnaissance Faciale en BDD.
     * @return boolean : succès/échec de l'exécution.
     *
     */
    public String getModel();



    /**
     * Méthode qui renvoie la liste des modèles de Reconnaissance Faciale.
     * @return List<String> : Liste des modèles.
     *
     */
    public List<String> getListModele();






}

