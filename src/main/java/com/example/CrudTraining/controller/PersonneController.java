package com.example.CrudTraining.controller;

import com.example.CrudTraining.bo.Personne;
import com.example.CrudTraining.controller.dto.PersonneDTO;
import com.example.CrudTraining.service.PersonneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;






/**
 * Controller pour gérer des Personnes.
 *
 */
@RestController
@RequestMapping("/api/personne")
public class PersonneController {





    // *************************** Attributs *************************** //
    private final PersonneService personneService;





    // *************************** Constructeur *************************** //
    @Autowired
    public PersonneController (PersonneService personneService) {
        this.personneService = personneService;
    }





    // *************************** Méthodes *************************** //

    /**
     * Méthode qui renvoie toutes les Personnes.
     * @return List<Personne>
     *
     */
    @GetMapping("/all")
    public List<Personne> getAllPersonnes(){
        return personneService.getAllPersonnes();
    }



    /**
     * Méthode qui renvoie une personne.
     * @param id de la personne.
     * @return personne
     *
     */
    @GetMapping("/{id}")
    public Personne getPersonneById(@PathVariable("id") Long id){
        return personneService.getPersonneById(id);
    }



    /**
     * Méthode qui enregistre une personne.
     * @param personneDTO
     * @return personne
     *
     */
    @PostMapping("/add-personne/")
    public Personne createPersonne(@RequestBody PersonneDTO personneDTO){
        return personneService.createPersonne(personneDTO.getPersonne(), personneDTO.getPhotoBase64String());
    }



    /**
     * Méthode qui supprime une Personne.
     * @param id de la personne supprimée.
     *
     */
    @DeleteMapping("/delete/{id}")
    public void deletePersonne(@PathVariable("id") Long id)
    {
        personneService.delete(id);
    }



    /**
     * Méthode qui intègre le fichier Excel d'une liste de personne.
     * @param MultipartFile : Contient la liste des personnes au format Excel.
     * @return boolean : succès/échec de l'exécution.
     *
     */
    @PostMapping("/import/excel/")
    public boolean importExcelFile(@RequestParam("file") MultipartFile file) throws IOException {
        return personneService.importExcelPersonsFile(file);
    }



    /**
     * Méthode qui génère un fichier Excel contenant la liste des personnes en BDD.
     * @return boolean : succès/échec de l'exécution.
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
     * Méthode qui intègre le fichier Csv d'une liste de personne.
     * @param MultipartFile : Contient la liste des personnes au format csv.
     * @return boolean : succès/échec de l'exécution.
     *
     */
    @PostMapping("/import/csv/")
    public boolean importCsvFile(@RequestParam("file") MultipartFile file) throws IOException {
        return personneService.importCsvPersonsFile(file);
    }



    /**
     * Méthode qui génère un fichier Csv contenant la liste des personnes en BDD.
     * @return boolean : succès/échec de l'exécution
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



    // *************************** TEST RECUPERER UNE IMAGE *************************** //
    /**
     * Méthode qui génère une String à partir d'un byte[].
     * @return String qui contient l'image.
     *
     */
    @GetMapping("/image-base64")
    public String getImagebase64() throws IOException {
        return personneService.convertToBase64();
    }
    // *************************** TEST RECUPERER UNE IMAGE *************************** //






}

