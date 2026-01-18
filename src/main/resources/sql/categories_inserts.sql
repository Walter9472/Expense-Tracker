-- Standard-Kategorien für Expense Tracker
-- owner = username des Benutzers

-- Ausgaben-Kategorien
INSERT INTO category (name, description, color, owner) VALUES ('Lebensmittel', 'Einkäufe im Supermarkt, Bäcker, Metzger etc.', '#4CAF50', 'walter');
INSERT INTO category (name, description, color, owner) VALUES ('Transport', 'Öffentliche Verkehrsmittel, Benzin, Parken', '#2196F3', 'walter');
INSERT INTO category (name, description, color, owner) VALUES ('Wohnen', 'Miete, Nebenkosten, Strom, Internet', '#9C27B0', 'walter');
INSERT INTO category (name, description, color, owner) VALUES ('Freizeit', 'Kino, Konzerte, Hobbys, Sport', '#FF9800', 'walter');
INSERT INTO category (name, description, color, owner) VALUES ('Restaurant', 'Essen gehen, Cafés, Lieferdienste', '#E91E63', 'walter');
INSERT INTO category (name, description, color, owner) VALUES ('Shopping', 'Kleidung, Elektronik, Haushaltsartikel', '#00BCD4', 'walter');
INSERT INTO category (name, description, color, owner) VALUES ('Gesundheit', 'Arztbesuche, Medikamente, Fitnessstudio', '#8BC34A', 'walter');
INSERT INTO category (name, description, color, owner) VALUES ('Bildung', 'Bücher, Kurse, Weiterbildung, Studium', '#3F51B5', 'walter');
INSERT INTO category (name, description, color, owner) VALUES ('Abonnements', 'Streaming, Zeitschriften, Software', '#607D8B', 'walter');
INSERT INTO category (name, description, color, owner) VALUES ('Sonstiges', 'Alle anderen Ausgaben', '#9E9E9E', 'walter');

-- Einkommens-Kategorien
INSERT INTO category (name, description, color, owner) VALUES ('Gehalt', 'Monatliches Einkommen aus Arbeit', '#4CAF50', 'walter');
INSERT INTO category (name, description, color, owner) VALUES ('Nebenjob', 'Einkommen aus Nebentätigkeiten', '#8BC34A', 'walter');
INSERT INTO category (name, description, color, owner) VALUES ('Geschenke', 'Geldgeschenke von Familie und Freunden', '#FF9800', 'walter');
INSERT INTO category (name, description, color, owner) VALUES ('Rückerstattung', 'Erstattungen, Rückzahlungen', '#2196F3', 'walter');
