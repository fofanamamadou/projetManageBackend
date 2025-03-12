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

import ispatecgestapprov.demo.entities.role;
import ispatecgestapprov.demo.repositories.roleRepository;

@RestController
@RequestMapping(value = "/anonyme")
public class roleController {
    @Autowired
    roleRepository roleRepository;




    @PostMapping("/role")
    public ResponseEntity<String> addroles (@RequestBody Map<String, String> map) {

        role role = new role();
        
        role.setNom(map.get("nom"));
        role.setDescription(map.get("description"));

        roleRepository.saveAndFlush(role);
        return new ResponseEntity<>("role cree avec succès", HttpStatus.OK);
    }

    @PutMapping("/role/{id}")
    public ResponseEntity<role> modifierrole(@RequestBody Map<String, Object> map, @PathVariable int id) {
        role role = roleRepository.findById(id).get();
        role.setNom(map.get("nom").toString());
        role.setDescription(map.get("description").toString());

        roleRepository.flush();
        return ResponseEntity.ok(role);
    }

    @GetMapping("/role")
    public List<role> listerroles(){
        return roleRepository.findAll();
    }

    @DeleteMapping("/role/{id}")
    public ResponseEntity<String> supprimerrole(@PathVariable int id){
        roleRepository.deleteById(id);
        return ResponseEntity.ok("role effacé avec succès");

    }

    
    
}
