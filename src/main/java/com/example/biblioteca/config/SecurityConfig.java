package com.example.biblioteca.config;

// Qui importiamo tutte le cose che ci servono per gestire la sicurezza con Spring
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// Diciamo a Spring che questa classe serve per configurare la sicurezza
@Configuration
public class SecurityConfig {

    // Qui si configura tutto il comportamento della sicurezza: chi può accedere a cosa, come si fa login ecc.
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        // Qui diciamo cosa può vedere l'utente senza fare login
                        .requestMatchers("/auth/login", "/auth/register", "/css/**", "/biblioteca/**").permitAll()
                        .anyRequest().authenticated()
                ) // Qui configuriamo come deve funzionare il login
                .formLogin(form -> form
                        .loginPage("/auth/login")
                        .usernameParameter("email")
                        .defaultSuccessUrl("/biblioteca/home", true)
                        .permitAll()
                ) // Configuriamo anche il logout
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/auth/login?logout")
                        .permitAll()
                )
                .build();
    }

    // NON ho criptato le psw (le ho lasciate in chiaro). Va bene solo per test locali
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
