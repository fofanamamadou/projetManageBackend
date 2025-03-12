package ispatecgestapprov.demo.entities;




import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class utilisateur extends idclass{

    @Column(name="nom", unique = false, nullable = false)
    private String nom;

    @Column(name="email", unique = true, nullable = false)
    private String email;

    @Column(name="mot_de_passe", unique = true, nullable = false)
    private String mot_de_passe;

    @Column(name="date_ajout", unique = false, nullable = false)
    private LocalDate date_ajout;

    @Column(name="derniere_connexion", unique = false, nullable = true)
    private LocalDate derniere_connexion;


    @ManyToMany(cascade = CascadeType.DETACH, fetch =  FetchType.EAGER)
    @JoinTable(name = "utilisateur_role",joinColumns = @JoinColumn(name ="utilisateur_id"),inverseJoinColumns = @JoinColumn(name="role_id"))
    private List<role> roles;


    public String getNom() {
        return nom;
    }


    public void setNom(String nom) {
        this.nom = nom;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getMot_de_passe() {
        return mot_de_passe;
    }


    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }


    public LocalDate getDate_ajout() {
        return date_ajout;
    }


    public void setDate_ajout(LocalDate date_ajout) {
        this.date_ajout = date_ajout;
    }


   


    public List<role> getRoles() {
        return roles;
    }


    public void setRoles(List<role> roles) {
        this.roles = roles;
    }


    public LocalDate getDerniere_connexion() {
        return derniere_connexion;
    }


    public void setDerniere_connexion(LocalDate derniere_connexion) {
        this.derniere_connexion = derniere_connexion;
    }


    

   
}
