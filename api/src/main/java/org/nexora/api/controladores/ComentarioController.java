package org.nexora.api.controladores;

import org.nexora.api.modelos.Comentario;
import org.nexora.api.servicios.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/comentario/") //http://localhost:8080/api/comentario/
public class ComentarioController {

        private final ComentarioService comentarioService;

        @Autowired
        public ComentarioController(ComentarioService service){
            this.comentarioService = service;
        }//constructor

        @GetMapping
        public List<Comentario> getComentario(){
            return comentarioService.getComentario();
        }//get

        @GetMapping(path = "{comentarioId}")
        public Comentario getComentario(@PathVariable("comentarioId") Long id) {
            return comentarioService.getComentario(id);
        }//get

        @DeleteMapping(path = "{comentarioId}")
        public Comentario deleteComentario(@PathVariable("comentarioId") Long id) {
            return comentarioService.deleteComentario(id);
        }//delete

}//PC CC