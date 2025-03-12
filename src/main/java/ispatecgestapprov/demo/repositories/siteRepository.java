package ispatecgestapprov.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import ispatecgestapprov.demo.entities.site;

@EnableJpaRepositories
public interface siteRepository extends JpaRepository<site, Integer>{
     List<site> findByNom(String nom);
    
}
