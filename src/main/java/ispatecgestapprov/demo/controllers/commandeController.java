package ispatecgestapprov.demo.controllers;

import java.math.BigDecimal;
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
import ispatecgestapprov.demo.entities.site;
import ispatecgestapprov.demo.entities.transporteur;
import ispatecgestapprov.demo.repositories.fournisseurRepository;
import ispatecgestapprov.demo.repositories.ligneCommandeRepository;
import ispatecgestapprov.demo.repositories.commandeRepository;
import ispatecgestapprov.demo.repositories.siteRepository;
import ispatecgestapprov.demo.repositories.articleRepository; 
import ispatecgestapprov.demo.repositories.transporteurRepository; 

@RestController
@RequestMapping(value = "/anonyme")
public class commandeController {
    @Autowired
    commandeRepository commandeRepository;
    @Autowired
    fournisseurRepository fournisseurRepository;
    @Autowired
    siteRepository siteRepository;
    @Autowired
    articleRepository articleRepository;
    @Autowired
    transporteurRepository transporteurRepository;
    @Autowired
    ligneCommandeRepository ligneCommandeRepository;

    
    @PostMapping("/commande")
        public ResponseEntity<String> addCommandes(@RequestBody Map<String, Object> map) {
            try {
                // 1️⃣ Création de l'objet commande
                commande commande = new commande();
                commande.setDate_emission(LocalDate.parse(map.get("date_emission").toString()));
                commande.setDate_reception_prevue(LocalDate.parse(map.get("date_reception_prevue").toString()));
                commande.setAdresse_facturation(map.get("adresse_facturation").toString());
                commande.setAdresse_expedition(map.get("adresse_expedition").toString());
                commande.setNotes_commande(map.get("notes_commande").toString());
                commande.setNumerosuivi(map.get("numerosuivi").toString());
                commande.setNotes_internes(map.get("notes_internes").toString());
                commande.setStatut(map.get("statut").toString());

                // 2️⃣ Gestion des relations (fournisseur, site, transporteur)
                int fournisseurId = Integer.parseInt(map.get("fournisseur_id").toString());
                int siteId = Integer.parseInt(map.get("site_id").toString());
                int transporteurId = Integer.parseInt(map.get("transporteur_id").toString());

                // Vérification et récupération des relations
                fournisseur fournisseur = fournisseurRepository.findById(fournisseurId)
                        .orElseThrow(() -> new RuntimeException("Fournisseur introuvable avec ID : " + fournisseurId));
                site site = siteRepository.findById(siteId)
                        .orElseThrow(() -> new RuntimeException("Site introuvable avec ID : " + siteId));
                transporteur transporteur = transporteurRepository.findById(transporteurId)
                        .orElseThrow(() -> new RuntimeException("Transporteur introuvable avec ID : " + transporteurId));

                commande.setFournisseurs(fournisseur);
                commande.setSites(site);
                commande.setTransporteurs(transporteur);

                // 3️⃣ Sauvegarde de la commande avant d'ajouter les articles
                commande savedCommande = commandeRepository.saveAndFlush(commande);

                // 4️⃣ Ajout des articles via ligneCommande
                List<Map<String, Object>> articles = (List<Map<String, Object>>) map.get("articles");

                for (Map<String, Object> articleMap : articles) {
                    int articleId = Integer.parseInt(articleMap.get("id").toString());

                    // Vérification et récupération de l'article
                    article article = articleRepository.findById(articleId)
                            .orElseThrow(() -> new RuntimeException("Article introuvable avec ID : " + articleId));

                    ligneCommande ligneCommande = new ligneCommande();
                    ligneCommande.setArticles(article);
                    ligneCommande.setCommandes(savedCommande);
                    ligneCommande.setQuantite_commandee(Integer.parseInt(articleMap.get("quantity").toString()));
                    ligneCommande.setPrix_achat(new BigDecimal(articleMap.get("prix_achat").toString()));
                    ligneCommande.setRemise(new BigDecimal(articleMap.get("prixRemise").toString()));
                    ligneCommande.setQuantite_recue(0);  // Par défaut, aucun article reçu
                    ligneCommande.setStatut("En attente");

                    ligneCommandeRepository.save(ligneCommande);
                }

                return new ResponseEntity<>("Commande créée avec succès", HttpStatus.OK);

            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Erreur lors de la création de la commande : " + e.getMessage());
            }
        }



        


    
   
    @GetMapping("/commande")
    public List<commande> listercommandes(){
        return commandeRepository.findAll();
    }

    @GetMapping("/commande/expediee")
    public ResponseEntity<List<commande>> listerCommandesExpediees() {
        List<commande> commandes = commandeRepository.findByStatut("Expédiée");
        return ResponseEntity.ok(commandes);
    }


    @GetMapping("/commande/{id}")
    public ResponseEntity<commande> getCommandeById(@PathVariable int id) {
        commande commande = commandeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Commande introuvable avec ID : " + id));

        return ResponseEntity.ok(commande);
    }


    @GetMapping("/commande/recue")
    public ResponseEntity<List<commande>> listerCommandesRecues() {
        List<commande> commandes = commandeRepository.findByStatut("Reçue");
        return ResponseEntity.ok(commandes);
    }


    

    



    @DeleteMapping("/commande/{id}")
    public ResponseEntity<String> supprimercommande(@PathVariable int id){
        commandeRepository.deleteById(id);
        return ResponseEntity.ok("commande effacé avec succès");

    }

    @PostMapping("/commande/reception")
public ResponseEntity<String> receptionArticles(@RequestBody Map<String, Object> map) {
    try {
        String numeroSuivi = map.get("numerosuivi").toString();
        List<Map<String, Object>> articlesRecus = (List<Map<String, Object>>) map.get("articles");

        commande commande = commandeRepository.findByNumerosuivi(numeroSuivi)
            .orElseThrow(() -> new RuntimeException("Commande non trouvée avec numéro de suivi : " + numeroSuivi));

        boolean allReceived = true;

        for (Map<String, Object> articleRecus : articlesRecus) {
            int articleId = Integer.parseInt(articleRecus.get("id").toString());
            int quantiteRecue = Integer.parseInt(articleRecus.get("quantite_recue").toString());

            ligneCommande ligneCommande = ligneCommandeRepository
                .findByCommandesAndArticles(commande, articleRepository.findById(articleId)
                .orElseThrow(() -> new RuntimeException("Article introuvable avec ID : " + articleId)))
                .orElseThrow(() -> new RuntimeException("Article non trouvé dans la commande"));

            int nouvelleQuantiteRecue = ligneCommande.getQuantite_recue() + quantiteRecue;
            ligneCommande.setQuantite_recue(nouvelleQuantiteRecue);

            if (nouvelleQuantiteRecue == 0) {
                //  Si la quantité reçue est 0, on ne change pas le statut
            } else if (nouvelleQuantiteRecue >= ligneCommande.getQuantite_commandee()) {
                ligneCommande.setStatut("Reçu");
            } else {
                ligneCommande.setStatut("Partiellement reçu");
                allReceived = false;
            }
            

            article article = ligneCommande.getArticles();
            article.setQuantite(article.getQuantite() + quantiteRecue);
            articleRepository.save(article);
            ligneCommandeRepository.save(ligneCommande);
        }

        if (allReceived) {
            commande.setStatut("Reçue");
        } else {
            commande.setStatut("Partiellement reçue");
        }

        commandeRepository.save(commande);
        return ResponseEntity.ok("Réception des articles enregistrée avec succès");

    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erreur lors de la réception : " + e.getMessage());
    }
}



}