package com.example.CrudTraining.service;

import com.example.CrudTraining.bo.City;
import com.example.CrudTraining.controller.dto.CityDTO;
import java.util.List;






/**
 * Service pour manipuler les Villes.
 *
 */
public interface CityService {



    /**
     * Méthode qui récupère les villes de l'Api.
     * @return City[] : liste des villes récupérées dans l'Api.
     *
     */
    public City[] getCitiesFromExternalApi();



    /**
     * Méthode qui enregistre les villes de l'Api en BDD.
     * @return City[] : liste des villes enregistrées en BDD.
     *
     */
    public City[] getCitiesFromApi();



    /**
     * Méthode qui récupère toutes les Villes.
     * @return List<City> : liste de toutes les villes.
     *
     */
    public List<CityDTO> getAllCities();



    /**
     * Méthode qui récupère une Ville.
     * @param Long : id de la ville récupérée.
     * @return City : ville récupérée.
     *
     */
    public CityDTO getCityById(Long id);



    /**
     * Méthode qui enregistre une Ville.
     * @param City : ville enregistrée.
     * @return City : ville enregistrée.
     *
     */
    public City createCity(CityDTO cityDto);



    /**
     * Méthode qui supprime une Ville.
     * @param Long : id de la ville supprimée.
     *
     */
    public void deleteCity(Long id);



    /**
     * Méthode qui transforme un objet City en objet CityDTO.
     *
     */
    public CityDTO mapCityToCityDTO(City city);



    /**
     * Méthode qui transforme un objet CityDTO en objet City.
     *
     */
    public City mapCityDTOToCity(CityDTO cityDto);






}

