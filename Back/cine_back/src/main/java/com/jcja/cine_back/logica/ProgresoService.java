package com.jcja.cine_back.logica;


import com.jcja.cine_back.bd.jpa.ProgresoJPA;
import com.jcja.cine_back.bd.orm.ProgresoORM;
import com.jcja.cine_back.bd.orm.ProyectoORM;
import com.jcja.cine_back.controller.dto.ProgresoDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProgresoService {

    private ProgresoJPA progresoJPA;

    public ProgresoORM crearProgreso(ProgresoDTO progresoDTO, ProyectoORM proyecto) {
        if (progresoDTO!=null){
            return new ProgresoORM(
                    progresoDTO.etapa(),
                    progresoDTO.porcentajeCompletado(),
                    progresoDTO.fechaActualizacion(),
                    proyecto
            );
        }
        return null;

    }
    public ProgresoORM actualizarProgreso(ProgresoDTO progresoDTO, ProyectoORM proyecto) {
        if (proyecto == null) {
            throw new IllegalArgumentException("Proyecto no encontrado");
        }

        // Verifica si ya existe un progreso para este proyecto
        ProgresoORM progresoExistente = proyecto.getProgreso();
        if (progresoExistente != null) {
            // Si el progreso ya existe, solo actualiza si hay cambios
            if (!progresoExistente.getEtapa().equals(progresoDTO.etapa()) ||
                    !progresoExistente.getPorcentajeCompletado().equals(progresoDTO.porcentajeCompletado())) {
                // Actualiza el progreso existente
                progresoExistente.setEtapa(progresoDTO.etapa());
                progresoExistente.setPorcentajeCompletado(progresoDTO.porcentajeCompletado());
                progresoExistente.setFechaActualizacion(progresoDTO.fechaActualizacion());
                return progresoJPA.save(progresoExistente);
            } else {
                // No hay cambios, no actualices ni guardes
                System.out.println("No se realizaron cambios en el progreso.");
                return progresoExistente;
            }
        } else {
            // Si no existe un progreso, crea uno nuevo
            ProgresoORM nuevoProgreso = crearProgreso(progresoDTO, proyecto);
            return progresoJPA.save(nuevoProgreso);
        }
    }
    public ProgresoORM obtenerProgresoPorProyecto(ProyectoORM proyecto) {
        return progresoJPA.findByProyecto(proyecto); // Este método buscará el progreso con base en el proyecto
    }


    public Double obtenerProgreso(ProgresoORM progresoORM){

        return progresoORM.getPorcentajeCompletado();
    }
}
