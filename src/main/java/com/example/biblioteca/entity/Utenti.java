package com.example.biblioteca.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="utenti")
public class Utenti {

    @Id    
@GeneratedValue(strategy= GenerationType.IDENTITY)
private int id;
private String username;
private String password;
private String email;
private int biblioteca_id;
private String telefono;
private String indirizzoresidenza;
public int getId() {
    return id;
}
public void setId(int id) {
    this.id = id;
}
public String getUsername() {
    return username;
}
public void setUsername(String username) {
    this.username = username;
}
public String getPassword() {
    return password;
}
public void setPassword(String password) {
    this.password = password;
}
public String getEmail() {
    return email;
}
public void setEmail(String email) {
    this.email = email;
}
public int getBiblioteca_id() {
    return biblioteca_id;
}
public void setBiblioteca_id(int biblioteca_id) {
    this.biblioteca_id = biblioteca_id;
}
public String getTelefono() {
    return telefono;
}
public void setTelefono(String telefono) {
    this.telefono = telefono;
}
public String getIndirizzoresidenza() {
    return indirizzoresidenza;
}
public void setIndirizzoresidenza(String indirizzoresidenza) {
    this.indirizzoresidenza = indirizzoresidenza;
}





}
