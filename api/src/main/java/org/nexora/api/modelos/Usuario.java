package org.nexora.api.modelos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Usuarios") // Alineado con la tabla de la base de datos
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private Long id;

    @Column(name = "nombre", nullable = false, length = 45)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 45)
    private String apellido;

    @Column(name = "rol", nullable = false, length = 45)
    private String rol;

    @Column(name = "fecha_nacimiento", nullable = false, length = 45)
    private String nacimiento;

    @Column(name = "genero", nullable = false, length = 45)
    private String genero;

    @Column(name = "email", nullable = false, length = 45, unique = true)
    private String email;

    @Column(name = "telefono", nullable = false, length = 20)
    private String telefono;

    @Column(name = "contrasena", nullable = false, length = 255) // Ampliado para soportar hash de Spring Security posterior
    private String contrasena;

    // RELACIÓN DE CARDINALIDAD REAL: Muchos Usuarios pueden tener Una Especialidad (ahora opcional)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "especialidad_especialidad_id", nullable = true) // 👈 ahora permite null
    @JsonBackReference // 👈 rompe el ciclo con Especialidad.usuarios
    private Especialidad especialidad;

    // Relación bidireccional opcional de Uno a Uno con Perfil (mapeado por el atributo 'usuario' en Perfil)
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Perfil perfil;

    //Relación de cardinalidad: Un Usuario tiene muchas Publicaciones
    @JsonManagedReference("usuario-publicaciones")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario" )
    private List<Publicacion> publicaciones = new ArrayList<>();
    // ---------- Constructores -----------
    public Usuario() {}

    // Constructor actualizado con el objeto Especialidad
    public Usuario(String nombre, String apellido, String rol, String nacimiento, String genero, String email, String telefono, String contrasena, Especialidad especialidad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.rol = rol;
        this.nacimiento = nacimiento;
        this.genero = genero;
        this.email = email;
        this.telefono = telefono;
        this.contrasena = contrasena;
        this.especialidad = especialidad;
    }

    // ------ Getters & Setters --------------------
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public String getNacimiento() { return nacimiento; }
    public void setNacimiento(String nacimiento) { this.nacimiento = nacimiento; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    public Especialidad getEspecialidad() { return especialidad; }
    public void setEspecialidad(Especialidad especialidad) { this.especialidad = especialidad; }

    public Perfil getPerfil() { return perfil; }
    public void setPerfil(Perfil perfil) { this.perfil = perfil; }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nombre='" + nombre + '\'' + ", email='" + email + '\'' + '}';
    }
}