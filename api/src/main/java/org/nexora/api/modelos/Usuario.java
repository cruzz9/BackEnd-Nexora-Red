package org.nexora.api.modelos;

import java.util.Date;

public class Usuario {
    private Long id;
    private String nombre;
    private String apellido;
    private String rol;
    private Date nacimiento;
    private String genero;
    private String email;
    private String telefono;
    private String contrasena;
    private Long especialidad_especialidad_id; //clave foranea

    public Usuario(String nombre, String apellido, String rol, Date nacimiento, String genero, String email, String telefono, String contrasena ){
        this.nombre = nombre;
        this.apellido = apellido;
        this.rol = rol;
        this.nacimiento = nacimiento;
        this.genero = genero;
        this.email = email;
        this.telefono = telefono;
        this.contrasena = contrasena;
    }//constructor usuario

    public Usuario(){}//constructor vacio para JPA

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Date getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(Date nacimiento) {
        this.nacimiento = nacimiento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Long getEspecialidad_especialidad_id() {
        return especialidad_especialidad_id;
    }

    public void setEspecialidad_especialidad_id(Long especialidad_especialidad_id) {
        this.especialidad_especialidad_id = especialidad_especialidad_id;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", rol='" + rol + '\'' +
                ", nacimiento=" + nacimiento +
                ", genero='" + genero + '\'' +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", especialidad_especialidad_id=" + especialidad_especialidad_id +
                '}';
    }
}//class usuario
