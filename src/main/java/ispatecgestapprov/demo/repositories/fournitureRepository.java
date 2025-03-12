package ispatecgestapprov.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import ispatecgestapprov.demo.entities.fourniture;

@EnableJpaRepositories
public interface fournitureRepository extends JpaRepository<fourniture, Integer>{
    
}
