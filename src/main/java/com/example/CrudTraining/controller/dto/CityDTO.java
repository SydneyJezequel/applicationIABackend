package com.example.CrudTraining.controller.dto;






/**
 * DTO utilisé pour mapper la Classe City du Backend avec la classe City du Frontend.
 *
 */
public class CityDTO {





    // *************************** Attributs *************************** //

    private Long no_city;

    private String name;

    private String department_code;

    private String siren;

    private String region_code;

    private String[] postal_codes;

    private Long population;





    // *************************** Constructeur *************************** //

    public CityDTO(){}

    public CityDTO(String name, String department_code, String siren, String region_code, String[] postal_codes, Long population) {
        this.name = name;
        this.department_code = department_code;
        this.siren = siren;
        this.region_code = region_code;
        this.postal_codes = postal_codes;
        this.population = population;
    }

    public CityDTO(Long no_city, String name, String department_code, String siren, String region_code, String[] postal_codes, Long population) {
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

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }






}

