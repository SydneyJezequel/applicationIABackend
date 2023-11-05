package com.example.CrudTraining.controller;

import com.example.CrudTraining.bo.Personne;
import com.example.CrudTraining.service.PersonneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.List;






/**
 * Controller pour la gestion des Personnes.
 *
 */
@RestController
@RequestMapping("/api/personne")
public class PersonneController {







    // ********************* Attributs *********************
    private final PersonneService personneService;







    // ********************* Constructeur *********************
    @Autowired
    public PersonneController (PersonneService personneService) {
        this.personneService = personneService;
    }







    // ********************* Méthodes *********************


    /**
     * Méthode qui renvoie toutes les Personnes.
     * @return
     */
    @GetMapping("/all")
    public List<Personne> getAll(){
        return personneService.getAll();
    }



    /**
     * Méthode qui renvoie une Personne en fonction de son Id.
     * @return
     *
     */
    @GetMapping("/{id}")
    public Personne getById(@PathVariable("id") Long id){
        return personneService.getById(id);
    }



    /**
     * Méthode qui enregistre une personne.
     * @param personne enregistrée
     * @return
     *
     */
    @PostMapping("/add-personne/")
    public Personne createPersonne(@RequestBody Personne personne){
        return personneService.create(personne);
    }
    // *********************** NOUVELLE VERSION DE LA METHODE ********************* //
    /*
    @PostMapping("/add-personne/")
    public Personne createPersonne(@RequestBody Personne personne, @RequestBody String photoBase64String){
        return personneService.create(personne, photoBase64String);
    }
    */
    // *********************** NOUVELLE VERSION DE LA METHODE ********************* //



    /**
     * Méthode qui supprime une Personne en fonction de son Id.
     * @param id de la personne à supprimer.
     *
     */
    @DeleteMapping("/delete/{id}")
    public void deletePersonne(@PathVariable("id") Long id)
    {
        personneService.delete(id);
    }



    /**
     * Méthode qui intègre en base de données un fichier Excel contenant une liste de personne.
     * @param file qui contient la liste des personnes à intégrer.
     *
     */
    @PostMapping("/import/excel/")
    public boolean importExcelFile(@RequestParam("file") MultipartFile file) throws IOException {
        return personneService.importExcelPersonsFile(file);
    }



    /**
     * Controller qui génère un fichier Excel.
     * @return String
     *
     */
    @GetMapping("/generateExcel")
    public boolean generateExcelFile() {
        try {
            return personneService.generateExcel();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }



    /**
     * Méthode qui intègre en base de données un fichier Csv contenant une liste de personne.
     * @param file qui contient la liste des personnes à intégrer.
     *
     */
    @PostMapping("/import/csv/")
    public boolean importCsvFile(@RequestParam("file") MultipartFile file) throws IOException {
        return personneService.importCsvPersonsFile(file);
    }



    /**
     * Controller qui génère un fichier Excel.
     * @return String
     *
     */
    @GetMapping("/generateCsv")
    public boolean generateCsvFile() {
        try {
            return personneService.generateCsv();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return false;
        }
    }



    /**
     * Controller qui enregistre une photo en Base de données.
     * @param base64String
     * @return String
     *
     */
    @PostMapping("/upload")
    public void uploadPicture(@RequestBody String base64String) {
        personneService.uploadPicture(base64String);
    }











    // **********************************  TEST ENCODAGE/DECODAGE ********************************  //
    // **********************************  TEST ENCODAGE/DECODAGE ********************************  //
    // **********************************  TEST ENCODAGE/DECODAGE ********************************  //



    // ********* ENCODEUR JAVA ********* //
    // ********* ENCODEUR JAVA ********* //
    // ********* ENCODEUR JAVA ********* //
    @GetMapping("/image-base64")
    public String getImagebase64() throws IOException {
        return personneService.convertToBase64();
    }
    // ********* ENCODEUR JAVA ********* //
    // ********* ENCODEUR JAVA ********* //
    // ********* ENCODEUR JAVA ********* //



    // **********************************  TEST ENCODAGE/DECODAGE ********************************  //
    // **********************************  TEST ENCODAGE/DECODAGE ********************************  //
    // **********************************  TEST ENCODAGE/DECODAGE ********************************  //






}
