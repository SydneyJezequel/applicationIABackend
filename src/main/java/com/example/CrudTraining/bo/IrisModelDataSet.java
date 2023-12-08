package com.example.CrudTraining.bo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;








/**
 * Réponse renvoyée par le projet du modèle de Machine Learning.
 * Cette classe correspond au DataSet des Iris.
 *
 */
public class IrisModelDataSet {





    // *************************** Attributs *************************** //
    private List<IrisModelResponse> dataLines;





    // *************************** Constructeur *************************** //
    public IrisModelDataSet() {}

    @JsonCreator
    public IrisModelDataSet(@JsonProperty("data_lines") List<IrisModelResponse> dataLines) {
        this.dataLines = dataLines;
    }





    // *************************** Getter / Setter *************************** //

    public List<IrisModelResponse> getDataLines() {
        return dataLines;
    }

    public void setDataLines(List<IrisModelResponse> dataLines) {
        this.dataLines = dataLines;
    }



}
