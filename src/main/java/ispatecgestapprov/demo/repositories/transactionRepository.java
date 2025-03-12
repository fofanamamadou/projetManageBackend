package ispatecgestapprov.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import ispatecgestapprov.demo.entities.transaction;

@EnableJpaRepositories
public interface transactionRepository extends JpaRepository<transaction, Integer>{
    
}
