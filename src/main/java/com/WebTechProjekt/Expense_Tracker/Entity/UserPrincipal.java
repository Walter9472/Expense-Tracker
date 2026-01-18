package com.WebTechProjekt.Expense_Tracker.Entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * Implementierung von UserDetails für Spring Security.
 * Dient als Wrapper um die User-Entität, um diese für den Authentifizierungsprozess nutzbar zu machen.
 */
public class UserPrincipal implements UserDetails {

    private User user;

    public UserPrincipal(User user) {
        this.user = user;
    }

    /**
     * Gibt die Berechtigungen (Roles) des Benutzers zurück.
     * @return Eine Liste mit der Berechtigung "USER".
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Konto läuft nie ab
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Konto ist nie gesperrt
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Zugangsdaten laufen nie ab
    }

    @Override
    public boolean isEnabled() {
        return true; // Benutzer ist immer aktiviert
    }
}
