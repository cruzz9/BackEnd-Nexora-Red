package org.nexora.api.repositorios;

import org.nexora.api.modelos.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
    public interface PublicacionRepository extends JpaRepository<Publicacion, Long> {

Optional<Publicacion> findByContenido(String contenido);
}//interface PublicacionesRepository
