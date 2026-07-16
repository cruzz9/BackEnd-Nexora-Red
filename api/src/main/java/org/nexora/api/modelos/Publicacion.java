package org.nexora.api.modelos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="publicacion")

public class Publicacion {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="publicacion_id", unique=true, nullable=false)
    private Long id;

    @Column(name = "contenido", nullable=false)
    private String contenido;

    @Column(name="likes", nullable=false)
    private Long likes;

    // RELACIÓN DE CARDINALIDAD : Muchas Publicaciones tienen Un Usuario
    @JsonBackReference("usuario-publicaciones")
    @ManyToOne
    @JoinColumn(name="usuario_id")
    private Usuario usuario;

    // Una Publicación puede tener Muchos comentarios
    @JsonManagedReference("publicacion-comentarios")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "publicacion")
    private List<Comentario> comentarios = new ArrayList<>();

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

    public Usuario getUsuario() {
        return usuario;
    }//getUsuario

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }//setUsuario

    public List<Comentario> getComentarios() {
        return comentarios;
    }//getComentarios

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }//setComentarios


    @Override
    public String toString() {
        return "Publicacion{" +
                "idPublicacion=" + id +
                ", contenido='" + contenido + '\'' +
                ", likes=" + likes +
                '}';
    }//toString
}//classPublicacion