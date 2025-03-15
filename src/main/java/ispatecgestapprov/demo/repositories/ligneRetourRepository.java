package ispatecgestapprov.demo.repositories;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import ispatecgestapprov.demo.entities.article;
import ispatecgestapprov.demo.entities.ligneRetour;
import ispatecgestapprov.demo.entities.retour;


@EnableJpaRepositories
public interface ligneRetourRepository extends JpaRepository<ligneRetour, Integer>{
    //  Trouver une ligneRetour pour un article et un retour spécifique
    Optional<ligneRetour> findByRetoursAndArticles(retour retours, article articles);

    //  Trouver tous les articles retournés d'une commande
    List<ligneRetour> findByRetours_Commandes_Id(int commandeId);

    // Trouver tous les retours liés à un fournisseur
    List<ligneRetour> findByRetours_Fournisseurs_Id(int fournisseurId);

    //  Trouver tous les articles retournés ayant un état spécifique
    List<ligneRetour> findByEtat(String etat);

    
    
}
