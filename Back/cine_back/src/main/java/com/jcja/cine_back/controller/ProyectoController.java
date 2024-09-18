package com.jcja.cine_back.controller;
import com.jcja.cine_back.bd.orm.ProyectoORM;
import com.jcja.cine_back.controller.dto.ProyectoDTO;
import com.jcja.cine_back.logica.ProyectoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor

public class ProyectoController {

    private ProyectoService proyectoService;

    @PostMapping(path = "/Proyecto")
    public String guardarProyecto(@RequestBody ProyectoDTO proyecto) {

        proyectoService.crearYGuardarProyectoCompleto(proyecto);

        return "Proyecto guardado correctamente";

    }
    @GetMapping (path = "/Proyectos/All")
    public List consultarProyectos(){
        return
                proyectoService.obtenerProyectos();
    }
    @GetMapping(path = "/Proyectos/titulos")
    public Map consultarTituloProyectos(){
        return
                proyectoService.obtenerTitulosProyectos();

    }
    @GetMapping (path = "/Proyectos")
    public List<ProyectoDTO> obtenerProyectos(){
        return proyectoService.obtenerProyectoDetallado();
    }


}


