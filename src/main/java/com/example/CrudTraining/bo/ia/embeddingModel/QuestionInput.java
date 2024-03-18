package com.example.CrudTraining.bo.ia.embeddingModel;






/**
 * Question posée au LLM.
 *
 */
public class QuestionInput {





    // *************************** Attributs *************************** //
    private String question;

    private String category;





    // *************************** Constructeur *************************** //

    public QuestionInput() {}

    public QuestionInput(String question, String category) {
        this.question = question;
        this.category = category;
    }





    // *************************** Getter / Setter *************************** //

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }






}

