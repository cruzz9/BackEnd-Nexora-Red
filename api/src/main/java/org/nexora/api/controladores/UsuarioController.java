package org.nexora.api.controladores;

import org.nexora.api.dto.PassDto;
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
    private org.nexora.api.configuracion.JwtUtil jwtUtil;
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
        Usuario usuarioAutenticado = usuarioService.autenticar(credenciales.getEmail(), credenciales.getContrasena());

        if (usuarioAutenticado != null) {
            // 1. Si los datos coinciden, le fabricamos su token único pasándole su email
            String tokenGenerado = jwtUtil.generarToken(usuarioAutenticado.getEmail());

            // 2. Respondemos al Frontend enviando el token en formato JSON
            // También podemos mandar datos básicos como el ID o nombre para facilitar el uso en JS
            return ResponseEntity.ok("{" +
                    "\"token\": \"" + tokenGenerado + "\"," +
                    "\"usuarioId\": " + usuarioAutenticado.getId() + "," +
                    "\"nombre\": \"" + usuarioAutenticado.getNombre() + "\"" +
                    "}");
        }

        return ResponseEntity.status(401).body("{\"error\": \"Credenciales incorrectas o el usuario no existe.\"}");
    }

    // EndPoint para actualizar la contraseña (Petición PUT)
    @PutMapping("/{id}/cambiar-password")
    public ResponseEntity<?> actualizarPassword(@PathVariable Long id, @RequestBody PassDto passwordData) {
        boolean exito = usuarioService.cambiarContrasena(id, passwordData);

        if (exito) {
            return ResponseEntity.ok("{\"mensaje\": \"La contraseña ha sido actualizada con éxito.\"}");
        } else {
            return ResponseEntity.status(400)
                    .body("{\"error\": \"La contraseña actual es incorrecta o el usuario no existe.\"}");
        }
    }

}//class usuarioController
