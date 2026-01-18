package com.WebTechProjekt.Expense_Tracker.Service;


import com.WebTechProjekt.Expense_Tracker.Entity.User;
import com.WebTechProjekt.Expense_Tracker.Entity.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Eigener Service zur Implementierung des UserDetailsService von Spring Security.
 * Diese Klasse wird genutzt, um Benutzerdaten für den Authentifizierungsprozess aus der Datenbank zu laden.
 */
@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;

    /**
     * Lädt einen Benutzer anhand seines Benutzernamens.
     * @param username Der Name des zu ladenden Benutzers.
     * @return Ein UserDetails-Objekt (UserPrincipal), das Spring Security für die Validierung nutzt.
     * @throws UsernameNotFoundException Falls kein Benutzer mit diesem Namen gefunden wird.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = null;

        // Durchläuft alle Benutzer (vereinfachte Suche, idealerweise über Repository)
        for(User user1 : userService.getAllUsers()){
            if(user1.getUsername().equals(username)){
                user = user1;
                return new UserPrincipal(user);
            }
        }

        System.out.println("Benutzer nicht gefunden: " + username);
        throw new UsernameNotFoundException("Benutzer nicht gefunden");

    }
}
