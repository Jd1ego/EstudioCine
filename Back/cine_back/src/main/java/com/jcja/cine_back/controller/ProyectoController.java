package com.jcja.cine_back.controller;
import com.jcja.cine_back.bd.orm.ProgresoORM;
import com.jcja.cine_back.bd.orm.ProyectoORM;
import com.jcja.cine_back.controller.dto.ProgresoActualizadoEvento;
import com.jcja.cine_back.controller.dto.ProgresoDTO;
import com.jcja.cine_back.controller.dto.ProyectoDTO;
import com.jcja.cine_back.logica.EquipoProduccionService;
import com.jcja.cine_back.logica.ProgresoService;
import com.jcja.cine_back.logica.ProyectoService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor

@CrossOrigin(origins = "http://157.173.122.1:3000")
public class ProyectoController {

    private ProyectoService proyectoService;
    private ProgresoService progresoService;
    private EquipoProduccionService equipoProduccionService;
    private final RabbitTemplate rabbitTemplate;

    @PostMapping(path = "/Proyecto")
    public String guardarProyecto(@RequestBody ProyectoDTO proyecto) {

        proyectoService.crearYGuardarProyectoCompleto(proyecto);

        return "Proyecto guardado correctamente";

    }
    @PostMapping("/Proyectos/{id}/progreso")
    public String actualizarProgreso(@PathVariable Long id, @RequestBody ProgresoDTO progresoDTO) {
        ProyectoORM proyecto = proyectoService.obtenerProyectoPorId(id);
        if (proyecto == null) {
            return "Proyecto no encontrado";
        }

        ProgresoORM progresoExistente = progresoService.obtenerProgresoPorProyecto(proyecto);

        if (progresoExistente == null) {
            return "No se ha encontrado progreso para este proyecto";
        }

        String estadoAnterior = progresoExistente.getEtapa();
        String nuevoEstado = progresoDTO.etapa();

        if (!estadoAnterior.equals(nuevoEstado)) {
            boolean actualizado = proyectoService.actualizarProgresoDelProyecto(id, progresoDTO);

            if (actualizado) {
                String tituloProyecto = proyectoService.obtenerTituloPorId(id);

                // Obtener los correos del equipo
                List<String> correosEquipo = equipoProduccionService.obtenerCorreosPorProyecto(id);

                // Crear el evento con los nuevos y antiguos estados
                ProgresoActualizadoEvento evento = new ProgresoActualizadoEvento(
                        tituloProyecto,
                        id,
                        estadoAnterior,
                        nuevoEstado,
                        LocalDateTime.now(),
                        "UsuarioSistema",
                        correosEquipo
                );

                // Enviar el evento a RabbitMQ
                rabbitTemplate.convertAndSend("progreso_exchange", "progreso.actualizado", evento);

                System.out.println("Evento generado y enviado: " + evento);

                return "Progreso actualizado correctamente";
            } else {
                return "No se pudo actualizar el progreso";
            }
        } else {
            return "El estado no ha cambiado";
        }
    }






    @GetMapping (path = "/Proyectos/All")
    public List<ProyectoORM> consultarProyectos(){
        return
                proyectoService.obtenerProyectos();
    }
    @GetMapping(path = "/Proyectos/titulos")
    public Map<Long, String> consultarTituloProyectos(){
        return
                proyectoService.obtenerTitulosProyectos();

    }
    @GetMapping (path = "/Proyectos")
    public List<ProyectoDTO> obtenerProyectos(){
        return proyectoService.obtenerProyectoDetallado();
    }



}


