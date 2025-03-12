package ispatecgestapprov.demo.entities;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class representant extends idclass{

    @Column(name="nom_representant", unique = false, nullable = false)
    private String nom_representant;

    public String getNom_representant() {
        return nom_representant;
    }

    public void setNom_representant(String nom_representant) {
        this.nom_representant = nom_representant;
    }

    

    

    

    

    
}
