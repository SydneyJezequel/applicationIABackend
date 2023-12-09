package com.example.CrudTraining.bo.ia.reconaissancefacialemodele;

import lombok.Getter;
import javax.persistence.*;






/**
 * Table qui représente le modèle choisi
 * pour faire de la reconnaissance faciale.
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

        @Column(nullable = true, name ="modele")
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

        public String getModele() {
                return modele;
        }

        public void setModele(String modele) {
                this.modele = modele;
        }







}

