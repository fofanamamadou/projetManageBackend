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
import org.springframework.web.bind.annotation.RestController;

import ispatecgestapprov.demo.entities.representant;
import ispatecgestapprov.demo.repositories.representantRepository;

@RestController
@RequestMapping(value = "/anonyme")
public class representantController {

    @Autowired
    representantRepository representantRepository;


    @PostMapping("/representant")
    public ResponseEntity<String> addRepresentants (@RequestBody Map<String, String> map) {

        representant representant = new representant();
        representant.setNom_representant(map.get("nom_representant"));

        representantRepository.saveAndFlush(representant);
        return new ResponseEntity<>("representant enregistré avec succès", HttpStatus.OK);
    }

    @PutMapping("/representant/{id}")
    public ResponseEntity<representant> modifierRepresentant(@RequestBody Map<String, Object> map, @PathVariable int id) {
        representant representant = representantRepository.findById(id).get();
        representant.setNom_representant(map.get("nom_representant").toString());

        representantRepository.flush();
        return ResponseEntity.ok(representant);
    }

    @GetMapping("/representant")
    public List<representant> listerRepresentants(){
        return representantRepository.findAll();
    }

    @DeleteMapping("/representant/{id}")
    public ResponseEntity<String> supprimerRepresentant(@PathVariable int id){
        representantRepository.deleteById(id);
        return ResponseEntity.ok("representant effacé avec succès");

    }
    
}
