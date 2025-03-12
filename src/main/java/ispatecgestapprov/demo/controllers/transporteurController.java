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

import ispatecgestapprov.demo.entities.transporteur;
import ispatecgestapprov.demo.repositories.transporteurRepository;

@RestController
@RequestMapping(value = "/anonyme")
public class transporteurController {

    @Autowired
    transporteurRepository transporteurRepository;


    @PostMapping("/transporteur")
    public ResponseEntity<String> addtransporteurs (@RequestBody Map<String, String> map) {

        transporteur transporteur = new transporteur();
        transporteur.setNom(map.get("nom"));
        

        transporteurRepository.saveAndFlush(transporteur);
        return new ResponseEntity<>("transporteur enregistré avec succès", HttpStatus.OK);
    }

    @PutMapping("/transporteur/{id}")
    public ResponseEntity<transporteur> modifiertransporteur(@RequestBody Map<String, Object> map, @PathVariable int id) {
        transporteur transporteur = transporteurRepository.findById(id).get();
        transporteur.setNom(map.get("nom").toString());
        

        transporteurRepository.flush();
        return ResponseEntity.ok(transporteur);
    }

    @GetMapping("/transporteur")
    public List<transporteur> listertransporteurs(){
        return transporteurRepository.findAll();
    }

    @DeleteMapping("/transporteur/{id}")
    public ResponseEntity<String> supprimertransporteur(@PathVariable int id){
        transporteurRepository.deleteById(id);
        return ResponseEntity.ok("transporteur effacé avec succès");

    }
    
}
