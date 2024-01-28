package com.example.CrudTraining.controller.iaController;

import com.example.CrudTraining.service.iaService.FaceRecognizerService;
import com.example.CrudTraining.service.iaService.GanModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * Controller pour manipuler le modèle de Génération d'image.
 *
 */
@RestController
@RequestMapping("/api/ia/gan-image-generation")
public class GanModelController {






    // *************************** Attributs *************************** //
    private final GanModelService ganModelService;






    // *************************** Constructeur *************************** //
    @Autowired
    public GanModelController(GanModelService ganModelService) { this.ganModelService = ganModelService; }






    // ************************************ Méthodes ************************************ //

    /**
     * Méthode pour générer des images avec le modèle GAN.
     *
     */
    @GetMapping("/generate-faces")
    public boolean generateImage(){
        return ganModelService.generateImage();
    }



    /**
     * Méthode pour générer des images avec le modèle GAN.
     *
     */
    @PostMapping("/train-gan-model")
    public boolean trainGanModel(@RequestParam int nbEpochs, @RequestParam int batchSize, @RequestParam double lr, @RequestParam int zDim, @RequestParam String device, @RequestParam int showStep, @RequestParam int saveStep){
        // ********************** TEST ********************** //
        System.out.println(nbEpochs);
        System.out.println(batchSize);
        System.out.println(lr);
        System.out.println(zDim);
        System.out.println(device);
        System.out.println(showStep);
        System.out.println(saveStep);
        // ********************** TEST ********************** //
        return ganModelService.trainGanModel(nbEpochs, batchSize, lr, zDim, device, showStep, saveStep);
    }






}



                /*
n_epochs = 10000    # Nombre d'époques d'entraînement.
batch_size = 128    # Taille du lot (batch) d'échantillons utilisés lors de chaque itération d'entraînement.
lr = 1e-4           # Taux d'apprentissage : Contrôle la taille des pas lors de l'optimisation. Définit la vitesse de convergence du modèle.
z_dim = 200         # Dimension de l'espace latent.
device = 'cpu'  # device = 'cuda'    # GPU  # Dispositif sur lequel le modèle est entraîné
cur_step = 0        # Étape courante dans l'entraînement. --> Suivi du progrès de l'entrainement.
crit_cycles = 5     # Spécifie le nombre d'itérations d'entraînement du critique pour chaque itération d'entraînement du générateur.
gen_losses = []     # Stocke les pertes du générateur à chaque itération d'entraînement.
crit_losses = []    # Stocke les pertes du critique à chaque itération d'entraînement.
show_step = 35      # Fréquence à laquelle la méthode show doit être appelée pour afficher des échantillons générés.
save_step = 35      # Fréquence à laquelle les modèles ou les poids du modèle sont sauvegardés pendant l'entraînement.
wandbact = 1        # Track les statistiques des poids et biais (paramètre optionnel). # Paramètre binaire (1 ou 0)
# pour activer ou désactiver le suivi des statistiques des poids et des biais<;
*/

