package ispatecgestapprov.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import ispatecgestapprov.demo.entities.commandeDetail;

@EnableJpaRepositories
public interface commandeDetailRepository extends JpaRepository<commandeDetail, Integer>{
    
}
