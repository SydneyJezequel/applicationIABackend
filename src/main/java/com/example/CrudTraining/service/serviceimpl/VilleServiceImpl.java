package com.example.CrudTraining.service.serviceimpl;

import com.example.CrudTraining.bo.Ville;
import com.example.CrudTraining.repository.VilleRepository;
import com.example.CrudTraining.service.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.logging.Logger;






/**
 * Implémentation du service pour manipuler les villes.
 *
 */
@Service
public class VilleServiceImpl implements VilleService {






    // ********************* Attributs *********************

    Logger logger;

    @Autowired
    private VilleRepository villeRepository;







    // ********************* Constructeur *********************

    public VilleServiceImpl(VilleRepository villeRepository) {
        this.villeRepository = villeRepository;
    }







    // ********************* Méthodes *********************


    @Override
    public Ville[] getVillesromExternalApi() {

        // Définition de l'Api et du RestTemplate :
        String apiUrl = "https://geo.api.gouv.fr/communes";
        RestTemplate restTemplate = new RestTemplate();

        try{
            // Vider la BDD :
            villeRepository.deleteAll();
            // Chargement des données de l'API.
            return restTemplate.getForObject(apiUrl, Ville[].class);
        }catch (Exception e){ // Gestion des Erreurs :
            System.out.println("Erreur : "+ e);
        }
        return null;
    }



    @Override
    public Ville[] getVilles(){
        // Chargement des villes de l'Api dans l'Array :
        Ville[] villes = getVillesromExternalApi();
        int nbVilleEnBdd = 0;
        // Enregistrement de chaque ville dans la BDD :
        try {
            for(Ville ville : villes)
            {
                if(ville.getPopulation()>=90000L){
                    Ville databaseVille = new Ville();
                    databaseVille.setNom(ville.getNom());
                    databaseVille.setCodeDepartement(ville.getCodeDepartement());
                    databaseVille.setSiren(ville.getSiren());
                    databaseVille.setCodeRegion(ville.getCodeRegion());
                    databaseVille.setCodesPostaux(ville.getCodesPostaux());
                    databaseVille.setPopulation(ville.getPopulation());
                    villeRepository.save(databaseVille);
                    nbVilleEnBdd++;
                }
            }
            // TRANSFORMER SYSTEM.OUT EN LOGGER :
            System.out.println("Nombre de villes enregistrées en BDD : "+ nbVilleEnBdd);
        } catch (Exception e){ // Gestion des Erreurs :
            // TRANSFORMER SYSTEM.OUT EN LOGGER :
            System.out.println("Erreur : "+ e);
        }
        return villes;
    }



    @Override
    public List<Ville> getAll(){
        return villeRepository.findAll();
    }



    @Override
    public Ville getById(Long id){
        return villeRepository.findVilleById(id);
    }



    @Override
    public Ville create(Ville ville){
        return villeRepository.save(ville);
    }



    @Override
    public void delete(Long id){
        villeRepository.deleteById(id);
    }





}

