package com.example.CrudTraining.bo;

import javax.persistence.*;
import java.util.Date;






/**
 * Entité Personne
 */
@Entity
@Table(name="Personne")
public class Personne {




    // *************** Attributs ***************
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name="id_personne")
    Long no_personne;

    @Column(nullable = false, name ="nom")
    String nom;

    @Column(nullable = false, name ="prenom")
    String prenom;

    @Column(nullable = false, name="date_naissance")
    Date date_naissance;

    @Column(nullable = false, name="no_securite_sociale")
    long no_securite_sociale;






    // *************** Constructeur ***************

    public Personne() {}

    public Personne(String nom, String prenom, Date date_naissance, int no_securite_sociale) {
        this.nom = nom;
        this.prenom = prenom;
        this.date_naissance = date_naissance;
        this.no_securite_sociale = no_securite_sociale;
    }

    public Personne(Long no_personne, String nom, String prenom, Date date_naissance, int no_securite_sociale) {
        this.no_personne = no_personne;
        this.nom = nom;
        this.prenom = prenom;
        this.date_naissance = date_naissance;
        this.no_securite_sociale = no_securite_sociale;
    }






    // *************** Getter / Setter ***************

    public Long getNo_personne() {
        return no_personne;
    }

    public void setNo_personne(Long no_personne) {
        this.no_personne = no_personne;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    public long getNo_securite_sociale() {
        return no_securite_sociale;
    }

    public void setNo_securite_sociale(long no_securite_sociale) {
        this.no_securite_sociale = no_securite_sociale;
    }




}

