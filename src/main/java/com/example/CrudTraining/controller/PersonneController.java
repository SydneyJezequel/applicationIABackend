package com.example.CrudTraining.controller;

import com.example.CrudTraining.bo.Personne;
import com.example.CrudTraining.service.PersonneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.List;






/**
 * Controller pour la gestion des Personnes.
 *
 */
@RestController
@RequestMapping("/api/personne")
public class PersonneController {







    // ********************* Attributs *********************
    private final PersonneService personneService;







    // ********************* Constructeur *********************
    @Autowired
    public PersonneController (PersonneService personneService) {
        this.personneService = personneService;
    }







    // ********************* Méthodes *********************


    /**
     * Méthode qui renvoie toutes les Personnes.
     * @return
     */
    @GetMapping("/all")
    public List<Personne> getAll(){
        return personneService.getAll();
    }



    /**
     * Méthode qui renvoie une Personne en fonction de son Id.
     * @return
     *
     */
    @GetMapping("/{id}")
    public Personne getById(@PathVariable("id") Long id){
        return personneService.getById(id);
    }



    /**
     * Méthode qui enregistre une personne.
     * @param personne enregistrée
     * @return
     *
     */
    @PostMapping("/add-personne/")
    public Personne createPersonne(@RequestBody Personne personne){
        return personneService.create(personne);
    }



    /**
     * Méthode qui supprime une Personne en fonction de son Id.
     * @param id de la personne à supprimer.
     *
     */
    @DeleteMapping("/delete/{id}")
    public void deletePersonne(@PathVariable("id") Long id)
    {
        personneService.delete(id);
    }



    /**
     * Méthode qui intègre en base de données un fichier Excel contenant une liste de personne.
     * @param file qui contient la liste des personnes à intégrer.
     *
     */
    @PostMapping("/import/excel/")
    public List<Personne> importExcelPersonsFile(@RequestParam("file") MultipartFile file) throws IOException {
        return personneService.importExcelPersonsFile(file);
    }








    // **********************************  TEST ********************************  //
    // **********************************  TEST ********************************  //
    // **********************************  TEST ********************************  //




    // ********* CONTROLLER ********* //
    @PostMapping("/upload")
    public void uploadFile(@RequestBody String base64String) {
        System.out.println("Fichier reçu en Base64 : " + base64String);
        byte[] photoAStockerEnBdd = decodeBase64(base64String);
        System.out.println(photoAStockerEnBdd);


    }




    // ********* ENCODEUR JAVA ********* //
    public String convertToBase64(InputStream inputStream) throws IOException {

        // Ouvre un flux d'entrée à partir d'un fichier
        InputStream fis = inputStream;

        // Lit le contenu du fichier dans un tableau d'octets
        byte[] bytes = new byte[fis.available()];
        fis.read(bytes);

        // Encode le tableau d'octets en base64
        String base64 = Base64.getEncoder().encodeToString(bytes);

        // Ferme le flux d'entrée
        fis.close();

        // Retourne la chaîne base64
        return base64;
    }



    // ********* DECODEUR JAVA ********* //
    public byte[] decodeBase64(String base64Polygraphie) {
        // Supprime les caractères "\\" de la chaîne
        base64Polygraphie = base64Polygraphie.replace("\\", "");

        // Supprime les caractères "\"" de la chaîne
        base64Polygraphie = base64Polygraphie.replace("\"", "");

        // Décode la chaîne en un tableau d'octets à l'aide de l'encodeur Base64
        byte[] decoder = Base64.getDecoder().decode(base64Polygraphie);

        // Retourne le tableau d'octets décodé
        return decoder;
    }





    // **********************************  TEST ********************************  //
    // **********************************  TEST ********************************  //
    // **********************************  TEST ********************************  //







}

