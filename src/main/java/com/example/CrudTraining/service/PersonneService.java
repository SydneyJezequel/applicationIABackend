package com.example.CrudTraining.service;

import com.example.CrudTraining.bo.Personne;
import java.util.List;






/**
 * Service pour manipuler les personnes.
 */
public interface PersonneService {



    /**
     * Service qui renvoie toutes les Personnes.
     * @return Liste de toutes les Personnes.
     */
    public List<Personne> getAll();



    /**
     * Service qui renvoie une Personne en fonction de son Id.
     * @param id
     * @return Personne
     */
    public Personne getById(Long id);



    /**
     * Méthode qui enregistre une personne.
     * @param personne à enregistrer.
     * @return Personne enregistré.
     */
    public Personne create(Personne personne);



    /**
     * Méthode qui modifie une personne.
     * @param personne à modifier
     * @return Personne modifiée.
     */
    public Personne update (Personne personne);



    /**
     * Méthode qui enregistre une personne.
     * @param id de la personne à supprimer.
     */
    public void delete (Long id);



}

