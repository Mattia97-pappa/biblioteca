package com.example.biblioteca.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.biblioteca.entity.Prenotazioni;
import com.example.biblioteca.entity.Utenti;

import com.example.biblioteca.repository.BibliotecheRepository;
import com.example.biblioteca.repository.NuoviArriviRepository;
import com.example.biblioteca.repository.PrenotazioniRepository;
import com.example.biblioteca.repository.UtentiRepository;


/* Questo è il controller per tutto ciò che riguarda login, registrazione, profilo ecc.
   Ho messo tutto sotto "/auth" per tenere organizzate le rotte. */
@Controller
@RequestMapping("/auth")
public class AuthController {
// Qui ho collegato i repository per utenti, prenotazioni, biblioteche e libri
    @Autowired
    private UtentiRepository utentiRepository;

    @Autowired
    private BibliotecheRepository bibliotecheRepository;

    @Autowired
    private NuoviArriviRepository nuoviArriviRepository;


    @Autowired
    private PrenotazioniRepository prenotazioniRepository;


    
// Mostro la pagina di registrazione con il form vuoto e la lista delle biblioteche
    @GetMapping("/register")
    public String mostraFormRegistrazione(Model model) {
        model.addAttribute("utenti", new Utenti());
        model.addAttribute("biblioteche", bibliotecheRepository.findAll());
        return "register";
    }

    // Quando un utente si registra, salvo i suoi dati nel database e lo mando alla home
    @PostMapping("/register")
    public String registraUtente(@ModelAttribute Utenti utenti) {
        
        utentiRepository.save(utenti);
        return "redirect:/biblioteca/home";
    }

// Semplice: mostro la pagina di login
    @GetMapping("/login")
    public String mostraLogin() {
        
        return "login";
    }
    
  
// Questa parte mostra la pagina del profilo dell’utente loggato
    @GetMapping("/profilo")
    public String profilo(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        // Prendo l’utente dal DB usando l’email del login
        Utenti utente = utentiRepository.findByEmail(userDetails.getUsername());
        List<Prenotazioni> prenotazioni = prenotazioniRepository.findByUtenteId(utente.getId());
            // Recupero tutte le sue prenotazion
        List<Map<String, Object>> prenotazioniComplete = new ArrayList<>();
        for (Prenotazioni p : prenotazioni) {
            Map<String, Object> prenotazione = new HashMap<>();
            prenotazione.put("id", p.getId()); // IMPORTANTE
            prenotazione.put("libro", nuoviArriviRepository.findById(p.getLibroId()).orElse(null));
            prenotazione.put("biblioteca", bibliotecheRepository.findById(p.getBibliotecaId()).orElse(null));
            prenotazione.put("data", p.getDataPrenotazione());
            prenotazioniComplete.add(prenotazione);
        }
    
        model.addAttribute("utenti", utente);
        model.addAttribute("prenotazioniComplete", prenotazioniComplete);
    
        return "profilo";
    }
      /* Qui gestisco quando un utente clicca "annulla" su una prenotazione:
       - controllo se è sua
       - aumento la quantità del libro di +1
       - cancello la prenotazione */
    @PostMapping("/annulla-prenotazione")
    public String annullaPrenotazione(@RequestParam("prenotazioneId") int prenotazioneId,
                                      @AuthenticationPrincipal UserDetails userDetails) {
        Prenotazioni prenotazione = prenotazioniRepository.findById(prenotazioneId).orElse(null);
    
        if (prenotazione != null) {
         
            Utenti utente = utentiRepository.findByEmail(userDetails.getUsername());
            if (prenotazione.getUtenteId() == utente.getId()) {
                
                
                nuoviArriviRepository.findById(prenotazione.getLibroId()).ifPresent(libro -> {
                    libro.setQuantita(libro.getQuantita() + 1);
                    nuoviArriviRepository.save(libro); 
                });
    
              
                prenotazioniRepository.deleteById(prenotazioneId);
            }
        }
    
        return "redirect:/auth/profilo";
    }
    
    
    
}



