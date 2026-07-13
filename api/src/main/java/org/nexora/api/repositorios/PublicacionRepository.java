package org.nexora.api.repositorios;

import org.nexora.api.modelos.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
    public interface PublicacionRepository extends JpaRepository<Publicacion, Long> {
}//interface PublicacionesRepository
