package com.example.CrudTraining.service.personneServiceImpl;

import com.example.CrudTraining.bo.Personne;
import com.example.CrudTraining.repository.PersonneRepository;
import com.example.CrudTraining.service.PersonneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PersonneServiceImpl implements PersonneService {


    @Autowired
    PersonneRepository personneRepository;





    @Override
    public List<Personne> getAll(){
        return personneRepository.findAll();
    }

    @Override
    public Personne getById(Long id){
        return personneRepository.findPersonneById(id);
    }

    @Override
    public Personne create(Personne personne){
        return personneRepository.save(personne);
    }

    @Override
    public Personne update (Personne personne){
        return personneRepository.save(personne);
    }

    @Override
    public void delete (Long id){
        personneRepository.deleteById(id);
    }




}
