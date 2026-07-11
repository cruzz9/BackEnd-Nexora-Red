package org.nexora.api.controladores;


import org.nexora.api.modelos.Especialidad;
import org.nexora.api.servicios.EspecialidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/especialidades/")
public class EspecialidadController {

    private final EspecialidadService especialidadService;

    @Autowired
    public EspecialidadController(EspecialidadService especialidadService) {
        this.especialidadService = especialidadService;
    }//constructor EspecialidadController

    @GetMapping
    public List<Especialidad> getEspecialidades() {
        return especialidadService.getEspecialidades();
    }//getEspecialidad

    @GetMapping(path = "{especialidad_id}")
    public Especialidad getEspecialidad(@PathVariable("especialidad_id") Long id) {
        return especialidadService.getEspecialidad(id);
    }//getEspecialidad

    @DeleteMapping(path = "{especialidad_id}")
    public Especialidad deleteEspecialidad(@PathVariable("especialidad_id") Long id) {
        return especialidadService.deleteEspecialidad(id);

    }//deleteEspecialidad

    @PostMapping
    public Especialidad crearEspecialidad(@RequestBody Especialidad especialidad) {
        return especialidadService.crearEspecialidad(especialidad);
    }//crearEspecialidad

    @PutMapping(path = "{especialidad_id}")
    public Especialidad actualizarEspecialidad(@PathVariable("especialidad_id") Long id,
                                               @RequestParam(value = "especialidad", required = false) String especialidad) {
        return especialidadService.actualizarEspecialidad(id, especialidad);

    }//actualizarEspecialidad

}
