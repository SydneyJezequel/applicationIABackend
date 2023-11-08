package com.example.CrudTraining.repository;

import com.example.CrudTraining.bo.Ville;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;






/**
 * Repository pour manipuler les Entités Villes en Base de données.
 *
 */
@Repository
public interface VilleRepository extends JpaRepository<Ville, Long> {



    /**
     * Requête qui renvoie une Ville à partir de son Id.
     * @param id
     * @return List<Ville>
     */
    @Query("SELECT v FROM Ville v WHERE v.no_ville = :id")
    Ville findVilleById(@Param("id") java.lang.Long id);





}

