package ispatecgestapprov.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import ispatecgestapprov.demo.entities.representant;

@EnableJpaRepositories
public interface representantRepository extends JpaRepository<representant, Integer>{
    
}
