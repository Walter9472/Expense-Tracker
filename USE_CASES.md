# Dokumentation der Use Cases & Screenshot-Leitfaden

Diese Dokumentation bietet eine Übersicht über die funktionalen Anforderungen (Use Cases) des **Expense Trackers** und dient als Leitfaden für die Erstellung der Screenshots für die Projekt-Abgabe.

## Übersicht der Use Cases

| UC-ID | Use Case Name | Akteur | Kurzbeschreibung |
|-------|---------------|--------|------------------|
| **UC1** | Registrierung | Gast | Erstellung eines neuen Benutzerkontos mit automatischem Setup von Standardkategorien. |
| **UC2** | Login | Benutzer | Sicherer Zugang zur App mittels JWT-Authentifizierung. |
| **UC3** | Dashboard einsehen | Benutzer | Zentrale Übersicht der Finanzen (Guthaben, Einnahmen, Ausgaben) inkl. Charts. |
| **UC4** | Transaktions-Liste | Benutzer | Einsicht aller getätigten Buchungen in einer scrollbaren Gesamtübersicht. |
| **UC5** | Buchung erfassen | Benutzer | Erstellung neuer Einnahmen oder Ausgaben inkl. Kategorisierung. |
| **UC6** | Buchung löschen | Benutzer | Entfernen fehlerhafter oder veralteter Transaktionen. |
| **UC7** | Filtern & Suchen | Benutzer | Gezieltes Suchen nach Titeln oder Filtern nach Kategorien/Typen. |
| **UC8** | CSV-Export | Benutzer | Herunterladen der persönlichen Finanzdaten für externe Auswertungen. |
| **UC9** | Profilanzeige | Benutzer | Ansicht der eigenen Benutzerdaten. |
| **UC10**| Logout | Benutzer | Sicheres Beenden der Sitzung und Löschen des lokalen Tokens. |

---

## Screenshot-Leitfaden (Vorbereitung für die Präsentation)

Bitte erstelle die folgenden Screenshots, um die Funktionalität für Prof. Dr. Arif Wider zu belegen:

### 1. Sicherheit & Zugriff
- **Registrierung (`/register`)**: Zeige das ausgefüllte Formular mit einem neuen Test-User.
- **Login (`/login`)**: Screenshot nach der Eingabe der Daten, bevor du auf "Anmelden" klickst.

### 2. Dashboard (Frontend-Logik & Charts)
- **Die Finanzzentrale (`/`)**: Mache einen Screenshot des Dashboards. Achte darauf, dass die Diagramme (Pie-Chart für Verteilung, Line-Chart für Trend) Daten anzeigen.
- **Statistiken**: Zeige die Karten für "Gesamtguthaben", "Einnahmen" und "Ausgaben" oben auf der Seite.

### 3. Effiziente Datenverwaltung (`/transactions`)
- **Vollständige Liste**: Screenshot der Transaktionsseite. Zeige hierbei die **neue scrollbare Liste**, die den Export-Button immer sichtbar hält.
- **Filter-Funktion**: Tippe einen Suchbegriff ein (z.B. "Edeka") und mache einen Screenshot des gefilterten Ergebnisses.
- **Neue Buchung**: Öffne das Formular "Neue Transaktion" auf der rechten Seite, fülle es aus und fotografiere den Zustand vor dem Speichern.

### 4. Daten-Schnittstellen
- **Export-Aktion**: Klicke auf "Als CSV exportieren" und mache einen Screenshot, während der Browser den Download anzeigt (unten oder oben im Download-Manager).
- **Die CSV-Datei**: Öffne die heruntergeladene Datei kurz in Excel oder einem Texteditor und mache einen Screenshot der Rohdaten.

### 5. Backend-Abschluss (Services & Security)
- **Code-Dokumentation**: (Optional) Öffne eine Klasse wie `UserService.java` oder `SecurityConfig.java` in deiner IDE und zeige die ausführlichen **deutschen Kommentare**, die ich hinzugefügt habe.

---

## Vorbereitung für die 5-Minuten-Demo
1. **Reset**: Lösche ggf. alte Testdaten oder erstelle einen frischen Account.
2. **Standard-Flow**: Login -> Dashboard (Charts zeigen) -> Zu Transaktionen wechseln -> Neuen Eintrag erstellen -> Filter testen -> CSV Export -> Logout.
3. **Render-Check**: Prüfe vorab, ob die Umgebungsvariablen (`VITE_API_BASE_URL` und `APP_CORS_ALLOWED_ORIGINS`) auf Render korrekt gesetzt sind.

---
*Viel Erfolg bei der Präsentation und der Abgabe!*
