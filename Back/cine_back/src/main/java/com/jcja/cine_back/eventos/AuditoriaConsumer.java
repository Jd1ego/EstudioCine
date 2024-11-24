package com.jcja.cine_back.eventos;


import com.jcja.cine_back.controller.dto.ProgresoActualizadoEvento;
import com.jcja.cine_back.logica.AuditoriaService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuditoriaConsumer {

    @Autowired
    private AuditoriaService auditoriaService;

    @RabbitListener(queues = "auditoria_queue")
    public void procesarEvento(ProgresoActualizadoEvento evento) {
        System.out.println("Evento recibido para auditoría: " + evento);

        // Guardar datos en la base de datos
        auditoriaService.guardarAuditoria(
                evento.getNombreProyecto(),
                evento.getIdProyecto(),
                evento.getEstadoAnterior(),
                evento.getNuevoEstado(),
                evento.getUsuarioCambio()
        );
    }
}