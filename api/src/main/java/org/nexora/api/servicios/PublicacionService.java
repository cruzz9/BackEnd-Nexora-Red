package org.nexora.api.servicios;

import org.nexora.api.modelos.Publicacion;
import org.nexora.api.repositorios.PublicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublicacionService {

    private final PublicacionRepository publicacionRepository;

    @Autowired
    public PublicacionService(PublicacionRepository repository) {
        this.publicacionRepository = repository;
    } //constructor PublicacionService

    public List<Publicacion> getPublicaciones() {
        return publicacionRepository.findAll();
    }//getPublicaciones

    public Publicacion getPublicacion(Long id) {
        return publicacionRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException(
                        "La publicacion con el id" + id + " no existe")
        );
    } //getPublicacion

    public Publicacion deletePublicacion(Long id) {
        Publicacion publicacion = null;
        if (publicacionRepository.existsById(id)) {
            publicacion = publicacionRepository.findById(id).get();
            publicacionRepository.deleteById(id);
        }//if
        return publicacion;
    }// deletePublicacion

    public Publicacion crearPublicacion(Publicacion publicacion) {
        Optional<Publicacion> post = publicacionRepository.findByContenido(publicacion.getContenido());

        if (post.isEmpty()) {
            publicacionRepository.save(publicacion);
        } else {
            publicacion = null;
        } //if-else
        return publicacion;
    }// crearPublicacion

    public Publicacion actualizarPublicacion(Long id, String contenido) {
        Publicacion publicacion = null;

        if (publicacionRepository.existsById(id)) {
            Publicacion p = publicacionRepository.findById(id).get();
            if (contenido != null) p.setContenido(contenido);
            publicacion = publicacionRepository.save(p);

        }//if
        return publicacion;
    }//actualizarPublicacion
}//class PublicacionService

