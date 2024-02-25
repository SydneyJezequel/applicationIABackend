package com.example.CrudTraining.bo.ia.randomForestmodel;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;






/**
 * Liste de réponses envoyée par le modèle de Machine Learning.
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

