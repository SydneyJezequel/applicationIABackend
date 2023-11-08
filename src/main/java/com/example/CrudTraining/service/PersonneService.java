package com.example.CrudTraining.service;

import com.example.CrudTraining.bo.Personne;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;






/**
 * Service pour manipuler les entités Personnes.
 *
 */
public interface PersonneService {



    /**
     * Service qui renvoie toutes les Personnes.
     * @return Liste de toutes les Personnes.
     *
     */
    public List<Personne> getAll();



    /**
     * Service qui renvoie une Personne en fonction de son Id.
     * @param id de la personne.
     * @return personne
     *
     */
    public Personne getById(Long id);



    /**
     * Méthode qui enregistre une personne.
     * @param personne, photoBase64String à enregistrer.
     * @return Personne enregistrée.
     *
     */
    public Personne create(Personne personne, String photoBase64String);



    /**
     * Méthode qui modifie une personne.
     * @param personne à modifier.
     * @return personne modifiée.
     *
     */
    public Personne update (Personne personne);



    /**
     * Méthode qui supprime une personne.
     * @param id de la personne à supprimer.
     *
     */
    public void delete (Long id);



    /**
     * Méthode qui intègre en base de données un fichier Excel
     * contenant une liste de personne.
     * @param MultipartFile file qui contient la liste des personnes.
     * @return boolean : succès/échec de l'exécution.
     * @throws IOException
     *
     */
    public boolean importExcelPersonsFile(MultipartFile file) throws IOException;



    /**
     * Méthode qui génère un fichier Excel.
     * @return boolean : succès/échec de l'exécution.
     * @throws IOException
     *
     */
    public boolean generateExcel() throws IOException;



    /**
     * Méthode qui génère un fichier Csv.
     * @return boolean : succès/échec de l'exécution.
     *
     */
    public boolean generateCsv();



    /**
     * Méthode qui intègre les données d'un fichier Csv en Base de données.
     * @param MultipartFile file qui contient la liste des personnes.
     * @return boolean : succès/échec de l'exécution.
     *
     */
    public boolean importCsvPersonsFile(MultipartFile file);



    /**
     * Méthode pour convertir une String en byte[].
     * @param image en base64
     * @return byte[]
     *
     */
    public byte[] decodeBase64(String base64String);



    // *************************** TEST RECUPERER UNE IMAGE *************************** //
    // *************************** TEST RECUPERER UNE IMAGE *************************** //
    // *************************** TEST RECUPERER UNE IMAGE *************************** //
    /**
     * Méthode pour convertir un byte [] en StringBase64
     * @return String qui contient l'image
     * @throws IOException
     *
     */
    public String convertToBase64() throws IOException;
    // *************************** TEST RECUPERER UNE IMAGE *************************** //
    // *************************** TEST RECUPERER UNE IMAGE *************************** //
    // *************************** TEST RECUPERER UNE IMAGE *************************** //





}

