package com.example.CrudTraining.bo.ia.embeddingModel;






/**
 * Question posée au LLM.
 *
 */
public class QuestionInput {





    // *************************** Attributs *************************** //
    private String question;





    // *************************** Constructeur *************************** //

    public QuestionInput() {}

    public QuestionInput(String question) {
        this.question = question;
    }





    // *************************** Getter / Setter *************************** //

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }






}

