package com.example.biblioteca.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="biblioteche")
public class Biblioteche {
    
    @Id    
@GeneratedValue(strategy= GenerationType.IDENTITY)
private int id;
private String nome;
private String indirizzo;
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
public String getIndirizzo() {
    return indirizzo;
}
public void setIndirizzo(String indirizzo) {
    this.indirizzo = indirizzo;
}




}
