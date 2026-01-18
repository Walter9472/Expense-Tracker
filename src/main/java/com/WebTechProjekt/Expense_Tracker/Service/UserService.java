package com.WebTechProjekt.Expense_Tracker.Service;

import com.WebTechProjekt.Expense_Tracker.Entity.User;
import com.WebTechProjekt.Expense_Tracker.Repository.UserRepo;
import com.WebTechProjekt.Expense_Tracker.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service-Klasse für die Benutzerverwaltung.
 * Beinhaltet Logik für Registrierung, Authentifizierung (Login) und Profilabfrage.
 */
@Service
public class UserService {

    @Autowired
    private JWTService jwtService;

    private UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    /**
     * Gibt eine Liste aller registrierten Benutzer zurück.
     * @return Liste von User-Entitäten.
     */
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    /**
     * Registriert einen neuen Benutzer und verschlüsselt sein Passwort.
     * @param user Das zu speichernde User-Objekt.
     * @return Der gespeicherte Benutzer.
     */
    public User registerUser(User user) {
        user.setPassword(encoder.encode(user.getPassword())); // Passwort-Hashing mit BCrypt
        return userRepo.save(user);
    }

    /**
     * Verifiziert die Login-Daten eines Benutzers und generiert bei Erfolg einen JWT-Token.
     * @param user Die Login-Daten (Benutzername und Passwort).
     * @return Ein JWT-Token als String.
     * @throws BadCredentialsException Falls Benutzername oder Passwort falsch sind.
     */
    public String verify(User user) {
        Optional<User> userOpt = userRepo.findByUsername(user.getUsername());

        if (userOpt.isEmpty()){
            throw new BadCredentialsException("Ungültiger Benutzername oder Passwort");
        }

        User storedUser = userOpt.get();

        // Passwort-Abgleich
        if(!encoder.matches(user.getPassword(), storedUser.getPassword())){
            throw new BadCredentialsException("Ungültiger Benutzername oder Passwort");
        }

        // Token erstellen für erfolgreich authentifizierten Benutzer
        return jwtService.generateToken(storedUser.getUsername());
    }

    /**
     * Holt die Daten des aktuell angemeldeten Benutzers basierend auf dem SecurityContext.
     * @return Das User-Objekt des aktuellen Benutzers oder null.
     */
    public User getUser() {
        String currentUser = SecurityUtils.getCurrentUsername();
        return userRepo.findByUsername(currentUser).orElse(null);
    }
}
