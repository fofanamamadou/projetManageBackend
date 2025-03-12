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

import ispatecgestapprov.demo.entities.site;
import ispatecgestapprov.demo.entities.transfert;
import ispatecgestapprov.demo.repositories.siteRepository;
import ispatecgestapprov.demo.repositories.transfertRepository;
import ispatecgestapprov.demo.repositories.articleRepository;

@RestController
@RequestMapping(value = "/anonyme")
public class transfertController {
    @Autowired
    transfertRepository transfertRepository;
    @Autowired
    articleRepository produitRepository;
    @Autowired
    siteRepository siteRepository;




    @PostMapping("/transfert")
    public ResponseEntity<String> addtransferts (@RequestBody Map<String, Object> map) {

        try {

        transfert transfert = new transfert();
        
        transfert.setQuantite(Integer.parseInt(map.get("quantite").toString()));

        // Sérialisation des articles en JSON
            ObjectMapper objectMapper = new ObjectMapper();
            String articlesJson = objectMapper.writeValueAsString(map.get("articles"));
            transfert.setArticles(articlesJson);

        int commandeId = Integer.parseInt(map.get("site_origine_id").toString());
        transfert.setSiteOrigine(siteRepository.findById(commandeId).get());

        int siteOrigineId = Integer.parseInt(map.get("site_tranferer_id").toString());
        transfert.setSiteTransferer(siteRepository.findById(siteOrigineId).get());

        transfertRepository.saveAndFlush(transfert);
        return new ResponseEntity<>("transfert cree avec succès", HttpStatus.OK);
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erreur lors de la création de la commande : " + e.getMessage());
    }
}

    // @PutMapping("/transfert/{id}")
    // public ResponseEntity<transfert> modifiertransfert(@RequestBody Map<String, Object> map, @PathVariable int id) {
    //     transfert transfert = transfertRepository.findById(id).get();
    //     transfert.setRaison(map.get("raison").toString());
    //     transfert.setType_transfert(map.get("type_transfert").toString());
    //     transfert.setStatut(map.get("statut").toString());

    //     int produitId = Integer.parseInt(map.get("produit_id").toString());
    //     transfert.setProduits(produitRepository.findById(produitId).get());

    //     int commandeId = Integer.parseInt(map.get("commande_id").toString());
    //     transfert.setcommandes(commandeRepository.findById(commandeId).get());

    //     int siteId = Integer.parseInt(map.get("site_id").toString());
    //     transfert.setsites(siteRepository.findById(siteId).get());

    //     transfertRepository.flush();
    //     return ResponseEntity.ok(transfert);
    // }

    @GetMapping("/transfert")
    public List<transfert> listertransferts(){
        return transfertRepository.findAll();
    }

    @DeleteMapping("/transfert/{id}")
    public ResponseEntity<String> supprimertransfert(@PathVariable int id){
        transfertRepository.deleteById(id);
        return ResponseEntity.ok("transfert effacé avec succès");

    }


    
    
}
