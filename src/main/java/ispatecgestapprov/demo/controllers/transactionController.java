package ispatecgestapprov.demo.controllers;

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

import ispatecgestapprov.demo.entities.transaction;
import ispatecgestapprov.demo.repositories.utilisateurRepository;
import ispatecgestapprov.demo.repositories.articleRepository;
import ispatecgestapprov.demo.repositories.transactionRepository;

@RequestMapping(value = "/anonyme")
public class transactionController {

    @Autowired
    transactionRepository transactionRepository;
    @Autowired
    articleRepository produitRepository;
    @Autowired
    utilisateurRepository utilisateurRepository;




    @PostMapping("/transaction")
    public ResponseEntity<String> addtransactions (@RequestBody Map<String, String> map) {

        transaction transaction = new transaction();
        
        transaction.setDescription(map.get("description"));
        transaction.setQuantite(Integer.parseInt(map.get("quantite")));
        transaction.setType(map.get("type"));
        transaction.setMode_transaction(map.get("mode_transaction"));
        transaction.setDate_transaction(Date.valueOf(LocalDate.now()));

        int produitId = Integer.parseInt(map.get("produit_id"));
        transaction.setProduits(produitRepository.findById(produitId).get());

        int utilisateurId = Integer.parseInt(map.get("utilisateur_id"));
        transaction.setUtilisateurs(utilisateurRepository.findById(utilisateurId).get());


        transactionRepository.saveAndFlush(transaction);
        return new ResponseEntity<>("transaction cree avec succès", HttpStatus.OK);
    }

    @PutMapping("/transaction/{id}")
    public ResponseEntity<transaction> modifiertransaction(@RequestBody Map<String, Object> map, @PathVariable int id) {
        transaction transaction = transactionRepository.findById(id).get();
        transaction.setDescription(map.get("description").toString());
        transaction.setQuantite(Integer.parseInt(map.get("quantite").toString()));
        transaction.setType(map.get("type").toString());
        transaction.setMode_transaction(map.get("mode_transaction").toString());

        int produitId = Integer.parseInt(map.get("produit_id").toString());
        transaction.setProduits(produitRepository.findById(produitId).get());

        int utilisateurId = Integer.parseInt(map.get("utilisateur_id").toString());
        transaction.setUtilisateurs(utilisateurRepository.findById(utilisateurId).get());
        transactionRepository.flush();
        return ResponseEntity.ok(transaction);
    }

    @GetMapping("/transaction")
    public List<transaction> listertransactions(){
        return transactionRepository.findAll();
    }

    @DeleteMapping("/transaction/{id}")
    public ResponseEntity<String> supprimertransaction(@PathVariable int id){
        transactionRepository.deleteById(id);
        return ResponseEntity.ok("transaction effacé avec succès");

    }

    
}
