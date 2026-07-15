package org.nexora.api.servicios;

import org.nexora.api.modelos.Comentario;
import org.nexora.api.modelos.Publicacion;

import org.nexora.api.modelos.Usuario;
import org.nexora.api.repositorios.ComentarioRepository;
import org.nexora.api.repositorios.PublicacionRepository;
import org.nexora.api.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComentarioService {

    private final ComentarioRepository comentarioRepository;
    private final PublicacionRepository publicacionRepository;





    @Autowired
    public ComentarioService(ComentarioRepository comentarioRepository, PublicacionRepository publicacionRepository) {

        this.comentarioRepository = comentarioRepository;
        this.publicacionRepository = publicacionRepository;

    }//constructor ComentarioService

    public List<Comentario> getComentarios() {
        return comentarioRepository.findAll();
    }//getComentarios

    public Comentario getComentario(Long id) {
        return comentarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El comentario con el id [" + id + "] no existe"));
    }//getComentario

    public Comentario deleteComentario(Long id) {
        Comentario comentario = null;

        if (comentarioRepository.existsById(id)) {
            comentario = comentarioRepository.findById(id).get();
            comentarioRepository.deleteById(id);
        }//if

        return comentario;
    }//deleteComentario

    public Comentario crearComentario(Comentario comentario){

        return comentarioRepository.save(comentario);
    }//crearComentario

    public Comentario actualizarComentario(Long id, String contenido, Long publicacionId ){
        Comentario comentario=null;
        if(comentarioRepository.existsById(id)){
            Comentario c=comentarioRepository.findById(id).get();

            if(contenido!=null) c.setContenido(contenido);
            if(publicacionId!=null){
                if(publicacionRepository.existsById(publicacionId)){
                    Publicacion publicacion=publicacionRepository.findById(publicacionId).get();
                c.setPublicacion(publicacion);
                }//if
            }//if publicacionId

            comentario=comentarioRepository.save(c);
        }//if
        return comentario;
    }//actualizarComentario


}//class ComentarioService