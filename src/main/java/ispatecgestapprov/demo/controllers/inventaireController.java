package ispatecgestapprov.demo.controllers;

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


import ispatecgestapprov.demo.entities.inventaire;
import ispatecgestapprov.demo.repositories.articleRepository;
import ispatecgestapprov.demo.repositories.siteRepository;
import ispatecgestapprov.demo.repositories.inventaireRepository;

@RequestMapping(value = "/anonyme")
public class inventaireController {
    @Autowired
    inventaireRepository inventaireRepository;
    @Autowired
    articleRepository produitRepository;
    @Autowired
    siteRepository siteRepository;




    @PostMapping("/inventaire")
    public ResponseEntity<String> addinventaires (@RequestBody Map<String, String> map) {

        inventaire inventaire = new inventaire();

        inventaire.setQuantite(Integer.parseInt(map.get("nom")));
        inventaire.setLocalisation(map.get("localisation"));

        int produitId = Integer.parseInt(map.get("produit_id"));
        inventaire.setProduits(produitRepository.findById(produitId).get());

        int siteId = Integer.parseInt(map.get("site_id"));
        inventaire.setSites(siteRepository.findById(siteId).get());

        inventaireRepository.saveAndFlush(inventaire);
        return new ResponseEntity<>("Inventaire cree avec succès", HttpStatus.OK);
    }

    @PutMapping("/inventaire/{id}")
    public ResponseEntity<inventaire> modifierinventaire(@RequestBody Map<String, Object> map, @PathVariable int id) {
        inventaire inventaire = inventaireRepository.findById(id).get();
        inventaire.setQuantite(Integer.parseInt(map.get("nom").toString()));
        inventaire.setLocalisation(map.get("localisation").toString());

        int produitId = Integer.parseInt(map.get("produit_id").toString());
        inventaire.setProduits(produitRepository.findById(produitId).get());

        int siteId = Integer.parseInt(map.get("site_id").toString());
        inventaire.setSites(siteRepository.findById(siteId).get());

        inventaireRepository.flush();
        return ResponseEntity.ok(inventaire);
    }

    @GetMapping("/inventaire")
    public List<inventaire> listerinventaires(){
        return inventaireRepository.findAll();
    }

    @DeleteMapping("/inventaire/{id}")
    public ResponseEntity<String> supprimerinventaire(@PathVariable int id){
        inventaireRepository.deleteById(id);
        return ResponseEntity.ok("inventaire effacé avec succès");

    }

    
    
}
