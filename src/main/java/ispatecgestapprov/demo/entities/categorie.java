package ispatecgestapprov.demo.entities;

import java.sql.Date;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class categorie extends idclass{

    @Column(name="nom", unique = true, nullable = false)
    private String nom;

    @Column(name="description", unique = false, nullable = true)
    private String description;

    @Column(name="date_creation", unique = false, nullable = false)
    private LocalDate date_creation;

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

    public LocalDate getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(LocalDate date_creation) {
        this.date_creation = date_creation;
    }

   



    
}
