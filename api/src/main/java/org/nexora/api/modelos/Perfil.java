package org.nexora.api.modelos;

import jakarta.persistence.*;

@Entity
@Table(name = "Perfil")
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "perfil_id")
    private Long perfilId;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "carrera", nullable = false, length = 45)
    private String carrera;

    @Column(name = "sobre_mi", nullable = false, length = 45)
    private String sobreMi;

    @Column(name = "datos_carrera", nullable = false, length = 45)
    private String datosCarrera;

    // RELACIÓN DE CARDINALIDAD REAL: Un Perfil pertenece a un único Usuario
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Usuarios_usuario_id", referencedColumnName = "usuario_id", nullable = false) // Columna física de FK
    private Usuario usuario;

    // ---------- Constructores -----------
    public Perfil() {}

    public Perfil(String nombre, String carrera, String sobreMi, String datosCarrera, Usuario usuario) {
        this.nombre = nombre;
        this.carrera = carrera;
        this.sobreMi = sobreMi;
        this.datosCarrera = datosCarrera;
        this.usuario = usuario;
    }

    // ------ Getters & Setters --------------------
    public Long getPerfilId() { return perfilId; }
    public void setPerfilId(Long perfilId) { this.perfilId = perfilId; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCarrera() { return carrera; }
    public void setCarrera(String carrera) { this.carrera = carrera; }

    public String getSobreMi() { return sobreMi; }
    public void setSobreMi(String sobreMi) { this.sobreMi = sobreMi; }

    public String getDatosCarrera() { return datosCarrera; }
    public void setDatosCarrera(String datosCarrera) { this.datosCarrera = datosCarrera; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    @Override
    public String toString() {
        return "Perfil{" + "perfilId=" + perfilId + ", nombre='" + nombre + '\'' + '}';
    }
}