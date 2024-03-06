package com.example.CrudTraining.bo;

import javax.persistence.*;
import java.util.Date;






/**
 * Classe pour manipuler une Personne.
 *
 */
@Entity
@Table(name="person")
public class Person {





    // *************************** Attributs *************************** //
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name="no_person")
    private Long no_person;

    @Column(nullable = false, name ="name")
    private String name;

    @Column(nullable = false, name ="surname")
    private String surname;

    @Column(nullable = false, name="birth_date")
    private Date birth_date;

    @Column(nullable = false, name="no_social_safety")
    private long no_social_safety;

    @Lob
    @Column(name = "photo")
    private byte[] photo;





    // *************************** Constructeur *************************** //

    public Person() {}

    public Person(String name, String surname, Date birth_date, long no_social_safety) {
        this.name = name;
        this.surname = surname;
        this.birth_date = birth_date;
        this.no_social_safety = no_social_safety;
    }

    public Person(Long no_person, String name, String surname, Date birth_date, long no_social_safety) {
        this.no_person = no_person;
        this.name = name;
        this.surname = surname;
        this.birth_date = birth_date;
        this.no_social_safety = no_social_safety;
    }

    public Person(Long no_person, String name, String surname, Date birth_date, long no_social_safety, byte[] photo) {
        this.no_person = no_person;
        this.name = name;
        this.surname = surname;
        this.birth_date = birth_date;
        this.no_social_safety = no_social_safety;
        this.photo = photo;
    }





    // *************************** Getter / Setter *************************** //

    public Long getNo_person() {
        return no_person;
    }

    public void setNo_person(Long no_person) {
        this.no_person = no_person;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public long getNo_social_safety() {
        return no_social_safety;
    }

    public void setNo_social_safety(long no_social_safety) {
        this.no_social_safety = no_social_safety;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }






}

