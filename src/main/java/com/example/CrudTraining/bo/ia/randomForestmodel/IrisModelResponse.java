package com.example.CrudTraining.bo.ia.randomForestmodel;

import javax.persistence.*;






/**
 * Réponse envoyée par modèle de Machine Learning Random Forest qui prédit le type d'Iris.
 * Cette réponse contient la prédiction et les paramètres utilisés pour la calculer.
 *
 */
@Entity
@Table(name="iris_model_response")
public class IrisModelResponse {





    // *************************** Attributs *************************** //

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name="no_prediction")
    private Long no_prediction;

    @Column(nullable = false, name ="sepal_length")
    private double sepalLength;

    @Column(nullable = false, name ="sepal_width")
    private double sepalWidth;

    @Column(nullable = false, name ="petal_length")
    private double petalLength;

    @Column(nullable = false, name ="petal_width")
    private double petalWidth;

    @Column(nullable = false, name ="prediction")
    private String prediction;





    // *************************** Constructeur *************************** //

    public IrisModelResponse() {}

    public IrisModelResponse(double sepalLength, double sepalWidth, double petalLength, double petalWidth, String prediction) {
        this.sepalLength = sepalLength;
        this.sepalWidth = sepalWidth;
        this.petalLength = petalLength;
        this.petalWidth = petalWidth;
        this.prediction = prediction;
    }

    public IrisModelResponse(Long no_prediction, double sepalLength, double sepalWidth, double petalLength, double petalWidth, String prediction) {
        this.no_prediction = no_prediction;
        this.sepalLength = sepalLength;
        this.sepalWidth = sepalWidth;
        this.petalLength = petalLength;
        this.petalWidth = petalWidth;
        this.prediction = prediction;
    }





    // *************************** Getter / Setter *************************** //

    public Long getNo_prediction() {
        return no_prediction;
    }

    public void setNo_prediction(Long no_prediction) {
        this.no_prediction = no_prediction;
    }

    public double getSepalLength() {
        return sepalLength;
    }

    public void setSepalLength(double sepalLength) {
        this.sepalLength = sepalLength;
    }

    public double getSepalWidth() {
        return sepalWidth;
    }

    public void setSepalWidth(double sepalWidth) {
        this.sepalWidth = sepalWidth;
    }

    public double getPetalLength() {
        return petalLength;
    }

    public void setPetalLength(double petalLength) {
        this.petalLength = petalLength;
    }

    public double getPetalWidth() {
        return petalWidth;
    }

    public void setPetalWidth(double petalWidth) {
        this.petalWidth = petalWidth;
    }

    public String getPrediction() {
        return prediction;
    }

    public void setPrediction(String prediction) {
        this.prediction = prediction;
    }






}

