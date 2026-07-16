package org.nexora.api.controladores;

import org.nexora.api.modelos.Publicacion;
import org.nexora.api.servicios.PublicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/publicaciones/") //http://localhost:8080/api/publicaciones/
public class PublicacionController {

    private final PublicacionService publicacionService;

    @Autowired
    public PublicacionController(PublicacionService service) {
        this.publicacionService = service;
    } //constructor PublicacionController

    @GetMapping // // http://localhost:8080/api/publicaciones/
    public List<Publicacion> getPublicaciones() {
        return publicacionService.getPublicaciones();
    }//getPublicaciones

    @GetMapping("{publicacion_id}") // // http:localhost:8080/api/publicaciones/{publicacion_id}
    public Publicacion getPublicacion(@PathVariable("publicacion_id") Long id) {
        return publicacionService.getPublicacion(id);
    }//getPublicacion

    @DeleteMapping(path = "{publicacion_id}")
    public Publicacion deletePublicacion(@PathVariable("publicacion_id") Long id) {
        return publicacionService.deletePublicacion(id);
    }//deletePublicacion

    @PostMapping
    public Publicacion crearPublicacion(@RequestBody Publicacion publicacion) {
        return publicacionService.crearPublicacion(publicacion);
    } // crearPublicacion

    @PutMapping(path = "{publicacion_id}")
    public Publicacion actualizarPublicacion(@PathVariable("publicacion_id") Long id,
                                             @RequestParam(value = "contenido") String contenido) {
        return publicacionService.actualizarPublicacion(id, contenido);
    }//actualizarPublicacion


}// class PublicacionController

