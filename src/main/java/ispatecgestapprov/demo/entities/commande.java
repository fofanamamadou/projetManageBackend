package ispatecgestapprov.demo.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class commande extends idclass{

    @Column(name="date_emission", unique = false, nullable = true)
    private LocalDate date_emission;
    
    @Column(name="statut", unique = false, nullable = false)
    private String statut; //Statut de la commande (ex:  expédiée, reçue, partiellement recu)

    @Column(name = "date_reception_prevue", unique = false, nullable = false)
    private LocalDate date_reception_prevue;

    @Column(name = "adresse_facturation", nullable = true)
    private String adresse_facturation;

    @Column(name = "adresse_expedition", nullable = true)
    private String adresse_expedition;

    @Column(name = "notes_commande", nullable = true)
    private String notes_commande;

    @Column(name = "numerosuivi", nullable = true, unique = true)
    private String numerosuivi;

    @Column(name = "notes_internes", nullable = true)
    private String notes_internes;


    //Relations
    @ManyToOne
    @JoinColumn(name = "fournisseur_id")
    private fournisseur fournisseurs;

    @ManyToOne
    @JoinColumn(name = "site_id")
    private site sites;

    @ManyToOne
    @JoinColumn(name = "transporteur_id")
    private transporteur transporteurs;

    // 🔥 Ajout de la relation avec LigneCommande
    @OneToMany(mappedBy = "commandes", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<ligneCommande> ligneCommandes = new ArrayList<>();

    public LocalDate getDate_emission() {
        return date_emission;
    }

    public void setDate_emission(LocalDate date_emission) {
        this.date_emission = date_emission;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public LocalDate getDate_reception_prevue() {
        return date_reception_prevue;
    }

    public void setDate_reception_prevue(LocalDate date_reception_prevue) {
        this.date_reception_prevue = date_reception_prevue;
    }

    public String getAdresse_facturation() {
        return adresse_facturation;
    }

    public void setAdresse_facturation(String adresse_facturation) {
        this.adresse_facturation = adresse_facturation;
    }

    public String getAdresse_expedition() {
        return adresse_expedition;
    }

    public void setAdresse_expedition(String adresse_expedition) {
        this.adresse_expedition = adresse_expedition;
    }

    public String getNotes_commande() {
        return notes_commande;
    }

    public void setNotes_commande(String notes_commande) {
        this.notes_commande = notes_commande;
    }

   

    public String getNotes_internes() {
        return notes_internes;
    }

    public void setNotes_internes(String notes_internes) {
        this.notes_internes = notes_internes;
    }


    public fournisseur getFournisseurs() {
        return fournisseurs;
    }

    public void setFournisseurs(fournisseur fournisseurs) {
        this.fournisseurs = fournisseurs;
    }

    public site getSites() {
        return sites;
    }

    public void setSites(site sites) {
        this.sites = sites;
    }

    public transporteur getTransporteurs() {
        return transporteurs;
    }

    public void setTransporteurs(transporteur transporteurs) {
        this.transporteurs = transporteurs;
    }

    public String getNumerosuivi() {
        return numerosuivi;
    }

    public void setNumerosuivi(String numerosuivi) {
        this.numerosuivi = numerosuivi;
    }

    

   

}
