package com.jcja.cine_back.logica;

import com.jcja.cine_back.bd.jpa.EquipoProduccionJPA;
import com.jcja.cine_back.bd.jpa.ProyectoJPA;
import com.jcja.cine_back.bd.orm.*;
import com.jcja.cine_back.controller.dto.ProyectoDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProyectoService {

    private final PresupuestoService presupuestoService;
    private final ProgresoService progresoService;
    ProyectoJPA proyectoJPA;
    GuionService guionService;
    EquipoProduccionJPA equipoProduccionJPA;


    public boolean adjuntarGuion(GuionORM guionORM,ProyectoORM proyectoORM) {

        if (guionORM != null) {
            proyectoORM.setGuion(guionORM);
            return true;
        }
        return false;
    }
    public boolean asignarEquiposProduccion(ProyectoDTO proyectoDTO,ProyectoORM proyectoORM) {
        List<EquipoProduccionORM> equiposProduccion =
                equipoProduccionJPA.findAllById(proyectoDTO.equipoIds());
        proyectoORM.setEquiposProduccion(equiposProduccion);

        return true;
    }

    public boolean asignarPresupuesto(PresupuestoORM presupuestoORM, ProyectoORM proyectoORM) {
        if (presupuestoORM != null) {
            proyectoORM.setPresupuesto(presupuestoORM);
            return true;
        }
        return false;
    }

    public boolean asignarProgreso(ProgresoORM progresoORM, ProyectoORM proyectoORM) {
        if (progresoORM != null) {
            proyectoORM.setProgreso(progresoORM);
            return true;
        }
        return false;
    }
    public  boolean guardarProyecto(ProyectoDTO proyectoDTO) {

        ProyectoORM proyectoORM = new ProyectoORM();
        proyectoORM.setTitulo(proyectoDTO.titulo());
        asignarEquiposProduccion(proyectoDTO,proyectoORM);
        GuionORM guionORM = guionService.crearGuion(proyectoDTO.guionDTO(),proyectoORM);
        adjuntarGuion(guionORM,proyectoORM);
        PresupuestoORM presupuestoORM = presupuestoService.crearPresupuesto(proyectoDTO.presupuestoDTO(),proyectoORM);
        asignarPresupuesto(presupuestoORM,proyectoORM);
        ProgresoORM progresoORM = progresoService.CrearProgreso(proyectoDTO.progresoDTO(),proyectoORM);
        asignarProgreso(progresoORM,proyectoORM);
        proyectoJPA.save(proyectoORM);

        return true;
    }






    public Map<Long, String> obtenerTitulosProyectos() {
        return proyectoJPA.findAll().stream()
                .collect(Collectors.toMap(ProyectoORM::getId, ProyectoORM::getTitulo));
    }
    public List<ProyectoORM> obtenerProyectos() {
        return proyectoJPA.findAll().stream()
                .map(proyecto -> new ProyectoORM(proyecto.getId(), proyecto.getTitulo()))
                .collect(Collectors.toList());
    }
}