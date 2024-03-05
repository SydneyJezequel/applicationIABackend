package com.example.CrudTraining.repository;

import com.example.CrudTraining.bo.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;






/**
 * Repository pour manipuler les Villes en Base de données.
 *
 */
@Repository
public interface CityRepository extends JpaRepository<City, Long> {



    /**
     * Requête qui renvoie une Ville à partir de son Id.
     * @param Long : id de la ville récupérée.
     * @return City : ville récupérée.
     *
     */
    @Query("SELECT c FROM City c WHERE c.no_city = :id")
    City findCityById(@Param("id") java.lang.Long id);






}

