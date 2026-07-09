package org.nexora.api.controladores;

import org.nexora.api.modelos.Publicaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/publicaciones/") //http://localhost:8080/api/publicaciones/
public class PublicacionesController {

    private final PublicacionesService publicacionesService;

@Autowired
    public PublicacionesController(PublicacionesService service){
    this.publicacionesService = service;
}//constructor PublicacionesController

@GetMapping
public List<Publicaciones> getPublicaciones(){
    return publicacionesService.getPublicaciones();
}//getPublicaciones