package com.example.CrudTraining.bo.convertisseur;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;






/**
 *  Classe qui déssérialise un String[] JSON en String.
 *
 */
public class StringArrayDeserializer extends StdDeserializer<String[]> {





    // *************************** Constructeur *************************** //

    public StringArrayDeserializer() {
        super(String[].class);
    }





    // *************************** Méthodes *************************** //

    @Override
    public String[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);  // Récupère l'objet JSON à partir du parser JSON.
        String[] strings = new String[node.size()];   // Crée un tableau de chaînes de la taille de l'objet JSON.
        int index = 0;
        node.textValue();
        for (JsonNode element : node) {
            strings[index++] = node.textValue(); // Convertit chaque élément en chaîne de caractères et l'ajoute au tableau
        }
        return strings;
    }





}

