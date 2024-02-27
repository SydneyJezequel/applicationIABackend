package com.example.CrudTraining.bo.converter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;






/**
 *  Classe qui désérialise un tableau de chaînes de caractères au format JSON en une chaîne de caractères.
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
        // Récupèrer l'objet JSON :
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String[] strings = new String[node.size()];
        int index = 0;
        node.textValue();
        // Conversion de chaque élément en chaîne de caractères :
        for (JsonNode element : node) {
            strings[index++] = node.textValue();
        }
        return strings;
    }






}

