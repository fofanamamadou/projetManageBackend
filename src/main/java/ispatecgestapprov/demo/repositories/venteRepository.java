package ispatecgestapprov.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import ispatecgestapprov.demo.entities.vente;

@EnableJpaRepositories
public interface venteRepository extends JpaRepository<vente, Integer>{
    
}
