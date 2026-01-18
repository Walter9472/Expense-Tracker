package com.WebTechProjekt.Expense_Tracker.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Service-Klasse zur Handhabung von JSON Web Tokens (JWT).
 * Diese Klasse ist verantwortlich für das Erstellen, Extrahieren und Validieren von JWTs.
 */
@Service
public class JWTService {
    private String secretKey = "";

    /**
     * Konstruktor, der einen zufälligen geheimen Schlüssel für HmacSHA256 generiert.
     */
    public JWTService(){
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
            SecretKey sk = keyGen.generateKey();
            secretKey = Base64.getEncoder().encodeToString(sk.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Fehler bei der Key-Generierung", e);
        }

    }

    /**
     * Generiert einen neuen JWT-Token für einen gegebenen Benutzernamen.
     * @param username Der Name des Benutzers, für den der Token erstellt wird.
     * @return Ein kompakter JWT-String.
     */
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 30)) // Token gültig für 30 Stunden
                .and()
                .signWith(getKey())
                .compact();
    }

    /**
     * Dekodiert den Base64-verschlüsselten secretKey und gibt ihn als SecretKey-Objekt zurück.
     * @return Das SecretKey-Objekt für Signierung und Verifizierung.
     */
    private SecretKey getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Extrahiert den Benutzernamen (Subject) aus einem gegebenen JWT-Token.
     * @param token Der JWT-Token.
     * @return Der Benutzername.
     */
    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Generische Methode zum Extrahieren einzelner Claims aus einem Token.
     * @param token Der JWT-Token.
     * @param claimResolver Eine Funktion, die den gewünschten Claim aus den Claims extrahiert.
     * @param <T> Der Typ des Claims.
     * @return Der extrahierte Claim.
     */
    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    /**
     * Extrahiert alle Claims (Payload) aus dem Token unter Verwendung des geheimen Schlüssels.
     * @param token Der JWT-Token.
     * @return Ein Claims-Objekt mit allen Daten.
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /**
     * Überprüft, ob ein Token gültig ist und zum gegebenen Benutzer passt.
     * @param token Der JWT-Token.
     * @param userDetails Die Benutzerdetails zum Abgleich.
     * @return true, wenn der Token gültig und nicht abgelaufen ist.
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * Prüft, ob der Token bereits abgelaufen ist.
     * @param token Der JWT-Token.
     * @return true, wenn das Ablaufdatum in der Vergangenheit liegt.
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Extrahiert das Ablaufdatum aus dem Token.
     * @param token Der JWT-Token.
     * @return Das Ablaufdatum als Date-Objekt.
     */
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
