package ispatecgestapprov.demo.entities;


import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class retour extends idclass{

    @Column(name="date_retour", unique = false, nullable = false)
    private LocalDate date_retour;

    @Column(name="note", unique = false, nullable = false)
    private String note;

    @Column(name="statut", unique = false, nullable = false)// Pour indiquer l'état actuel du retour.... "En cours, Terminé" 
    private String statut;

    // Colonne JSON pour les articles
    @Column(name = "articles", columnDefinition = "json")
    private String articles; // Stocke JSON sous forme de chaîne

    @ManyToOne
    @JoinColumn(name="founisseur_id")
    private fournisseur fournisseurs;

    @ManyToOne
    @JoinColumn(name="commande_id")
    private commande commandes;

    public LocalDate getDate_retour() {
        return date_retour;
    }

    public void setDate_retour(LocalDate date_retour) {
        this.date_retour = date_retour;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getArticles() {
        return articles;
    }

    public void setArticles(String articles) {
        this.articles = articles;
    }

    public fournisseur getFournisseurs() {
        return fournisseurs;
    }

    public void setFournisseurs(fournisseur fournisseurs) {
        this.fournisseurs = fournisseurs;
    }

    public commande getCommandes() {
        return commandes;
    }

    public void setCommandes(commande commandes) {
        this.commandes = commandes;
    }

    
    








    

}
