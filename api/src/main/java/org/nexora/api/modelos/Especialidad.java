package org.nexora.api.modelos;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="especialidad")
public class Especialidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "especialidad_id", unique = true, nullable = false)
    private Long id;

//esto esta crelacionado con especialidad de modelo usuario se ingresa en el mapped el nombre de la variable de java
    @JsonManagedReference
    @OneToMany(mappedBy = "especialidad")
    private List<Usuario> usuarios;


    @Column(name = "especialidad", nullable = false)
    private String especialidad;

    public Especialidad(String especialidad) {
        this.especialidad = especialidad;
    }//constructor Especialidad

    public Especialidad() {

    }//Constructor JPA

    public Long getId() {
        return id;
    }//getId

    public void setId(Long id) {
        this.id = id;
    }//setId

    public List<Usuario> getUsuarios() {
        return usuarios;
    }//getUsuarios

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }//setUsuarios

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }//constructor setEspecialidad

    public String getEspecialidad(){
        return especialidad;
    }//getEspecialidad



}//class Especialidad
