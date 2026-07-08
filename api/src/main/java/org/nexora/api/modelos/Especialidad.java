package org.nexora.api.modelos;

public class Especialidad {


    private Long id;

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
