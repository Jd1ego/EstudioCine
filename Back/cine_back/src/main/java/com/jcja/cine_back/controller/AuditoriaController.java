package com.jcja.cine_back.controller;

import com.jcja.cine_back.bd.orm.AuditoriaORM;
import com.jcja.cine_back.logica.AuditoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuditoriaController {

    @Autowired
    private AuditoriaService auditoriaService;

    // Endpoint para obtener todos los registros de auditoría
    @GetMapping("/auditoria")
    public List<AuditoriaORM> obtenerAuditorias() {
        return auditoriaService.obtenerAuditorias();
    }
}