package org.nexora.api.servicios;

import org.nexora.api.repositorios.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComentarioService {

    private final ComentarioRepository comentarioRepository;

    @Autowired
    public ComentarioService(ComentarioRepository repository){
        this.comentarioRepository = repository;
    }//constructor

}//PC CS