package ispatecgestapprov.demo.entities;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class transfert extends idclass{

    @Column(name="quantite", unique = false, nullable = false)
    private int quantite;

    // Colonne JSON pour les articles
    @Column(name = "articles", columnDefinition = "json")
    private String articles; // Stocke JSON sous forme de chaîne

    @ManyToOne
    @JoinColumn(name="site_origine_id")
    private site siteOrigine;

    @ManyToOne
    @JoinColumn(name="site_tranferer_id")
    private site siteTransferer;

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getArticles() {
        return articles;
    }

    public void setArticles(String articles) {
        this.articles = articles;
    }

    public site getSiteOrigine() {
        return siteOrigine;
    }

    public void setSiteOrigine(site siteOrigine) {
        this.siteOrigine = siteOrigine;
    }

    public site getSiteTransferer() {
        return siteTransferer;
    }

    public void setSiteTransferer(site siteTransferer) {
        this.siteTransferer = siteTransferer;
    }

    

    
}
