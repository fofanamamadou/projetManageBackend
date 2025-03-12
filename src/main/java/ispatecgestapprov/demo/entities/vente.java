package ispatecgestapprov.demo.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class vente extends idclass{

    @Column(name = "date_vente", unique = false, nullable = true)
    private LocalDate date_vente;

    @Column(name = "note", unique = false, nullable = true)
    private String note;

    // Colonne JSON pour les articles
    @Column(name = "articles", columnDefinition = "json")
    private String articles; // Stocke JSON sous forme de chaîne

    @ManyToOne
    @JoinColumn(name = "client_id")
    private client clients;

    @ManyToOne
    @JoinColumn(name = "site_id")
    private site sites;

    public LocalDate getDate_vente() {
        return date_vente;
    }

    public void setDate_vente(LocalDate date_vente) {
        this.date_vente = date_vente;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getArticles() {
        return articles;
    }

    public void setArticles(String articles) {
        this.articles = articles;
    }

    public client getClients() {
        return clients;
    }

    public void setClients(client clients) {
        this.clients = clients;
    }

    public site getSites() {
        return sites;
    }

    public void setSites(site sites) {
        this.sites = sites;
    }

    

  
    

}
