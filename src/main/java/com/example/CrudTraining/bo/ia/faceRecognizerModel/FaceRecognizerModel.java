package com.example.CrudTraining.bo.ia.faceRecognizerModel;

import lombok.Getter;
import javax.persistence.*;






/**
 * Classe du modèle de Reconnaissance faciale sélectionné.
 *
 */
@Getter
@Entity
@Table(name="face_recognizer_model")
public class FaceRecognizerModel {





        // *************************** Attributs *************************** //

        @Id
        @Column(nullable = true, name ="no_model")
        private Long no_model;

        @Column(nullable = true, name ="model")
        private String modele;





        // *************************** Constructeur *************************** //

        public FaceRecognizerModel() {}

        public FaceRecognizerModel(Long no_model, String modele) {
                this.no_model = no_model;
                this.modele = modele;
        }





        // *************************** Getter / Setter *************************** //

        public Long getNo_model() {
                return no_model;
        }

        public void setNo_model(Long no_model) {
                this.no_model = no_model;
        }

        public String getModel() {
                return modele;
        }

        public void setModel(String modele) {
                this.modele = modele;
        }






}

