package org.nexora.api.modelos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

//POJO - Plain Old Java Object

@Entity
@Table(name = "Comentarios")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comentario_id", unique = true, nullable = false)
    private Long comentarioId;


    @Column(name = "contenido", nullable = false)
    private String contenido;

    //Fk muchos a uno: muchos comentarios tiene una publicacion
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="Publicaciones_publicacion_id")
    private Publicacion publicacion;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="usuario_id")
    private Usuario usuario;



    public Comentario(String contenido) {
        this.contenido = contenido;
    }//constructor Comentario

    public Comentario() {

    }//constructor JPA

    public Long getComentarioId() {
        return comentarioId;
    }//getComentarioId

    public String getContenido() {
        return contenido;
    }//getContenido

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }//setContenido

    public Publicacion getPublicacion() {
        return publicacion;
    }//getPublicacion

    public void setPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
    }//setPublicacion


    public Usuario getUsuario() {
        return usuario;
    }//getUsuario

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }//setUsuario


    @Override
    public String toString() {
        return "Comentario{" +
                "comentarioId=" + comentarioId +
                ", contenido='" + contenido + '\'' +
                ", publicacion=" + publicacion +
                ", usuario=" + usuario +
                '}';
    }
}//class Comentario