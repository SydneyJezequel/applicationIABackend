package com.example.CrudTraining.service.serviceIaImpl;

import com.example.CrudTraining.bo.ia.faceRecognizerModel.FaceRecognizerModel;
import com.example.CrudTraining.bo.ia.faceRecognizerModel.FaceRecognizerModels;
import com.example.CrudTraining.repository.FaceRecognizerRepository;
import com.example.CrudTraining.service.iaService.FaceRecognizerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;






/**
 * Implémentation du Service pour manipuler le modèle de Reconnaissance Faciale.
 *
 */
@Service
public class FaceRecognizerServiceImpl implements FaceRecognizerService {





    // *************************** Attributs *************************** //

    @Autowired
    private FaceRecognizerRepository faceRecognizerRepository;


    // Chemins :
    @Value("${face.recognizer.path.training.dataset.folder}")
    private String pathLoadTrainingDataSetZip;

    @Value("${face.recognizer.path.validation.dataset.folder}")
    private String pathLoadValidationDataSetZip;

    @Value("${face.recognizer.path.image.to.identify.folder}")
    private String pathIdentifyFaceFolder;

    @Value("${face.recognizer.path.image.to.identify}")
    private String pathIdentifyFace;

    @Value("${face.recognizer.path.output.folder}")
    private String pathOutputFolder;

    @Value("${face.recognizer.encoding.file.name}")
    private String encodingFileName;


    // Urls :
    private final String urlFaceEncoding = "http://localhost:8009/encode-and-train-dataset";
    private final String urlFaceRecognizing = "http://localhost:8009/recognize-face";
    private final String urlModelValidation = "http://localhost:8009/recognize-face-test";





    // ************************** Implémentation des logs ************************** //
    private static final Logger logger = Logger.getLogger(FaceRecognizerServiceImpl.class.getName());





    // ************************** Méthodes de chargement des images ************************** //

