package com.example.CrudTraining.service.iaService;

import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.util.zip.ZipInputStream;








/**
 * Service pour manipuler le modèle de Reconnaissance.
 *
 */
public interface FaceRecognizerService {



    /**
     * Méthode pour déposer les images d'entrainement
     * dans le projet du modèle.
     * @param imageZip
     * @return booléen : Action réussie ou non.
     *
     */
    public boolean loadTrainingSetZip(MultipartFile imageZip);



    /**
     * Méthode pour déposer les images de validation
     * dans le projet du modèle.
     * @param imageZip
     * @return booléen : Action réussie ou non.
     *
     */
    public boolean loadValidationSetZip(MultipartFile imageZip);



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
     * Extraction et copie de chaque image contenue dans le fichier .zip;
     * @param zipInputStream
     * @param filePath
     * @throws FileNotFoundException
     *
     */
    public void extractFile(ZipInputStream zipInputStream, String filePath) throws FileNotFoundException;
































    // ****************************** TESTS ****************************** //
    // ****************************** TESTS ****************************** //
    // ****************************** TESTS ****************************** //
    /**
     * Méthode qui XXXXXXXXXX
     * @return
     *
     */
    public String test1();



    /**
     * Méthode qui XXXXXXXXXX
     * @return
     *
     */
    public String test3(String test3);
    // ****************************** TESTS ****************************** //
    // ****************************** TESTS ****************************** //
    // ****************************** TESTS ****************************** //









}

