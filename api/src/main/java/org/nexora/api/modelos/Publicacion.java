package org.nexora.api.modelos;

import jakarta.persistence.*;

@Entity
@Table(name="publicacion")

public class Publicacion {

@Id
@GeneratedValue(strategy= GenerationType.IDENTITY)
@Column(name="publicacionId", unique=true, nullable=false)
private Long id;

@Column(name = "contenido", nullable=false)
private String contenido;

@Column(name="likes", nullable=false)
private Long likes;

    public Publicacion(String contenido, Long likes ) {
        this.contenido =contenido;
        this.likes = likes;
    }//constructorPublicacion

    public Publicacion() {
            }//constructor vacío (requisito JPA)

    public Long getId() {  return id;
    }

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
        return "Publicacion{" +
                "idPublicacion=" + id +
                ", contenido='" + contenido + '\'' +
                ", likes=" + likes +
                '}';
    }//toString
}//classPublicacion
