package com.jcja.cine_back.eventos;


import com.jcja.cine_back.controller.dto.ProgresoActualizadoEvento;

import com.jcja.cine_back.logica.AuditoriaService;
import com.jcja.cine_back.logica.NotificationService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {


        private final AuditoriaService auditoriaService;
        private final NotificationService notificacionService;

        @Autowired
        public RabbitMQConsumer(AuditoriaService auditoriaService, NotificationService notificacionService) {
            this.auditoriaService = auditoriaService;
            this.notificacionService = notificacionService;
        }

        @RabbitListener(queues = "progreso_actualizado_queue")
        public void recibirEvento(ProgresoActualizadoEvento evento) {
            // Registrar el evento en el sistema de auditoría


            // Notificar a los equipos involucrados
            notificacionService.notificarEquipos(evento);

            System.out.println("Evento recibido: " + evento);
        }
    }

