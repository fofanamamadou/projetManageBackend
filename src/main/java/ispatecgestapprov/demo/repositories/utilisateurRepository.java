package ispatecgestapprov.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import ispatecgestapprov.demo.entities.utilisateur;

@EnableJpaRepositories
public interface utilisateurRepository extends JpaRepository<utilisateur, Integer>{
    utilisateur findByEmail(String email);
    
}
