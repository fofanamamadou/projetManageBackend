package ispatecgestapprov.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class inventaire extends idclass{

    @Column(name="quantite", unique = false, nullable = false)
    private Integer quantite;

    @Column(name="localisation", unique = false, nullable = false)
    private String localisation;

    @ManyToOne
    @JoinColumn(name = "produit_id")
    private article produits;

    @ManyToOne
    @JoinColumn(name = "site_id")
    private site sites;

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public article getProduits() {
        return produits;
    }

    public void setProduits(article produits) {
        this.produits = produits;
    }

    public site getSites() {
        return sites;
    }

    public void setSites(site sites) {
        this.sites = sites;
    }
    




    

}
