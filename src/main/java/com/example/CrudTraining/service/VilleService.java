package com.example.CrudTraining.service;

import com.example.CrudTraining.bo.Ville;
import java.util.List;






/**
 * Service pour manipuler les entités Ville.
 *
 */
public interface VilleService {



    /**
     * Méthode qui récupère les villes de l'Api.
     * @return Ville[]
     *
     */
    public Ville[] getVillesromExternalApi();



    /**
     * Méthode qui sauvegarde les villes de l'Api en BDD.
     * @return Ville[]
     *
     */
    public Ville[] getVilles();



    /**
     * Méthode qui renvoie toutes les Villes.
     * @return Liste de toutes les villes.
     *
     */
    public List<Ville> getAll();



    /**
     * Méthode qui renvoie une Ville en fonction de son Id.
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
     * Méthode qui supprime une Ville en fonction de son Id.
     * @param id de la personne à supprimer.
     *
     */
    public void delete(Long id);





}

