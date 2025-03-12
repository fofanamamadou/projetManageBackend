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

import ispatecgestapprov.demo.entities.fourniture;
import ispatecgestapprov.demo.repositories.fournitureRepository;

@RequestMapping(value = "/anonyme")
public class fournitureController {
    @Autowired
    fournitureRepository fournitureRepository;



    @PostMapping("/fourniture")
    public ResponseEntity<String> addfournitures (@RequestBody Map<String, String> map) {

        fourniture fourniture = new fourniture();
        fourniture.setType_fourniture(map.get("type_fourniture"));
        fourniture.setDescription(map.get("description"));

        fournitureRepository.saveAndFlush(fourniture);
        return new ResponseEntity<>("fourniture enregistré avec succès", HttpStatus.OK);
    }

    @PutMapping("/fourniture/{id}")
    public ResponseEntity<fourniture> modifierfourniture(@RequestBody Map<String, Object> map, @PathVariable int id) {
        fourniture fourniture = fournitureRepository.findById(id).get();
        fourniture.setType_fourniture(map.get("type_fourniture").toString());
        fourniture.setDescription(map.get("description").toString());

        fournitureRepository.flush();
        return ResponseEntity.ok(fourniture);
    }

    @GetMapping("/fourniture")
    public List<fourniture> listerfournitures(){
        return fournitureRepository.findAll();
    }

    @DeleteMapping("/fourniture/{id}")
    public ResponseEntity<String> supprimerfourniture(@PathVariable int id){
        fournitureRepository.deleteById(id);
        return ResponseEntity.ok("fourniture effacé avec succès");

    }

    
    
}
