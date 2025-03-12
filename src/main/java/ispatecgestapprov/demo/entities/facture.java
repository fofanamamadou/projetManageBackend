package ispatecgestapprov.demo.entities;


import java.math.BigDecimal;
import java.sql.Date;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class facture extends idclass{

    @Column(name="date_emission", unique = false, nullable = false)
    private Date date_emission;

    @Column(name="montant_total", unique = false, nullable = false)
    private BigDecimal montant_total;

    @Column(name="statut", unique = false, nullable = false) //statut de la facture(payée, en attente etc...)
    private String statut;

    @Column(name="type", unique = false, nullable = false) //Type de la facture(Approvisionnement ou vente)
    private String type;

    @ManyToOne
    @JoinColumn(name = "commande_id", nullable = true)
    private commande commandes;

    @ManyToOne
    @JoinColumn(name = "vente_id", nullable = true)
    private vente ventes;

    @ManyToOne
    @JoinColumn(name = "fournisseur_id", nullable = true)
    private fournisseur fournisseurs;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = true)
    private client clients;

    

   
    public Date getDate_emission() {
        return date_emission;
    }

    public void setDate_emission(Date date_emission) {
        this.date_emission = date_emission;
    }

    public BigDecimal getMontant_total() {
        return montant_total;
    }

    public void setMontant_total(BigDecimal montant_total) {
        this.montant_total = montant_total;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public commande getCommandes() {
        return commandes;
    }

    public void setCommandes(commande commandes) {
        this.commandes = commandes;
    }

    public vente getVentes() {
        return ventes;
    }

    public void setVentes(vente ventes) {
        this.ventes = ventes;
    }

    public fournisseur getFournisseurs() {
        return fournisseurs;
    }

    public void setFournisseurs(fournisseur fournisseurs) {
        this.fournisseurs = fournisseurs;
    }

    public client getClients() {
        return clients;
    }

    public void setClients(client clients) {
        this.clients = clients;
    }

    // Validations pour s'assurer qu'une seule relation est renseignée
    public boolean isCommandeFacture() {
        return "APPROVISIONNEMENT".equalsIgnoreCase(this.type) && this.commandes != null;
    }

    public boolean isVenteFacture() {
        return "VENTE".equalsIgnoreCase(this.type) && this.ventes != null;
    }

    

}
