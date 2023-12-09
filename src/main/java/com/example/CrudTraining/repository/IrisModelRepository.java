package com.example.CrudTraining.repository;

import com.example.CrudTraining.bo.ia.irismodele.IrisModelResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;






/**
 * Repository pour manipuler les Réponses du modèle de Machine Learning
 * qui prédit les types d'Iris.
 *
 */
@Repository
public interface IrisModelRepository extends JpaRepository<IrisModelResponse, Long> {






}

