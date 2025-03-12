package ispatecgestapprov.demo.entities;



import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class notification extends idclass{

    @Column(name="type", unique = false, nullable = false)
    private String type;

    @Column(name="message", unique = false, nullable = false)
    private String message;

    @Column(name="priorite", unique = false, nullable = false)//pour distinguer les notifications urgentes.
    private String priorite;

    @Column(name="lien_associe", unique = false, nullable = false)//pour diriger l'utilisateur vers l'objet lié à la notification.
    private String lien_associe;

    @Column(name="date_notification", unique = false, nullable = false)
    private Date date_notification;

    @Column(name="statut", unique = false, nullable = false)//statut de la notification ( vue, non vue)
    private String statut;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private utilisateur utilisateurs;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPriorite() {
        return priorite;
    }

    public void setPriorite(String priorite) {
        this.priorite = priorite;
    }

    public String getLien_associe() {
        return lien_associe;
    }

    public void setLien_associe(String lien_associe) {
        this.lien_associe = lien_associe;
    }

    public Date getDate_notification() {
        return date_notification;
    }

    public void setDate_notification(Date date_notification) {
        this.date_notification = date_notification;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public utilisateur getUtilisateurs() {
        return utilisateurs;
    }

    public void setUtilisateurs(utilisateur utilisateurs) {
        this.utilisateurs = utilisateurs;
    }

    

   

    

   
    

}
