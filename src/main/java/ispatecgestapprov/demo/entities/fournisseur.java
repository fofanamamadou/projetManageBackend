package ispatecgestapprov.demo.entities;


import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class fournisseur extends idclass{

    @Column(name="nom", unique = false, nullable = false)
    private String nom;

    @Column(name="autrui", unique = false, nullable = false)
    private String autrui;

    @Column(name="prenom_autrui", unique = false, nullable = false)
    private String prenom_autrui;

    @Column(name="adresse", unique = false, nullable = false)
    private String adresse;

    @Column(name="fax", unique = false, nullable = false)
    private String fax;

    @Column(name="email", unique = true, nullable = false) 
    private String email;

    @Column(name="date_debut_collaboration", unique = false, nullable = false) 
    private LocalDate date_debut_collaboration;

    @Column(name="telephone", unique = true, nullable = false) 
    private String telephone;

    @Column(name="telephone_second", unique = true, nullable = false) 
    private String telephone_second;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAutrui() {
        return autrui;
    }

    public void setAutrui(String autrui) {
        this.autrui = autrui;
    }

    public String getPrenom_autrui() {
        return prenom_autrui;
    }

    public void setPrenom_autrui(String prenom_autrui) {
        this.prenom_autrui = prenom_autrui;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getTelephone_second() {
        return telephone_second;
    }

    public void setTelephone_second(String telephone_second) {
        this.telephone_second = telephone_second;
    }

    public LocalDate getDate_debut_collaboration() {
        return date_debut_collaboration;
    }

    public void setDate_debut_collaboration(LocalDate date_debut_collaboration) {
        this.date_debut_collaboration = date_debut_collaboration;
    }

    

    

    

    

    
}
