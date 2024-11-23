package com.jcja.cine_back.logica;

import com.jcja.cine_back.bd.jpa.EquipoProduccionJPA;
import com.jcja.cine_back.bd.jpa.ProyectoJPA;
import com.jcja.cine_back.bd.orm.*;
import com.jcja.cine_back.controller.dto.ProgresoDTO;
import com.jcja.cine_back.controller.dto.ProyectoDTO;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.hibernate.Hibernate;
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


    public boolean asignarGuion(GuionORM guionORM,ProyectoORM proyectoORM) {

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

    public ProyectoORM crearProyecto(ProyectoDTO proyectoDTO) {
        ProyectoORM proyectoORM = new ProyectoORM();
        proyectoORM.setTitulo(proyectoDTO.titulo());
        return proyectoORM;
    }

    public boolean guardarProyecto(ProyectoORM proyectoORM) {
        proyectoJPA.save(proyectoORM);
        return true;
    }
    public  boolean crearYGuardarProyectoCompleto(ProyectoDTO proyectoDTO) {

        ProyectoORM proyectoORM = crearProyecto(proyectoDTO);
        asignarEquiposProduccion(proyectoDTO,proyectoORM);
        GuionORM guionORM = guionService.crearGuion(proyectoDTO.guionDTO(),proyectoORM);
        asignarGuion(guionORM,proyectoORM);
        PresupuestoORM presupuestoORM = presupuestoService.crearPresupuesto(proyectoDTO.presupuestoDTO(),proyectoORM);
        asignarPresupuesto(presupuestoORM,proyectoORM);
        ProgresoORM progresoORM = progresoService.crearProgreso(proyectoDTO.progresoDTO(),proyectoORM);
        asignarProgreso(progresoORM,proyectoORM);
        guardarProyecto(proyectoORM);

        return true;
    }


    public Map<Long, String> obtenerTitulosProyectos() {
        return proyectoJPA.findAll().stream()
                .collect(Collectors.toMap(ProyectoORM::getId, ProyectoORM::getTitulo));
    }

    public List<ProyectoORM> obtenerProyectos() {
        return proyectoJPA.findAll().stream()
                .map(proyecto -> new ProyectoORM(proyecto.getId(), proyecto.getTitulo()))
                .toList();
    }
    public List<Long> obtenerIdsProyectos(List<ProyectoORM> proyectos) {
        return proyectos.stream()
                .map(ProyectoORM::getId)
                .toList();
    }
    public List<String> obtenerListaTitulosProyectos(List<ProyectoORM> proyectos) {
        return proyectos.stream()
                .map(ProyectoORM::getTitulo)
                .toList();
    }
    public boolean actualizarProgreso(Long proyectoId, ProgresoORM nuevoProgreso) {
        ProyectoORM proyectoORM = proyectoJPA.findById(proyectoId)
                .orElseThrow(() -> new IllegalArgumentException("Proyecto no encontrado con ID: " + proyectoId));

        if (nuevoProgreso != null) {
            proyectoORM.setProgreso(nuevoProgreso);
            proyectoJPA.save(proyectoORM);
            return true;
        }

        return false;
    }


    public List<ProyectoDTO> obtenerProyectoDetallado(){
        return proyectoJPA.findAll().stream().map(proyecto-> new ProyectoDTO(
                
                proyecto.getTitulo(),
                null,
                null,
                null,
                null,
                proyecto.getGuion() != null ? guionService.obtenerAutor(proyecto.getGuion()) : "Autor desconocido",
                proyecto.getPresupuesto() != null ? presupuestoService.obtenerPresupuesto(proyecto.getPresupuesto()) : 0,
                proyecto.getProgreso() != null ? progresoService.obtenerProgreso(proyecto.getProgreso()) : 0

        )).toList();

    }
    public ProyectoORM obtenerProyectoPorId(Long id) {
        return proyectoJPA.findById(id).orElse(null);
    }

    public boolean actualizarProgresoDelProyecto(Long proyectoId, ProgresoDTO progresoDTO) {
        ProyectoORM proyecto = obtenerProyectoPorId(proyectoId);
        if (proyecto == null) {
            return false;
        }

        ProgresoORM nuevoProgreso = progresoService.actualizarProgreso(progresoDTO, proyecto);
        proyecto.setProgreso(nuevoProgreso); // Asocia el nuevo progreso al proyecto.
        proyectoJPA.save(proyecto); // Guarda el proyecto con el progreso actualizado.
        return true;
    }
    public String obtenerTituloPorId(Long id) {
        ProyectoORM proyecto = proyectoJPA.findById(id).orElse(null);
        return proyecto != null ? proyecto.getTitulo() : null; // Retorna el título o null si no se encuentra el proyecto
    }




}

