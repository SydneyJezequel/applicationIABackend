package com.example.CrudTraining.controller;

import com.example.CrudTraining.bo.Ville;
import com.example.CrudTraining.service.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;






/**
 * Controller pour gérer des Villes.
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
     * Méthode qui charge les villes de l'Api en BD et les affiche.
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
    public List<Ville> getAllVilles(){
        return villeService.getAllVilles();
    }



    /**
     * Méthode qui renvoie une Ville.
     * @return Ville
     *
     */
    @GetMapping("/{id}")
    public Ville getVilleById(@PathVariable("id") Long id){
        return villeService.getVilleById(id);
    }



    /**
     * Méthode qui enregistre une Ville.
     * @param Ville enregistrée
     * @return Ville
     *
     */
    @PostMapping("/add-ville/")
    public Ville createVille(@RequestBody Ville ville){
        return villeService.createVille(ville);
    }



    /**
     * Méthode qui supprime une Ville.
     * @param id de la ville supprimée.
     *
     */
    @DeleteMapping("/delete/{id}")
    public void deleteVille(@PathVariable("id") Long id)
    {
        villeService.deleteVille(id);
    }






}

