package com.WebTechProjekt.Expense_Tracker.Config;

import com.WebTechProjekt.Expense_Tracker.Service.JWTService;
import com.WebTechProjekt.Expense_Tracker.Service.MyUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * JWT-Filter zur Authentifizierung von Anfragen.
 * Dieser Filter fängt jede HTTP-Anfrage ab und prüft, ob ein gültiger JWT-Token im 'Authorization'-Header vorhanden ist.
 */
@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JWTService jwtService;

    @Autowired
    ApplicationContext context;

    /**
     * Führt die Filterlogik für jede Anfrage aus.
     * @param request Die eingehende HTTP-Anfrage.
     * @param response Die ausgehende HTTP-Antwort.
     * @param filterChain Die Filterkette.
     * @throws ServletException Falls ein Servlet-Fehler auftritt.
     * @throws IOException Falls ein E/A-Fehler auftritt.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;

        // Prüfen, ob der Authorization-Header mit 'Bearer ' beginnt
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7); // Token extrahieren
            username = jwtService.extractUserName(token); // Benutzernamen aus dem Token lesen
        }

        // Wenn ein Benutzername gefunden wurde und noch keine Authentisierung im SecurityContext besteht
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = context.getBean(MyUserDetailsService.class).loadUserByUsername(username);

            // Validierung des Tokens
            if (jwtService.validateToken(token, userDetails)) {
                // Authentifizierungsobjekt erstellen und im SecurityContext setzen
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // Filterkette fortsetzen
        filterChain.doFilter(request, response);
    }
}
