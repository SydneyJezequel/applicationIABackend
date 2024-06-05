package com.example.CrudTraining.service.iaService;






/**
 * Service pour FineTuner et utiliser un modèle Llm.
 *
 */
public interface FineTuningModelService {





    /**
     * Méthode qui pose une question au Llm.
     * @return boolean : succès/échec de l'exécution.
     *
     */
    public boolean fineTuneModel(int nbEpochs, int trainDatasetSize, int validationDatasetSize, int trainBatchSize, int evalBatchSize);



    /**
     * Méthode pour Fine-Tuner le modèle Llm.
     * @param question : question posée au Llm.
     * @param context : contexte de la question.
     * @return String : réponse.
     *
     */
    public String executeModel(String question, String context);





}

