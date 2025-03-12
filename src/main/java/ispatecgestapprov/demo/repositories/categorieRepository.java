package ispatecgestapprov.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import ispatecgestapprov.demo.entities.categorie;

@EnableJpaRepositories
public interface categorieRepository extends JpaRepository<categorie, Integer>{
    
}
