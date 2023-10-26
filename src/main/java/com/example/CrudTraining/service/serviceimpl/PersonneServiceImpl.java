package com.example.CrudTraining.service.serviceimpl;

import com.example.CrudTraining.bo.Personne;
import com.example.CrudTraining.repository.PersonneRepository;
import com.example.CrudTraining.service.PersonneService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;




/**
 * Implémentation du service pour manipuler les personnes.
 *
 */
@Service
public class PersonneServiceImpl implements PersonneService {





    // ********************* Lien avec le Repository *********************
    @Autowired
    PersonneRepository personneRepository;





    // ********************* Méthodes *********************


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



    @Override
    public List<Personne> importExcelPersonsFile(MultipartFile file) throws IOException {
        // Liste de Personnes à intégrer :
        List<Personne> personnes = new ArrayList<>();
        try {
            Workbook workbook = WorkbookFactory.create(file.getInputStream()); // Conversion du fichier en workbook.
            Sheet dataSheet = workbook.getSheetAt(0); // Récupération de la feuille 1.
            for (int rowIndex = 1; rowIndex <= dataSheet.getLastRowNum(); rowIndex++) { // On parcourt le fichier.
                Row row = dataSheet.getRow(rowIndex);
                if (row != null) {
                    // Récupération des valeurs de la cellule :
                    String nom = row.getCell(0).getStringCellValue();
                    String prenom = row.getCell(1).getStringCellValue();
                    Date dateNaissance = row.getCell(2).getDateCellValue();
                    long noSecuriteSociale = (long) row.getCell(3).getNumericCellValue();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    // Création d'un objet personne et attribution des valeurs à l'objet :
                    Personne personne = new Personne();
                    personne.setNom(nom);
                    personne.setPrenom(prenom);
                    personne.setDate_naissance(dateNaissance);
                    personne.setNo_securite_sociale(noSecuriteSociale);
                    // Ajout de la personne dans la liste :
                    personnes.add(personne);
                }
            }
            // Enregistrement de la liste des personnes en BDD :
            personneRepository.saveAll(personnes);
        }
        catch (IOException e){ // Gestion des erreurs :
            System.out.println("Erreur à l'insertion des données : " + e);
        }
        return personneRepository.findAll(); // Renvoie de la liste des personnes.
    }








}

