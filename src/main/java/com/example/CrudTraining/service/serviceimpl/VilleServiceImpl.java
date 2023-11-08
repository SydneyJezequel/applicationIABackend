package com.example.CrudTraining.service.serviceimpl;

import com.example.CrudTraining.bo.Ville;
import com.example.CrudTraining.repository.VilleRepository;
import com.example.CrudTraining.service.VilleService;
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
public class VilleServiceImpl implements VilleService {





    // ************************** Attributs ************************** //
    @Autowired
    private VilleRepository villeRepository;





    // ************************** Constructeur ************************** //
    public VilleServiceImpl(VilleRepository villeRepository) {
        this.villeRepository = villeRepository;
    }





    // ************************** Implémentation des logs ************************** //
    private static final Logger logger = Logger.getLogger(VilleServiceImpl.class.getName());





    // ************************** Méthodes ************************** //
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



    @Override
    public Ville[] getVillesromExternalApi() {
        // Définition de l'Api et du RestTemplate :
        String apiUrl = "https://geo.api.gouv.fr/communes";
        RestTemplate restTemplate = new RestTemplate();
        try {
            // Vider la BDD :
            villeRepository.deleteAll();
            // Chargement des données de l'API :
            return restTemplate.getForObject(apiUrl, Ville[].class);
        } catch (Exception e) { // Gestion des Erreurs :
            logger.warning("Erreur : " + e);
        }
        return null;
    }



    @Override
    public Ville[] getVilles(){
        // Chargement des villes de l'Api dans l'Array :
        Ville[] villes = getVillesromExternalApi();
        List<Ville> villesTrie = new ArrayList<>();
        int nbVilleEnBdd = 0;
        // Tri et Enregistrement de chaque ville dans la BDD :
        try {
            villesTrie = Arrays.stream(villes) // Création d'un flux
                    .filter(ville -> ville.getPopulation() >= 90000L) // Filtre en fonction du nombre d'habitant.
                    .sorted((ville1, ville2) -> ville1.getNom().compareTo(ville2.getNom())) // Tri par ordre alphabétique.
                    .collect(Collectors.toList()); // Stockage dans une liste.

            // Enregistrement de chaque élément de la liste dans la BDD (sens inverse pour conserver ordre alphabétique.
            for(int i = villesTrie.size()-1 ; i>0; i--){
                villeRepository.save(villesTrie.get(i));
            }
            nbVilleEnBdd = villesTrie.size();
            logger.info("Chargement des données de l'Api exécuté avec succès. Nombre de villes enregistrées en BDD : "+ nbVilleEnBdd);
        } catch (Exception e) { // Gestion des Erreurs :
            logger.warning("Erreur : " + e);
        }
        return villes;
    }





}

