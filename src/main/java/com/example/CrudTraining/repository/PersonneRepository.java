package com.example.CrudTraining.repository;

import com.example.CrudTraining.bo.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;






/**
 * Repository pour manipuler les Entités Personnes en Base de données.
 *
 */
@Repository
public interface PersonneRepository extends JpaRepository <Personne, Long> {




    /**
     * Requête qui renvoie la liste de toutes les Entités Personnes.
     * @param id d'une Personne.
     * @return
     *
     */
    @Query("SELECT p FROM Personne p WHERE p.no_personne = :id")
    Personne findPersonneById(@Param("id") Long id);






}

