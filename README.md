# Expense Tracker

Nutzer verwalten Einnahmen, Ausgaben, Kategorien und Budgets.

**Expense Tracker – WebTech Projekt**

Der Expense Tracker ist eine moderne Webanwendung, mit der Benutzer ihre Einnahmen und Ausgaben übersichtlich verwalten, kategorisieren und analysieren können. Ziel des Projekts ist es, ein benutzerfreundliches Finanz-Management-Tool zu entwickeln, das sowohl im Frontend als auch im Backend auf saubere, strukturierte und moderne Technologien setzt.

**Projektziele**

- **Nutzerfreundliche Oberfläche**: Ein intuitives Interface zur Erfassung und Verwaltung von Transaktionen.  
- **Übersicht**: Klare Darstellung von Ausgaben und Einnahmen, strukturiert nach Kategorien.  
- **Automatische Einrichtung**: Bei der Registrierung werden Standardkategorien automatisch eingerichtet.  
- **Filter- und Sortierfunktionen**: Transaktionen können nach Datum, Kategorie oder Betrag gefiltert und sortiert werden.  
- **Statistik und Diagramme**: Grundlage für aussagekräftige Auswertungen im Frontend.

**Backend (Spring Boot)**

Das Backend stellt eine RESTful API bereit und ist in Java (Spring Boot) entwickelt.  
Verantwortlichkeiten:

- **User-Authentifizierung**: Registrierung, Login und Benutzerverwaltung.  
- **Transaktionsmanagement**: Verwaltung von Einnahmen und Ausgaben.  
- **Kategorisierung**: Unterstützung sowohl standardisierter als auch benutzerdefinierter Kategorien.  
- **Berichte und Filterung**: Bereitstellung von Filtermöglichkeiten und Berichten.  

Struktur:

- **Entities**:
  - `User` – Repräsentiert den Benutzer.
  - `Transaction` – Speichert Details zu Ausgaben und Einnahmen.
  - `Category` – Ordnet Transaktionen den jeweiligen Kategorien zu.
- **Datenbank**: Speicherung der Daten über JPA in einer relationalen Datenbank.

**Frontend**

Das Frontend (mit Vue.js) soll es den Benutzern ermöglichen:

- Transaktionen zu erstellen, zu bearbeiten und zu löschen.
- Diagramme und Statistiken einzusehen.
- Filter nach Zeitraum und Kategorie anzuwenden.

**Verwendete Technologien**

- **Backend**: Java, Spring Boot, JPA/Hibernate
- **Frontend**: Vue.js
- **Datenbank**: PostgreSQL
- **Tools**: Maven, GitHub, REST API, JUnit (Tests)

**Beispiel-Use-Cases**

- **Registrierung**: Der Benutzer registriert sich und erhält automatisch Standardkategorien.  
- **Ausgabe erfassen**: Der Benutzer legt eine neue Ausgabe an (z. B. „Essen“, 25 €).  
- **Filterung**: Transaktionen können nach der Kategorie „Essen“ gefiltert werden.  
- **Monatsübersicht**: Zusammenfassung der monatlichen Ausgaben.



## Deployment-Hinweis (Render & Co.)

Damit das gehostete Frontend mit dem Backend kommunizieren kann, muss die erlaubte Origin des Frontends in der Spring-Konfiguration hinterlegt werden. Dies geschieht über die Property `app.cors.allowed-origins` (siehe `src/main/resources/application.properties`). Sie akzeptiert eine kommagetrennte Liste, z. B.:

```
app.cors.allowed-origins=http://localhost:5173,https://mein-frontend.onrender.com
```

Beim Deployment auf Render kann der Wert komfortabel über eine Environment-Variable überschrieben werden, sodass sowohl lokale Entwicklung als auch Produktionsbetrieb ohne Codeänderungen funktionieren.
