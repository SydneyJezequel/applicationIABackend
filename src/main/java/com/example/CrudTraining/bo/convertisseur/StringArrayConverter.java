package com.example.CrudTraining.bo.convertisseur;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;










/**
 *  Classe qui convertit un String[] en String et une String en String[].
 *
 */
@Converter
public class StringArrayConverter implements AttributeConverter<String[], String> {



    /**
     * Méthode qui convertit l'Array String de code postal
     * en String à intégrer dans la BDD.
     * @param attribute String[]
     * @return String
     *
     */
    @Override
    public String convertToDatabaseColumn(String[] attribute) {
        // S'il n'y a pas de données :
        if (attribute == null || attribute.length == 0) {
            return null;
        }

        // Renvoie de l'attribut String :
        return String.join(",", attribute);
    }



    /**
     * Méthode qui convertit la String récupérée en BDD en l'attribut Array String
     * @param dbData String
     * @return String[]
     *
     */
    @Override
    public String[] convertToEntityAttribute(String dbData) {
        // S'il n'y a pas de données :
        if (dbData == null || dbData.trim().isEmpty()) {
            return new String[0];
        }

        // Renvoie de l'attribut String[] :
        return dbData.split(",");
    }



}

