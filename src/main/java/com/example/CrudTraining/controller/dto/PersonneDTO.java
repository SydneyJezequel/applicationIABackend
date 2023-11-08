package com.example.CrudTraining.controller.dto;

import com.example.CrudTraining.bo.Personne;






/**
 * Classe Dto pour l'objet Personne.
 * Utilisée pour manipuler les objets Personne et leur photo entre le front et le back.
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

