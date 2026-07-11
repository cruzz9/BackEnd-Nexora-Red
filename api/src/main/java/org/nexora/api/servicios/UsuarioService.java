package org.nexora.api.servicios;

import org.nexora.api.modelos.Usuario;
import org.nexora.api.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class UsuarioService {
    // Inyectamos el repositorio para poder usar sus métodos automáticos (.save, .findAll, etc.)
    @Autowired
    private UsuarioRepository usuarioRepository;

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
    // Método para validar las credenciales del usuario
    public Usuario autenticar(String email, String contrasena) {
        // Buscamos al usuario por su email
        return usuarioRepository.findByEmail(email)
                .filter(user -> user.getContrasena().equals(contrasena)) // Si existe, filtramos que la contraseña coincida
                .orElse(null); // Si no existe o la contraseña está mal, retornamos null
    }
}//class UsuarioService
