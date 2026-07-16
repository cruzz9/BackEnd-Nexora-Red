package org.nexora.api.configuracion;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component // Permite que Spring reconozca esta clase como un componente inyectable
public class JwtUtil {

    //  Una clave secreta lo suficientemente larga para firmar de forma segura.
    // IMPORTANTE: En producción esto debe ir en variables de entorno.
    private final String SECRETO = "NexoraComunidadSTEMSegura2026ClaveSuperSecretaParaFirmarTokens";
    private final Key CLAVE_FIRMA = Keys.hmacShaKeyFor(SECRETO.getBytes());

    // El token durará 5 horas activo antes de que expire y obligue a re-loguear
    private final long TIEMPO_EXPIRACION = 1000 * 60 * 60 * 5;

    // 🔨 FUNCIÓN 1: Crear la pulsera (Token) cuando el Login sea correcto
    public String generarToken(String email) {
        return Jwts.builder()
                .setSubject(email) // Guardamos el email como identificador dentro de la pulsera
                .setIssuedAt(new Date(System.currentTimeMillis())) // Fecha de creación
                .setExpiration(new Date(System.currentTimeMillis() + TIEMPO_EXPIRACION)) // Fecha de caducidad
                .signWith(CLAVE_FIRMA, SignatureAlgorithm.HS256) // Firmamos criptográficamente el token
                .compact(); // Une la cabecera, cuerpo y firma en un solo String largo
    }

    // 🔑 FUNCIÓN 2: Leer la pulsera y extraer el email de su interior
    public String extraerEmail(String token) {
        Claims claims = Jwts.parser() // Usamos parser() en lugar de parserBuilder()
                .verifyWith(CLAVE_FIRMA) // CLAVE_FIRMA debe ser de tipo SecretKey
                .build()
                .parseSignedClaims(token) // Usamos parseSignedClaims para tokens firmados
                .getPayload(); // .getPayload() sustituye al antiguo .getBody()

        return claims.getSubject();
    }

    // 🔑 FUNCIÓN 3: Comprobar si la pulsera ya caducó
    public boolean esTokenValido(String token) {
        try {
            Jwts.parser()
                    .verifyWith(CLAVE_FIRMA)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
