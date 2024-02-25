package com.example.CrudTraining.repository;

import com.example.CrudTraining.bo.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;






/**
 * Repository pour manipuler les Personnes en Base de données.
 *
 */
@Repository
public interface PersonneRepository extends JpaRepository <Personne, Long> {



    /**
     * Requête qui renvoie une Personne à partir de son Id.
     * @param id d'une Personne.
     * @return Personne.
     *
     */
    @Query("SELECT p FROM Personne p WHERE p.no_personne = :id")
    Personne findPersonneById(@Param("id") Long id);






}

