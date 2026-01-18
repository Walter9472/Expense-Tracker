-- Beispiel-Transaktionen für den Account "walter"
-- Annahme: Kategorien wurden bereits eingefügt (category_id 1-14)
-- type: 0 = EINKOMMEN, 1 = AUSGABEN

-- Dezember 2025 Transaktionen
INSERT INTO transaction (title, amount, date, type, description, category_id, owner) 
VALUES ('Wocheneinkauf Rewe', 85.47, '2025-12-28', 1, 'Wöchentlicher Großeinkauf', 1, 'walter');

INSERT INTO transaction (title, amount, date, type, description, category_id, owner) 
VALUES ('BVG Monatskarte', 86.00, '2025-12-01', 1, 'Monatskarte AB', 2, 'walter');

INSERT INTO transaction (title, amount, date, type, description, category_id, owner) 
VALUES ('Dezember Miete', 750.00, '2025-12-01', 1, 'Warmmiete Dezember', 3, 'walter');

INSERT INTO transaction (title, amount, date, type, description, category_id, owner) 
VALUES ('Kino Star Wars', 15.50, '2025-12-22', 1, 'Kinobesuch mit Freunden', 4, 'walter');

INSERT INTO transaction (title, amount, date, type, description, category_id, owner) 
VALUES ('Sushi Restaurant', 42.80, '2025-12-20', 1, 'Abendessen mit Freundin', 5, 'walter');

INSERT INTO transaction (title, amount, date, type, description, category_id, owner) 
VALUES ('Amazon Kopfhörer', 79.99, '2025-12-15', 1, 'Sony Wireless Kopfhörer', 6, 'walter');

INSERT INTO transaction (title, amount, date, type, description, category_id, owner) 
VALUES ('Apotheke Medikamente', 28.50, '2025-12-18', 1, 'Erkältungsmedikamente', 7, 'walter');

INSERT INTO transaction (title, amount, date, type, description, category_id, owner) 
VALUES ('Udemy Kurs', 19.99, '2025-12-10', 1, 'Vue.js Masterclass', 8, 'walter');

INSERT INTO transaction (title, amount, date, type, description, category_id, owner) 
VALUES ('Netflix', 12.99, '2025-12-01', 1, 'Monatliches Abo', 9, 'walter');

INSERT INTO transaction (title, amount, date, type, description, category_id, owner) 
VALUES ('Spotify', 9.99, '2025-12-01', 1, 'Premium Abo', 9, 'walter');

INSERT INTO transaction (title, amount, date, type, description, category_id, owner) 
VALUES ('Dezember Gehalt', 2500.00, '2025-12-27', 0, 'Werkstudent Gehalt', 11, 'walter');

INSERT INTO transaction (title, amount, date, type, description, category_id, owner) 
VALUES ('Weihnachtsgeld Oma', 100.00, '2025-12-24', 0, 'Weihnachtsgeschenk', 13, 'walter');

-- November 2025 Transaktionen
INSERT INTO transaction (title, amount, date, type, description, category_id, owner) 
VALUES ('Wocheneinkauf Edeka', 72.33, '2025-11-25', 1, 'Lebensmittel für die Woche', 1, 'walter');

INSERT INTO transaction (title, amount, date, type, description, category_id, owner) 
VALUES ('BVG Monatskarte', 86.00, '2025-11-01', 1, 'Monatskarte AB', 2, 'walter');

INSERT INTO transaction (title, amount, date, type, description, category_id, owner) 
VALUES ('November Miete', 750.00, '2025-11-01', 1, 'Warmmiete November', 3, 'walter');

INSERT INTO transaction (title, amount, date, type, description, category_id, owner) 
VALUES ('Bowling Abend', 25.00, '2025-11-15', 1, 'Bowling mit Kommilitonen', 4, 'walter');

INSERT INTO transaction (title, amount, date, type, description, category_id, owner) 
VALUES ('Pizza Lieferdienst', 18.90, '2025-11-10', 1, 'Lieferando Bestellung', 5, 'walter');

INSERT INTO transaction (title, amount, date, type, description, category_id, owner) 
VALUES ('H&M Winterjacke', 119.99, '2025-11-08', 1, 'Neue Winterjacke', 6, 'walter');

INSERT INTO transaction (title, amount, date, type, description, category_id, owner) 
VALUES ('McFit Beitrag', 24.99, '2025-11-01', 1, 'Monatlicher Beitrag', 7, 'walter');

