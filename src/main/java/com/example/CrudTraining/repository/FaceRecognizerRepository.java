package com.example.CrudTraining.repository;

import com.example.CrudTraining.bo.ia.reconaissancefacialemodele.FaceRecognizerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;






/**
 * Repository pour manipuler le modèle de Machine Learning
 * de reconnaissance faciale.
 *
 */
@Repository
public interface FaceRecognizerRepository extends JpaRepository<FaceRecognizerModel, Long> {



    /**
     * Récupérer le modèle via son Id.
     *
     */
    @Query("SELECT f.modele FROM FaceRecognizerModel f WHERE f.no_model = :idModel")
    String getFaceRecognizerModelById(@Param("idModel") Long idModel);





}

