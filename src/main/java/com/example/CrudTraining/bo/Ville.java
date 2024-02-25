package com.example.CrudTraining.bo;

import com.example.CrudTraining.bo.converter.StringArrayConverter;
import com.example.CrudTraining.bo.converter.StringArrayDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import javax.persistence.*;






/**
 * Classe pour manipuler une Ville.
 *
 */
@Entity
@Table(name="ville")
public class Ville {





    // *************************** Attributs *************************** //

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name="no_ville")
    private Long no_ville;

    @Column(nullable = true, name ="nom")
    private String nom;

    @Column(nullable = true, name ="code_departement")
    private String codeDepartement;

    @Column(nullable = true, name ="siren")
    private String siren;

    @Column(nullable = true, name ="code_region")
    private String codeRegion;

    @JsonDeserialize(using = StringArrayDeserializer.class)
    // Convertit un tableau de chaînes en une seule chaîne pour le stockage en BDD et inversement :
    @Convert(converter = StringArrayConverter.class)
    @Column(nullable = true, name ="code_codes")
    private String[] codesPostaux;

    @Column(nullable = true, name ="population")
    private long population;





    // *************************** Constructeur *************************** //

    public Ville(){}

    public Ville(String nom, String codeDepartement, String siren, String codeRegion, String[] codesPostaux, long population) {
        this.nom = nom;
        this.codeDepartement = codeDepartement;
        this.siren = siren;
        this.codeRegion = codeRegion;
        this.codesPostaux = codesPostaux;
        this.population = population;
    }

    public Ville(Long no_ville, String nom, String codeDepartement, String siren, String codeRegion, String[] codesPostaux, long population) {
        this.no_ville = no_ville;
        this.nom = nom;
        this.codeDepartement = codeDepartement;
        this.siren = siren;
        this.codeRegion = codeRegion;
        this.codesPostaux = codesPostaux;
        this.population = population;
    }





    // *************************** Getter / Setter *************************** //

    public Long getNo_ville() {
        return no_ville;
    }

    public void setNo_ville(Long no_ville) {
        this.no_ville = no_ville;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCodeDepartement() {
        return codeDepartement;
    }

    public void setCodeDepartement(String codeDepartement) {
        this.codeDepartement = codeDepartement;
    }

    public String getSiren() {
        return siren;
    }

    public void setSiren(String siren) {
        this.siren = siren;
    }

    public String getCodeRegion() {
        return codeRegion;
    }

    public void setCodeRegion(String codeRegion) {
        this.codeRegion = codeRegion;
    }

    public String[] getCodesPostaux() {
        return codesPostaux;
    }

    public void setCodesPostaux(String[] codesPostaux) {
        this.codesPostaux = codesPostaux;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }






}

