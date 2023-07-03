package org.lessons.pizzeria.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    /* per definire un AuthenticationProvider ho bisogno di:
    - uno UserDetailsService
    - un PasswordEncoder
   */
    //UserDetailsService
    @Bean
    DatabaseUserDetailsService userDetailsService() {
        return new DatabaseUserDetailsService();
    }

    //PasswordEncoder
    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        // creo l'authenticationProvider
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        //setto il PasswordEncoder
        provider.setPasswordEncoder(passwordEncoder());
        //setto lo UserDetailsService
        provider.setUserDetailsService(userDetailsService());
        return provider;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // definisco la catena di filtri
        http.authorizeHttpRequests()
                .requestMatchers("/ingredients").hasAuthority("ADMIN")
                .requestMatchers("/pizze/edit/**").hasAuthority("ADMIN")
                .requestMatchers("/pizze/create").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST).hasAuthority("ADMIN")
                .requestMatchers("/pizze/**").hasAnyAuthority("ADMIN", "USER")
                .requestMatchers("/offers/**").hasAuthority("ADMIN")
                .requestMatchers("/**").permitAll()
                .and().formLogin()
                .and().logout();
        return http.build();

    }
}
