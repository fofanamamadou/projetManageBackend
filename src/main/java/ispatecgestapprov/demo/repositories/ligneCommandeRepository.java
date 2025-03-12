package ispatecgestapprov.demo.repositories;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import ispatecgestapprov.demo.entities.article;
import ispatecgestapprov.demo.entities.commande;
import ispatecgestapprov.demo.entities.ligneCommande;


@EnableJpaRepositories
public interface ligneCommandeRepository extends JpaRepository<ligneCommande, Integer>{
    Optional<ligneCommande> findByCommandesAndArticles(commande commandes, article articles);
    List<ligneCommande> findByCommandes_Fournisseurs_IdAndStatut(int fournisseurId, String statut);

    
    
}
