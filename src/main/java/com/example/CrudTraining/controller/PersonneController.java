package com.example.CrudTraining.controller;

import com.example.CrudTraining.bo.Personne;
import com.example.CrudTraining.service.PersonneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

    @GetMapping("/all")
    public List<Personne> getAll(){
        return personneService.getAll();
    }

    @GetMapping("/personne/{id}")
    public Personne getById(@PathVariable("id") Long id){
        return personneService.getById(id);
    }

    @PostMapping("/add_personne/")
    public Personne createPersonne(@RequestBody Personne personne){
        // *********** TEST : **********
        System.out.println(personne.getNom());
        System.out.println(personne.getPrenom());
        System.out.println(personne.getNo_personne());
        // *********** TEST : **********
        return personneService.create(personne);
    }


    @PutMapping("/edit_personne")
    public Personne editPersonne(@RequestBody Personne personne){
        return personneService.update(personne);
    }


    @DeleteMapping("/delete/{id}")
    public void deletePersonne(@PathVariable("id") Long id)
    {
        System.out.println(id);
        personneService.delete(id);
    }




}
