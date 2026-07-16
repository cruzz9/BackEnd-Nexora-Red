package org.nexora.api.servicios;

import org.nexora.api.dto.PassDto;
import org.nexora.api.modelos.Usuario;
import org.nexora.api.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    // Inyectamos el repositorio para poder usar sus métodos automáticos (.save, .findAll, etc.)
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    // Metodo para obtener todos los usuarios
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }

    // Metodo para registrar un usuario nuevo (El que usaremos desde el formulario cuenta.html)
    public Usuario guardarUsuario(Usuario nuevoUsuario) {
        // Aquí en el futuro encriptaremos la contraseña antes de guardar
        return usuarioRepository.save(nuevoUsuario);
    }

    // Metodo para buscar un usuario por su email (El que usaremos en el login)
    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
    // Metodo para validar las credenciales del usuario
    public Usuario autenticar(String email, String contrasena) {
        // Buscamos al usuario por su email
        return usuarioRepository.findByEmail(email)
                .filter(user -> user.getContrasena().equals(contrasena)) // Si existe, filtramos que la contraseña coincida
                .orElse(null); // Si no existe o la contraseña está mal, retornamos null
    }

    // Metodo para cambiar contraseña de forma segura usando nuestro nuevo DTO
    public boolean cambiarContrasena(Long usuarioId, PassDto datosPassword) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(usuarioId);

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();

            // Comparamos con el encriptador si la contraseña actual coincide
            if (passwordEncoder.matches(datosPassword.getPassActual(), usuario.getContrasena())) {
                // Ciframos la nueva contraseña
                String nuevoHash = passwordEncoder.encode(datosPassword.getPassNuevo());
                usuario.setContrasena(nuevoHash);

                usuarioRepository.save(usuario);
                return true;
            }
        }
        return false;
    }
}//class UsuarioService
