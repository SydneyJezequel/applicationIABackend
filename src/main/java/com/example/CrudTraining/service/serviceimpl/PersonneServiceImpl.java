package com.example.CrudTraining.service.serviceimpl;

import com.example.CrudTraining.bo.Personne;
import com.example.CrudTraining.repository.PersonneRepository;
import com.example.CrudTraining.service.PersonneService;
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
 * Implémentation du service pour manipuler les personnes.
 *
 */
@Service
public class PersonneServiceImpl implements PersonneService {








    // ********************* Lien avec le Repository *********************
    @Autowired
    PersonneRepository personneRepository;








    // ********************* Implémentation des logs *********************
    private static final Logger logger = Logger.getLogger(PersonneServiceImpl.class.getName());








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
    public boolean importExcelPersonsFile(MultipartFile file) throws IOException {
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
            logger.info("Méthode importExcelPersonsFile() exécutée avec succès.");
            return true;
        }
        catch (IOException e) { // Gestion des erreurs :
            logger.warning("Méthode importExcelPersonsFile() en erreur : " + e);
            return false;
        }
    }



    @Override
    public boolean generateExcel() throws IOException {
        try {
            // Récupération des données :
            List<Personne> personnes = personneRepository.findAll();

            // Création du fichier Excel et la feuille qui contient les données :
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Personnes");

            // Création ligne d'en-tête du fichier Excel :
            Row headerRow = sheet.createRow(0); // La nouvelle ligne fait partie de la feuille du fichier Excel.
            headerRow.createCell(0).setCellValue("ID Personne");
            headerRow.createCell(1).setCellValue("Nom");
            headerRow.createCell(2).setCellValue("Prénom");
            headerRow.createCell(3).setCellValue("Date de Naissance");
            headerRow.createCell(4).setCellValue("Numéro de Sécurité Sociale");

            // Remplir chaque ligne du fichier Excel avec une Personne :
            int rowNum = 1;
            for (Personne personne : personnes) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(personne.getNo_personne());
                row.createCell(1).setCellValue(personne.getNom());
                row.createCell(2).setCellValue(personne.getPrenom());
                row.createCell(3).setCellValue(personne.getDate_naissance().toString());
                row.createCell(4).setCellValue(personne.getNo_securite_sociale());
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
            String[] ligne;
            // Liste des personnes à sauvegarder en BDD :
            List<Personne> personnes = new ArrayList<>();
            // Instance pour formater la date :
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            // Lecture du fichier ligne par ligne :
            while ((ligne = csvReader.readNext()) != null) { // S'il y a une ligne dans le fichier, on l'affecte à ligne.
                // Création d'un objet Personne :
                Personne personne = new Personne();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date parsedDate = dateFormat.parse(ligne[0]);
                personne.setDate_naissance(parsedDate);
                personne.setNo_securite_sociale(Long.parseLong(ligne[1]));
                personne.setNom(ligne[2]);
                personne.setPrenom(ligne[3]);
                // Ajout de l'objet Personne à la liste :
                personnes.add(personne);
            }
            // Enregistrement de la liste en BDD :
            personneRepository.saveAll(personnes);
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
            List<Personne> personnes = personneRepository.findAll();
            // Path du fichier CSV ou sont chargées les données :
            String filePath = "/Users/sjezequel/Desktop/Liste_personnes_csv";
            // Création du fichier Csv :
            CSVWriter writer = new CSVWriter(new FileWriter(filePath));
            // Injection de l'en-tête dans le fichier CSV :
            String[] entete = {"no personne", "date naissance", "no securite sociale", "nom", "prenom"};
            writer.writeNext(entete);
            // Injection des données dans le fichier CSV :
            for(Personne personne : personnes){
                String no_securite_sociale = String.valueOf(personne.getNo_securite_sociale());
                String[] ligne = { personne.getNo_personne().toString(), personne.getDate_naissance().toString(), no_securite_sociale, personne.getNom(), personne.getPrenom() };
                writer.writeNext(ligne);
            }
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }



    // ********************************** TERMINER IMPLEMENTATION METHODE ********************************  //
    // ********************************** TERMINER IMPLEMENTATION METHODE ********************************  //
    // ********************************** TERMINER IMPLEMENTATION METHODE ********************************  //

    @Override
    public boolean uploadPicture(String base64String)
    {
        try {
            byte[] picture = decodeBase64(base64String);
            Personne personne = new Personne();
            personne.setPrenom("test prénom");
            personne.setNom("test nom");
            personne.setPhoto(picture);
            personne.setNo_securite_sociale(1111111L);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            personne.setDate_naissance(new Date());
            personneRepository.save(personne);
            // TERMINER L'IMPLEMENTATION EN BDD.
            // TERMINER L'IMPLEMENTATION EN BDD.
            // TERMINER L'IMPLEMENTATION EN BDD.
            // TERMINER L'IMPLEMENTATION EN BDD.
            return true;
        }catch (RuntimeException e)
        {
            e.printStackTrace();
            return false;
        }
    }
    // ********************************** TERMINER IMPLEMENTATION METHODE ********************************  //
    // ********************************** TERMINER IMPLEMENTATION METHODE ********************************  //
    // ********************************** TERMINER IMPLEMENTATION METHODE ********************************  //




    @Override
    public byte[] decodeBase64(String base64String) {
        // Supprimer les caractères "\\" de la chaîne :
        base64String = base64String.replace("\\", "");
        // Supprimer les caractères "\"" de la chaîne :
        base64String = base64String.replace("\"","");
        // Retourner le tableau d'octets :
        return Base64.getMimeDecoder().decode(base64String);
    }



















    // **********************************  TEST ENCODAGE/DECODAGE ********************************  //
    // **********************************  TEST ENCODAGE/DECODAGE ********************************  //
    // **********************************  TEST ENCODAGE/DECODAGE ********************************  //


    // ********* ENCODEUR JAVA ********* //
    public String convertToBase64() throws IOException {
        // Récupèration de la photo :
        Personne personne = personneRepository.findPersonneById(101L);
        byte[] bytes = personne.getPhoto();
        // Encoder le tableau d'octets en base64 :
        String base64 = Base64.getEncoder().encodeToString(bytes);
        // Retourner la chaîne base64 :
        return base64;
    }
    // ********* ENCODEUR JAVA ********* //


    // ********* ENCODEUR JAVA - VERSION ORIGINALE ********* //
    // ********* ENCODEUR JAVA - VERSION ORIGINALE ********* //
    // ********* ENCODEUR JAVA - VERSION ORIGINALE ********* //
    /*
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
    */
    // ********* ENCODEUR JAVA - VERSION ORIGINALE ********* //
    // ********* ENCODEUR JAVA - VERSION ORIGINALE ********* //
    // ********* ENCODEUR JAVA - VERSION ORIGINALE ********* //


    // **********************************  TEST ENCODAGE/DECODAGE ********************************  //
    // **********************************  TEST ENCODAGE/DECODAGE ********************************  //
    // **********************************  TEST ENCODAGE/DECODAGE ********************************  //












}





















    // ********* VERSION CORRIGE DE LA METHODE : DECODEUR JAVA ********* //
    // ********* VERSION CORRIGE DE LA METHODE : DECODEUR JAVA ********* //
    // ********* VERSION CORRIGE DE LA METHODE : DECODEUR JAVA ********* //
    /*
    public byte[] decodeBase64(String base64Polygraphie) {
        // ************ TEST ********* //
        System.out.println(base64Polygraphie);
        // ************ TEST ********* //
        // Supprime les caractères "\\" de la chaîne :
        base64Polygraphie = base64Polygraphie.replace("\\", "");
        // Supprime les caractères "\"" de la chaîne :
        base64Polygraphie = base64Polygraphie.replace("\"","");
        // Supprime les caractères "==}" de la chaîne :
        // base64Polygraphie = base64Polygraphie.replace("==}", "");
        // Ajout de la fin de String corrigée :
        // base64Polygraphie = base64Polygraphie.concat("g==}");
        // ************ TEST ********* //
        System.out.println(base64Polygraphie);
        // ************ TEST ********* //
        byte[] decoder = Base64.getMimeDecoder().decode(base64Polygraphie);
        // ************ TEST ********* //
        System.out.println(base64Polygraphie);
        // ************ TEST ********* //
        // Retourne le tableau d'octets décodé
        return decoder;
    }
    */
    // ********* VERSION CORRIGE DE LA METHODE : DECODEUR JAVA ********* //
    // ********* VERSION CORRIGE DE LA METHODE : DECODEUR JAVA ********* //
    // ********* VERSION CORRIGE DE LA METHODE : DECODEUR JAVA ********* //











