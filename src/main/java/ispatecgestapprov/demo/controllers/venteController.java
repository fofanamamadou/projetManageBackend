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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import ispatecgestapprov.demo.entities.article;
import ispatecgestapprov.demo.entities.vente;
import ispatecgestapprov.demo.repositories.clientRepository;
import ispatecgestapprov.demo.repositories.utilisateurRepository;
import ispatecgestapprov.demo.repositories.articleRepository;
import ispatecgestapprov.demo.repositories.venteRepository;
import ispatecgestapprov.demo.repositories.siteRepository;

@RestController
@RequestMapping(value = "/anonyme")
public class venteController {
    @Autowired
    venteRepository venteRepository;
    @Autowired
    articleRepository articleRepository;
    @Autowired
    clientRepository clientRepository;
    @Autowired
    siteRepository siteRepository;
    @Autowired
    utilisateurRepository utilisateurRepository;




   @PostMapping("/vente")
        public ResponseEntity<String> enregistrerVente(@RequestBody Map<String, Object> payload) {
            try {
                // Création de l'objet Vente
                vente vente = new vente();
                vente.setDate_vente(LocalDate.parse(payload.get("date_vente").toString()));
                vente.setNote(payload.get("note").toString());

                // Sérialisation des articles en JSON
                ObjectMapper objectMapper = new ObjectMapper();
                String articlesJson = objectMapper.writeValueAsString(payload.get("articles"));
                vente.setArticles(articlesJson);

                // Gestion du client
                int clientId = Integer.parseInt(payload.get("client_id").toString());
                vente.setClients(clientRepository.findById(clientId)
                        .orElseThrow(() -> new RuntimeException("Client introuvable avec ID : " + clientId)));

                int siteId = Integer.parseInt(payload.get("site_id").toString());
                vente.setSites(siteRepository.findById(siteId)
                        .orElseThrow(() -> new RuntimeException("Site introuvable avec ID : " + siteId)));

                // Récupération des articles de la vente
                List<Map<String, Object>> articlesVente = objectMapper.readValue(
                        articlesJson, new TypeReference<List<Map<String, Object>>>() {});

                for (Map<String, Object> articleVente : articlesVente) {
                    int articleId = Integer.parseInt(articleVente.get("id").toString());
                    int quantityToSell = Integer.parseInt(articleVente.get("quantity").toString());
                    String siteName = articleVente.get("site").toString();

                    // Trouver l'article dans la base
                    article article = articleRepository.findById(articleId)
                            .orElseThrow(() -> new RuntimeException("Article introuvable avec ID : " + articleId));

                    // Vérifier que le site correspond
                    if (!article.getSites().getNom().equals(siteName)) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body("Erreur : L'article avec ID " + articleId + " n'est pas disponible sur le site " + siteName);
                    }

                    // Vérifier la disponibilité de la quantité
                    if (article.getQuantite() < quantityToSell) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body("Erreur : La quantité demandée pour l'article ID " + articleId + " dépasse le stock disponible.");
                    }

                    // Soustraire la quantité vendue
                    article.setQuantite(article.getQuantite() - quantityToSell);
                    articleRepository.saveAndFlush(article);
                }

                // Sauvegarde de la vente
                venteRepository.saveAndFlush(vente);

                return ResponseEntity.ok("Vente enregistrée avec succès !");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Erreur lors de l'enregistrement de la vente : " + e.getMessage());
            }
        }

    // @PutMapping("/vente/{id}")
    // public ResponseEntity<vente> modifiervente(@RequestBody Map<String, Object> map, @PathVariable int id) {
    //     vente vente = venteRepository.findById(id).get();
    //     vente.setQuantite(Integer.parseInt(map.get("quantite").toString()));
    //     vente.setPrix_unitaire_vente(new BigDecimal(map.get("prix_unitaire_vente").toString()));
    //     vente.setMontant_total(new BigDecimal(map.get("montant_total").toString()));

    //     int produitId = Integer.parseInt(map.get("produit_id").toString());
    //     vente.setProduits(produitRepository.findById(produitId).get());

    //     int clientId = Integer.parseInt(map.get("client_id").toString());
    //     vente.setClients(clientRepository.findById(clientId).get());

    //     int utilisateurId = Integer.parseInt(map.get("utilisateur_id").toString());
    //     vente.setUtilisateurs(utilisateurRepository.findById(utilisateurId).get());

    //     venteRepository.flush();
    //     return ResponseEntity.ok(vente);
    // }

    @GetMapping("/vente")
    public List<vente> listerventes(){
        return venteRepository.findAll();
    }

    @DeleteMapping("/vente/{id}")
    public ResponseEntity<String> supprimervente(@PathVariable int id){
        venteRepository.deleteById(id);
        return ResponseEntity.ok("vente effacé avec succès");

    }


    
    
}
