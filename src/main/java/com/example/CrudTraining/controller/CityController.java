package com.example.CrudTraining.controller;

import com.example.CrudTraining.bo.City;
import com.example.CrudTraining.controller.dto.CityDTO;
import com.example.CrudTraining.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;






/**
 * Controller pour gérer des Villes.
 *
 */
@RestController
@RequestMapping("/api/city")
public class CityController {





    // *************************** Attributs *************************** //
    private final CityService cityService;





    // *************************** Constructeur *************************** //
    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }





    // *************************** Méthodes *************************** //

    /**
     * Méthode qui enregistre les villes de l'Api en BDD.
     * @return City[] : liste des villes enregistrées en BDD.
     *
     */
    @GetMapping("/load-api")
    public City[] getCitiesFromApi(){
        return cityService.getCitiesFromApi();
    }



    /**
     * Méthode qui récupère toutes les Villes.
     * @return List<CityDTO> : liste de toutes les villes.
     *
     */
    @GetMapping("/all")
    public List<CityDTO> getAllCities(){
        return cityService.getAllCities();
    }



    /**
     * Méthode qui récupère une Ville.
     * @param Long : id de la ville récupérée.
     * @return City : ville récupérée.
     *
     */
    @GetMapping("/{id}")
    public CityDTO getCityById(@PathVariable("id") Long id){
        return cityService.getCityById(id);
    }



    /**
     * Méthode qui enregistre une Ville.
     * @param City : ville enregistrée.
     * @return City : ville enregistrée.
     *
     */
    @PostMapping("/add-city/")
    public City createCity(@RequestBody CityDTO cityDto){
        return cityService.createCity(cityDto);
    }



    /**
     * Méthode qui supprime une Ville.
     * @param Long : id de la ville supprimée.
     *
     */
    @DeleteMapping("/delete/{id}")
    public void deleteCity(@PathVariable("id") Long id)
    {
        cityService.deleteCity(id);
    }






}

