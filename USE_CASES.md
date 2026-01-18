# Use-Case-Dokumentation: Expense Tracker

Diese Dokumentation beschreibt die wichtigsten Funktionen (Use Cases) der Anwendung, wie von Prof. Dr. Arif Wider gefordert.

## Übersicht der Use Cases

| UC-ID | Use Case Name | Beschreibung |
|-------|---------------|--------------|
| UC1 | **Registrierung** | Ein neuer Benutzer erstellt ein Konto mit Benutzername und Passwort. |
| UC2 | **Login** | Ein bestehender Benutzer meldet sich an der App an. |
| UC3 | **Dashboard einsehen** | Der Benutzer sieht eine Zusammenfassung seiner Finanzen mit Charts und Statistiken. |
| UC4 | **Transaktionen auflisten** | Der Benutzer sieht eine detaillierte Liste aller getätigten Buchungen. |
| UC5 | **Neue Transaktion erstellen** | Der Benutzer fügt eine neue Einnahme oder Ausgabe hinzu. |
| UC6 | **Transaktion löschen** | Der Benutzer entfernt eine bestehende Buchung aus der Liste. |
| UC7 | **Transaktionen filtern & suchen** | Der Benutzer sucht nach spezifischen Begriffen oder filtert nach Kategorien/Typen. |
| UC8 | **CSV Export** | Der Benutzer lädt seine Transaktionsdaten als CSV-Datei herunter. |
| UC9 | **Profil verwalten** | Der Benutzer kann seine Profilinformationen einsehen. |
| UC10 | **Logout** | Der Benutzer meldet sich sicher von der Anwendung ab. |

---

## Screenshot-Leitfaden

Machen Sie von den folgenden Ansichten Screenshots für die Dokumentation:

### 1. Registrierung & Login
- **Registrierung**: Screenshot der Seite `/register` mit ausgefüllten Testdaten.
- **Login**: Screenshot der Seite `/login` vor dem Absenden.

### 2. Dashboard (Finanzübersicht)
- **Ansicht**: Screenshot der Seite `/`.
- **Inhalt**: Zeigt das Gesamtguthaben, die Einnahmen/Ausgaben-Karten und die drei Diagramme (Pie-Chart, Kategorie-Chart, Trend-Linie).

### 3. Transaktionsverwaltung
- **Liste**: Screenshot der Seite `/transactions`.
- **Filter-Aktion**: Zeigen Sie die Liste mit einem aktiven Suchbegriff oder ausgewählter Kategorie.
- **Neue Buchung**: Screenshot des ausgefüllten Formulars "Neue Buchung" vor dem Speichern.

### 4. Daten-Export (CSV)
- **Aktion**: Screenshot des `/transactions` Screens, idealerweise mit dem Mauszeiger über dem Button "Als CSV exportieren". (Tipp: Nach dem Klick erscheint oft ein Download-Dialog im Browser, diesen mit aufnehmen).

### 5. Profil & Logout
- **Profil**: Screenshot der Seite `/profile`.
- **Logout**: Klicken Sie auf "Abmelden" im Header.

---

## Tipps für die Präsentation
- **Live-Demo**: Zeigen Sie den Ablauf: Login -> Dashboard prüfen -> Neue Transaktion hinzufügen -> Chart-Update beobachten -> Suche/Filter nutzen -> Logout.
- **Dauer**: Halten Sie sich an die 5 Minuten!
- **Render-URL**: Stellen Sie sicher, dass das Backend unter der öffentlichen URL erreichbar ist, bevor Sie anfangen.
