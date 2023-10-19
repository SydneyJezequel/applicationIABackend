package com.example.CrudTraining.service.serviceimpl;

import com.example.CrudTraining.bo.Personne;
import com.example.CrudTraining.repository.PersonneRepository;
import com.example.CrudTraining.service.PersonneService;
// import org.apache.poi.ss.usermodel.*;
// import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.*;
import java.util.ArrayList;
// import java.util.Iterator;
import java.util.List;






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
    public List<Personne> importExcelPersonsFile(File file) {


        // Liste de Personnes à intégrer :
        List<Personne> personnes = new ArrayList<>();

        /*
        // 1- Conversion du fichier List<Personnes> :
        try (FileInputStream excelFile = new FileInputStream(file);  // Création d'un flux d'entrée à partir du fichier Excel.
             Workbook workbook = new XSSFWorkbook(excelFile)) {     // Création d'un objet Workbook à partir du flux d'entrée.

            Sheet sheet = workbook.getSheetAt(0);  // Récupère la première feuille de calcul du fichier Excel.
            Iterator<org.apache.poi.ss.usermodel.Row> iterator = sheet.iterator();  // Création d'un itérateur qui parcourt les lignes (Row) de la feuille de calcul.

            while (iterator.hasNext()) {        // Boucle tant qu'il y a des lignes non lues dans la feuille de calcul.
                org.apache.poi.ss.usermodel.Row currentRow = iterator.next();       // Récupère la ligne suivante.
                Iterator<Cell> cellIterator = currentRow.iterator();        // Itérateur pour parcourir chaque cellule de la ligne.
                Personne personne = new Personne();         // Création d'une Personne et affectation des valeurs de chaque cellule.

                personne.setNo_personne((long) getCellValue((org.apache.poi.ss.usermodel.Cell) cellIterator.next()));
                personne.setNom((String) getCellValue((org.apache.poi.ss.usermodel.Cell) cellIterator.next()));
                personne.setPrenom((String) getCellValue((org.apache.poi.ss.usermodel.Cell) cellIterator.next()));
                personne.setDate_naissance((Date) getCellValue((org.apache.poi.ss.usermodel.Cell) cellIterator.next()));
                personne.setNo_securite_sociale((int) getCellValue((org.apache.poi.ss.usermodel.Cell) cellIterator.next()));

                personnes.add(personne);        // Ajout d'une personne dans la liste.
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        */

        // 2- Sauvegarde en BDD :
        personneRepository.saveAll(personnes);


        // 3- Renvoie de la liste des Users vers le front :
        return personneRepository.findAll();
    }



    /**
     * Méthode qui récupère la valeur des cellules.
     * @param cell dont on souhaite récupérer la valeur.
     * @return valeur de la cellule sous forme d'objet.
     *
     */
    /*
    private Object getCellValue(org.apache.poi.ss.usermodel.Cell cell) {
        switch (cell.getCellType()) {
            case CellType.STRING: // Retour d'une valeur String :
                return cell.getStringCellValue();
            case CellType.NUMERIC: // Retour d'une date ou d'une valeur Numérique :
                if (DateUtil.isCellDateFormatted(cell)){
                    return cell.getDateCellValue();
                }
                return cell.getNumericCellValue();
            default:
                return null;
        }
    }
    */




}

