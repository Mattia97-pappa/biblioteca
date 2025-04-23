package com.example.biblioteca.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.biblioteca.entity.Biblioteche;
import com.example.biblioteca.entity.NuoviArrivi;
import com.example.biblioteca.entity.Prenotazioni;
import com.example.biblioteca.entity.Utenti;
import com.example.biblioteca.repository.BibliotecheRepository;
import com.example.biblioteca.repository.NuoviArriviRepository;
import com.example.biblioteca.repository.PrenotazioniRepository;
import com.example.biblioteca.repository.UtentiRepository;


/* Questo controller gestisce tutte le funzionalità principali della biblioteca:
   - visualizzazione dei nuovi arrivi
   - ricerca
   - selezione biblioteca
   - prenotazione libri */


@Controller
@RequestMapping("/biblioteca")
public class BibliotecaController {


@Autowired
private NuoviArriviRepository nuoviArriviRepository;

@Autowired
private BibliotecheRepository bibliotecheRepository;

@Autowired
private PrenotazioniRepository prenotazioniRepository;

@Autowired
private UtentiRepository utentiRepository;


    @GetMapping("/home")
public String homepage(){
    return "homepage";
}


@GetMapping("/arrivi")
public String nuoviArrivi(Model model){
    List<NuoviArrivi> nuoviarrivi = nuoviArriviRepository.findAll();
    model.addAttribute("nuoviarrivi", nuoviarrivi);
    return "nuoviarrivi";
}
// Gestisce la ricerca dei libri per nome o autore
@GetMapping("/cerca")
public String cercaLibri(String query, Model model){
    List<NuoviArrivi> risultati= nuoviArriviRepository.findByNomeContainingIgnoreCaseOrAutoreContainingIgnoreCase(query,query);
model.addAttribute("nuoviarrivi", risultati);
model.addAttribute("query", query);// così posso mostrare anche cosa è stato cercato
return "nuoviArrivi";
}



// Quando l’utente sceglie un libro da prenotare, seleziona la biblioteca dove ritirarlo

@GetMapping("/seleziona/{id}")
public String selezionaBiblioteca(@PathVariable("id")int libroId, Model model){
    NuoviArrivi libro= nuoviArriviRepository.findById(libroId).orElse(null);
    if(libro==null){
        return "redirect:/biblioteca/arrivi";
    }
    model.addAttribute("libro", libro);
    model.addAttribute("biblioteche", bibliotecheRepository.findAll());
    return "selezionaBiblioteca";
}


@PostMapping("/prenota")
public String effettuaPrenotazione(
    @RequestParam("libroId") int libroId,
    @RequestParam("bibliotecaId") int bibliotecaId,
    @AuthenticationPrincipal UserDetails userDetails,
    RedirectAttributes redirectAttributes
) {
    if (userDetails == null) {
        return "redirect:/auth/login";
    }

    Utenti utente = utentiRepository.findByEmail(userDetails.getUsername());

    NuoviArrivi libro = nuoviArriviRepository.findById(libroId).orElse(null);
    // prendo il libro, e controllo che ci sia ancora almeno una copia
    if (libro == null || libro.getQuantita() <= 0) {
        redirectAttributes.addFlashAttribute("errore", " Libro non disponibile.");
        return "redirect:/biblioteca/arrivi";
    }

    Biblioteche biblioteca = bibliotecheRepository.findById(bibliotecaId).orElse(null);

    Prenotazioni prenotazioni = new Prenotazioni();
    prenotazioni.setUtenteId(utente.getId());  
    prenotazioni.setLibroId(libro.getId());   
    prenotazioni.setBibliotecaId(biblioteca.getId());  
    prenotazioni.setDataPrenotazione(LocalDate.now());  

    prenotazioniRepository.save(prenotazioni);

    libro.setQuantita(libro.getQuantita() - 1);
    nuoviArriviRepository.save(libro);

    redirectAttributes.addFlashAttribute("successo", " Prenotazione effettuata con successo!");
    return "redirect:/auth/profilo";
}
}




