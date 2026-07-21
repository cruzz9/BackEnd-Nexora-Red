package org.nexora.api.servicios;

import org.nexora.api.modelos.Perfil;
import org.nexora.api.repositorios.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PerfilService {

    private final PerfilRepository perfilRepository;

    @Autowired // Inyectamos el repositorio correspondiente
    public PerfilService(PerfilRepository perfilRepository) {
        this.perfilRepository = perfilRepository;
    }

    // Obtener un perfil por su ID único
    public Optional<Perfil> obtenerPorId(Long id) {
        return perfilRepository.findById(id);
    }

    // Obtener el perfil asociado a un Usuario específico
    public Optional<Perfil> obtenerPorUsuarioId(Long usuarioId) {
        return perfilRepository.findByUsuario_Id(usuarioId);
    }

    // Guardar o registrar un perfil nuevo
    public Perfil guardarPerfil(Perfil perfil) {
        return perfilRepository.save(perfil);
    }

    // Lógica para actualizar un perfil existente
    public Perfil actualizarPerfil(Long id, Perfil perfilActualizado) {
        return perfilRepository.findById(id).map(perfilExistente -> {
            perfilExistente.setNombre(perfilActualizado.getNombre());
            perfilExistente.setCarrera(perfilActualizado.getCarrera());
            perfilExistente.setSobreMi(perfilActualizado.getSobreMi());
            perfilExistente.setDatosCarrera(perfilActualizado.getDatosCarrera());
            return perfilRepository.save(perfilExistente);
        }).orElseThrow(() -> new RuntimeException("Perfil no encontrado con ID: " + id));
    }
}
