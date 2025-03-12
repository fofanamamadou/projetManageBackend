package ispatecgestapprov.demo.controllers;

import java.sql.Date;
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

import ispatecgestapprov.demo.entities.notification;
import ispatecgestapprov.demo.repositories.notificationRepository;
import ispatecgestapprov.demo.repositories.utilisateurRepository;

@RequestMapping(value = "/anonyme")
public class notificationController {
    @Autowired
    notificationRepository notificationRepository;
    @Autowired
    utilisateurRepository utilisateurRepository;




    @PostMapping("/notification")
    public ResponseEntity<String> addnotifications (@RequestBody Map<String, String> map) {

        notification notification = new notification();

        
        notification.setType(map.get("notification"));
        notification.setMessage(map.get("message"));
        notification.setPriorite(map.get("priorite"));
        notification.setLien_associe(map.get("lien_associe"));
        notification.setStatut(map.get("statut"));
        notification.setDate_notification(Date.valueOf(LocalDate.now()));

        int utilisateurId = Integer.parseInt(map.get("utilisateur_id"));
        notification.setUtilisateurs(utilisateurRepository.findById(utilisateurId).get());

        notificationRepository.saveAndFlush(notification);
        return new ResponseEntity<>("notification cree avec succès", HttpStatus.OK);
    }

    @PutMapping("/notification/{id}")
    public ResponseEntity<notification> modifiernotification(@RequestBody Map<String, Object> map, @PathVariable int id) {
        notification notification = notificationRepository.findById(id).get();
        notification.setType(map.get("notification").toString());
        notification.setMessage(map.get("message").toString());
        notification.setPriorite(map.get("priorite").toString());
        notification.setLien_associe(map.get("lien_associe").toString());
        notification.setStatut(map.get("statut").toString());

        int utilisateurId = Integer.parseInt(map.get("utilisateur_id").toString());
        notification.setUtilisateurs(utilisateurRepository.findById(utilisateurId).get());

        notificationRepository.flush();
        return ResponseEntity.ok(notification);
    }

    @GetMapping("/notification")
    public List<notification> listernotifications(){
        return notificationRepository.findAll();
    }

    @DeleteMapping("/notification/{id}")
    public ResponseEntity<String> supprimernotification(@PathVariable int id){
        notificationRepository.deleteById(id);
        return ResponseEntity.ok("notification effacé avec succès");

    }


    
    
}
