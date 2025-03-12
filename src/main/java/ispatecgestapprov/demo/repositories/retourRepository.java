package ispatecgestapprov.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import ispatecgestapprov.demo.entities.retour;

@EnableJpaRepositories
public interface retourRepository extends JpaRepository<retour, Integer>{
    
}
