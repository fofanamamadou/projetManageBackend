package ispatecgestapprov.demo.repositories;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import ispatecgestapprov.demo.entities.article;
import ispatecgestapprov.demo.entities.commande;
import ispatecgestapprov.demo.entities.ligneCommande;


@EnableJpaRepositories
public interface ligneCommandeRepository extends JpaRepository<ligneCommande, Integer>{
    Optional<ligneCommande> findByCommandesAndArticles(commande commandes, article articles);

    //  Trouver les articles d’un fournisseur grace au statut de la commande
    List<ligneCommande> findByCommandes_Fournisseurs_IdAndStatut(int fournisseurId, String statut);

    // Trouver les articles d’un fournisseur avec les statuts "En attente" ou "Partiellement reçu"
    @Query("SELECT lc FROM ligneCommande lc WHERE lc.commandes.fournisseurs.id = :fournisseurId AND lc.statut IN ('En attente', 'Partiellement reçu')")
    List<ligneCommande> findArticlesEnAttenteOuPartiellementRecu(@Param("fournisseurId") int fournisseurId);

    
    
}
