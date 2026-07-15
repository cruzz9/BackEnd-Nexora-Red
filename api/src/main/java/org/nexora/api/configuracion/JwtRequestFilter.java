package org.nexora.api.configuracion;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Component
// OncePerRequestFilter garantiza que este filtro se ejecute exactamente una vez por cada petición HTTP
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 1. Obtener el encabezado 'Authorization' de la petición HTTP
        final String authorizationHeader = request.getHeader("Authorization");

        String email = null;
        String jwt = null;

        // 2. Convención estándar: El token viaja como "Bearer cadena_del_token..."
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7); // Extraemos únicamente el texto del token (quitamos "Bearer ")
            try {
                email = jwtUtil.extraerEmail(jwt); // Extraemos el email guardado en el token
            } catch (Exception e) {
                logger.warn("No se pudo extraer el email del token o el token expiró.");
            }
        }

        // 3. Si encontramos un email válido en el token y el usuario no ha sido autenticado aún en este ciclo de petición
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // Validamos que la firma del token no esté vencida ni alterada
            if (jwtUtil.esTokenValido(jwt)) {

                // Creamos un objeto de autenticación para Spring Security
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        email, null, new ArrayList<>() // Pasamos una lista vacía de permisos/roles por ahora
                );

                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // LE DAMOS ACCESO: Registramos la autenticación en el contexto de Spring.
                // A partir de esta línea, el escudo de seguridad dejará pasar la petición al controlador.
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        // 4. Continuar con el siguiente filtro en la cadena de seguridad de Spring
        filterChain.doFilter(request, response);
    }
}