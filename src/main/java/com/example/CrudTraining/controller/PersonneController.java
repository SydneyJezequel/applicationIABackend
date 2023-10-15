package com.example.CrudTraining.controller;

import com.example.CrudTraining.bo.Personne;
import com.example.CrudTraining.service.PersonneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;






/**
 * Controller pour la gestion des Personnes.
 *
 */
@RestController
@RequestMapping("/api")
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
     */
    @GetMapping("/personne/{id}")
    public Personne getById(@PathVariable("id") Long id){
        return personneService.getById(id);
    }



    /**
     * Méthode qui enregistre une personne.
     * @param personne enregistrée
     * @return
     */
    @PostMapping("/add_personne/")
    public Personne createPersonne(@RequestBody Personne personne){
        return personneService.create(personne);
    }



    /**
     * Méthode qui modifie une personne.
     * @param personne enregistrée
     * @return
     */
    @PutMapping("/edit_personne")
    public Personne editPersonne(@RequestBody Personne personne){
        return personneService.update(personne);
    }



    /**
     * Méthode qui supprime une Personne en fonction de son Id.
     * @param Id de la personne à supprimer.
     */
    @DeleteMapping("/delete/{id}")
    public void deletePersonne(@PathVariable("id") Long id)
    {
        personneService.delete(id);
    }




}

