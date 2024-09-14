package com.jcja.cine_back.controller;
import com.jcja.cine_back.controller.dto.ProyectoDTO;
import com.jcja.cine_back.logica.ProyectoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor

public class ProyectoController {

    private ProyectoService proyectoService;

    @PostMapping(path = "/Proyecto")
    public String guardarProyecto(@RequestBody ProyectoDTO proyecto) {

        proyectoService.guardarProyecto(proyecto);

        return "Proyecto guardado correctamente";

    }
}
