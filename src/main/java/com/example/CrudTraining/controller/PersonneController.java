package com.example.CrudTraining.controller;

import com.example.CrudTraining.bo.Personne;
import com.example.CrudTraining.service.PersonneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.File;
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
    @PostMapping("/import/excel")
    public List<Personne> importExcelPersonsFile(@RequestParam("file") File file) {
        return personneService.importExcelPersonsFile(file);
    }





}

