package ispatecgestapprov.demo.entities;



import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ligneRetour extends idclass {

    @ManyToOne
    @JoinColumn(name = "retour_id", nullable = false)
    @JsonBackReference
    private retour retours;

    @ManyToOne
    @JoinColumn(name = "article_id", nullable = false)
    private article articles;

    @Column(name = "quantite_retournee", nullable = false)
    private int quantite_retournee;

    @Column(name = "raison_retour", nullable = false)
    private String raison_retour; // Exemple : "Défectueux", "Erreur de commande"

    @Column(name = "etat", nullable = false)
    private String etat; // "Neuf", "Endommagé", etc.

    public retour getRetours() {
        return retours;
    }

    public void setRetours(retour retours) {
        this.retours = retours;
    }

    public article getArticles() {
        return articles;
    }

    public void setArticles(article articles) {
        this.articles = articles;
    }

    public int getQuantite_retournee() {
        return quantite_retournee;
    }

    public void setQuantite_retournee(int quantite_retournee) {
        this.quantite_retournee = quantite_retournee;
    }

    public String getRaison_retour() {
        return raison_retour;
    }

    public void setRaison_retour(String raison_retour) {
        this.raison_retour = raison_retour;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }


}