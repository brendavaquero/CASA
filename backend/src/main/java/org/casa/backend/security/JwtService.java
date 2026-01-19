package org.casa.backend.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    // 🔐 CLAVE BASE64 (mínimo 256 bits)
    private static final String SECRET_KEY =
            "ZmFrZV9zZWNyZXRfa2V5X2Nhc2FfMjU2X2JpdHNfYmFzZTY0";

    // ⏱️ 1 día
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24;

    // =======================
    // GENERAR TOKEN
    // =======================
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(
                        new Date(System.currentTimeMillis() + EXPIRATION_TIME)
                )
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // =======================
    // VALIDAR TOKEN
    // =======================
    public boolean isTokenValid(String token) {
        return !isTokenExpired(token);
    }

    // =======================
    // EXTRAER USERNAME
    // =======================
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // =======================
    // MÉTODOS INTERNOS
    // =======================
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
