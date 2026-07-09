package org.nexora.api.modelos;

public class Publicaciones {

private Long idPublicacion;
private String contenido;
private Long likes;

    public Publicaciones(String contenido, Long likes ) {
        this.contenido =contenido;
        this.likes = likes;
    }//constructorPublicaciones

    public Publicaciones() {
            }//constructor vacío (requisito JPA)

     public String getContenido() {
        return contenido;
    }//getContenido

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }//setContenido

    public Long getLikes() {
        return likes;
    }//getLikes

    public void setLikes(Long likes) {
        this.likes = likes;
    }//setLikes


    @Override
    public String toString() {
        return "Publicaciones{" +
                "idPublicacion=" + idPublicacion +
                ", contenido='" + contenido + '\'' +
                ", likes=" + likes +
                '}';
    }//toString
}//classPublicaciones
