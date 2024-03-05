package com.example.CrudTraining.repository;

import com.example.CrudTraining.bo.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;






/**
 * Repository pour manipuler les Personnes en Base de données.
 *
 */
@Repository
public interface PersonRepository extends JpaRepository <Person, Long> {



    /**
     * Requête qui renvoie une Personne à partir de son Id.
     * @param id d'une Personne.
     * @return Personne.
     *
     */
    @Query("SELECT p FROM Person p WHERE p.no_person = :id")
    Person findPersonById(@Param("id") Long id);






}

