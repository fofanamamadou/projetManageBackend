package ispatecgestapprov.demo.controllers;

import java.math.BigDecimal;
import java.sql.Date;
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

import ispatecgestapprov.demo.entities.paiement;
import ispatecgestapprov.demo.repositories.paiementRepository;
import ispatecgestapprov.demo.repositories.commandeRepository;
import ispatecgestapprov.demo.repositories.clientRepository;
import ispatecgestapprov.demo.repositories.factureRepository;

@RequestMapping(value = "/anonyme")
public class paiementController {

    @Autowired
    paiementRepository paiementRepository;
    @Autowired
    commandeRepository commandeRepository;
    @Autowired
    clientRepository clientRepository;
    @Autowired
    factureRepository factureRepository;




    @PostMapping("/paiement")
    public ResponseEntity<String> addpaiements (@RequestBody Map<String, String> map) {

        paiement paiement = new paiement();
        
        paiement.setMontant(new BigDecimal(map.get("montant")));
        paiement.setMoyen_paiement(map.get("moyen_paiement"));
        paiement.setStatut(map.get("statut"));
        paiement.setDate_paiement(Date.valueOf(LocalDate.now()));

        int commandeId = Integer.parseInt(map.get("commande_id"));
        paiement.setCommandes(commandeRepository.findById(commandeId).get());

        int clientId = Integer.parseInt(map.get("client_id"));
        paiement.setClients(clientRepository.findById(clientId).get());

        int factureId = Integer.parseInt(map.get("facture_id"));
        paiement.setFactures(factureRepository.findById(factureId).get());

        paiementRepository.saveAndFlush(paiement);
        return new ResponseEntity<>("paiement cree avec succès", HttpStatus.OK);
    }

    @PutMapping("/paiement/{id}")
    public ResponseEntity<paiement> modifierpaiement(@RequestBody Map<String, Object> map, @PathVariable int id) {
        paiement paiement = paiementRepository.findById(id).get();
        paiement.setMontant(new BigDecimal(map.get("montant").toString()));
        paiement.setMoyen_paiement(map.get("moyen_paiement").toString());
        paiement.setStatut(map.get("statut").toString());

        int commandeId = Integer.parseInt(map.get("commande_id").toString());
        paiement.setCommandes(commandeRepository.findById(commandeId).get());

        int clientId = Integer.parseInt(map.get("client_id").toString());
        paiement.setClients(clientRepository.findById(clientId).get());

        int factureId = Integer.parseInt(map.get("facture_id").toString());
        paiement.setFactures(factureRepository.findById(factureId).get());

        paiementRepository.flush();
        return ResponseEntity.ok(paiement);
    }

    @GetMapping("/paiement")
    public List<paiement> listerpaiements(){
        return paiementRepository.findAll();
    }

    @DeleteMapping("/paiement/{id}")
    public ResponseEntity<String> supprimerpaiement(@PathVariable int id){
        paiementRepository.deleteById(id);
        return ResponseEntity.ok("paiement effacé avec succès");

    }

    
    
}
