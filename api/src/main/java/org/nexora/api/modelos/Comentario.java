package org.nexora.api.modelos;

import jakarta.persistence.*;
import org.nexora.api.modelos.Publicaciones;

//POJO - Plain Old Java Object

@Entity
@Table(name = "Comentarios")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comentario_id")

    private Long comentarioId;

    @Column(nullable = false)
    private String contenido;

    @ManyToOne
    @JoinColumn(name = "Publicaciones_publicacion_id")
    private Publicaciones publicacion;

    public Comentario() {
    }

    public Long getComentarioId() {
        return comentarioId;
    }//get comentarioId

    public void setComentarioId(Long comentarioId) {
        this.comentarioId = comentarioId;
    }//set comentarioId

    public String getContenido() {
        return contenido;
    }//get contenido

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }//set contenido

    public Publicaciones getPublicacion() {
        return publicacion;
    }//get publicacion

    public void setPublicacion(Publicaciones publicacion) {
        this.publicacion = publicacion;
    }//set publicación

}//PC Comentario