package com.example.CrudTraining.controller.iaController;

import com.example.CrudTraining.service.iaService.FineTuningModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;






/**
 * Controller pour Fine-Tuner et utiliser un modèle Llm.
 *
 */
@RestController
@RequestMapping("/api/ia/llm")
public class FineTuningLlmModelController {





    // *************************** Attributs *************************** //

    private final FineTuningModelService fineTuningModelService;





    // *************************** Constructeur *************************** //
    @Autowired
    public FineTuningLlmModelController(FineTuningModelService fineTuningModelService) {
        this.fineTuningModelService = fineTuningModelService;
    }





    // *************************** Méthodes *************************** //

    /**
     * Méthode qui pose une question au Llm.
     * @return boolean : succès/échec de l'exécution.
     *
     */
    @PostMapping("/fine-tune-model")
    public boolean fineTuneModel(@RequestParam int nbEpochs, @RequestParam int trainDatasetSize, @RequestParam int validationDatasetSize, @RequestParam int trainBatchSize, @RequestParam int evalBatchSize){
        return fineTuningModelService.fineTuneModel(nbEpochs, trainDatasetSize, validationDatasetSize, trainBatchSize, evalBatchSize);
    }



    /**
     * Méthode pour Fine-Tuner le modèle Llm.
     * @param question : question posée au Llm.
     * @param context : contexte de la question.
     * @return String : réponse.
     *
     */
    @PostMapping("/execute-model")
    public String executeModel(@RequestParam String question, @RequestParam String context){
        return fineTuningModelService.executeModel(question, context);
    }





}

