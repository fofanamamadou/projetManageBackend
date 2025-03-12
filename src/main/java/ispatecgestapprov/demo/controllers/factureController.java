// package ispatecgestapprov.demo.controllers;


// import java.sql.Date;
// import java.time.LocalDate;
// import java.util.Map;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;

// import ispatecgestapprov.demo.entities.commande;
// import ispatecgestapprov.demo.entities.facture;
// import ispatecgestapprov.demo.entities.vente;
// import ispatecgestapprov.demo.repositories.factureRepository;
// import ispatecgestapprov.demo.repositories.venteRepository;
// import ispatecgestapprov.demo.repositories.commandeRepository;

// @RequestMapping(value = "/anonyme")
// public class factureController {

//     @Autowired
//     private factureRepository factureRepository;

//     @Autowired
//     private commandeRepository commandeRepository;

//     @Autowired
//     private venteRepository venteRepository;

//     @PostMapping
//     public ResponseEntity<?> createFacture(@RequestBody Map<String, Object> payload) {
//         try {
//             // Récupération des champs obligatoires
//             String type = payload.get("type").toString();
//             if (!type.equalsIgnoreCase("APPROVISIONNEMENT") && !type.equalsIgnoreCase("VENTE")) {
//                 return ResponseEntity.badRequest().body("Le type de facture doit être 'APPROVISIONNEMENT' ou 'VENTE'.");
//             }

//             facture facture = new facture();
//             facture.setDate_emission(Date.valueOf(LocalDate.now()));
//             facture.setStatut(payload.get("statut").toString());
//             facture.setType(type);

//             if (type.equalsIgnoreCase("APPROVISIONNEMENT")) {
//                 // Gestion des factures liées à une commande
//                 int commandeId = Integer.parseInt(payload.get("commande_id").toString());
//                 commande commande = commandeRepository.findById(commandeId)
//                         .orElseThrow(() -> new RuntimeException("Commande introuvable"));

//                 // Associer la commande
//                 facture.setCommandes(commande);

//                 //utilisé le montant total de la commande
//                 facture.setMontant_total(commande.getMontant_total());
                

//                 // Associer le fournisseur de la commande
//                 facture.setFournisseurs(commande.getFournisseurs());

//             } else if (type.equalsIgnoreCase("VENTE")) {
//                 // Gestion des factures liées à une vente
//                 int venteId = Integer.parseInt(payload.get("vente_id").toString());
//                 vente vente = venteRepository.findById(venteId)
//                         .orElseThrow(() -> new RuntimeException("Vente introuvable"));

//                 // Associer la vente
//                 facture.setVentes(vente);

//                 // Utiliser le montant total de la vente
//                 facture.setMontant_total(vente.getMontant_total());

//                 // Associer le client de la vente
//                 facture.setClients(vente.getClients());
//             }

//             // Sauvegarder la facture
//             factureRepository.save(facture);
//             return ResponseEntity.status(HttpStatus.CREATED).body("Facture créée avec succès.");

//         } catch (Exception e) {
//             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur : " + e.getMessage());
//         }
//     }
// }

