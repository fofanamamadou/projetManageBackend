package ispatecgestapprov.demo.controllers;

import java.time.LocalDate;
import java.util.Date;
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
import ispatecgestapprov.demo.entities.sousCategorie;
import ispatecgestapprov.demo.repositories.sousCategorieRepository;
import ispatecgestapprov.demo.repositories.categorieRepository;


@RestController
@RequestMapping(value = "/anonyme")
public class sousCategorieController {

    @Autowired
    sousCategorieRepository sousCategorieRepository;
    @Autowired
    categorieRepository categorieRepository;

    @PostMapping("/sousCategorie")
    public ResponseEntity<String> addSousCategorie(@RequestBody Map<String, String> map) {
        sousCategorie sousCat = new sousCategorie();
        sousCat.setNom_sous(map.get("nom_sous"));
        sousCat.setDescription_sous(map.get("description_sous"));
        sousCat.setDate_creation(LocalDate.now());

        // Récupération de la catégorie associée
        int categorieId = Integer.parseInt(map.get("categorie_id"));
        categorie cat = categorieRepository.findById(categorieId).orElseThrow(() -> new RuntimeException("Catégorie introuvable"));
        sousCat.setCategories(cat);

        sousCategorieRepository.saveAndFlush(sousCat);
        return new ResponseEntity<>("Sous-Catégorie ajoutée avec succès", HttpStatus.OK);
    }


    @PutMapping("/sousCategorie/{id}")
    public ResponseEntity<sousCategorie> modifiersousCategorie(@RequestBody Map<String, Object> map, @PathVariable int id) {
        sousCategorie sousCat = sousCategorieRepository.findById(id).get();
        sousCat.setNom_sous(map.get("nom_sous").toString());
        sousCat.setDescription_sous(map.get("description_sous").toString());

        sousCategorieRepository.flush();
        return ResponseEntity.ok(sousCat);

    }

    @GetMapping("/sousCategorie")
    public List <sousCategorie> listesousCategories(){
        return sousCategorieRepository.findAll();
    }

    @DeleteMapping("/sousCategorie/{id}")
    public ResponseEntity<String> supprimersousCategorie(@PathVariable int id) {
        sousCategorieRepository.deleteById(id);
        return ResponseEntity.ok("Sous-Catégorie supprimé avec succès");
    }

    //Methode pour recuperer les sous-categorie d'une categorie
    @GetMapping("/sousCategorie/categorie/{categorieId}")
    public ResponseEntity<List<sousCategorie>> getSousCategoriesByCategorie(@PathVariable int categorieId) {
        List<sousCategorie> sousCategories = sousCategorieRepository.findByCategoriesId(categorieId);
        return ResponseEntity.ok(sousCategories);
    }

}
