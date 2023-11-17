package com.example.CrudTraining.bo;





/**
 * Requête envoyée au Modèle de machine Learning qui prédit le type d'Iris.
 * Cette requête contient les paramètres pour calculer la prédiction.
 *
 */
public class IrisModelRequest {





    // *************************** Attributs *************************** //

    private double sepal_length;
    private double sepal_width;
    private double petal_length;
    private double petal_width;





    // *************************** Constructeur *************************** //

    public IrisModelRequest() {}

    public IrisModelRequest(double sepal_length, double sepal_width, double petal_length, double petal_width) {
        this.sepal_length = sepal_length;
        this.sepal_width = sepal_width;
        this.petal_length = petal_length;
        this.petal_width = petal_width;
    }




    // *************************** Getter / Setter *************************** //

    public double getSepal_length() {
        return sepal_length;
    }

    public void setSepal_length(double sepal_length) {
        this.sepal_length = sepal_length;
    }

    public double getSepal_width() {
        return sepal_width;
    }

    public void setSepal_width(double sepal_width) {
        this.sepal_width = sepal_width;
    }

    public double getPetal_length() {
        return petal_length;
    }

    public void setPetal_length(double petal_length) {
        this.petal_length = petal_length;
    }

    public double getPetal_width() {
        return petal_width;
    }

    public void setPetal_width(double petal_width) {
        this.petal_width = petal_width;
    }




}

