package com.example.CrudTraining.service;


import com.example.CrudTraining.bo.Personne;
import org.springframework.stereotype.Service;

import java.util.List;



public interface PersonneService {


    public List<Personne> getAll();

    public Personne getById(Long id);

    public Personne create(Personne personne);

    public Personne update (Personne personne);

    public void delete (Long id);



}
