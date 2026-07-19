package org.nexora.api.controladores;

import org.nexora.api.modelos.Comentario;
import org.nexora.api.modelos.Publicacion;
import org.nexora.api.servicios.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/comentarios/") //http://localhost:8080/api/comentarios/
public class ComentarioController {

    private final  ComentarioService comentarioService;

    @Autowired

    public ComentarioController(ComentarioService comentarioService) {
        this.comentarioService = comentarioService;
    }//constructor ComentarioController

    @GetMapping
    public  List<Comentario> getComentarios(){
        return  comentarioService.getComentarios();
    }//getComentarios

    @GetMapping(path = "{comentarioId}")
    public Comentario getComentario(@PathVariable("comentarioId") Long id){
        return comentarioService.getComentario(id);
    }//getComentario

    @DeleteMapping(path = "{comentarioId}")
    public Comentario deleteComentario(@PathVariable("comentarioId") Long id){
        return comentarioService.deleteComentario(id);
    }//deleteComentario


    @PostMapping
    public Comentario crearComentario(@RequestBody Comentario comentario){
        return comentarioService.crearComentario(comentario);
    }//crearComentario

    @PutMapping(path = "{comentarioId}")
    public Comentario actualizarComentario(@PathVariable("comentarioId") Long id,
                                           @RequestParam(value = "contenido", required = false) String contenido,
                                           @RequestParam(value = "publicacionId", required = false) Long publicacionId){
        return comentarioService.actualizarComentario(id, contenido, publicacionId);
    }//actualizarComentario
}//class ComentarioController