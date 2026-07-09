package org.nexora.api.servicios;

import org.nexora.api.modelos.Publicaciones;
import org.nexora.api.repositorios.PublicacionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublicacionesService {
    private final PublicacionesRepository publicacionesRepository;
}//PublicacionesService

@Autowired
public PublicacionesService(PublicacionesRepository repository){
    this.publicacionesRepository =repository;
}//constructor PublicacionesService
