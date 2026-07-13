package org.nexora.api.servicios;

import org.nexora.api.modelos.Comentario;
import org.nexora.api.repositorios.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    public ComentarioService(ComentarioRepository repository){
        this.comentarioRepository = repository;
    }//constructor

    public List<Comentario> findAll() {
        return comentarioRepository.findAll();
    }//getComentario

    public Comentario findById(Long id){
        return comentarioRepository.findById(id).orElse(null);
    }//buscar

    public Comentario saveComentario(Comentario comentario){
        return comentarioRepository.save(comentario);
    }//guardar

    public Comentario deleteComentario(Long id) {
        Comentario comentario = null;

        if (comentarioRepository.existsById(id)) {
            comentario = comentarioRepository.findById(id).get();
            comentarioRepository.deleteById(id);
        }
        return comentario;
    }//borrar

    public Comentario actualizarComentario(Long id, Comentario comentarioActualizado) {

        if (comentarioRepository.existsById(id)) {

            Comentario comentario = comentarioRepository.findById(id).get();

            comentario.setContenido(comentarioActualizado.getContenido());
            comentario.setPublicacion(comentarioActualizado.getPublicacion());

            return comentarioRepository.save(comentario);
        }
        return null;
    }//actualizar

}//PC CS