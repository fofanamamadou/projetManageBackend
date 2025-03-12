package ispatecgestapprov.demo.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import ispatecgestapprov.demo.entities.role;

@EnableJpaRepositories
public interface roleRepository extends JpaRepository<role, Integer>{
    List<role> findByNom(String nom);
    
}
