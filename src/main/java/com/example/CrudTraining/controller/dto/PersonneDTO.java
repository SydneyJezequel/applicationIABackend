package com.example.CrudTraining.controller.dto;

import com.example.CrudTraining.bo.Personne;






/**
 * Classe Dto de l'objet Personne.
 * Elle permet de manipuler les objets de type Personne et leur photo
 * entre le Frontend et le Backend.
 *
 */
public class PersonneDTO {





    // *************************** Attributs *************************** //

    private Personne personne;
    private String photoBase64String;





    // *************************** Getter / Setter *************************** //

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public String getPhotoBase64String() {
        return photoBase64String;
    }

    public void setPhotoBase64String(String photoBase64String) {
        this.photoBase64String = photoBase64String;
    }






}

