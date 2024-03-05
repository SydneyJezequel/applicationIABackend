package com.example.CrudTraining.bo;

import com.example.CrudTraining.bo.converter.StringArrayConverter;
import com.example.CrudTraining.bo.converter.StringArrayDeserializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import javax.persistence.*;






/**
 * Classe pour manipuler une Ville.
 *
 */
@Entity
@Table(name="city")
public class City {





    // *************************** Attributs *************************** //

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name="no_city")
    private Long no_city;

    @Column(nullable = true, name ="name")
    @JsonProperty("nom")
    private String name;

    @Column(nullable = true, name ="department_code")
    @JsonProperty("codeDepartement")
    private String department_code;

    @Column(nullable = true, name ="siren")
    private String siren;

    @Column(nullable = true, name ="region_code")
    @JsonProperty("codeRegion")
    private String region_code;

    @JsonDeserialize(using = StringArrayDeserializer.class)
    // Convertit un tableau de chaînes en une seule chaîne pour le stockage en BDD et inversement :
    @Convert(converter = StringArrayConverter.class)
    @Column(nullable = true, name ="postal_codes")
    @JsonProperty("codesPostaux")
    private String[] postal_codes;

    @Column(nullable = true, name ="population")
    private long population;





    // *************************** Constructeur *************************** //

    public City(){}

    public City(String name, String department_code, String siren, String region_code, String[] postal_codes, long population) {
        this.name = name;
        this.department_code = department_code;
        this.siren = siren;
        this.region_code = region_code;
        this.postal_codes = postal_codes;
        this.population = population;
    }

    public City(Long no_city, String name, String department_code, String siren, String region_code, String[] postal_codes, long population) {
        this.no_city = no_city;
        this.name = name;
        this.department_code = department_code;
        this.siren = siren;
        this.region_code = region_code;
        this.postal_codes = postal_codes;
        this.population = population;
    }





    // *************************** Getter / Setter *************************** //

    public Long getNo_city() {
        return no_city;
    }

    public void setNo_city(Long no_city) {
        this.no_city = no_city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment_code() {
        return department_code;
    }

    public void setDepartment_code(String department_code) {
        this.department_code = department_code;
    }

    public String getSiren() {
        return siren;
    }

    public void setSiren(String siren) {
        this.siren = siren;
    }

    public String getRegion_code() {
        return region_code;
    }

    public void setRegion_code(String region_code) {
        this.region_code = region_code;
    }

    public String[] getPostal_codes() {
        return postal_codes;
    }

    public void setPostal_codes(String[] postal_codes) {
        this.postal_codes = postal_codes;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }






}

