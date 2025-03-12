package ispatecgestapprov.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import ispatecgestapprov.demo.entities.uniteMesure;

@EnableJpaRepositories
public interface uniteMesureRepository extends JpaRepository<uniteMesure, Integer>{
    
}
