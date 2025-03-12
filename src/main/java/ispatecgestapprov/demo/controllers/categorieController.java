package ispatecgestapprov.demo.controllers;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
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

import ispatecgestapprov.demo.entities.categorie;
import ispatecgestapprov.demo.repositories.categorieRepository;

@RestController
@RequestMapping(value = "/anonyme")
public class categorieController {

    @Autowired
    categorieRepository categorieRepository;

    @PostMapping("/categorie")
    public ResponseEntity<Map<String, Object>> addCategorie(@RequestBody Map<String, String> map) {
        categorie cat = new categorie();
        cat.setNom(map.get("nom"));
        cat.setDescription(map.get("description"));
        cat.setDate_creation((LocalDate.now()));

        categorie savedCategorie = categorieRepository.saveAndFlush(cat);

        // Retourner l'ID de la catégorie créée
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Catégorie ajoutée avec succès");
        response.put("id", savedCategorie.getId());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PutMapping("/categorie/{id}")
    public ResponseEntity<categorie> modifiercategorie(@RequestBody Map<String, Object> map, @PathVariable int id) {
        categorie cat = categorieRepository.findById(id).get();
        cat.setNom(map.get("nom").toString());
        cat.setDescription(map.get("description").toString());

        categorieRepository.flush();
        return ResponseEntity.ok(cat);

    }

    @GetMapping("/categorie")
    public List <categorie> listeCategories(){
        return categorieRepository.findAll();
    }

    @DeleteMapping("/categorie/{id}")
    public ResponseEntity<String> supprimerCategorie(@PathVariable int id) {
        categorieRepository.deleteById(id);
        return ResponseEntity.ok("Catégorie supprimé avec succès");
    }
}
