package ispatecgestapprov.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class transporteur extends idclass{

    @Column(name="nom", unique = true, nullable = false)
    private String nom;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    
    
    

   

    

}
