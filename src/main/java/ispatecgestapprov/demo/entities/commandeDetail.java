package ispatecgestapprov.demo.entities;

import java.math.BigDecimal;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class commandeDetail extends idclass{

    @Column(name="quantite", unique = false, nullable = false)
    private Integer quantite;

    @Column(name="prix_unitaire", unique = false, nullable = false)
    private BigDecimal prix_unitaire;

    @Column(name="montant_total", unique = false, nullable = false)
    private BigDecimal montant_total;

    @Column(name="taxes", nullable = true)
    private BigDecimal taxes;

    @Column(name="remise", nullable = true) // Au cas ou il y'a des promotions sur des produits(par ex 20%, etc...)
    private BigDecimal remise;

    @Column(name="unite_mesure", nullable = true)
    private String unite_mesure;

    @ManyToOne
    @JoinColumn(name = "produit_id")
    private article produits;

    @ManyToOne
    @JoinColumn(name = "commande_id")
    private commande commandes;

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }
    
    public BigDecimal getPrix_unitaire() {
        return prix_unitaire;
    }

    public void setPrix_unitaire(BigDecimal prix_unitaire) {
        this.prix_unitaire = prix_unitaire;
    }

    public BigDecimal getMontant_total() {
        return montant_total;
    }

    public void setMontant_total(BigDecimal montant_total) {
        this.montant_total = montant_total;
    }

    public BigDecimal getTaxes() {
        return taxes;
    }

    public void setTaxes(BigDecimal taxes) {
        this.taxes = taxes;
    }

    public BigDecimal getRemise() {
        return remise;
    }

    public void setRemise(BigDecimal remise) {
        this.remise = remise;
    }

    public String getUnite_mesure() {
        return unite_mesure;
    }

    public void setUnite_mesure(String unite_mesure) {
        this.unite_mesure = unite_mesure;
    }

    public article getProduits() {
        return produits;
    }

    public void setProduits(article produits) {
        this.produits = produits;
    }

    public commande getCommandes() {
        return commandes;
    }

    public void setCommandes(commande commandes) {
        this.commandes = commandes;
    }

    
}
