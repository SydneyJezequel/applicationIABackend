package com.example.CrudTraining.service;

import com.example.CrudTraining.bo.Person;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;






/**
 * Service pour manipuler les Personnes.
 *
 */
public interface PersonService {



    /**
     * Service qui renvoie toutes les Personnes.
     * @return List<Person> : Liste de toutes les Personnes.
     *
     */
    public List<Person> getAllPersons();



    /**
     * Service qui renvoie une Personne.
     * @param id de la personne renvoyée.
     * @return personne
     *
     */
    public Person getPersonById(Long id);



    /**
     * Méthode qui enregistre une personne.
     * @param person, photoBase64String enregistrée.
     * @return Personne enregistrée.
     *
     */
    public Person createPerson(Person person, String photoBase64String);



    /**
     * Méthode qui modifie une personne.
     * @param person modifiée.
     * @return personne modifiée.
     *
     */
    public Person update (Person person);



    /**
     * Méthode qui supprime une personne.
     * @param id de la personne supprimée.
     *
     */
    public void delete (Long id);



    /**
     * Méthode qui intègre le fichier Excel d'une liste de personne.
     * @param MultipartFile : Contient la liste des personnes au format Excel.
     * @return boolean : succès/échec de l'exécution.
     * @throws IOException
     *
     */
    public boolean importExcelPersonsFile(MultipartFile file) throws IOException;



    /**
     * Méthode qui génère un fichier Excel contenant la liste des personnes en BDD.
     * @return boolean : succès/échec de l'exécution.
     * @throws IOException
     *
     */
    public boolean generateExcel() throws IOException;



    /**
     * Méthode qui intègre le fichier Csv d'une liste de personne.
     * @param MultipartFile : Contient la liste des personnes au format csv.
     * @return boolean : succès/échec de l'exécution.
     *
     */
    public boolean importCsvPersonsFile(MultipartFile file);



    /**
     * Méthode qui génère un fichier Csv contenant la liste des personnes en BDD.
     * @return boolean : succès/échec de l'exécution.
     *
     */
    public boolean generateCsv();



    /**
     * Méthode pour convertir une String en byte[].
     * @param image en base64
     * @return byte[]
     *
     */
    public byte[] decodeBase64(String base64String);



    // *************************** TEST RECUPERER UNE IMAGE *************************** //
    /**
     * Méthode pour convertir un byte [] en StringBase64
     * @return String qui contient l'image
     * @throws IOException
     *
     */
    public String convertToBase64() throws IOException;
    // *************************** TEST RECUPERER UNE IMAGE *************************** //






}

