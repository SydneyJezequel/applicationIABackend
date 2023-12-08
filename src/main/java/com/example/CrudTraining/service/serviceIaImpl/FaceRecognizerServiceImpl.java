package com.example.CrudTraining.service.serviceIaImpl;

import com.example.CrudTraining.service.iaService.FaceRecognizerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;






/**
 * Implémentation mes méthodes pour manipuler le modèle de Reconnaissance.
 *
 */
@Service
public class FaceRecognizerServiceImpl implements FaceRecognizerService {






    // *************************** Attributs Modèle de Reconnaissance Faciale *************************** //
    // Url pour manipuler le modèle FaceRecognizer :
    private final String urlEncodageDesVisages = "http://localhost:8008/encode-and-train-dataset";
    private final String urlReconnaissanceFaciale = "http://localhost:8008/recognize-face";
    private final String urlValidationModel = "http://localhost:8008/recognize-face-test";






    // ************************** Implémentation des logs ************************** //
    private static final Logger logger = Logger.getLogger(IrisModelServiceImpl.class.getName());






    // ************************** Méthodes de chargement des images ************************** //

    @Override
    public boolean loadTrainingSetZip(MultipartFile imageZip) {
        try{

            // Nom du dossier de stockage des images :
            String nomDuDossier = "training";

            // Chemin du dossier de stockage des images :
            String cheminDuDossier = "/Users/sjezequel/PycharmProjects/FaceRecognizer/" + nomDuDossier;
            Path cheminPath = Paths.get(cheminDuDossier);

            // Création du dossier :
            if (!Files.exists(cheminPath)) {
                try {
                    Files.createDirectories(cheminPath);
                    logger.info("Dossier " + nomDuDossier + " créé avec succès !");
                } catch (IOException e) {
                    logger.info("Échec de la création du dossier : " + e.getMessage());
                }
            } else {
                logger.info("Le dossier existe déjà.");
            }

            // Dézip et copie de l'image dans le dossier :
            try {
                unzipTrainingFile(imageZip.getInputStream(), cheminDuDossier);
            } catch (IOException e) {
                throw new RuntimeException("Erreur lors de la décompression du fichier imageZip", e);
            }
            return true;

        }catch (RuntimeException e){
            e.printStackTrace();
            return false;
        }
    }



    @Override
    public boolean loadValidationSetZip(MultipartFile imageZip) {
        try {
            // Nom du dossier de stockage des images :
            String nomDuDossier = "validation";

            // Chemin du dossier de stockage des images :
            String cheminDuDossier = "/Users/sjezequel/PycharmProjects/FaceRecognizer/" + nomDuDossier;
            Path cheminPath = Paths.get(cheminDuDossier);

            // Création du dossier :
            if (!Files.exists(cheminPath)) {
                try {
                    Files.createDirectories(cheminPath);
                    logger.info("Dossier " + nomDuDossier + " créé avec succès !");
                } catch (IOException e) {
                    logger.info("Échec de la création du dossier : " + e.getMessage());
                }
            } else {
                logger.info("Le dossier existe déjà.");
            }

            // Dézip et copie de l'image dans le dossier :
            try {
                unzipValidationFile(imageZip.getInputStream(), cheminDuDossier);
            } catch (IOException e) {
                throw new RuntimeException("Erreur lors de la décompression du fichier imageZip", e);
            }
            return true;
        } catch (RuntimeException e){
            e.printStackTrace();
            return false;
        }
    }



    @Override
    public boolean loadFaceIdentifyFile(MultipartFile faceIdentifyFile){
        //********************** CODE A IMPLEMENTER ********************* //
        try {
            // Nom du dossier de stockage des images :
            String nomDuDossier = "identifyFace";

            // Chemin du dossier de stockage des images :
            String cheminDuDossier = "/Users/sjezequel/PycharmProjects/FaceRecognizer/" + nomDuDossier;
            Path cheminPath = Paths.get(cheminDuDossier);

            // Création du dossier :
            if (!Files.exists(cheminPath)) {
                try {
                    Files.createDirectories(cheminPath);
                    logger.info("Dossier " + nomDuDossier + " créé avec succès !");
                } catch (IOException e) {
                    logger.info("Échec de la création du dossier : " + e.getMessage());
                }
            } else {
                logger.info("Le dossier existe déjà.");
            }

            // Copier la photo à identifier dans le dossier "identifyFace" :
            String nomDuFichier = "identifyFace.jpg";
            // String nomDuFichier = StringUtils.cleanPath(Objects.requireNonNull(faceIdentifyFile.getOriginalFilename()));
            Path cheminFichierDestination = Paths.get(cheminDuDossier, nomDuFichier);

            try (OutputStream outputStream = Files.newOutputStream(cheminFichierDestination)) {
                outputStream.write(faceIdentifyFile.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return true;
        } catch(RuntimeException e){
            e.printStackTrace();
            return false;
        }
    }



    @Override
    public void unzipTrainingFile(InputStream inputStream, String destDezipFile) {
        // Nombre d'images dézippées :
        int cpt = 0;

        // Décompression du fichier :
        try (ZipInputStream zipInputStream = new ZipInputStream(inputStream)) {
            ZipEntry entry;

            // Parcours du .zip :
            while ((entry = zipInputStream.getNextEntry()) != null) {
                String entryName = entry.getName();
                String filePath = destDezipFile + File.separator + entryName;

                // Extraction du fichier contenu dans le ".zip" :
                if (!entry.isDirectory()) {
                    extractFile(zipInputStream, filePath);
                } else {
                    File dir = new File(filePath);
                    dir.mkdir();
                }
                zipInputStream.closeEntry();
                cpt++;
            }
            logger.info(cpt + " fichiers ont été décompressés.");
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de la décompression du fichier .zip", e);
        }
    }



    @Override
    public void unzipValidationFile(InputStream inputStream, String destDezipFile) {
        // Nombre d'images dézippées :
        int cpt = 0;

        // Décompression du fichier :
        try (ZipInputStream zipInputStream = new ZipInputStream(inputStream)) {
            ZipEntry entry;

            // Parcours du .zip :
            while ((entry = zipInputStream.getNextEntry()) != null) {
                String entryName = entry.getName();
                String filePath = destDezipFile + File.separator + entryName;

                // Extraction du fichier contenu dans le ".zip" :
                if (!entry.isDirectory()) {
                    // Exclure le dossier de décompression du chemin
                    String fileName = new File(entryName).getName();
                    String imageFilePath = destDezipFile + File.separator + fileName;
                    extractFile(zipInputStream, imageFilePath);
                }
                zipInputStream.closeEntry();
                cpt++;
            }
            logger.info(cpt + " fichiers ont été décompressés.");
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de la décompression du fichier .zip", e);
        }
    }



    public void extractFile(ZipInputStream zipInputStream, String filePath) throws FileNotFoundException {

         // Chargement du Buffer dans l'emplacement de destination :
         try(BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath))){
             byte[] buffer = new byte[1024];
             int bytesRead;

             // Ecriture du fichier :
             while ((bytesRead = zipInputStream.read(buffer)) !=-1){
                 bos.write(buffer, 0, bytesRead);
             }
             logger.info("Décompression du fichier image réussie.");
         } catch (IOException e) {
             throw new RuntimeException(e);
         }
     }






