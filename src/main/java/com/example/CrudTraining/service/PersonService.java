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
     * Méthode qui récupère toutes les Personnes.
     * @return List<Person> : liste des personnes.
     *
     */
    public List<Person> getAllPersons();



    /**
     * Méthode qui récupère une personne.
     * @param Long : id de la persone récupérée.
     * @return Person : personne récupérée.
     *
     */
    public Person getPersonById(Long id);



    /**
     * Méthode qui enregistre une personne.
     * @param Person : personne enregistrée.
     * @param String : photo de la personne enregistrée.
     * @return Personne enregistrée.
     *
     */
    public Person createPerson(Person person, String photoBase64String);



    /**
     * Méthode qui modifie une personne.
     * @param Person : personne modifiée.
     * @return Person : personne modifiée.
     *
     */
    public Person update (Person person);



    /**
     * Méthode qui supprime une Personne.
     * @param Long : id de la personne supprimée.
     *
     */
    public void delete (Long id);



    /**
     * Méthode qui intègre le fichier Excel d'une liste de personne.
     * @param MultipartFile : Contient la liste des personnes au format Excel.
     * @return boolean : succès/échec de l'exécution.
     * @throws IOException : Erreur d'intégration du fichier.
     *
     */
    public boolean importExcelPersonsFile(MultipartFile file) throws IOException;



    /**
     * Méthode qui génère un fichier Excel contenant la liste des personnes en BDD.
     * @return boolean : succès/échec de l'exécution.
     * @throws IOException : Erreur de génération du fichier.
     *
     */
    public boolean generateExcel() throws IOException;



    /**
     * Méthode qui intègre le fichier Csv d'une liste de personne.
     * @param MultipartFile : Contient la liste des personnes au format Csv.
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
     * @param String : image en base64.
     * @return byte[] : tableau de byte.
     *
     */
    public byte[] decodeBase64(String base64String);



    // *************************** TEST RECUPERER UNE IMAGE *************************** //
    /**
     * Méthode pour convertir un byte [] en String.
     * @return String qui contenant l'image.
     * @throws IOException : Erreur de conversion.
     *
     */
    public String convertToBase64() throws IOException;
    // *************************** TEST RECUPERER UNE IMAGE *************************** //






}

