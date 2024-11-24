package com.jcja.cine_back.eventos;

import com.jcja.cine_back.controller.dto.ProgresoActualizadoEvento;
import com.jcja.cine_back.logica.AuditoriaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuditoriaConsumerTest {

    @Mock
    private AuditoriaService auditoriaService;

    @InjectMocks
    private AuditoriaConsumer auditoriaConsumer;

    private ProgresoActualizadoEvento evento;

    @BeforeEach
    void setUp() {
        // Actualizamos el constructor para incluir el campo correosEquipo
        evento = new ProgresoActualizadoEvento(
                "Proyecto A",
                1L,
                "Estado Inicial",
                "Nuevo Estado",
                LocalDateTime.now(),
                "usuario1",
                Arrays.asList("correo1@example.com", "correo2@example.com")
        );
    }

    @Test
    void testProcesarEvento() {
        // Act
        auditoriaConsumer.procesarEvento(evento);

        // Assert
        verify(auditoriaService, times(1)).guardarAuditoria(
                evento.getNombreProyecto(),
                evento.getIdProyecto(),
                evento.getEstadoAnterior(),
                evento.getNuevoEstado(),
                evento.getUsuarioCambio()
        );
    }
}
