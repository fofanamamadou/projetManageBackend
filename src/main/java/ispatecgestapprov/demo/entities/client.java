package ispatecgestapprov.demo.entities;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class client extends idclass{

    @Column(name="nom", unique = false, nullable = false)
    private String nom;

    @Column(name="autrui", unique = false, nullable = false)
    private String autrui;

    @Column(name="prenom_autrui", unique = false, nullable = false)
    private String prenom_autrui;

    @Column(name="adresse_facturation", unique = false, nullable = false)
    private String adresse_facturation;

    @Column(name="adresse_expedition", unique = false, nullable = false)
    private String adresse_expedition;

    @Column(name="ville_pays", unique = false, nullable = false)
    private String ville_pays;

    @Column(name="etat_province", unique = false, nullable = false)
    private String etat_province;

    @Column(name="code_postal", unique = false, nullable = false)
    private String code_postal;

    @Column(name="telephone",length = 15, unique = true, nullable = false)
    private String telephone;

    @Column(name="telephone_second",length = 15, unique = true, nullable = false)
    private String telephone_second;

    @Column(name="email", unique = true, nullable = false)
    private String email;

    @Column(name="numero_fax", unique = true, nullable = false)
    private String numero_fax;

    @Column(name="note", unique = true, nullable = false)
    private String note;

    @Column(name="date_inscription", unique = false, nullable = false)
    private Date date_inscription;

    @ManyToOne
    @JoinColumn(name="representant_id")
    private representant representants;

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

    public String getVille_pays() {
        return ville_pays;
    }

    public void setVille_pays(String ville_pays) {
        this.ville_pays = ville_pays;
    }

    public String getEtat_province() {
        return etat_province;
    }

    public void setEtat_province(String etat_province) {
        this.etat_province = etat_province;
    }

    public String getCode_postal() {
        return code_postal;
    }

    public void setCode_postal(String code_postal) {
        this.code_postal = code_postal;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumero_fax() {
        return numero_fax;
    }

    public void setNumero_fax(String numero_fax) {
        this.numero_fax = numero_fax;
    }

    public Date getDate_inscription() {
        return date_inscription;
    }

    public void setDate_inscription(Date date_inscription) {
        this.date_inscription = date_inscription;
    }

    public representant getRepresentants() {
        return representants;
    }

    public void setRepresentants(representant representants) {
        this.representants = representants;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    


    

    

}