package ispatecgestapprov.demo.entities;


import java.math.BigDecimal;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class article extends idclass{


    @Column(name="description", unique = false, nullable = true)
    private String description;

    @Column(name="code_barre", unique = false, nullable = true)
    private String code_barre;

    @Column(name="note", unique = false, nullable = true)
    private String note;

    @Column(name="site_web", unique = false, nullable = true)
    private String site_web;

    @Column(name="taxe", unique = false, nullable = true)
    private String taxe;

    @Column(name="image", unique = false, nullable = true)
    private String image;

    @Column(name="number_fournisseur", unique = false, nullable = true)
    private String number_fournisseur;

    @Column(name="prix_achat", unique = false, nullable = true)
    private BigDecimal prix_achat;

    @Column(name="prix_vente", unique = false, nullable = true)
    private BigDecimal prix_vente;

    @Column(name="quantite", unique = false, nullable = true)
    private int quantite;

    @Column(name="seuil_minimum", unique = false, nullable = true)
    private int seuil_minimum;

    @Column(name="quantite_maximum", unique = false, nullable = true)
    private int quantite_maximum;

    
    @Column(name="date_creation", unique = false, nullable = true)
    private Date date_creation;
    
    


    //relation
    @ManyToOne
    @JoinColumn(name = "sousCategorie_id")
    private sousCategorie sousCategories;

    @ManyToOne
    @JoinColumn(name = "site_id")
    private site sites;

    @ManyToOne
    @JoinColumn(name = "categorie_id")
    private categorie categories;

    @ManyToOne
    @JoinColumn(name="fournisseur_id")
    private fournisseur fournisseurs;

    @ManyToOne
    @JoinColumn(name="uniteMesure_id")
    private uniteMesure uniteMesures;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode_barre() {
        return code_barre;
    }

    public void setCode_barre(String code_barre) {
        this.code_barre = code_barre;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getSite_web() {
        return site_web;
    }

    public void setSite_web(String site_web) {
        this.site_web = site_web;
    }

    public String getTaxe() {
        return taxe;
    }

    public void setTaxe(String taxe) {
        this.taxe = taxe;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNumber_fournisseur() {
        return number_fournisseur;
    }

    public void setNumber_fournisseur(String number_fournisseur) {
        this.number_fournisseur = number_fournisseur;
    }

    public BigDecimal getPrix_achat() {
        return prix_achat;
    }

    public void setPrix_achat(BigDecimal prix_achat) {
        this.prix_achat = prix_achat;
    }

    public BigDecimal getPrix_vente() {
        return prix_vente;
    }

    public void setPrix_vente(BigDecimal prix_vente) {
        this.prix_vente = prix_vente;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getSeuil_minimum() {
        return seuil_minimum;
    }

    public void setSeuil_minimum(int seuil_minimum) {
        this.seuil_minimum = seuil_minimum;
    }

    public Date getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }

    public sousCategorie getSousCategories() {
        return sousCategories;
    }

    public void setSousCategories(sousCategorie sousCategories) {
        this.sousCategories = sousCategories;
    }

    public site getSites() {
        return sites;
    }

    public void setSites(site sites) {
        this.sites = sites;
    }

    public categorie getCategories() {
        return categories;
    }

    public void setCategories(categorie categories) {
        this.categories = categories;
    }

    public fournisseur getFournisseurs() {
        return fournisseurs;
    }

    public void setFournisseurs(fournisseur fournisseurs) {
        this.fournisseurs = fournisseurs;
    }

    public uniteMesure getUniteMesures() {
        return uniteMesures;
    }

    public void setUniteMesures(uniteMesure uniteMesures) {
        this.uniteMesures = uniteMesures;
    }

    public int getQuantite_maximum() {
        return quantite_maximum;
    }

    public void setQuantite_maximum(int quantite_maximum) {
        this.quantite_maximum = quantite_maximum;
    }

    

   
    
}