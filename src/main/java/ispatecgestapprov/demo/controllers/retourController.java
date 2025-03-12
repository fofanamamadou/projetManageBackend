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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import ispatecgestapprov.demo.entities.retour;
import ispatecgestapprov.demo.repositories.commandeRepository;
import ispatecgestapprov.demo.repositories.fournisseurRepository;
import ispatecgestapprov.demo.repositories.retourRepository;
import ispatecgestapprov.demo.repositories.articleRepository;

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




    @PostMapping("/retour")
    public ResponseEntity<String> addretours (@RequestBody Map<String, Object> map) {

        try {

        retour retour = new retour();
        
        retour.setDate_retour(LocalDate.parse(map.get("date_retour").toString()));
        retour.setNote(map.get("note").toString());

        // Sérialisation des articles en JSON
            ObjectMapper objectMapper = new ObjectMapper();
            String articlesJson = objectMapper.writeValueAsString(map.get("articles"));
            retour.setArticles(articlesJson);

        int commandeId = Integer.parseInt(map.get("commande_id").toString());
        retour.setCommandes(commandeRepository.findById(commandeId).get());

        int fournisseurId = Integer.parseInt(map.get("fournisseur_id").toString());
        retour.setFournisseurs(fournisseurRepository.findById(fournisseurId).get());

        retourRepository.saveAndFlush(retour);
        return new ResponseEntity<>("retour cree avec succès", HttpStatus.OK);
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erreur lors de la création de la commande : " + e.getMessage());
    }
}

    // @PutMapping("/retour/{id}")
    // public ResponseEntity<retour> modifierretour(@RequestBody Map<String, Object> map, @PathVariable int id) {
    //     retour retour = retourRepository.findById(id).get();
    //     retour.setRaison(map.get("raison").toString());
    //     retour.setType_retour(map.get("type_retour").toString());
    //     retour.setStatut(map.get("statut").toString());

    //     int produitId = Integer.parseInt(map.get("produit_id").toString());
    //     retour.setProduits(produitRepository.findById(produitId).get());

    //     int commandeId = Integer.parseInt(map.get("commande_id").toString());
    //     retour.setcommandes(commandeRepository.findById(commandeId).get());

    //     int fournisseurId = Integer.parseInt(map.get("fournisseur_id").toString());
    //     retour.setFournisseurs(fournisseurRepository.findById(fournisseurId).get());

    //     retourRepository.flush();
    //     return ResponseEntity.ok(retour);
    // }

    @GetMapping("/retour")
    public List<retour> listerretours(){
        return retourRepository.findAll();
    }

    @DeleteMapping("/retour/{id}")
    public ResponseEntity<String> supprimerretour(@PathVariable int id){
        retourRepository.deleteById(id);
        return ResponseEntity.ok("retour effacé avec succès");

    }


    
    
}
