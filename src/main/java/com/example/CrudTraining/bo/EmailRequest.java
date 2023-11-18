package com.example.CrudTraining.bo;






/**
 * Entité EmailRequest : Mail utilisés pour le formulaire de contact.
 *
 */
public class EmailRequest {





    // *************************** Attributs *************************** //

    private String email;
    private String message;





    // *************************** Constructeur *************************** //

    public EmailRequest(){}

    public EmailRequest(String email, String message) {
        this.email = email;
        this.message = message;
    }





    // *************************** Getter / Setter *************************** //

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }





}

