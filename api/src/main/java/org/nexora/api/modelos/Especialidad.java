package org.nexora.api.modelos;

import jakarta.persistence.*;

@Entity
@Table(name="especialidades")
public class Especialidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "especialidad_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "especialidad", nullable = false)
    private String especialidad;

    public Especialidad(String especialidad) {
        this.especialidad = especialidad;
    }//constructor Especialidad


    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }//constructor setEspecialidad

    public String getEspecialidad(){
        return especialidad;
    }//getEspecialidad

}//class Especialidad
