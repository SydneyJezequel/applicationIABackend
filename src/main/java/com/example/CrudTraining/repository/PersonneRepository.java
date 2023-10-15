package com.example.CrudTraining.repository;

import com.example.CrudTraining.bo.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonneRepository extends JpaRepository <Personne,Long> {



    @Query("SELECT p FROM Personne p WHERE p.no_personne = :id")
    Personne findPersonneById(@Param("id") Long id);




}
