package com.WebTechProjekt.Expense_Tracker.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Sicherheitskonfiguration für die Anwendung.
 * Diese Klasse konfiguriert Spring Security, inklusive CORS, Authentifizierung und JWT-Filter.
 */
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtFilter jwtFilter;

    @Value("${app.cors.allowed-origins:http://localhost:5173}")
    private String corsAllowedOrigins;

    /**
     * Definiert die Sicherheitsfilterkette für HTTP-Anfragen.
     * @param http Das HttpSecurity-Objekt zur Konfiguration.
     * @return Die konfigurierte SecurityFilterChain.
     * @throws Exception Falls ein Fehler bei der Konfiguration auftritt.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(customizer -> customizer.disable()) // Deaktiviert CSRF (da wir JWT nutzen)
                .cors(customizer -> customizer.configurationSource(corsConfigurationSource())) // Aktiviert CORS
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/register","/login").permitAll() // Erlaubt Zugriff auf Registrierung und Login ohne Auth
                        .anyRequest().authenticated()) // Alle anderen Anfragen erfordern Authentifizierung
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Setzt Session auf zustandslos
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class) // Fügt den JWT-Filter hinzu
                .build();
    }

    /**
     * Konfiguriert die CORS-Einstellungen (Cross-Origin Resource Sharing).
     * @return Eine CorsConfigurationSource mit den erlaubten Quellen, Methoden und Headern.
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        // Erlaubte Origins (Frontend-URLs) aus der Konfiguration laden
        List<String> allowedOrigins = Arrays.stream(corsAllowedOrigins.split(","))
                .map(String::trim)
                .filter(origin -> !origin.isEmpty())
                .collect(Collectors.toList());
        configuration.setAllowedOriginPatterns(allowedOrigins);

        // Erlaubte HTTP-Methoden (GET, POST, etc.)
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));

        // Erlaubte Header in den Anfragen
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type","X-Requested-With"));

        // Erlaubt das Senden von Credentials (z.B. Cookies oder Auth-Header)
        configuration.setAllowCredentials(true);

        // Bestimmt, wie lange die Preflight-Antwort im Cache gespeichert werden darf (1 Stunde)
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Gilt für alle Pfade

        return source;
    }

    /**
     * Erstellt den AuthenticationProvider für die Datenbank-Authentifizierung.
     * @return Ein DaoAuthenticationProvider mit BCrypt und UserDetailsService.
     */
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12)); // Nutzt BCrypt mit Stärke 12
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    /**
     * Erstellt den AuthenticationManager aus der Konfiguration.
     * @param config Das AuthenticationConfiguration-Objekt.
     * @return Der AuthenticationManager.
     * @throws Exception Falls ein Fehler auftritt.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}
