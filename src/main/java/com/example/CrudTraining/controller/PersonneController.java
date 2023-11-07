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
     * @param personneDTO
     * @return
     *
     */
    @PostMapping("/add-personne/")
    public Personne createPersonne(@RequestBody PersonneDTO personneDTO){
        return personneService.create(personneDTO.getPersonne(), personneDTO.getPhotoBase64String());
    }



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
















    // *************************** TEST RECUPERER UNE IMAGE *************************** //
    // *************************** TEST RECUPERER UNE IMAGE *************************** //
    // *************************** TEST RECUPERER UNE IMAGE *************************** //
    @GetMapping("/image-base64")
    public String getImagebase64() throws IOException {
        return personneService.convertToBase64();
    }
    // *************************** TEST RECUPERER UNE IMAGE *************************** //
    // *************************** TEST RECUPERER UNE IMAGE *************************** //
    // *************************** TEST RECUPERER UNE IMAGE *************************** //







}

