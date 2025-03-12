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

import ispatecgestapprov.demo.entities.uniteMesure;
import ispatecgestapprov.demo.repositories.uniteMesureRepository;

@RestController
@RequestMapping(value = "/anonyme")
public class uniteMesureController {

    @Autowired
    uniteMesureRepository uniteMesureRepository;


    @PostMapping("/unite")
    public ResponseEntity<String> adduniteMesures (@RequestBody Map<String, String> map) {

        uniteMesure uniteMesure = new uniteMesure();
        uniteMesure.setNom(map.get("nom"));
        

        uniteMesureRepository.saveAndFlush(uniteMesure);
        return new ResponseEntity<>("uniteMesure enregistré avec succès", HttpStatus.OK);
    }

    @PutMapping("/unite/{id}")
    public ResponseEntity<uniteMesure> modifieruniteMesure(@RequestBody Map<String, Object> map, @PathVariable int id) {
        uniteMesure uniteMesure = uniteMesureRepository.findById(id).get();
        uniteMesure.setNom(map.get("nom").toString());
        

        uniteMesureRepository.flush();
        return ResponseEntity.ok(uniteMesure);
    }

    @GetMapping("/unite")
    public List<uniteMesure> listeruniteMesures(){
        return uniteMesureRepository.findAll();
    }

    @DeleteMapping("/unite/{id}")
    public ResponseEntity<String> supprimeruniteMesure(@PathVariable int id){
        uniteMesureRepository.deleteById(id);
        return ResponseEntity.ok("uniteMesure effacé avec succès");

    }
    
}
