package com.example.CrudTraining.controller;

import com.example.CrudTraining.bo.Ville;
import com.example.CrudTraining.service.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;






/**
 * Controller pour la gestion des Villes.
 *
 */
@RestController
@RequestMapping("/api/ville")
public class VilleController {





    // *************************** Attributs *************************** //
    private final VilleService villeService;





    // *************************** Constructeur *************************** //
    @Autowired
    public VilleController (VilleService villeService) {
        this.villeService = villeService;
    }





    // *************************** Méthodes *************************** //

    /**
     * Méthode qui charge les villes de l'Api en BD et les renvoie.
     * @return Ville[]
     *
     */
    @GetMapping("/load-api")
    public Ville[] loadApi(){
        return villeService.getVilles();
    }



    /**
     * Méthode qui renvoie toutes les Villes.
     * @return List<Ville>
     *
     */
    @GetMapping("/all")
    public List<Ville> getAll(){
        return villeService.getAll();
    }



    /**
     * Méthode qui renvoie une Ville en fonction de son Id.
     * @return
     *
     */
    @GetMapping("/{id}")
    public Ville getById(@PathVariable("id") Long id){
        return villeService.getById(id);
    }



    /**
     * Méthode qui enregistre une Ville.
     * @param ville enregistrée
     * @return
     *
     */
    @PostMapping("/add-ville/")
    public Ville createVille(@RequestBody Ville ville){
        return villeService.create(ville);
    }



    /**
     * Méthode qui supprime une Ville en fonction de son Id.
     * @param id de la personne à supprimer.
     *
     */
    @DeleteMapping("/delete/{id}")
    public void deleteVille(@PathVariable("id") Long id)
    {
        villeService.delete(id);
    }




}

