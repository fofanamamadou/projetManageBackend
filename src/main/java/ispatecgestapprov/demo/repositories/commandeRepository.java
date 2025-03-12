package ispatecgestapprov.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import ispatecgestapprov.demo.entities.commande;

@EnableJpaRepositories
public interface commandeRepository extends JpaRepository<commande, Integer>{
    List<commande> findByStatut(String statut);
    Optional<commande> findByNumerosuivi(String numerosuivi);
    
}
