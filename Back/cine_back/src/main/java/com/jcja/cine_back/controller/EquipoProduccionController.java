package com.jcja.cine_back.controller;

import com.jcja.cine_back.bd.orm.EquipoProduccionORM;
import com.jcja.cine_back.controller.dto.EquipoProduccionDTO;
import com.jcja.cine_back.logica.EquipoProduccionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://157.173.122.1:3000")
public class EquipoProduccionController {
    EquipoProduccionService equipoProduccionService;

    @PostMapping(path = "/Equipo")
    public String crearYGuardarEquipoProduccion(@RequestBody EquipoProduccionDTO equipoDTO) {
        equipoProduccionService.crearYGuardarEquipoProduccion(equipoDTO);
        return "Equipo guardado correctamente";
    }
    @GetMapping(path = "/Equipos")
    public List<EquipoProduccionORM> obtenerEquipos() {
        return equipoProduccionService.obtenerTodosEquipos();
    }
    @GetMapping(path = "/Equipos/Proyectos")
    public List<EquipoProduccionDTO> obtenerEquiposProyectos() {
        return equipoProduccionService.obtenerEquiposProyectos();
    }



}
