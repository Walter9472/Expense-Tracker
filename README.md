# Expense Tracker - Backend

Dieses Projekt stellt das Backend für die Expense Tracker Anwendung dar. Es ist als RESTful API mit Spring Boot konzipiert und verwaltet Benutzer, Kategorien sowie Transaktionen.

## Features
- **JWT-Authentifizierung**: Sicherer Zugriffsschutz durch JSON Web Tokens.
- **Benutzerverwaltung**: Registrierung und Login.
- **Transaktions-CRUD**: Vollständige Verwaltung von Einnahmen und Ausgaben.
- **Kategoriemanagement**: Unterstützung für globale Standardkategorien und benutzerdefinierte Kategorien.
- **Datenbank-Anbindung**: Nutzung von PostgreSQL für die persistente Speicherung.
- **Sicherheitskonfiguration**: Umfassende CORS-Einstellungen für die Frontend-Backend-Kommunikation.

## Technologiestack
- **Framework**: Spring Boot 3.x
- **Sprache**: Java 17+
- **Sicherheit**: Spring Security, JWT (io.jsonwebtoken)
- **Datenbank**: PostgreSQL, Spring Data JPA (Hibernate)
- **Testing**: JUnit 5, Mockito

## Installation & Setup (Lokal)
1. **Repository klonen**
2. **Datenbank vorbereiten**: Stellen Sie sicher, dass PostgreSQL läuft und eine Datenbank vorhanden ist.
3. **Konfiguration**: Passen Sie die `src/main/resources/application.properties` an:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/deine_db
   spring.datasource.username=dein_nutzer
   spring.datasource.password=dein_passwort
   ```
4. **Starten**: Führen Sie `./gradlew bootRun` aus.

## Deployment auf Render
Das Project ist für das Deployment auf Render vorkonfiguriert. Folgende Umgebungsvariablen müssen gesetzt werden:
- `PORT`: Der Port, auf dem die App läuft (Standard: 8080).
- `DATABASE_URL`: JDBC-URL für die PostgreSQL-Datenbank (wird oft automatisch von Render bereitgestellt).
- `APP_CORS_ALLOWED_ORIGINS`: Kommagetrennte Liste der erlaubten Frontend-URLs (z.B. `https://dein-frontend.onrender.com`).

## Dokumentation
Der gesamte Programmcode (Services und Security-Komponenten) ist ausführlich auf **Deutsch** (Javadoc-Stil) dokumentiert, um die Wartbarkeit und das Verständnis zu erleichtern.

---
*Entwickelt im Rahmen des Moduls WebTechnologien.*
