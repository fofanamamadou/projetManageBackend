package ispatecgestapprov.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import ispatecgestapprov.demo.entities.fournisseur;

@EnableJpaRepositories
public interface fournisseurRepository extends JpaRepository<fournisseur, Integer>{
    
}
