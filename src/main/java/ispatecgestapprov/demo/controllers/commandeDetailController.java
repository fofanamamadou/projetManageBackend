package ispatecgestapprov.demo.controllers;

import java.math.BigDecimal;
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

import ispatecgestapprov.demo.entities.commandeDetail;
import ispatecgestapprov.demo.repositories.commandeRepository;
import ispatecgestapprov.demo.repositories.commandeDetailRepository;
import ispatecgestapprov.demo.repositories.articleRepository;

@RequestMapping(value = "/anonyme")
public class commandeDetailController {
    @Autowired
    commandeDetailRepository commandeDetailRepository;
    @Autowired
    commandeRepository commandeRepository;
    @Autowired
    articleRepository produitRepository;




    @PostMapping("/commandeDetail")
    public ResponseEntity<String> addcommandeDetails (@RequestBody Map<String, String> map) {

        commandeDetail commandeDetail = new commandeDetail();
        commandeDetail.setQuantite(Integer.parseInt(map.get("quantite")));
        commandeDetail.setPrix_unitaire(new BigDecimal(map.get("prix_unitaire")));
        commandeDetail.setMontant_total(new BigDecimal(map.get("montant_total")));
        commandeDetail.setTaxes(new BigDecimal(map.get("taxes")));
        commandeDetail.setRemise(new BigDecimal(map.get("remise")));
        commandeDetail.setUnite_mesure(map.get("unite_mesure"));

        int commandeId = Integer.parseInt(map.get("commande_id"));
        commandeDetail.setCommandes(commandeRepository.findById(commandeId).get());

        int produitId = Integer.parseInt(map.get("produit_id"));
        commandeDetail.setProduits(produitRepository.findById(produitId).get());

        commandeDetailRepository.saveAndFlush(commandeDetail);
        return new ResponseEntity<>("commandeDetail lancée avec succès", HttpStatus.OK);
    }

    @PutMapping("/commandeDetail/{id}")
    public ResponseEntity<commandeDetail> modifiercommandeDetail(@RequestBody Map<String, Object> map, @PathVariable int id) {
        commandeDetail commandeDetail = commandeDetailRepository.findById(id).get();
        commandeDetail.setQuantite(Integer.parseInt(map.get("quantite").toString()));
        commandeDetail.setPrix_unitaire(new BigDecimal(map.get("prix_unitaire").toString()));
        commandeDetail.setMontant_total(new BigDecimal(map.get("montant_total").toString()));
        commandeDetail.setTaxes(new BigDecimal(map.get("taxes").toString()));
        commandeDetail.setRemise(new BigDecimal(map.get("remise").toString()));
        commandeDetail.setUnite_mesure(map.get("unite_mesure").toString());
        

        int produitId = Integer.parseInt(map.get("produit_id").toString());
        commandeDetail.setProduits(produitRepository.findById(produitId).get());

        int commandeId = Integer.parseInt(map.get("commande_id").toString());
        commandeDetail.setCommandes(commandeRepository.findById(commandeId).get());


        commandeDetailRepository.flush();
        return ResponseEntity.ok(commandeDetail);
    }


    @GetMapping("/commandeDetail")
    public List<commandeDetail> listercommandeDetails(){
        return commandeDetailRepository.findAll();
    }

    @DeleteMapping("/commandeDetail/{id}")
    public ResponseEntity<String> supprimercommandeDetail(@PathVariable int id){
        commandeDetailRepository.deleteById(id);
        return ResponseEntity.ok("commandeDetail effacé avec succès");

    }

    
    
}
