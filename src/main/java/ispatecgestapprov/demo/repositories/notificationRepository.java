package ispatecgestapprov.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import ispatecgestapprov.demo.entities.notification;

@EnableJpaRepositories
public interface notificationRepository extends JpaRepository<notification, Integer>{
    
}
