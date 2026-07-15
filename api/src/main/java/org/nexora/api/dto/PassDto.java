package org.nexora.api.dto; //

public class PassDto {

    private String passActual;
    private String passNuevo;

    // Constructor vacío (Indispensable para que Spring procese el JSON automáticamente)
    public PassDto() {}

    // Constructor con parámetros
    public PassDto(String passActual, String passNuevo) {
        this.passActual = passActual;
        this.passNuevo = passNuevo;
    }

    // Getters y Setters
    public String getPassActual() {
        return passActual;
    }

    public void setPassActual(String passActual) {
        this.passActual = passActual;
    }

    public String getPassNuevo() {
        return passNuevo;
    }

    public void setPassNuevo(String passNuevo) {
        this.passNuevo = passNuevo;
    }

    @Override
    public String toString() {
        return "PassDto{" +
                "passActual='***', passNuevo='***'" +
                '}';
    }
}
