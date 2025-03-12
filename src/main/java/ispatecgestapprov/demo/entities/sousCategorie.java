package ispatecgestapprov.demo.entities;

import java.sql.Date;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class sousCategorie extends idclass{

    @Column(name="nom_sous", unique = true, nullable = false)
    private String nom_sous;

    @Column(name="description_sous", unique = false, nullable = true)
    private String description_sous;

    @Column(name="date_creation", unique = false, nullable = false)
    private LocalDate date_creation;

    @ManyToOne
    @JoinColumn(name="categorie_id" , nullable = false)
    private categorie categories;

    public String getNom_sous() {
        return nom_sous;
    }

    public void setNom_sous(String nom_sous) {
        this.nom_sous = nom_sous;
    }

    public String getDescription_sous() {
        return description_sous;
    }

    public void setDescription_sous(String description_sous) {
        this.description_sous = description_sous;
    }

    public LocalDate getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(LocalDate date_creation) {
        this.date_creation = date_creation;
    }

    public categorie getCategories() {
        return categories;
    }

    public void setCategories(categorie categories) {
        this.categories = categories;
    }

    

   
}
