package ispatecgestapprov.demo.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

@Entity
public class role extends idclass{

    @Column(name="nom", unique = true, nullable = false)//(Administrateur, gestionnaire, employe.....)
    private String nom;

    @Column(name="description", nullable = true)
    private String description;

    @ManyToMany(mappedBy = "roles")
    private List<utilisateur> utilisateurs;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<utilisateur> getUtilisateurs() {
        return utilisateurs;
    }

    public void setUtilisateurs(List<utilisateur> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }

    


}
