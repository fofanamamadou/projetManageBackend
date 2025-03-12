package ispatecgestapprov.demo.repositories;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import ispatecgestapprov.demo.entities.article;


@EnableJpaRepositories
public interface articleRepository extends JpaRepository<article, Integer>{

    List<article> findByFournisseurs_Id(int fournisseurId);

    
}