INSERT INTO transaction (title, amount, date, type, description, category_id, owner) 
VALUES ('November Gehalt', 2500.00, '2025-11-28', 0, 'Werkstudent Gehalt', 11, 'walter');

-- Oktober 2025 Transaktionen
INSERT INTO transaction (title, amount, date, type, description, category_id, owner) 
VALUES ('Wocheneinkauf Lidl', 65.22, '2025-10-20', 1, 'Wocheneinkauf', 1, 'walter');

INSERT INTO transaction (title, amount, date, type, description, category_id, owner) 
VALUES ('BVG Monatskarte', 86.00, '2025-10-01', 1, 'Monatskarte AB', 2, 'walter');

INSERT INTO transaction (title, amount, date, type, description, category_id, owner) 
VALUES ('Oktober Miete', 750.00, '2025-10-01', 1, 'Warmmiete Oktober', 3, 'walter');

INSERT INTO transaction (title, amount, date, type, description, category_id, owner) 
VALUES ('Konzert Tickets', 65.00, '2025-10-18', 1, 'Konzert Mercedes-Benz Arena', 4, 'walter');

INSERT INTO transaction (title, amount, date, type, description, category_id, owner) 
VALUES ('Burger King', 12.50, '2025-10-12', 1, 'Mittagessen unterwegs', 5, 'walter');

INSERT INTO transaction (title, amount, date, type, description, category_id, owner) 
VALUES ('MediaMarkt USB-Hub', 34.99, '2025-10-05', 1, 'USB-C Hub für Laptop', 6, 'walter');

INSERT INTO transaction (title, amount, date, type, description, category_id, owner) 
VALUES ('Oktober Gehalt', 2500.00, '2025-10-28', 0, 'Werkstudent Gehalt', 11, 'walter');

INSERT INTO transaction (title, amount, date, type, description, category_id, owner) 
VALUES ('Freelance Webseite', 350.00, '2025-10-15', 0, 'Webseite für lokales Geschäft', 12, 'walter');

-- September 2025 Transaktionen
INSERT INTO transaction (title, amount, date, type, description, category_id, owner) 
VALUES ('Wocheneinkauf Penny', 58.77, '2025-09-22', 1, 'Lebensmitteleinkauf', 1, 'walter');

INSERT INTO transaction (title, amount, date, type, description, category_id, owner) 
VALUES ('BVG Monatskarte', 86.00, '2025-09-01', 1, 'Monatskarte AB', 2, 'walter');

INSERT INTO transaction (title, amount, date, type, description, category_id, owner) 
VALUES ('September Miete', 750.00, '2025-09-01', 1, 'Warmmiete September', 3, 'walter');

INSERT INTO transaction (title, amount, date, type, description, category_id, owner) 
VALUES ('Escape Room', 35.00, '2025-09-14', 1, 'Escape Room Gruppenaktivität', 4, 'walter');

INSERT INTO transaction (title, amount, date, type, description, category_id, owner) 
VALUES ('Thai Restaurant', 28.50, '2025-09-08', 1, 'Abendessen Thai Curry', 5, 'walter');

INSERT INTO transaction (title, amount, date, type, description, category_id, owner) 
VALUES ('September Gehalt', 2500.00, '2025-09-28', 0, 'Werkstudent Gehalt', 11, 'walter');

INSERT INTO transaction (title, amount, date, type, description, category_id, owner) 
VALUES ('Steuerrückerstattung', 285.00, '2025-09-20', 0, 'Lohnsteuerrückerstattung 2024', 14, 'walter');

-- Zusätzliche Transaktionen für Vielfalt
INSERT INTO transaction (title, amount, date, type, description, category_id, owner) 
VALUES ('Stromrechnung Q3', 145.00, '2025-09-15', 1, 'Stromabrechnung Juli-September', 3, 'walter');

INSERT INTO transaction (title, amount, date, type, description, category_id, owner) 
VALUES ('Geburtstagsgeschenk', 50.00, '2025-10-24', 1, 'Geschenk für Freund', 10, 'walter');

INSERT INTO transaction (title, amount, date, type, description, category_id, owner) 
VALUES ('Friseur', 35.00, '2025-11-20', 1, 'Haarschnitt', 10, 'walter');

INSERT INTO transaction (title, amount, date, type, description, category_id, owner) 
VALUES ('Weihnachtsgeschenke', 180.00, '2025-12-18', 1, 'Geschenke für Familie', 10, 'walter');
