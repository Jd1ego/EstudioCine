package com.jcja.cine_back.controller;

import com.jcja.cine_back.bd.orm.EquipoProduccionORM;
import com.jcja.cine_back.controller.dto.EquipoProduccionDTO;
import com.jcja.cine_back.logica.EquipoProduccionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@AllArgsConstructor
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
    @GetMapping(path = "/Equipos/Id")
    public List<EquipoProduccionDTO> obtenerEquiposConId() {
        return equipoProduccionService.obtenerTodosEquiposConId();
    }



}
