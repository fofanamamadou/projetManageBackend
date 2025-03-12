package ispatecgestapprov.demo.entities;


import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ligneCommande extends idclass {

    @ManyToOne
    @JoinColumn(name = "commande_id", nullable = false)
    @JsonBackReference // 🔥 Ajout pour éviter les boucles JSON infinies
    private commande commandes;

    @ManyToOne
    @JoinColumn(name = "article_id", nullable = false)
    private article articles;

    @Column(name = "quantite_commandee", nullable = false)
    private int quantite_commandee;

    @Column(name = "quantite_recue", nullable = false)
    private int quantite_recue;

    @Column(name = "prix_achat", nullable = false)
    private BigDecimal prix_achat;

    @Column(name = "remise", nullable = true)
    private BigDecimal remise;

    @Column(name = "statut", nullable = false)
    private String statut; // Ex: "En attente", "Partiellement reçu", "Reçu"

    public commande getCommandes() {
        return commandes;
    }

    public void setCommandes(commande commandes) {
        this.commandes = commandes;
    }

    public article getArticles() {
        return articles;
    }

    public void setArticles(article articles) {
        this.articles = articles;
    }

    public int getQuantite_commandee() {
        return quantite_commandee;
    }

    public void setQuantite_commandee(int quantite_commandee) {
        this.quantite_commandee = quantite_commandee;
    }

    public int getQuantite_recue() {
        return quantite_recue;
    }

    public void setQuantite_recue(int quantite_recue) {
        this.quantite_recue = quantite_recue;
    }

    public BigDecimal getPrix_achat() {
        return prix_achat;
    }

    public void setPrix_achat(BigDecimal prix_achat) {
        this.prix_achat = prix_achat;
    }

    public BigDecimal getRemise() {
        return remise;
    }

    public void setRemise(BigDecimal remise) {
        this.remise = remise;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    

}
