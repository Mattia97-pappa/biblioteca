package com.example.biblioteca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.biblioteca.entity.NuoviArrivi;

public interface NuoviArriviRepository extends JpaRepository<NuoviArrivi, Integer> {
    List<NuoviArrivi> findByNomeContainingIgnoreCaseOrAutoreContainingIgnoreCase(String nome, String autore);

    
}
