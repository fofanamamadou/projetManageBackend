package ispatecgestapprov.demo.entities;


import java.sql.Date;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class transaction extends idclass{

    @Column(name="date_transaction", unique = false, nullable = false)
    private Date date_transaction;

    @Column(name="type", unique = false, nullable = false)//type de la transaction (entrée ou sortie)
    private String type;

    @Column(name="mode_transaction", unique = false, nullable = false)// mode (en ligne, en magasin, etc.).
    private String mode_transaction;
    
    @Column(name="quantite", unique = false, nullable = false)//quantite impliquée
    private Integer quantite;

    @Column(name="description", unique = false, nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "produit_id")
    private article produits;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")//pour identifier qui a validé la transaction.
    private utilisateur utilisateurs;

    public Date getDate_transaction() {
        return date_transaction;
    }

    public void setDate_transaction(Date date_transaction) {
        this.date_transaction = date_transaction;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMode_transaction() {
        return mode_transaction;
    }

    public void setMode_transaction(String mode_transaction) {
        this.mode_transaction = mode_transaction;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public article getProduits() {
        return produits;
    }

    public void setProduits(article produits) {
        this.produits = produits;
    }

    public utilisateur getUtilisateurs() {
        return utilisateurs;
    }

    public void setUtilisateurs(utilisateur utilisateurs) {
        this.utilisateurs = utilisateurs;
    }

    
    
}
