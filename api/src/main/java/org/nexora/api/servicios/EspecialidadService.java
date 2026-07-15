package org.nexora.api.servicios;

import org.nexora.api.modelos.Especialidad;
import org.nexora.api.repositorios.EspecialidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EspecialidadService {
    private final EspecialidadRepository especialidadRepository;

    @Autowired

    public EspecialidadService(EspecialidadRepository especialidadRepository) {
        this.especialidadRepository = especialidadRepository;
    }

    public List<Especialidad> getEspecialidades() {
        return especialidadRepository.findAll();
    }//getEspecialidades

    public Especialidad getEspecialidad(Long id) {
        return especialidadRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("La especialidad con el id [" + id + "] no existe"));
    }//getEspecialidad

    public Especialidad deleteEspecialidad(Long id){
        Especialidad especialidad=null;

        if (especialidadRepository.existsById(id)){
            especialidad=especialidadRepository.findById(id).get();

            especialidadRepository.deleteById(id);
        }//if

        return especialidad;
    }//deleteEspecialidad

    public Especialidad crearEspecialidad(Especialidad especialidad){
        Optional<Especialidad> esp=especialidadRepository.findByEspecialidad(especialidad.getEspecialidad());

        if(esp.isEmpty()){
            especialidad.setEspecialidad( especialidad.getEspecialidad());
        especialidadRepository.save(especialidad);
        }else{
            especialidad=null;
        }//else-if

        return especialidad;

    }//crearEspecialidad

    public Especialidad actualizarEspecialidad(Long id, String nombre ){
        Especialidad especialidad=null;

        if(especialidadRepository.existsById(id)){
            Especialidad e=especialidadRepository.findById(id).get();

            if(nombre!=null) e.setEspecialidad(nombre);

            especialidad=especialidadRepository.save(e);
        }

        return especialidad;
    }//actualizarEspecialidad

}//class EspecialidadRepository
