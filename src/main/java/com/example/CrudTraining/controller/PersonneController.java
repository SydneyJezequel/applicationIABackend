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
    public boolean importExcelFile(@RequestParam("file") MultipartFile file) throws IOException {
        return personneService.importExcelPersonsFile(file);
    }



    /**
     * Controller qui génère un fichier Excel.
     * @return String
     *
     */
    @GetMapping("/generateExcel")
    public boolean generateExcelFile() {
        // Récupérez la liste de personnes à partir de votre source de données (base de données, service, etc.)
        try {
            // Appelez le service pour générer le fichier Excel
            return personneService.generateExcel();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }



    /**
     * Controller qui enregistre une photo en Base de données.
     * @param base64String
     * @return String
     *
     */
    @PostMapping("/upload")
    public void uploadPicture(@RequestBody String base64String) {
        personneService.uploadPicture(base64String);
    }










}









// **********************************  VERSION DE TEST ********************************  //
// **********************************  VERSION DE TEST ********************************  //
// **********************************  VERSION DE TEST ********************************  //
    /*
    public byte[] decodeBase64(String base64Polygraphie) {
        // Supprime les caractères "\\" de la chaîne
        base64Polygraphie = base64Polygraphie.replace("\\", "");

        // Supprime les caractères "\"" de la chaîne
        base64Polygraphie = base64Polygraphie.replace("\"","");
        base64Polygraphie = base64Polygraphie.replace("{", "");
        base64Polygraphie = base64Polygraphie.replace("}", "");
        base64Polygraphie = base64Polygraphie.replace("==", "");
        String base64Polygraphie2 = "{".concat(base64Polygraphie).concat("g==").concat("}");
        // String base64Polygraphie2 = (base64Polygraphie).concat("}");
        // System.out.println(base64Polygraphie2);
        System.out.println(base64Polygraphie2);

        // ******************** TEST *****************
        // base64Polygraphie = base64Polygraphie.replace("7b", "");


        // ******************** TEST *****************




        // Décode la chaîne en un tableau d'octets à l'aide de l'encodeur Base64
        // byte[] decoder = Base64.getDecoder().decode(base64Polygraphie);
        // byte[] decoder = Base64.getUrlDecoder().decode(base64Polygraphie);
        // byte[] decoder = Base64.getDecoder().decode(base64Polygraphie);
        byte[] decoder = Base64.getMimeDecoder().decode(base64Polygraphie2);


        // Retourne le tableau d'octets décodé
        return decoder;
    }

    */
// **********************************  VERSION DE TEST ********************************  //
// **********************************  VERSION DE TEST ********************************  //
// **********************************  VERSION DE TEST ********************************  //


