package ispatecgestapprov.demo.entities;



import java.math.BigDecimal;
import java.sql.Date;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class paiement extends idclass{

    @Column(name="montant", unique = false, nullable = false)
    private BigDecimal montant;

    @Column(name="date_paiement", unique = false, nullable = false)
    private Date date_paiement;

    @Column(name="moyen_paiement", unique = false, nullable = false)//moyen de paiement(carte, virement...)
    private String moyen_paiement;

    @Column(name="statut", unique = false, nullable = false)//statut du paiement ( payé, en attente)
    private String statut;

    @ManyToOne
    @JoinColumn(name="commande_id")
    private commande commandes;

    @ManyToOne
    @JoinColumn(name="client_id")
    private client clients;

    @ManyToOne
    @JoinColumn(name="facture_id")
    private facture factures;

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public Date getDate_paiement() {
        return date_paiement;
    }

    public void setDate_paiement(Date date_paiement) {
        this.date_paiement = date_paiement;
    }

    public String getMoyen_paiement() {
        return moyen_paiement;
    }

    public void setMoyen_paiement(String moyen_paiement) {
        this.moyen_paiement = moyen_paiement;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public commande getCommandes() {
        return commandes;
    }

    public void setCommandes(commande commandes) {
        this.commandes = commandes;
    }

    public client getClients() {
        return clients;
    }

    public void setClients(client clients) {
        this.clients = clients;
    }

    public facture getFactures() {
        return factures;
    }

    public void setFactures(facture factures) {
        this.factures = factures;
    }






    

}
