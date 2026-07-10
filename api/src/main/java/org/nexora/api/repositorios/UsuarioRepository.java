package org.nexora.api.repositorios;

import org.nexora.api.modelos.Usuario;
import org.springframework.stereotype.Repository;

import java.util.Optional;

    @Repository
    public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
        // Metodo personalizado para buscar un usuario por su correo electrónico (crucial para el Login)
        Optional<Usuario> findByEmail(String email);
    }//interface UsuarioRepository


