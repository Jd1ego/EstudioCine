package com.jcja.cine_back.logica;

import com.jcja.cine_back.controller.dto.ProgresoActualizadoEvento;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProgresoEventoServiceTest {

    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private ProgresoEventoService progresoEventoService;

    @Test
    void GivenProgresoActualizadoEvento_whenEnviarEvento_thenSendToRabbitMQ() {
        // Arrange
        List<String> correosEquipo = List.of("correo1@ejemplo.com", "correo2@ejemplo.com");
        ProgresoActualizadoEvento evento = new ProgresoActualizadoEvento(
                "Proyecto Ejemplo",
                1L,
                "Estado anterior",
                "Nuevo estado",
                LocalDateTime.now(),
                "usuario_prueba",
                correosEquipo
        );

        // Act
        progresoEventoService.enviarEvento(evento);

        // Assert
        verify(rabbitTemplate, times(1)).convertAndSend("progreso_exchange", "progreso.actualizado", evento);
    }
}
