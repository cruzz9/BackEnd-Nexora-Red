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

    @GetMapping("{publicacionId}") // // http:localhost:8080/api/publicaciones/{publicacionId}
    public Publicacion getPublicacion(@PathVariable("publicacionId") Long id) {
        return publicacionService.getPublicacion(id);
    }//getPublicacion

    @DeleteMapping(path = "{publicacionId}")
    public Publicacion deletePublicacion(@PathVariable("publicacionId") Long id) {
        return publicacionService.deletePublicacion(id);
    }//deletePublicacion

    @PostMapping
    public Publicacion crearPublicacion(@RequestBody Publicacion publicacion) {
        return publicacionService.crearPublicacion(publicacion);
    } // crearPublicacion

    @PutMapping(path = "{publicacionId}")
    public Publicacion actualizarPublicacion(@PathVariable("publicacionId") Long id,
                                             @RequestParam(value = "contenido") String contenido) {
        return publicacionService.actualizarPublicacion(id, contenido);
    }//actualizarPublicacion


}// class PublicacionController

