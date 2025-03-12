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
import org.springframework.web.bind.annotation.RestController;

import ispatecgestapprov.demo.entities.client;
import ispatecgestapprov.demo.repositories.clientRepository;
import ispatecgestapprov.demo.repositories.representantRepository;

@RestController
@RequestMapping(value = "/anonyme")
public class clientController {

    @Autowired
    clientRepository clientRepository;
    @Autowired
    representantRepository representantRepository;

    @PostMapping("/client")
    public ResponseEntity<String> addClient(@RequestBody Map <String, String> map){
        client client = new client();
        client.setNom(map.get("nom"));
        client.setAdresse_expedition(map.get("adresse_expedition"));
        client.setAdresse_facturation(map.get("adresse_facturation"));
        client.setAutrui(map.get("autrui"));
        client.setCode_postal(map.get("code_postal"));
        client.setEmail(map.get("email"));
        client.setEtat_province(map.get("etat_province"));
        client.setNumero_fax(map.get("numero_fax"));
        client.setPrenom_autrui(map.get("prenom_autrui"));
        client.setTelephone(map.get("telephone"));
        client.setTelephone_second(map.get("telephone_second"));
        client.setVille_pays(map.get("ville_pays"));
        client.setNote(map.get("note"));
        client.setDate_inscription(Date.valueOf(LocalDate.now()));

        int representantId = Integer.parseInt(map.get("representant_id"));
        client.setRepresentants(representantRepository.findById(representantId).get());
        

        clientRepository.saveAndFlush(client);
        return new ResponseEntity<>("Client ajouté avec succès", HttpStatus.OK);
    }

    @PutMapping("/client/{id}")
    public ResponseEntity<client> modifierClient(@RequestBody Map<String, Object> map, @PathVariable int id){
        client client = clientRepository.findById(id).get();
        client.setNom(map.get("nom").toString());
        client.setAdresse_expedition(map.get("adresse_expedition").toString());
        client.setAdresse_facturation(map.get("adresse_facturation").toString());
        client.setAutrui(map.get("autrui").toString());
        client.setCode_postal(map.get("code_postal").toString());
        client.setEmail(map.get("email").toString());
        client.setEtat_province(map.get("etat_province").toString());
        client.setNumero_fax(map.get("numero_fax").toString());
        client.setPrenom_autrui(map.get("prenom_autrui").toString());
        client.setTelephone(map.get("telephone").toString());
        client.setTelephone_second(map.get("telephone_second").toString());
        client.setVille_pays(map.get("ville_pays").toString());

        int representantId = Integer.parseInt(map.get("representant_id").toString());
        client.setRepresentants(representantRepository.findById(representantId).get());

        clientRepository.flush();
        return ResponseEntity.ok(client);
    }

    @GetMapping("/client")
    public List<client> listerClients(){
        return clientRepository.findAll();
    }

    @DeleteMapping("/client/{id}")
    public ResponseEntity<String> supprimerClient(@PathVariable int id){
        clientRepository.deleteById(id);
        return ResponseEntity.ok("client supprimé avec succès");
    }


}
