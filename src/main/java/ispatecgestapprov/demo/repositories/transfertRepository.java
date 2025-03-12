package ispatecgestapprov.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import ispatecgestapprov.demo.entities.transfert;

@EnableJpaRepositories
public interface transfertRepository extends JpaRepository<transfert, Integer>{
    
}
