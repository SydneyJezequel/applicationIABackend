package com.example.CrudTraining.bo.ia.embeddingModel;






/**
 * Chemin du fichier du DataSet.
 *
 */
public class SelectDataSet {





    // *************************** Attributs *************************** //
    private String file_path;





    // *************************** Constructeur *************************** //

    public SelectDataSet() {
    }

    public SelectDataSet(String file_path) {
        this.file_path = file_path;
    }





    // *************************** Getter / Setter *************************** //

    public String getFilePath() {
        return file_path;
    }

    public void setFilePath(String file_path) {
        this.file_path = file_path;
    }






}

