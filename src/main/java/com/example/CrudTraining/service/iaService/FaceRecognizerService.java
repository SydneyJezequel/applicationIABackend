package com.example.CrudTraining.service.iaService;

import com.example.CrudTraining.bo.ia.reconaissancefacialemodele.FaceRecognizerModels;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.util.List;
import java.util.zip.ZipInputStream;






/**
 * Service pour manipuler le modèle de Reconnaissance.
 *
 */
public interface FaceRecognizerService {






    // ************************** Méthodes de chargement des images ************************** //

    /**
     * Méthode pour déposer les images d'entrainement
     * dans le projet du modèle.
     * @param MultipartFile : liste des images
     * @return booléen : Action réussie ou non.
     *
     */
    public boolean loadTrainingSetZip(MultipartFile imageZip);



    /**
     * Méthode pour déposer les images de validation
     * dans le projet du modèle.
     * @param MultipartFile : liste des images
     * @return booléen : Action réussie ou non.
     *
     */
    public boolean loadValidationSetZip(MultipartFile imageZip);



    /**
     * Méthode pour déposer une image à reconnaitre
     * dans le projet du modèle.
     * @param MultipartFile : faceIdentifyFile
     * @return booléen : Action réussie ou non.
     *
     */
    public boolean loadFaceIdentifyFile(MultipartFile faceIdentifyFile);



    /**
     * Méthode qui dézip et copie le fichier qui contient les images d'entrainement.
     * @param inputStream : Dossier à déziper.
     * @param destDezipFile : Chemin du dossier dézipé
     *
     */
    public void unzipTrainingFile(InputStream zipInputStream, String destDezipFile);



    /**
     * Méthode qui dézip et copie le fichier qui contient les images de validation.
     * @param inputStream : Dossier à déziper.
     * @param destDezipFile : Chemin du dossier dézipé
     *
     */
    public void unzipValidationFile(InputStream inputStream, String destDezipFile);



    /**
     * Méthode qui extrait et copie de chaque image contenue dans le fichier .zip;
     * @param zipInputStream
     * @param filePath
     * @throws FileNotFoundException
     *
     */
    public void extractFile(ZipInputStream zipInputStream, String filePath) throws FileNotFoundException;






    // ************************** Méthodes de manipulation du modèle ************************** //

    /**
     * Méthode qui encode les images de visages.
     * @return booléen : Succès / Erreur de l'opération.
     *
     */
    public boolean encodageDesVisages();



    /**
     * Méthode de reconnaissance faciale.
     * @return booléen : Succès / Erreur de l'opération.
     *
     */
    public boolean reconnaissanceFaciale();



    /**
     * Méthode qui valide le modèle.
     * @return booléen : Succès / Erreur de l'opération.
     *
     */
    public boolean validationDuModel();



    /**
     * Méthode créer le fichier encoding.pkl
     * et le dossier output qui le contient.
     *
     */
    public void createEncodingFile();



    /**
     * Méthode qui initialise le modèle de machine learning
     * "hog" comme modèle par défaut pour la reconnaissance faciale.
     * @return boolean : Opération réussie ou non.
     */
    public boolean initializeFaceRecognizerModel();



    /**
     * Méthode qui change le modèle
     * en BDD.
     *
     */
    public boolean selectModel(String modele);



    /**
     * Méthode qui récupère le modèle
     * en BDD.
     */
    public String getModel();



    /**
     * Méthode qui renvoie la liste des modèles
     * vers le front pour le menu déroulant.
     *
     */
    public List<String> getListModele();





}

