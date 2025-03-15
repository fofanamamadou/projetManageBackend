package ispatecgestapprov.demo.controllers;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import ispatecgestapprov.demo.entities.article;
import ispatecgestapprov.demo.entities.ligneCommande;
import ispatecgestapprov.demo.repositories.categorieRepository;
import ispatecgestapprov.demo.repositories.sousCategorieRepository;
import ispatecgestapprov.demo.repositories.uniteMesureRepository;
import ispatecgestapprov.demo.repositories.fournisseurRepository;
import ispatecgestapprov.demo.repositories.articleRepository;
import ispatecgestapprov.demo.repositories.siteRepository;
import ispatecgestapprov.demo.repositories.ligneCommandeRepository;

@RestController
@RequestMapping(value = "/anonyme")
public class articleController {
    @Autowired
    articleRepository articleRepository;
    @Autowired
    sousCategorieRepository sousCategorieRepository;
    @Autowired
    categorieRepository categorieRepository;
    @Autowired
    fournisseurRepository fournisseurRepository;
    @Autowired
    siteRepository siteRepository;
    @Autowired
    uniteMesureRepository uniteMesureRepository;
    @Autowired
    ligneCommandeRepository ligneCommandeRepository;




    @PostMapping("/produit")
    public ResponseEntity<String> addProduits(@RequestBody List<Map<String, String>> articles) {
        try {
            for (Map<String, String> map : articles) {
                article produit = new article();

                // Mapping des champs
                produit.setPrix_achat(new BigDecimal(map.get("prix_achat")));
                produit.setPrix_vente(new BigDecimal(map.get("prix_vente")));
                produit.setImage(map.get("image"));
                produit.setQuantite(Integer.parseInt(map.get("quantite")));
                produit.setSeuil_minimum(Integer.parseInt(map.get("seuil_minimum")));
                produit.setQuantite_maximum(Integer.parseInt(map.get("quantite_maximum")));
                produit.setNote(map.get("note"));
                produit.setNumber_fournisseur(map.get("number_fournisseur"));
                produit.setDescription(map.get("description"));
                produit.setCode_barre(map.get("code_barre"));
                produit.setTaxe(map.get("taxe"));
                produit.setSite_web(map.get("site_web"));
                produit.setDate_creation(Date.valueOf(LocalDate.now()));

                // Relations avec d'autres entités
                if (map.get("sousCategorie_id") != null) {
                    int sousCategorieId = Integer.parseInt(map.get("sousCategorie_id"));
                    produit.setSousCategories(sousCategorieRepository.findById(sousCategorieId)
                            .orElseThrow(() -> new RuntimeException("Sous-catégorie introuvable : " + sousCategorieId)));
                }

                if (map.get("categorie_id") != null) {

                    int categorieId = Integer.parseInt(map.get("categorie_id"));
                    produit.setCategories(categorieRepository.findById(categorieId)
                            .orElseThrow(() -> new RuntimeException("Catégorie introuvable : " + categorieId)));
                }


                if (map.get("fournisseur_id") != null) {
                    int fournisseurId = Integer.parseInt(map.get("fournisseur_id"));
                    produit.setFournisseurs(fournisseurRepository.findById(fournisseurId)
                            .orElseThrow(() -> new RuntimeException("Fournisseur introuvable : " + fournisseurId)));
                }

                if (map.get("uniteMesure_id") != null) {

                    int uniteMesureId = Integer.parseInt(map.get("uniteMesure_id"));
                    produit.setUniteMesures(uniteMesureRepository.findById(uniteMesureId)
                            .orElseThrow(() -> new RuntimeException("Unité de mesure introuvable : " + uniteMesureId)));
                }


                if (map.get("site_id") != null) {

                    int siteId = Integer.parseInt(map.get("site_id"));
                    produit.setSites(siteRepository.findById(siteId)
                            .orElseThrow(() -> new RuntimeException("Site introuvable : " + siteId)));
                }


                // Sauvegarde de l'article
                articleRepository.saveAndFlush(produit);
            }
            return new ResponseEntity<>("Produits créés avec succès", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erreur lors de la création des produits : " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

        


    @PutMapping("/produit/{id}")
    public ResponseEntity<article> modifierproduit(@RequestBody Map<String, Object> map, @PathVariable int id) {
        article produit = articleRepository.findById(id).get();
        produit.setPrix_achat(new BigDecimal(map.get("prix_achat").toString()));
        produit.setPrix_vente(new BigDecimal(map.get("prix_vente").toString()));
        produit.setImage(map.get("image").toString());
        produit.setNote(map.get("note").toString());
        produit.setSite_web(map.get("site_web").toString());
        produit.setNumber_fournisseur(map.get("number_fournisseur").toString());
        produit.setDescription(map.get("description").toString());
        produit.setCode_barre(map.get("code_barre").toString());
        produit.setTaxe(map.get("taxe").toString());

        int sousCategorieId = Integer.parseInt(map.get("sousCategorie_id").toString());
        produit.setSousCategories(sousCategorieRepository.findById(sousCategorieId).get());

        int categorieId = Integer.parseInt(map.get("categorie_id").toString());
        produit.setCategories(categorieRepository.findById(categorieId).get());

        int fournisseurId = Integer.parseInt(map.get("fournisseur_id").toString());
        produit.setFournisseurs(fournisseurRepository.findById(fournisseurId).get());
        
        int uniteMesureId = Integer.parseInt(map.get("uniteMesure_id").toString());
        produit.setUniteMesures(uniteMesureRepository.findById(uniteMesureId).get());

        

        articleRepository.flush();
        return ResponseEntity.ok(produit);
    }

    @GetMapping("/produit")
    public List<article> listerproduits(){
        return articleRepository.findAll();
    }

    @DeleteMapping("/produit/{id}")
    public ResponseEntity<String> supprimerproduit(@PathVariable int id){
        articleRepository.deleteById(id);
        return ResponseEntity.ok("produit effacé avec succès");

    }

    

    @GetMapping("/articles/fournisseur/{fournisseurId}")
    public ResponseEntity<List<article>> getArticlesByFournisseur(@PathVariable int fournisseurId) {
        List<article> articles = articleRepository.findByFournisseurs_Id(fournisseurId);
        return ResponseEntity.ok(articles);
    }

    // @GetMapping("/articles/fournisseur/{id}/expedies")
    // public ResponseEntity<List<ligneCommande>> getArticlesExpediesFournisseur(@PathVariable int id) {
    //     List<ligneCommande> lignes = ligneCommandeRepository.findByCommandes_Fournisseurs_IdAndStatut(id, "En attente");
    //     return ResponseEntity.ok(lignes);
    // }
    
    @GetMapping("/articles/fournisseur/{id}/expedies")
    public ResponseEntity<List<Map<String, Object>>> getArticlesExpediesFournisseur(@PathVariable int id) {
        List<ligneCommande> lignes = ligneCommandeRepository.findByCommandes_Fournisseurs_IdAndStatut(id, "En attente");

        List<Map<String, Object>> result = new ArrayList<>();

        for (ligneCommande ligne : lignes) {
            Map<String, Object> ligneData = new HashMap<>();
            ligneData.put("id", ligne.getId());
            ligneData.put("quantite_commandee", ligne.getQuantite_commandee());
            ligneData.put("quantite_recue", ligne.getQuantite_recue());
            ligneData.put("prix_achat", ligne.getPrix_achat());
            ligneData.put("statut", ligne.getStatut());
            
            // Ajouter les informations de l'article
            if (ligne.getArticles() != null) {
                Map<String, Object> articleData = new HashMap<>();
                articleData.put("id", ligne.getArticles().getId());
                articleData.put("description", ligne.getArticles().getDescription());
                articleData.put("code_barre", ligne.getArticles().getCode_barre());
                articleData.put("site", ligne.getArticles().getSites() != null ? ligne.getArticles().getSites().getNom() : null);
                ligneData.put("articles", articleData);
            }

            // 🔥 Ajouter le numéro de suivi via la relation commande
            if (ligne.getCommandes() != null) {
                ligneData.put("numerosuivi", ligne.getCommandes().getNumerosuivi());
            } else {
                ligneData.put("numerosuivi", null);
            }

            result.add(ligneData);
        }

        return ResponseEntity.ok(result);
    }

        //  Lister les articles d’un fournisseur qui sont en attente ou partiellement reçus
    @GetMapping("/articles/fournisseur/{id}/retours")
    public ResponseEntity<List<Map<String, Object>>> getArticlesRetourFournisseur(@PathVariable int id) {
        List<ligneCommande> lignes = ligneCommandeRepository.findArticlesEnAttenteOuPartiellementRecu(id);

        List<Map<String, Object>> result = new ArrayList<>();

        for (ligneCommande ligne : lignes) {
            Map<String, Object> ligneData = new HashMap<>();
            ligneData.put("id", ligne.getId());
            ligneData.put("quantite_commandee", ligne.getQuantite_commandee());
            ligneData.put("quantite_recue", ligne.getQuantite_recue());
            ligneData.put("prix_achat", ligne.getPrix_achat());
            ligneData.put("statut", ligne.getStatut());

            // Ajouter les informations de l'article
            if (ligne.getArticles() != null) {
                Map<String, Object> articleData = new HashMap<>();
                articleData.put("id", ligne.getArticles().getId());
                articleData.put("description", ligne.getArticles().getDescription());
                articleData.put("code_barre", ligne.getArticles().getCode_barre());
                articleData.put("site", ligne.getArticles().getSites() != null ? ligne.getArticles().getSites().getNom() : null);
                ligneData.put("articles", articleData);
            }

            // Ajouter le numéro de suivi de la commande
            ligneData.put("numerosuivi", ligne.getCommandes() != null ? ligne.getCommandes().getNumerosuivi() : null);

            result.add(ligneData);
        }

        return ResponseEntity.ok(result);
    }






    
    
}
