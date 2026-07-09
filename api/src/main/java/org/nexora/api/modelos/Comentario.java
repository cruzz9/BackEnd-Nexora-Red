package org.nexora.api.modelos;

public class Comentario {

    private Long id;

    private String comentario;

    public Comentario(Long id, String comentario) {
        this.id = id;
        this.comentario = comentario;
    }//constructor

    public String getComentario() {
        return comentario;
    }//getComentario

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }//setComentario

    public Long getId() {
        return id;
    }//getId

    public void setId(Long id) {
        this.id = id;
    }//SetId


    @Override
    public String toString() {
        return "Comentario{" +
                "id=" + id +
                ", comentario='" + comentario + '\'' +
                '}';
    }
}//PC  Comentario
