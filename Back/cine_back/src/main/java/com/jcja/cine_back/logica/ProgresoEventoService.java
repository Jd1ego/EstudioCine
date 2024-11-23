package com.jcja.cine_back.logica;

import com.jcja.cine_back.controller.dto.ProgresoActualizadoEvento;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProgresoEventoService {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public ProgresoEventoService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarEvento(ProgresoActualizadoEvento evento) {
        rabbitTemplate.convertAndSend("progreso_exchange", "progreso.actualizado", evento);
    }
}