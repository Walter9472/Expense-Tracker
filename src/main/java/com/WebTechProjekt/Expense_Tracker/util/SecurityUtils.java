package com.WebTechProjekt.Expense_Tracker.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Hilfsklasse für sicherheitsbezogene Operationen.
 * Ermöglicht den Zugriff auf den aktuell authentifizierten Benutzer im SecurityContext.
 */
public final class SecurityUtils {
    private SecurityUtils() {}

    /**
     * Ruft den Benutzernamen des aktuell angemeldeten Benutzers ab.
     * @return Den Benutzernamen oder null, falls keine Authentifizierung vorliegt.
     */
    public static String getCurrentUsername(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated()) {
            return null;
        }

        return auth.getName();
    }
}
