package ispatecgestapprov.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import ispatecgestapprov.demo.entities.sousCategorie;

@EnableJpaRepositories
public interface sousCategorieRepository extends JpaRepository<sousCategorie, Integer>{
    List<sousCategorie> findByCategoriesId(int categorieId);
    
}
