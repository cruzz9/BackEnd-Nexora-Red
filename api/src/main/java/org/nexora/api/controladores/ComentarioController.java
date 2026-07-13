package org.nexora.api.controladores;

import org.nexora.api.modelos.Comentario;
import org.nexora.api.servicios.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/Comentarios/") //http://localhost:8080/api/Comentarios/
public class ComentarioController {

        @Autowired
        private final ComentarioService comentarioService;

        @Autowired
        public ComentarioController(ComentarioService service){
            this.comentarioService = service;
        }//constructor

        @GetMapping
        public List<Comentario> getComentario(){
            return comentarioService.findAll();
        }//get

        @GetMapping(path = "{comentarioId}")
        public Comentario getComentarioId(@PathVariable("comentarioId") Long id) {
            return comentarioService.findById(id);
        }//get

        @PostMapping
        public Comentario guardar(@RequestBody Comentario comentario){
            return comentarioService.saveComentario(comentario);
        }

        @DeleteMapping(path = "{comentarioId}")
        public Comentario deleteComentario(@PathVariable("comentarioId") Long id) {
            return comentarioService.deleteComentario(id);
        }//delete

        @PutMapping("/{id}")
        public Comentario actualizarComentario(
                @PathVariable Long id,
                @RequestBody Comentario comentario) {

            return comentarioService.actualizarComentario(id, comentario);
        }//actualizar

}//PC CC