package org.nexora.api.controladores;

import org.nexora.api.modelos.Usuario;
import org.nexora.api.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios/") // Ruta base para todos los endpoints de este controlador
@CrossOrigin(origins = "*") // Permite que tu Frontend se conecte sin bloqueos de CORS
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // EndPoint 1: Obtener la lista de todos los usuarios (Petición GET)
    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioService.obtenerTodosLosUsuarios();
    }

    // EndPoint 2: Registrar un usuario nuevo (Petición POST)
    @PostMapping("/registro")
    public ResponseEntity<Usuario> registrarUsuario(@RequestBody Usuario nuevoUsuario) {
        Usuario usuarioGuardado = usuarioService.guardarUsuario(nuevoUsuario);
        return ResponseEntity.ok(usuarioGuardado);
    }

    // EndPoint 3: Autenticar un usuario (Petición POST para el Login)
    @PostMapping("/login")
    public ResponseEntity<?> autenticarUsuario(@RequestBody Usuario credenciales) {
        // El controlador ahora solo le pide al servicio que haga la magia
        Usuario usuarioAutenticado = usuarioService.autenticar(credenciales.getEmail(), credenciales.getContrasena());

        if (usuarioAutenticado != null) {
            // Si el servicio nos devuelve al usuario todo está correcto (HTTP 200)
            return ResponseEntity.ok(usuarioAutenticado);
        }

        // Si nos devuelve null, las credenciales fallaron (HTTP 401)
        return ResponseEntity.status(401).body("{\"error\": \"Credenciales incorrectas o el usuario no existe.\"}");
    }

}
