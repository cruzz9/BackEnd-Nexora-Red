package org.nexora.api.repositorios;

import org.nexora.api.modelos.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {

    // Metodo personalizado para buscar el perfil usando el ID del usuario asociado
    Optional<Perfil> findByUsuarioId(Long usuarioId);
}
