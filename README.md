📝 Descrizione
Web app che collega più biblioteche tra loro, permettendo agli utenti di cercare, prenotare e ricevere libri nella biblioteca più vicina alla propria città. Gli utenti possono gestire le prenotazioni direttamente dal proprio profilo.



👀 Dove vedere il codice
Il codice sorgente completo si trova in src, suddiviso in package chiari:

controller → gestione delle rotte

entity → entità del database

repository → interfaccia con il DB

security & config → login, autenticazione e accessi

▶️ Come eseguire il progetto
Clona la repository


bash
   git clone https://github.com/Mattia97-pappa/biblioteca.git




Configura il database
```tabella biblioteche
CREATE TABLE `biblioteche` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `indirizzo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

```sql tabella nuoviarrivi(libri)
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





Avvia l'app
Esegui la classe BibliotecaApplication.java (o simile, con @SpringBootApplication)

Accedi all'app dal browser
Vai su http://localhost:8080

👤 Accessi
Puoi registrarti come utente per provare la funzionalità di prenotazione.
