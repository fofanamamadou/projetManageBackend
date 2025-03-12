package ispatecgestapprov.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import ispatecgestapprov.demo.entities.client;

@EnableJpaRepositories
public interface clientRepository extends JpaRepository<client, Integer>{
    
}
