package com.example.CrudTraining.service;

import com.example.CrudTraining.bo.Personne;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;






/**
 * Service pour manipuler les entités Personnes.
 */
public interface PersonneService {



    /**
     * Service qui renvoie toutes les Personnes.
     * @return Liste de toutes les Personnes.
     */
    public List<Personne> getAll();



    /**
     * Service qui renvoie une Personne en fonction de son Id.
     * @param id
     * @return Personne
     */
    public Personne getById(Long id);



    /**
     * Méthode qui enregistre une personne.
     * @param personne à enregistrer.
     * @return Personne enregistré.
     */
    public Personne create(Personne personne);



    /**
     * Méthode qui modifie une personne.
     * @param personne à modifier
     * @return Personne modifiée.
     */
    public Personne update (Personne personne);



    /**
     * Méthode qui enregistre une personne.
     * @param id de la personne à supprimer.
     */
    public void delete (Long id);



    /**
     * Méthode qui intègre en base de données un fichier Excel contenant une liste de personne.
     * @param file Excel qui contient la liste des personnes.
     */
    public boolean importExcelPersonsFile(MultipartFile file) throws IOException;



    /**
     * Méthode qui génère un fichier Excel.
     *
     */
    public boolean generateExcel() throws IOException;



    /**
     * Méthode qui génère un fichier Csv.
     *
     */
    public boolean generateCsv();



    /**
     * Méthode qui intègre les données d'un fichier Csv en Base de données.
     *
     */
    public boolean importCsvPersonsFile(MultipartFile file);



    /**
     * Méthode qui enregistre une photo sous forme de Byte Array en BDD.
     * @param image convertie en base64.
     * @return boolean : Succès de l'opération.
     *
     */
    public boolean uploadPicture(String base64String);



    /**
     * Méthode pour convertir une String en Byte Array.
     * @param image convertie en base64.
     * @return byte[]
     * @throws IOException
     *
     */
    public byte[] decodeBase64(String base64String);





















    // **********************************  TEST ENCODAGE/DECODAGE ********************************  //
    // **********************************  TEST ENCODAGE/DECODAGE ********************************  //
    // **********************************  TEST ENCODAGE/DECODAGE ********************************  //

    /**
     * Méthode pour convertir
     * @return String
     * @throws IOException
     *
     */
    public String convertToBase64() throws IOException;

    // **********************************  TEST ENCODAGE/DECODAGE ********************************  //
    // **********************************  TEST ENCODAGE/DECODAGE ********************************  //
    // **********************************  TEST ENCODAGE/DECODAGE ********************************  //













}

