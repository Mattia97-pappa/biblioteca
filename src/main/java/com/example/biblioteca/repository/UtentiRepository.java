package com.example.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.biblioteca.entity.Utenti;

public interface UtentiRepository extends JpaRepository<Utenti, Integer>{
    Utenti findByEmail(String email);
    Utenti findByUsername(String username);
    


}
