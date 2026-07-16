package org.nexora.api.configuracion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtRequestFilter jwtRequestFilter; //  Inyectamos el filtro de JWT

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Deshabilitamos CSRF para poder usar Postman libremente
                .authorizeHttpRequests(auth -> auth
                        // Permite registrarse sin requerir token JWT
                        .requestMatchers("/api/usuarios/registro").permitAll()
                        // Si tienes un endpoint de login, también debe ser libre:
                        .requestMatchers("/api/usuarios/login").permitAll()
                        // Cualquier otra ruta sí requerirá estar autenticado
                        .anyRequest().authenticated()
                )
                // Le decimos a Spring que no guarde sesiones en el servidor (Stateless),
                // ya que usaremos únicamente los tokens JWT para validar cada petición.
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // Colocamos nuestro filtro personalizado para que actúe ANTES del filtro de login estándar
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}