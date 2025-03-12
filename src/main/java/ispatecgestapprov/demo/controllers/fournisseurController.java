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

import ispatecgestapprov.demo.entities.fournisseur;
import ispatecgestapprov.demo.repositories.fournisseurRepository;
import ispatecgestapprov.demo.repositories.fournitureRepository;

@RestController
@RequestMapping(value = "/anonyme")
public class fournisseurController {

    @Autowired
    fournisseurRepository fournisseurRepository;
    @Autowired
    fournitureRepository fournitureRepository;



    @PostMapping("/fournisseur")
    public ResponseEntity<String> addfournisseurs (@RequestBody Map<String, String> map) {

        fournisseur fournisseur = new fournisseur();
        fournisseur.setNom(map.get("nom"));
        fournisseur.setAutrui(map.get("autrui"));
        fournisseur.setPrenom_autrui(map.get("prenom_autrui"));
        fournisseur.setFax(map.get("fax"));
        fournisseur.setAdresse(map.get("adresse"));
        fournisseur.setEmail(map.get("email"));
        fournisseur.setTelephone(map.get("telephone"));
        fournisseur.setTelephone_second(map.get("telephone_second"));
        fournisseur.setDate_debut_collaboration(LocalDate.parse(map.get("date_debut_collaboration")));


        fournisseurRepository.saveAndFlush(fournisseur);
        return new ResponseEntity<>("fournisseur enregistré avec succès", HttpStatus.OK);
    }

    @PutMapping("/fournisseur/{id}")
    public ResponseEntity<fournisseur> modifierfournisseur(@RequestBody Map<String, Object> map, @PathVariable int id) {
        fournisseur fournisseur = fournisseurRepository.findById(id).get();
        fournisseur.setNom(map.get("nom").toString());
        fournisseur.setAutrui(map.get("autrui").toString());
        fournisseur.setPrenom_autrui(map.get("prenom_autrui").toString());
        fournisseur.setFax(map.get("fax").toString());
        fournisseur.setAdresse(map.get("adresse").toString());
        fournisseur.setEmail(map.get("email").toString());
        fournisseur.setTelephone(map.get("telephone").toString());
        fournisseur.setTelephone_second(map.get("telephone_second").toString());
        fournisseur.setDate_debut_collaboration(LocalDate.parse(map.get("date_debut_collaboration").toString()));

        fournisseurRepository.flush();
        return ResponseEntity.ok(fournisseur);
    }

    @GetMapping("/fournisseur")
    public List<fournisseur> listerfournisseurs(){
        return fournisseurRepository.findAll();
    }

    @DeleteMapping("/fournisseur/{id}")
    public ResponseEntity<String> supprimerfournisseur(@PathVariable int id){
        fournisseurRepository.deleteById(id);
        return ResponseEntity.ok("fournisseur effacé avec succès");

    }

}
