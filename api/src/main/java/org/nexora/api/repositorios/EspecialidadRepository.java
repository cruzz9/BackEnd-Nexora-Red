package org.nexora.api.repositorios;

import jdk.jfr.Registered;
import org.nexora.api.modelos.Especialidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface EspecialidadRepository extends JpaRepository<Especialidad, Long> {

    Optional<Especialidad> findByEspecialidad(String especialidad);
}//interface ProductoRepository
