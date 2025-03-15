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
public class retour extends idclass {

    @Column(name="date_retour", nullable = false)
    private LocalDate date_retour;

    @Column(name="note", nullable = true)
    private String note;

    @Column(name="statut", nullable = false)
    private String statut; // Ex: "En cours", "Terminé"

    @ManyToOne
    @JoinColumn(name="fournisseur_id")
    private fournisseur fournisseurs;

    @ManyToOne
    @JoinColumn(name="commande_id")
    private commande commandes;

    @OneToMany(mappedBy = "retours", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<ligneRetour> ligneRetours = new ArrayList<>();

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

    public List<ligneRetour> getLigneRetours() {
        return ligneRetours;
    }

    public void setLigneRetours(List<ligneRetour> ligneRetours) {
        this.ligneRetours = ligneRetours;
    }

    

   
}