    @Override
    public boolean loadTrainingDataSetZip(MultipartFile imageZip) {
        try{
            // Chemin du dossier :
            String folderPath = pathLoadTrainingDataSetZip;
            Path path = Paths.get(folderPath);
            // Création du dossier :
            if (!Files.exists(path)) {
                try {
                    Files.createDirectories(path);
                    logger.info("Dossier 'training' créé avec succès !");
                } catch (IOException e) {
                    logger.info("Échec de la création du dossier : " + e.getMessage());
                }
            } else {
                logger.info("Le dossier existe déjà.");
            }
            // Dézippe et charge les images dans le dossier :
            try {
                unzipTrainingFile(imageZip.getInputStream(), folderPath);
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
    public boolean loadValidationDataSetZip(MultipartFile imageZip) {
        try {
            // Chemin du dossier :
            String folderPath = pathLoadValidationDataSetZip;
            Path path = Paths.get(folderPath);
            // Création du dossier :
            if (!Files.exists(path)) {
                try {
                    Files.createDirectories(path);
                    logger.info("Dossier 'validation' créé avec succès !");
                } catch (IOException e) {
                    logger.info("Échec de la création du dossier : " + e.getMessage());
                }
            } else {
                logger.info("Le dossier existe déjà.");
            }
            // Dézippe et charge les images dans le dossier :
            try {
                unzipValidationFile(imageZip.getInputStream(), folderPath);
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
        try {
            // Chemin du dossier :
            String folderPath = pathIdentifyFaceFolder;
            Path path = Paths.get(folderPath);
            // Création du dossier :
            if (!Files.exists(path)) {
                try {
                    Files.createDirectories(path);
                    logger.info("Dossier 'identifyFace' créé avec succès !");
                } catch (IOException e) {
                    logger.info("Échec de la création du dossier : " + e.getMessage());
                }
            } else {
                logger.info("Le dossier existe déjà.");
            }
            // Charge la photo à identifier dans le dossier :
            String fileName = "identifyFace.jpg";
            Path destinationFilePath = Paths.get(folderPath, fileName);
            try (OutputStream outputStream = Files.newOutputStream(destinationFilePath)) {
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
            // Parcours du fichier .zip :
            while ((entry = zipInputStream.getNextEntry()) != null) {
                String entryName = entry.getName();
                String filePath = destDezipFile + File.separator + entryName;
                // Extraction de chaque fichier contenu dans le ".zip" :
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
            // Parcours du fichier .zip :
            while ((entry = zipInputStream.getNextEntry()) != null) {
                String entryName = entry.getName();
                String filePath = destDezipFile + File.separator + entryName;
                // Extraction de chaque fichier contenu dans le ".zip" :
                if (!entry.isDirectory()) {
                    // Exclusion du dossier de décompression :
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



    @Override
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





    // ************************** Méthodes pour manipuler le modèle ************************** //

    @Override
    public boolean trainFaceRecognizerModel(){
        // Création du fichier encoding.pkl :
        createEncodingFile();
        try {
            // Attributs :
            String successMessage;
            String model = getModel();
            // En-tête de la requête Http :
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            // Contenu de la requête Http :
            Map<String, String> requestBodyMap = new HashMap<>();
            requestBodyMap.put("model", model);
            // Conversion du contenu en JSON :
            String requestBody = new ObjectMapper().writeValueAsString(requestBodyMap);
            HttpEntity<String> httpEntity = new HttpEntity<>(requestBody, headers);
            // Exécution de la requête :
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    urlFaceEncoding,
                    HttpMethod.POST,
                    httpEntity,
                    String.class
            );
            // Récupération de la réponse :
            successMessage = responseEntity.getBody();
            logger.info(successMessage);
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
            // Chemin du dossier :
            String folderPath = pathOutputFolder;
            Path path = Paths.get(folderPath);
            // Création du dossier :
            if (!Files.exists(path)) {
                try {
                    Files.createDirectories(path);
                    logger.info("Dossier 'output' créé avec succès !");
                } catch (IOException e) {
                    logger.info("Échec de la création du dossier et / ou du fichier encodings.pkl : " + e.getMessage());
                }
            } else {
                logger.info("Le dossier existe déjà.");
            }
            // Création du fichier encodings.pkl vide :
            try {
                String filePath = path + encodingFileName;
                Path encodingsPath = Paths.get(filePath);
                if (Files.exists(encodingsPath)) {
                    Files.delete(encodingsPath);
                    logger.info("Fichier 'encodings.pkl' supprimé.");
                }
                Files.createFile(encodingsPath);
                logger.info("Fichier 'encodings.pkl' créé vide avec succès!");
            }catch (IOException e){
                e.printStackTrace();
            }
        }catch (RuntimeException e){
            e.printStackTrace();
        }
    }



    @Override
    public boolean executeFaceRecognizerModel(){
        try {
            // Attributs :
            String successMessage;
            String imageLocation = pathIdentifyFace;
            String model = getModel();
            // En-tête de la requête Http :
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            // Contenu de la requête Http :
            Map<String, String> requestBodyMap = new HashMap<>();
            requestBodyMap.put("image_location", imageLocation);
            requestBodyMap.put("model", model);
            // Conversion du contenu en JSON :
            String requestBody = new ObjectMapper().writeValueAsString(requestBodyMap);
            HttpEntity<String> httpEntity = new HttpEntity<>(requestBody, headers);
            // Exécution de la requête :
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    urlFaceRecognizing,
                    HttpMethod.POST,
                    httpEntity,
                    String.class
            );
            // Récupération de la réponse :
            successMessage = responseEntity.getBody();
            logger.info(successMessage);
            return true;
        }catch (RuntimeException e){
            logger.info(e.toString());
            return false;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public boolean validateFaceRecognizerModel(){
        try {
            // Attributs :
            String successMessage;
            String model = getModel();
            // En-tête de la requête Http :
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            // Contenu de la requête Http :
            Map<String, String> requestBodyMap = new HashMap<>();
            requestBodyMap.put("model", model);
            // Conversion du contenu en JSON :
            String requestBody = new ObjectMapper().writeValueAsString(requestBodyMap);
            HttpEntity<String> httpEntity = new HttpEntity<>(requestBody, headers);
            // Exécution de la requête :
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    urlModelValidation,
                    HttpMethod.POST,
                    httpEntity,
                    String.class
            );
            // Récupération de la réponse :
            successMessage = responseEntity.getBody();
            logger.info(successMessage);
            return true;
        }catch (RuntimeException e){
            logger.info(e.toString());
            return false;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public boolean initializeFaceRecognizerModel(){
        try {
            // Suppression du modèle en BDD :
            if(!faceRecognizerRepository.findAll().isEmpty()){
                faceRecognizerRepository.deleteAll();
            }
            // Enregistrement du modèle par défaut en BDD :
            FaceRecognizerModel model = new FaceRecognizerModel();
            model.setNo_model(1L);
            model.setModel(String.valueOf(FaceRecognizerModels.HOG));
            faceRecognizerRepository.save(model);
            return true;
        }catch (RuntimeException e){
            e.printStackTrace();
            return false;
        }
    }



    @Override
    public boolean selectModel(String selectedModel) {
        try {
            // Suppression du modèle en BDD :
            faceRecognizerRepository.deleteAll();
            // Enregistrement du modèle sélectionné en BDD :
            FaceRecognizerModel model = new FaceRecognizerModel();
            model.setNo_model(1L);
            switch (selectedModel) {
                case "HOG" :
                    model.setModel("hog");
                    break;
                case "CNN" :
                    model.setModel("cnn");
                    break;
                default:
                    logger.info("modèle invalide");
            }
            faceRecognizerRepository.save(model);
            return true;
        } catch (RuntimeException e) {
            e.printStackTrace();
            return false;
        }
    }



    @Override
    public String getModel(){
        return faceRecognizerRepository.getFaceRecognizerModelById(1L);
    }



    @Override
    public List<String> getListModel(){
        String hog = FaceRecognizerModels.HOG.toString();
        String cnn = FaceRecognizerModels.CNN.toString();
        List<String> modelsList = new ArrayList<>();
        modelsList.add(hog);
        modelsList.add(cnn);
        return modelsList;
    }






}

