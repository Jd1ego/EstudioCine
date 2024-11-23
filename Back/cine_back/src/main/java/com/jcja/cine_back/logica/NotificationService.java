package com.jcja.cine_back.logica;

import com.jcja.cine_back.controller.dto.ProgresoActualizadoEvento;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    public void notificarEquipos(ProgresoActualizadoEvento evento) {
        // Aquí puedes implementar el envío de notificaciones a los equipos
        System.out.println("Notificando equipos sobre el evento: " + evento);
    }
}