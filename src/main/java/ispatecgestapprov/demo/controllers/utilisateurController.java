package ispatecgestapprov.demo.controllers;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ispatecgestapprov.demo.entities.role;
import ispatecgestapprov.demo.entities.utilisateur;
import ispatecgestapprov.demo.repositories.roleRepository;
import ispatecgestapprov.demo.repositories.utilisateurRepository;

@RestController
@RequestMapping(value = "/anonyme")
public class utilisateurController {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    utilisateurRepository utilisateurRepository;
    @Autowired
    roleRepository roleRepository;




    @PostMapping("/utilisateur")
    public ResponseEntity<String> addUsers (@RequestBody Map<String, String> map) {

        utilisateur user = new utilisateur();
        user.setNom(map.get("nom"));
        user.setEmail(map.get("email"));
        user.setMot_de_passe(passwordEncoder.encode(map.get("mot_de_passe")));
        user.setDate_ajout(LocalDate.now());
        user.setRoles(roleRepository.findByNom("ROLE_USERS"));

        utilisateurRepository.saveAndFlush(user);
        return new ResponseEntity<>("Inscription effectué avec succès", HttpStatus.OK);
    }

    //Controller role et utilisateur
    
    @PostMapping("/donnerRole/{utilisateur_id}")
   public String donnerRole(@RequestBody List<role> ok,  @PathVariable int utilisateur_id) {
       utilisateur infoutilisateur = utilisateurRepository.findById(utilisateur_id).get();
       infoutilisateur.setRoles(ok);
       utilisateurRepository.flush();
       return "Role modifier avec succes";
       
   }

    // @PutMapping("/utilisateur/{id}")
    // public ResponseEntity<utilisateur> modifierUser(@RequestBody Map<String, Object> map, @PathVariable int id) {
    //     utilisateur user = utilisateurRepository.findById(id).get();
    //     user.setNom(map.get("nom").toString());
    //     user.setEmail(map.get("email").toString());
    //     user.setMot_de_passe(map.get("mot_de_passe").toString());

        
    //     List<Integer> roleId = (List<Integer>) map.get("roles");
    //     List<role> roles = roleRepository.findAllById(roleId);
    //     user.setRoles(roles);


    //     utilisateurRepository.flush();
    //     return ResponseEntity.ok(user);
    // }

    @GetMapping("/utilisateur")
    public List<utilisateur> listerUtilisateurs(){
        return utilisateurRepository.findAll();
    }

    @DeleteMapping("/utilisateur/{id}")
    public ResponseEntity<String> supprimerUser(@PathVariable int id){
        utilisateurRepository.deleteById(id);
        return ResponseEntity.ok("Utilisateur effacé avec succès");

    }


    
    
}
