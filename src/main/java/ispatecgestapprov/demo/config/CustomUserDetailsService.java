/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispatecgestapprov.demo.config;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ispatecgestapprov.demo.entities.role;
import ispatecgestapprov.demo.repositories.utilisateurRepository;
import ispatecgestapprov.demo.entities.utilisateur;

/**
 *
 * @author PIERRE
 */
@Service("CustomUserDetailsService")
@RestController
@RequestMapping("/auth")
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
	private utilisateurRepository utilisateurRepository;

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        utilisateur users = utilisateurRepository.findByEmail(email);
        System.err.println(users.getRoles());
        UserDetails userDetails = new User(
                email,
                users.getMot_de_passe(),
                true,
                true,
                true,
                true,
                getAuthorities(users.getRoles()));
        
        return userDetails;
    }
    public Collection<? extends GrantedAuthority> getAuthorities(List<role> roles){
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        for (role role : roles){
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getNom());
            grantedAuthorities.add(grantedAuthority);
        }
                return grantedAuthorities;
    }
    
    @GetMapping("/login")
    public utilisateur login(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        utilisateur user = utilisateurRepository.findByEmail(authentication.getName());
        if (user != null) {
        // Mettre à jour la dernière connexion avec la date et l'heure actuelles
        user.setDerniere_connexion(LocalDate.now());
        utilisateurRepository.save(user);
        }

        return user;
    }
    
}
