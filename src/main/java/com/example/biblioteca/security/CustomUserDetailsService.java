package com.example.biblioteca.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import com.example.biblioteca.entity.Utenti;
import com.example.biblioteca.repository.UtentiRepository;

/* 
   Questo servizio serve per dire a Spring Security 
   come recuperare le info dell’utente quando fa il login.
   In pratica: gli passo un’email, e lui mi torna il profilo dell’utente 
   con email e password (così Spring può verificarla).
*/

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UtentiRepository utentiRepository;

    // Questo metodo viene chiamato automaticamente da Spring Security quando qualcuno fa il login
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        // Debug: stampo a console chi sta cercando di fare il login
        System.out.println("Tentativo di login con email: " + email);

        // Cerco nel DB se esiste un utente con questa email
        Utenti utente = utentiRepository.findByEmail(email);

        // Se non lo trovo, lancio un’eccezione (Spring poi mostra errore nel login)
        if (utente == null) {
            System.out.println("Utente non trovato con email: " + email);
            throw new UsernameNotFoundException("Utente non trovato con email: " + email);
        }

        // Se lo trovo, stampo una conferma
        System.out.println("Utente trovato: " + utente.getEmail());

        // Ritorno un oggetto User di Spring Security con email, password e lista di ruoli (qui vuota)
        return new User(
                utente.getEmail(),
                utente.getPassword(),
                new ArrayList<>() // niente ruoli/permessi per ora
        );
    }
}


