📝 Descrizione
Web app che collega più biblioteche tra loro, permettendo agli utenti di cercare, prenotare e ricevere libri nella biblioteca più vicina alla propria città. Gli utenti possono gestire le prenotazioni direttamente dal proprio profilo.


🎥 [Guarda la demo su Vimeo](https://vimeo.com/1078022973)




👀 
Il codice sorgente completo si trova in src, suddiviso in package chiari:

controller → gestione delle rotte

entity → entità del database

repository → interfaccia con il DB

security & config → login, autenticazione e accessi

▶️ Come eseguire il progetto
Clona la repository


bash
   git clone https://github.com/Mattia97-pappa/biblioteca.git


Tabelle database



```tabella biblioteche
CREATE TABLE `biblioteche` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `indirizzo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
```
``` Tabella libri disponibili
CREATE TABLE `nuoviarrivi` (
  `nome` varchar(255) DEFAULT NULL,
  `autore` varchar(255) DEFAULT NULL,
  `pubblicazione` date DEFAULT NULL,
  `codice` varchar(255) DEFAULT NULL,
  `quantita` int(11) DEFAULT NULL,
  `copertina` varchar(255) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
```
``` Tabella prenotazioni
CREATE TABLE `prenotazioni` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `utente_id` int(11) NOT NULL,
  `libro_id` int(11) NOT NULL,
  `biblioteca_id` int(11) NOT NULL,
  `data_prenotazione` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `utente_id` (`utente_id`),
  KEY `libro_id` (`libro_id`),
  KEY `biblioteca_id` (`biblioteca_id`),
  CONSTRAINT `prenotazioni_ibfk_1` FOREIGN KEY (`utente_id`) REFERENCES `utenti` (`id`) ON DELETE CASCADE,
  CONSTRAINT `prenotazioni_ibfk_2` FOREIGN KEY (`libro_id`) REFERENCES `nuoviarrivi` (`id`) ON DELETE CASCADE,
  CONSTRAINT `prenotazioni_ibfk_3` FOREIGN KEY (`biblioteca_id`) REFERENCES `biblioteche` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
```
``` Tabella User
CREATE TABLE `utenti` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `biblioteca_id` int(11) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `indirizzoresidenza` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  KEY `fk_biblioteca` (`biblioteca_id`),
  CONSTRAINT `fk_biblioteca` FOREIGN KEY (`biblioteca_id`) REFERENCES `biblioteche` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
```

Avvia l'app
Esegui la classe BibliotecaApplication.java (o simile, con @SpringBootApplication)

Accedi all'app dal browser
Vai su http://localhost:8080

👤 Accessi
Puoi registrarti come utente per provare la funzionalità di prenotazione.
