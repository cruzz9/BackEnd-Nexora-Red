package org.nexora.api.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // Le indica a Spring Boot que aquí se definen las reglas del escudo de seguridad
public class SecurityConfig {

    // 🌟 1. Inicializamos el encriptador BCrypt como un Bean (componente reutilizable)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 🌟 2. Definimos el filtro con las reglas de acceso para las URLs
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Deshabilitamos CSRF porque usaremos Tokens JWT
                .cors(cors -> cors.configure(http)) // Habilitamos la compatibilidad con las reglas CORS que pusimos en los controladores
                .authorizeHttpRequests(auth -> auth
                        // Declaramos como totalmente PÚBLICAS las rutas de registro y login de usuarios
                        .requestMatchers("/api/usuarios/registro", "/api/usuarios/login").permitAll()
                        // Cualquier otra petición al servidor requerirá que el usuario esté autenticado
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}
