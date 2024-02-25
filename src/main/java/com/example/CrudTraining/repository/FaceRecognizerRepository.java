package com.example.CrudTraining.repository;

import com.example.CrudTraining.bo.ia.faceRecognizerModel.FaceRecognizerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;






/**
 * Repository pour manipuler les modèles de reconnaissance faciale en BDD.
 *
 */
@Repository
public interface FaceRecognizerRepository extends JpaRepository<FaceRecognizerModel, Long> {



    /**
     * Requête qui renvoie un Modèle de reconnaissance faciale à partir de son Id.
     *
     */
    @Query("SELECT f.modele FROM FaceRecognizerModel f WHERE f.no_model = :idModel")
    String getFaceRecognizerModelById(@Param("idModel") Long idModel);






}

