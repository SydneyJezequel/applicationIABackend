package com.example.CrudTraining.service;

import com.example.CrudTraining.bo.Ville;
import java.util.List;






/**
 * Service pour manipuler les Villes.
 *
 */
public interface VilleService {



    /**
     * Méthode qui récupère les villes de l'Api.
     * @return Ville[]
     *
     */
    public Ville[] getVillesFromExternalApi();



    /**
     * Méthode qui enregistre les villes de l'Api en BDD.
     * @return Ville[]
     *
     */
    public Ville[] getVilles();



    /**
     * Méthode qui renvoie toutes les Villes.
     * @return List<Ville> : Liste de toutes les villes.
     *
     */
    public List<Ville> getAll();



    /**
     * Méthode qui renvoie une Ville.
     * @param id de la ville récupérée.
     * @return ville
     *
     */
    public Ville getById(Long id);



    /**
     * Méthode qui enregistre une Ville.
     * @param ville enregistrée.
     * @return ville
     *
     */
    public Ville create(Ville ville);



    /**
     * Méthode qui supprime une Ville.
     * @param id de la personne supprimée.
     *
     */
    public void delete(Long id);





}

