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
     * Méthode qui récupère toutes les Personnes.
     * @return List<Person> : liste des personnes.
     *
     */
    @GetMapping("/all")
    public List<Person> getAllPersons(){
        return personService.getAllPersons();
    }



    /**
     * Méthode qui récupère une personne.
     * @param Long : id de la persone récupérée.
     * @return Person : personne récupérée.
     *
     */
    @GetMapping("/{id}")
    public Person getPersonById(@PathVariable("id") Long id){
        return personService.getPersonById(id);
    }



    /**
     * Méthode qui enregistre une personne.
     * @param personDTO : personne récupérée depuis le front.
     * @return Person : personne enregistrée.
     *
     */
    @PostMapping("/add-person/")
    public Person createPerson(@RequestBody PersonDTO personDTO){
        return personService.createPerson(personDTO.getPerson(), personDTO.getPhotoBase64String());
    }



    /**
     * Méthode qui supprime une Personne.
     * @param Long : id de la personne supprimée.
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
     * @throws IOException : Erreur d'intégration du fichier.
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
     * @param MultipartFile : Contient la liste des personnes au format Csv.
     * @return boolean : succès/échec de l'exécution.
     * @throws IOException : Erreur d'intégration du fichier.
     *
     */
    @PostMapping("/import/csv/")
    public boolean importCsvFile(@RequestParam("file") MultipartFile file) throws IOException {
        return personService.importCsvPersonsFile(file);
    }



    /**
     * Méthode qui génère un fichier Csv contenant la liste des personnes en BDD.
     * @return boolean : succès/échec de l'exécution.
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
     * Méthode pour convertir un byte [] en String.
     * @return String qui contenant l'image.
     * @throws IOException : Erreur de conversion
     *
     */
    @GetMapping("/image-base64")
    public String getImagebase64() throws IOException {
        return personService.convertToBase64();
    }
    // *************************** TEST RECUPERER UNE IMAGE *************************** //






}

