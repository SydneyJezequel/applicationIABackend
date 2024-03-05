package com.example.CrudTraining.service.serviceImpl;

import com.example.CrudTraining.bo.City;
import com.example.CrudTraining.controller.dto.CityDTO;
import com.example.CrudTraining.repository.CityRepository;
import com.example.CrudTraining.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;






/**
 * Implémentation du service pour manipuler les villes.
 *
 */
@Service
public class CityServiceImpl implements CityService {





    // ************************** Attributs ************************** //
    String apiUrl = "https://geo.api.gouv.fr/communes";





    // ************************** Injection du Repository ************************** //
    @Autowired
    private CityRepository cityRepository;





    // ************************** Constructeur ************************** //
    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }





    // ************************** Implémentation des logs ************************** //
    private static final Logger logger = Logger.getLogger(CityServiceImpl.class.getName());





    // ************************** Méthodes ************************** //

    @Override
    public List<CityDTO> getAllCities(){
        // Attributs :
        List<City> cities = cityRepository.findAll();
        List<CityDTO> cityDTOList = new ArrayList<>();
        // Mapping de chaque City en CityDTO :
        for (City city : cities) {
            CityDTO cityDTO = mapCityToCityDTO(city);
            cityDTOList.add(cityDTO);
        }
        return cityDTOList;
    }



    @Override
    public CityDTO getCityById(Long id){
        City city = cityRepository.findCityById(id);
        CityDTO cityDTO = mapCityToCityDTO(city);
        return cityDTO;
    }



    @Override
    public City createCity(CityDTO cityDto){
        City city = new City();
        city = mapCityDTOToCity(cityDto);
        return cityRepository.save(city);
    }



    @Override
    public void deleteCity(Long id){
        cityRepository.deleteById(id);
    }



    @Override
    public City[] getCitiesFromExternalApi() {
        // Attributs :
        RestTemplate restTemplate = new RestTemplate();
        try {
            // Suppression des données en BDD :
            cityRepository.deleteAll();
            // Chargement des données de l'Api :
            City[] sortedCities = restTemplate.getForObject(this.apiUrl, City[].class);
            System.out.println(sortedCities);
            return restTemplate.getForObject(this.apiUrl, City[].class);
        } catch (Exception e) {
            logger.warning("Erreur : " + e);
        }
        return null;
    }



    @Override
    public City[] getCitiesFromApi(){
        // Récupération des villes de l'Api :
        City[] cities = getCitiesFromExternalApi();
        List<City> sortedCities = new ArrayList<>();
        int nbCitiesInDataBase = 0;
        // Tri et Enregistrement de chaque ville en BDD :
        try {
            sortedCities = Arrays.stream(cities)
                    // Filtre selon le nombre d'habitants :
                    .filter(city -> city.getPopulation() >= 90000L)
                    // Tri par ordre alphabétique :
                    .sorted((city1, city2) -> {
                        String name1 = city1.getName();
                        String name2 = city2.getName();
                        if (name1 == null && name2 == null) {
                            return 0;
                        } else if (name1 == null) {
                            return -1;
                        } else if (name2 == null) {
                            return 1;
                        }
                        return name1.compareTo(name2);
                    })
                    // Renvoie de la liste des villes :
                    .collect(Collectors.toList());
            // Enregistrement de chaque ville en BDD (Sens inverse pour conserver ordre alphabétique).
            for(int i = sortedCities.size()-1 ; i>0; i--){
                cityRepository.save(sortedCities.get(i));
            }
            nbCitiesInDataBase = sortedCities.size();
            logger.info("Chargement des données de l'Api exécuté avec succès. Nombre de villes enregistrées en BDD : "+ nbCitiesInDataBase);
        // Gestion des Erreurs :
        } catch (Exception e) {
            logger.warning("Erreur : " + e);
        }
        return cities;
    }



    @Override
    public CityDTO mapCityToCityDTO(City city) {
        CityDTO cityDTO = new CityDTO();
        cityDTO.setNo_city(city.getNo_city());
        cityDTO.setName(city.getName());
        cityDTO.setDepartment_code(city.getDepartment_code());
        cityDTO.setSiren(city.getSiren());
        cityDTO.setRegion_code(city.getRegion_code());
        cityDTO.setPostal_codes(city.getPostal_codes());
        cityDTO.setPopulation(city.getPopulation());
        return cityDTO;
    }



    @Override
    public City mapCityDTOToCity(CityDTO cityDto){
        City city = new City();
        city.setNo_city(cityDto.getNo_city());
        city.setName(cityDto.getName());
        city.setDepartment_code(cityDto.getDepartment_code());
        city.setSiren(cityDto.getSiren());
        city.setRegion_code(cityDto.getRegion_code());
        city.setPostal_codes(cityDto.getPostal_codes());
        city.setPopulation(cityDto.getPopulation());
        return city;
    }




}

