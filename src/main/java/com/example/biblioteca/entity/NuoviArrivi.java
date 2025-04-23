package com.example.biblioteca.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="nuoviarrivi")
public class NuoviArrivi {

    @Id    
@GeneratedValue(strategy= GenerationType.IDENTITY)
private int id;
private String nome;
private String autore;
private LocalDate pubblicazione;
private String codice;
private Integer quantita;
private String copertina;
public int getId() {
    return id;
}
public void setId(int id) {
    this.id = id;
}
public String getNome() {
    return nome;
}
public void setNome(String nome) {
    this.nome = nome;
}
public String getAutore() {
    return autore;
}
public void setAutore(String autore) {
    this.autore = autore;
}
public LocalDate getPubblicazione() {
    return pubblicazione;
}
public void setPubblicazione(LocalDate pubblicazione) {
    this.pubblicazione = pubblicazione;
}
public String getCodice() {
    return codice;
}
public void setCodice(String codice) {
    this.codice = codice;
}
public Integer getQuantita() {
    return quantita;
}
public void setQuantita(Integer quantita) {
    this.quantita = quantita;
}
public String getCopertina() {
    return copertina;
}
public void setCopertina(String copertina) {
    this.copertina = copertina;
}



}
