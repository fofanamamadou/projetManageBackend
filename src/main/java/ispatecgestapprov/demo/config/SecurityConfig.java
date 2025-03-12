 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispatecgestapprov.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


/**
 *
 * @author PIERRE
 */
@Configuration
@EnableWebSecurity
//@ComponentScan({"com.jfilter.components"})
//@EnableJsonFilter
public class SecurityConfig{
    
    @Autowired
    @Qualifier("CustomUserDetailsService")
    private UserDetailsService userDetailsService;
    
    
@Bean 
public PasswordEncoder passwordEncoder() { 
    return new BCryptPasswordEncoder(); 
} 
    
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
    .httpBasic()
    .and()
    .csrf(csrf -> csrf
            .disable())
    .authorizeHttpRequests(requests -> requests
            .requestMatchers("/anonyme/**").permitAll()
            //.requestMatchers("/access/**").hasRole("USERS")
            .anyRequest()
            .authenticated());

return http.build();
}
    
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

}
