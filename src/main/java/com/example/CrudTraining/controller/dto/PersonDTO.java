package com.example.CrudTraining.controller.dto;

import com.example.CrudTraining.bo.Person;






/**
 * Classe Dto de l'objet Personne.
 * Elle permet de manipuler les objets de type Personne et leur photo
 * entre le Frontend et le Backend.
 *
 */
public class PersonDTO {





    // *************************** Attributs *************************** //

    private Person person;
    private String photoBase64String;





    // *************************** Getter / Setter *************************** //

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getPhotoBase64String() {
        return photoBase64String;
    }

    public void setPhotoBase64String(String photoBase64String) {
        this.photoBase64String = photoBase64String;
    }






}

