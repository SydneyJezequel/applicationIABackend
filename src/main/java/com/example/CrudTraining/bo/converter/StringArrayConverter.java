package com.example.CrudTraining.bo.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;






/**
 *  Classe qui convertit un String[] en String et une String en String[].
 *
 */
@Converter
public class StringArrayConverter implements AttributeConverter<String[], String> {





    // *************************** Méthodes *************************** //

    /**
     * Méthode qui convertit l'Array String de code postal
     * en String à intégrer dans la BDD.
     * @param attribute String[]
     * @return String
     *
     */
    @Override
    public String convertToDatabaseColumn(String[] attribute) {
        // Si pas de données :
        if (attribute == null || attribute.length == 0) {
            return null;
        }
        // Envoi de l'attribut String :
        return String.join(",", attribute);
    }



    /**
     * Méthode qui convertit la chaine de caractères récupérée en BDD en tableau de chaines de caractères.
     * @param dbData String
     * @return String[]
     *
     */
    @Override
    public String[] convertToEntityAttribute(String dbData) {
        // Si pas de données :
        if (dbData == null || dbData.trim().isEmpty()) {
            return new String[0];
        }
        // Renvoie de l'attribut String[] :
        return dbData.split(",");
    }






}

