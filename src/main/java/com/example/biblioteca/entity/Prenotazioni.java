package com.example.biblioteca.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="prenotazioni")
public class Prenotazioni {

    @Id    
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "utente_id")
    private int utenteId;
    
    @Column(name = "libro_id")
    private int libroId;
    
    @Column(name = "biblioteca_id")
    private int bibliotecaId;
    
    @Column(name = "data_prenotazione")
    private LocalDate dataPrenotazione;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getUtenteId() {
        return utenteId;
    }
    
    public void setUtenteId(int utenteId) {
        this.utenteId = utenteId;
    }
    
    public int getLibroId() {
        return libroId;
    }
    
    public void setLibroId(int libroId) {
        this.libroId = libroId;
    }
    
    public int getBibliotecaId() {
        return bibliotecaId;
    }
    
    public void setBibliotecaId(int bibliotecaId) {
        this.bibliotecaId = bibliotecaId;
    }
    
    public LocalDate getDataPrenotazione() {
        return dataPrenotazione;
    }
    
    public void setDataPrenotazione(LocalDate dataPrenotazione) {
        this.dataPrenotazione = dataPrenotazione;
    }
}