package com.example.CrudTraining.repository;

import com.example.CrudTraining.bo.ia.randomForestmodel.IrisModelResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;






/**
 * Repository pour manipuler les Réponses du modèle de Machine Learning Random Forest.
 * Par défaut, ce modèle est utilisé sur le dataset de classification du type d'Iris.
 *
 */
@Repository
public interface IrisModelRepository extends JpaRepository<IrisModelResponse, Long> {






}

