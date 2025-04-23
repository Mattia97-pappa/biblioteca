package com.example.biblioteca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.example.biblioteca.entity.Prenotazioni;

public interface PrenotazioniRepository extends JpaRepository<Prenotazioni, Integer> {
    @Query("SELECT p FROM Prenotazioni p WHERE p.utenteId = :utenteId")
    List<Prenotazioni> findByUtenteId(int utenteId);

}
