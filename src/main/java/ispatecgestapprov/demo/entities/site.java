package ispatecgestapprov.demo.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class site extends idclass{

    @Column(name="nom", unique = true, nullable = false)
    private String nom;
    
    @Column(name="adresse", unique = false, nullable = false)
    private String adresse;
    
    @Column(name="description", unique = false, nullable = true)
    private String description;

    

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    

    

}
