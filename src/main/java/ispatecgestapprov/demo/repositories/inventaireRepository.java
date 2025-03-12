package ispatecgestapprov.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import ispatecgestapprov.demo.entities.inventaire;

@EnableJpaRepositories
public interface inventaireRepository extends JpaRepository<inventaire, Integer>{
    
}
