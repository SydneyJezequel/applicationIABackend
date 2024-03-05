package com.example.CrudTraining.service.serviceImpl;

import com.example.CrudTraining.bo.Person;
import com.example.CrudTraining.repository.PersonRepository;
import com.example.CrudTraining.service.PersonService;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;






/**
 * Implémentation du service pour manipuler les Personnes.
 *
 */
@Service
public class PersonServiceImpl implements PersonService {





    // ************************** Injection du Repository ************************** //
    @Autowired
    PersonRepository personRepository;





    // ************************** Implémentation des logs ************************** //
    private static final Logger logger = Logger.getLogger(PersonServiceImpl.class.getName());





    // ************************** Méthodes ************************** //
    @Override
    public List<Person> getAllPersons(){
        return personRepository.findAll();
    }



    @Override
    public Person getPersonById(Long id){
        return personRepository.findPersonById(id);
    }



    @Override
    public Person createPerson(Person person, String photoBase64String){
        // Ajout de la photo sur l'objet Person :
        person.setPhoto(decodeBase64(photoBase64String));
        // Sauvegarde en BDD :
        return personRepository.save(person);
    }



    @Override
    public Person update (Person person){
        return personRepository.save(person);
    }



    @Override
    public void delete (Long id){
        personRepository.deleteById(id);
    }



    @Override
    public boolean importExcelPersonsFile(MultipartFile file) throws IOException {
        // Liste de Personnes à intégrer :
        List<Person> persons = new ArrayList<>();
        try {
            // Création d'un classeur Excel :
            Workbook workbook = WorkbookFactory.create(file.getInputStream());
            // Récupération de la 1ere feuille :
            Sheet dataSheet = workbook.getSheetAt(0);
            // On parcourt le fichier :
            for (int rowIndex = 1; rowIndex <= dataSheet.getLastRowNum(); rowIndex++) {
                Row row = dataSheet.getRow(rowIndex);
                if (row != null) {
                    // Récupération des valeurs de la cellule :
                    String name = row.getCell(0).getStringCellValue();
                    String surname = row.getCell(1).getStringCellValue();
                    Date birth_date = row.getCell(2).getDateCellValue();
                    long no_social_safety = (long) row.getCell(3).getNumericCellValue();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    // Création d'un objet Person et attribution des valeurs à l'objet :
                    Person person = new Person();
                    person.setName(name);
                    person.setSurname(surname);
                    person.setBirth_date(birth_date);
                    person.setNo_social_safety(no_social_safety);
                    // Ajout de la person dans la liste :
                    persons.add(person);
                }
            }
            // Enregistrement de la liste des persons en BDD :
            personRepository.saveAll(persons);
            logger.info("Méthode importExcelPersonsFile() exécutée avec succès.");
            return true;
        }
        // Gestion des erreurs :
        catch (IOException e) {
            logger.warning("Méthode importExcelPersonsFile() en erreur : " + e);
            return false;
        }
    }



    @Override
    public boolean generateExcel() throws IOException {
        try {
            // Récupération des données :
            List<Person> persons = personRepository.findAll();
            // Création du fichier Excel et la feuille qui contient les données :
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Personnes");
            // Création ligne d'en-tête du fichier Excel :
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID Personne");
            headerRow.createCell(1).setCellValue("Nom");
            headerRow.createCell(2).setCellValue("Prénom");
            headerRow.createCell(3).setCellValue("Date de Naissance");
            headerRow.createCell(4).setCellValue("Numéro de Sécurité Sociale");
            // Remplir chaque ligne du fichier Excel avec une Personne :
            int rowNum = 1;
            for (Person person : persons) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(person.getNo_person());
                row.createCell(1).setCellValue(person.getName());
                row.createCell(2).setCellValue(person.getSurname());
                row.createCell(3).setCellValue(person.getBirth_date().toString());
                row.createCell(4).setCellValue(person.getNo_social_safety());
            }
            // Fichier Excel ou sont chargées les données :
            String filePath = "/Users/sjezequel/Desktop/Liste_personnes";
            // Écriture des données dans le fichier Excel :
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            }
            workbook.close();
            return true;
        } catch (RuntimeException e)
        {
            return false;
        }
    }



    @Override
    public boolean importCsvPersonsFile(MultipartFile file) {
        try {
            // Lecture des données du fichier et stockage en mémoire tampon :
            BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            // Objet pour lire les données csv du Buffer :
            CSVReader csvReader = new CSVReader(reader);
            // Ligne du fichier CSV :
            String[] line;
            // Liste des persons à sauvegarder en BDD :
            List<Person> persons = new ArrayList<>();
            // Instance pour formater la date :
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            // Lecture du fichier ligne par ligne :
            while ((line = csvReader.readNext()) != null) {
                // Création d'un objet Personne :
                Person person = new Person();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date parsedDate = dateFormat.parse(line[0]);
                person.setBirth_date(parsedDate);
                person.setNo_social_safety(Long.parseLong(line[1]));
                person.setName(line[2]);
                person.setSurname(line[3]);
                // Ajout de l'objet Personne à la liste :
                persons.add(person);
            }
            // Enregistrement de la liste en BDD :
            personRepository.saveAll(persons);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (CsvValidationException | ParseException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public boolean generateCsv() {
        try {
            // Récupération des données :
            List<Person> persons = personRepository.findAll();
            // Path du fichier CSV ou sont chargées les données :
            String filePath = "/Users/sjezequel/Desktop/Liste_personnes_csv";
            // Création du fichier Csv :
            CSVWriter writer = new CSVWriter(new FileWriter(filePath));
            // Injection de l'en-tête dans le fichier CSV :
            String[] header = {"no personne", "date naissance", "no securite sociale", "nom", "prenom"};
            writer.writeNext(header);
            // Injection des données dans le fichier CSV :
            for(Person person : persons){
                String no_social_safety = String.valueOf(person.getNo_social_safety());
                String[] ligne = { person.getNo_person().toString(), person.getBirth_date().toString(), no_social_safety, person.getName(), person.getSurname() };
                writer.writeNext(ligne);
            }
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }



    @Override
    public byte[] decodeBase64(String base64String) {
        // Supprimer les caractères "\\" de la chaîne :
        base64String = base64String.replace("\\", "");
        // Supprimer les caractères "\"" de la chaîne :
        base64String = base64String.replace("\"","");

        // ************ TEST ********* //
        // Supprime les caractères "==}" de la chaîne :
        base64String = base64String.replace("==}", "g==}");
        System.out.println(base64String);
        // ************ TEST ********* //

        // Retourner le tableau d'octets :
        return Base64.getMimeDecoder().decode(base64String);
    }



    // *************************** TEST RECUPERER UNE IMAGE *************************** //
    public String convertToBase64() throws IOException{
        return "A implémenter";
    }
    // *************************** TEST RECUPERER UNE IMAGE *************************** //






}

