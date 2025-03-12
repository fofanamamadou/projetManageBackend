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

import ispatecgestapprov.demo.entities.site;
import ispatecgestapprov.demo.repositories.siteRepository;

@RestController
@RequestMapping(value = "/anonyme")
public class siteController {

    @Autowired
    siteRepository siteRepository;


    @PostMapping("/site")
    public ResponseEntity<String> addsites (@RequestBody Map<String, String> map) {

        site site = new site();
        site.setNom(map.get("nom"));
        site.setAdresse(map.get("adresse"));
        site.setDescription(map.get("description"));

        siteRepository.saveAndFlush(site);
        return new ResponseEntity<>("site enregistré avec succès", HttpStatus.OK);
    }

    @PutMapping("/site/{id}")
    public ResponseEntity<site> modifiersite(@RequestBody Map<String, Object> map, @PathVariable int id) {
        site site = siteRepository.findById(id).get();
        site.setNom(map.get("nom").toString());
        site.setAdresse(map.get("adresse").toString());
        site.setDescription(map.get("description").toString());

        siteRepository.flush();
        return ResponseEntity.ok(site);
    }

    @GetMapping("/site")
    public List<site> listersites(){
        return siteRepository.findAll();
    }

    @DeleteMapping("/site/{id}")
    public ResponseEntity<String> supprimersite(@PathVariable int id){
        siteRepository.deleteById(id);
        return ResponseEntity.ok("site effacé avec succès");

    }
    
}
