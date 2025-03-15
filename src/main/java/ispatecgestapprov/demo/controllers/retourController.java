package ispatecgestapprov.demo.controllers;


import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import ispatecgestapprov.demo.entities.article;
import ispatecgestapprov.demo.entities.commande;
import ispatecgestapprov.demo.entities.fournisseur;
import ispatecgestapprov.demo.entities.ligneCommande;
import ispatecgestapprov.demo.entities.ligneRetour;
import ispatecgestapprov.demo.entities.retour;
import ispatecgestapprov.demo.repositories.commandeRepository;
import ispatecgestapprov.demo.repositories.fournisseurRepository;
import ispatecgestapprov.demo.repositories.retourRepository;
import ispatecgestapprov.demo.repositories.articleRepository;
import ispatecgestapprov.demo.repositories.ligneRetourRepository;
import ispatecgestapprov.demo.repositories.ligneCommandeRepository;

@RestController
@RequestMapping(value = "/anonyme")
public class retourController {
    @Autowired
    retourRepository retourRepository;
    @Autowired
    articleRepository produitRepository;
    @Autowired
    commandeRepository commandeRepository;
    @Autowired
    fournisseurRepository fournisseurRepository;
    @Autowired
    ligneRetourRepository ligneRetourRepository;
    @Autowired
    articleRepository articleRepository;
    @Autowired
    ligneCommandeRepository ligneCommandeRepository;




    @PostMapping
public ResponseEntity<String> ajouterRetour(@RequestBody Map<String, Object> request) {
    try {
        // ✅ Vérification des champs obligatoires
        if (!request.containsKey("date_retour") || !request.containsKey("commande_id") || 
            !request.containsKey("fournisseur_id") || !request.containsKey("articles")) {
            return ResponseEntity.badRequest().body("Données invalides !");
        }

        // 🔍 Récupération de la commande et du fournisseur
        int commandeId = Integer.parseInt(request.get("commande_id").toString());
        int fournisseurId = Integer.parseInt(request.get("fournisseur_id").toString());

        commande commande = commandeRepository.findById(commandeId)
                .orElseThrow(() -> new RuntimeException("Commande introuvable avec ID : " + commandeId));

        fournisseur fournisseur = fournisseurRepository.findById(fournisseurId)
                .orElseThrow(() -> new RuntimeException("Fournisseur introuvable avec ID : " + fournisseurId));

        // 📌 Création de l'objet retour
        retour retour = new retour();
        retour.setDate_retour(LocalDate.parse(request.get("date_retour").toString()));
        retour.setNote(request.getOrDefault("note", "").toString());
        retour.setStatut("En cours");
        retour.setCommandes(commande);
        retour.setFournisseurs(fournisseur);

        retour retourEnregistre = retourRepository.save(retour);

        // 🔍 Gestion des articles retournés
        List<Map<String, Object>> articles = (List<Map<String, Object>>) request.get("articles");

        for (Map<String, Object> articleMap : articles) {
            int articleId = Integer.parseInt(articleMap.get("id").toString());
            int quantite_retournee = Integer.parseInt(articleMap.get("quantite_retournee").toString());
            String raison_retour = articleMap.get("raison_retour").toString();
            String etat = articleMap.get("etat").toString();

            // 🔍 Vérification de l'article
            article article = articleRepository.findById(articleId)
                    .orElseThrow(() -> new RuntimeException("Article introuvable avec ID : " + articleId));

            // 🔍 Vérification que la quantité retournée ne dépasse pas la quantité reçue
            ligneCommande ligneCommande = ligneCommandeRepository.findByCommandesAndArticles(commande, article)
                    .orElseThrow(() -> new RuntimeException("Article non trouvé dans la commande"));

            if (quantite_retournee > ligneCommande.getQuantite_recue()) {
                return ResponseEntity.badRequest().body("La quantité retournée dépasse la quantité reçue pour l'article ID : " + articleId);
            }

            // 📌 Création de la ligne de retour
            ligneRetour ligneRetour = new ligneRetour();
            ligneRetour.setArticles(article);
            ligneRetour.setRetours(retourEnregistre);
            ligneRetour.setQuantite_retournee(quantite_retournee);
            ligneRetour.setRaison_retour(raison_retour);
            ligneRetour.setEtat(etat);

            ligneRetourRepository.save(ligneRetour);
        }

        return ResponseEntity.ok("Retour enregistré avec succès");

    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erreur : " + e.getMessage());
    }
}


    

    
   

    //Liste de Tous les retours
    @GetMapping("/retour")
    public List<retour> listerretours(){
        return retourRepository.findAll();
    }


    //Liste des Articles retourne d'une commande
    @GetMapping("/retour/commande/{commandeId}")
    public ResponseEntity<List<ligneRetour>> getRetoursByCommande(@PathVariable int commandeId) {
        List<ligneRetour> lignesRetour = ligneRetourRepository.findByRetours_Commandes_Id(commandeId);
        return ResponseEntity.ok(lignesRetour);
    }

   // Supprimer un article
    @DeleteMapping("/retour/{id}")
    public ResponseEntity<String> supprimerretour(@PathVariable int id){
        retourRepository.deleteById(id);
        return ResponseEntity.ok("retour effacé avec succès");

    }


    //Liste des Articles retourne d'un fournisseur
    @GetMapping("/retour/fournisseur/{fournisseurId}")
    public ResponseEntity<List<ligneRetour>> getRetoursByFournisseur(@PathVariable int fournisseurId) {
        List<ligneRetour> lignesRetour = ligneRetourRepository.findByRetours_Fournisseurs_Id(fournisseurId);
        return ResponseEntity.ok(lignesRetour);
    }



    
    
}