    // ************************** Méthodes de manipulation du modèle ************************** //

    @Override
    public boolean encodageDesVisages(){
        // Création du fichier encoding.pkl :
        createEncodingFile();
        try {
            // Attribut :
            String messageSucces;
            String model = "hog";
            // Création de l'en-tête de la requête Http :
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            // Création du contenu de la requête Http :
            Map<String, String> requestBodyMap = new HashMap<>();
            requestBodyMap.put("model", model);
            // Convertir la map en JSON
            String requestBody = new ObjectMapper().writeValueAsString(requestBodyMap);
            HttpEntity<String> httpEntity = new HttpEntity<>(requestBody, headers);
            // Exécution de la requête vers l'API du modèle de Machine Learning :
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    urlEncodageDesVisages,
                    HttpMethod.POST,
                    httpEntity,
                    String.class
            );
            // Récupération et renvoie de la réponse :
            messageSucces = responseEntity.getBody();
            logger.info(messageSucces);
            return true;
        }catch (RuntimeException e){
            logger.info(e.toString());
            return false;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public void createEncodingFile() {
        try {

            // Nom du dossier de stockage des images :
            String nomDuDossier = "output";

            // Chemin du dossier de stockage des images :
            String cheminDuDossier = "/Users/sjezequel/PycharmProjects/FaceRecognizer/" + nomDuDossier;
            Path chemin = Paths.get(cheminDuDossier);

            // Création du dossier :
            if (!Files.exists(chemin)) {
                try {
                    Files.createDirectories(chemin);
                    logger.info("Dossier " + nomDuDossier + " créé avec succès !");
                    logger.info("Fichier encodings.pkl créé vide avec succès!");
                } catch (IOException e) {
                    logger.info("Échec de la création du dossier et / ou du fichier encodings.pkl : " + e.getMessage());
                }
            } else {
                logger.info("Le dossier existe déjà.");
            }
            try {
                String filePath = chemin + "/encodings.pkl";
                Path encodingsPath = Paths.get(filePath);
                // Création du fichier encodings.pkl vide
                Files.createFile(encodingsPath);
            }catch (IOException e){
                e.printStackTrace();
            }
        }catch (RuntimeException e){
            e.printStackTrace();
        }
    }



    @Override
    public boolean reconnaissanceFaciale(){
        try {
            // Attribut :
            String messageSucces;
            String emplacementImage = "identifyFace/identifyFace.jpg";
            String model = "hog";
            // Création de l'en-tête de la requête Http :
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            // Création du contenu de la requête Http :
            Map<String, String> requestBodyMap = new HashMap<>();
            requestBodyMap.put("image_location", emplacementImage);
            requestBodyMap.put("model", model);
            // Convertir la map en JSON
            String requestBody = new ObjectMapper().writeValueAsString(requestBodyMap);
            HttpEntity<String> httpEntity = new HttpEntity<>(requestBody, headers);
            // Exécution de la requête vers l'API du modèle de Machine Learning :
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    urlReconnaissanceFaciale,
                    HttpMethod.POST,
                    httpEntity,
                    String.class
            );
            // Récupération et renvoie de la réponse :
            messageSucces = responseEntity.getBody();
            logger.info(messageSucces);
            return true;
        }catch (RuntimeException e){
            logger.info(e.toString());
            return false;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public boolean validationDuModel(){
        try {
            // Attribut :
            String model = "hog";
            String messageSucces;
            // Création de l'en-tête de la requête Http :
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            // Création du contenu de la requête Http :
            Map<String, String> requestBodyMap = new HashMap<>();
            requestBodyMap.put("model", model);
            // Convertir la map en JSON
            String requestBody = new ObjectMapper().writeValueAsString(requestBodyMap);
            HttpEntity<String> httpEntity = new HttpEntity<>(requestBody, headers);
            // Exécution de la requête vers l'API du modèle de Machine Learning :
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    urlValidationModel,
                    HttpMethod.POST,
                    httpEntity,
                    String.class
            );
            // Récupération et renvoie de la réponse :
            messageSucces = responseEntity.getBody();
            logger.info(messageSucces);
            return true;
        }catch (RuntimeException e){
            logger.info(e.toString());
            return false;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }









}

