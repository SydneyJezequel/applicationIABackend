package com.example.CrudTraining.controller;

import com.example.CrudTraining.bo.Person;
import com.example.CrudTraining.controller.dto.PersonDTO;
import com.example.CrudTraining.service.PersonService;
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
@RequestMapping("/api/person")
public class PersonController {





    // *************************** Attributs *************************** //
    private final PersonService personService;





    // *************************** Constructeur *************************** //
    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }





    // *************************** Méthodes *************************** //

    /**
     * Méthode qui renvoie toutes les Personnes.
     * @return List<Person>
     *
     */
    @GetMapping("/all")
    public List<Person> getAllPersons(){
        return personService.getAllPersons();
    }



    /**
     * Méthode qui renvoie une personne.
     * @param id de la person.
     * @return person
     *
     */
    @GetMapping("/{id}")
    public Person getPersonById(@PathVariable("id") Long id){
        return personService.getPersonById(id);
    }



    /**
     * Méthode qui enregistre une personne.
     * @param personDTO
     * @return person
     *
     */
    @PostMapping("/add-person/")
    public Person createPerson(@RequestBody PersonDTO personDTO){
        return personService.createPerson(personDTO.getPerson(), personDTO.getPhotoBase64String());
    }



    /**
     * Méthode qui supprime une Personne.
     * @param id de la personne supprimée.
     *
     */
    @DeleteMapping("/delete/{id}")
    public void deletePerson(@PathVariable("id") Long id)
    {
        personService.delete(id);
    }



    /**
     * Méthode qui intègre le fichier Excel d'une liste de personne.
     * @param MultipartFile : Contient la liste des personnes au format Excel.
     * @return boolean : succès/échec de l'exécution.
     *
     */
    @PostMapping("/import/excel/")
    public boolean importExcelFile(@RequestParam("file") MultipartFile file) throws IOException {
        return personService.importExcelPersonsFile(file);
    }



    /**
     * Méthode qui génère un fichier Excel contenant la liste des personnes en BDD.
     * @return boolean : succès/échec de l'exécution.
     *
     */
    @GetMapping("/generateExcel")
    public boolean generateExcelFile() {
        try {
            return personService.generateExcel();
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
        return personService.importCsvPersonsFile(file);
    }



    /**
     * Méthode qui génère un fichier Csv contenant la liste des personnes en BDD.
     * @return boolean : succès/échec de l'exécution
     *
     */
    @GetMapping("/generateCsv")
    public boolean generateCsvFile() {
        try {
            return personService.generateCsv();
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
        return personService.convertToBase64();
    }
    // *************************** TEST RECUPERER UNE IMAGE *************************** //






}

