package org.nexora.api.controladores;

import org.nexora.api.modelos.Perfil;
import org.nexora.api.servicios.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/perfiles")
 // Permite la comunicación con el Frontend de Nexora
public class PerfilController {

    private final PerfilService perfilService;

    @Autowired
    public PerfilController(PerfilService perfilService) {
        this.perfilService = perfilService;
    }

    // Endpoint para obtener un perfil por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Perfil> obtenerPerfil(@PathVariable Long id) {
        return perfilService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint para obtener el perfil del usuario logueado usando su ID de usuario
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<Perfil> obtenerPerfilPorUsuario(@PathVariable Long usuarioId) {
        return perfilService.obtenerPorUsuarioId(usuarioId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint para crear un perfil inicial
    @PostMapping
    public ResponseEntity<Perfil> crearPerfil(@RequestBody Perfil perfil) {
        Perfil nuevoPerfil = perfilService.guardarPerfil(perfil);
        return ResponseEntity.ok(nuevoPerfil);
    }

    // Endpoint para actualizar la información del perfil
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarPerfil(@PathVariable Long id, @RequestBody Perfil perfilData) {
        try {
            Perfil perfilEditado = perfilService.actualizarPerfil(id, perfilData);
            return ResponseEntity.ok(perfilEditado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }
}
