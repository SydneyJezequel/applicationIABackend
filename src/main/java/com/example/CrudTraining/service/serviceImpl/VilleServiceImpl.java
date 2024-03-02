package com.example.CrudTraining.service.serviceImpl;

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





    // ************************** Injection du Repository ************************** //
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
    public List<Ville> getAllVilles(){
        return villeRepository.findAll();
    }



    @Override
    public Ville getVilleById(Long id){
        return villeRepository.findVilleById(id);
    }



    @Override
    public Ville createVille(Ville ville){
        return villeRepository.save(ville);
    }



    @Override
    public void deleteVille(Long id){
        villeRepository.deleteById(id);
    }



    @Override
    public Ville[] getVillesFromExternalApi() {
        // Attributs :
        String apiUrl = "https://geo.api.gouv.fr/communes";
        RestTemplate restTemplate = new RestTemplate();
        try {
            // Suppression des données en BDD :
            villeRepository.deleteAll();
            // Chargement des données de l'Api :
            return restTemplate.getForObject(apiUrl, Ville[].class);
        } catch (Exception e) {
            logger.warning("Erreur : " + e);
        }
        return null;
    }



    @Override
    public Ville[] getVilles(){
        // Récupération des villes de l'Api :
        Ville[] villes = getVillesFromExternalApi();
        List<Ville> villesTrie = new ArrayList<>();
        int nbVilleEnBdd = 0;
        // Tri et Enregistrement de chaque ville en BDD :
        try {
            villesTrie = Arrays.stream(villes)
                    // Filtre selon le nombre d'habitants :
                    .filter(ville -> ville.getPopulation() >= 90000L)
                    // Tri par ordre alphabétique :
                    .sorted((ville1, ville2) -> ville1.getNom().compareTo(ville2.getNom()))
                    // Renvoie d'une liste :
                    .collect(Collectors.toList());

            // Enregistrement de chaque ville en BDD (Sens inverse pour conserver ordre alphabétique).
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

