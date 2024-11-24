package com.jcja.cine_back.logica;

import com.jcja.cine_back.bd.jpa.ProgresoJPA;
import com.jcja.cine_back.bd.orm.ProgresoORM;
import com.jcja.cine_back.bd.orm.ProyectoORM;
import com.jcja.cine_back.controller.dto.ProgresoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProgresoServiceTest {

    @Mock
    private ProgresoJPA progresoJPA;

    @Mock
    private ProyectoORM proyectoMock;

    @InjectMocks
    private ProgresoService progresoService;

    private ProgresoDTO progresoDTO;
    private ProgresoORM progresoORM;

    @BeforeEach
    void setUp() {
        progresoDTO = new ProgresoDTO("Etapa 1", 75.0, LocalDate.now(), proyectoMock);
        progresoORM = new ProgresoORM("Etapa 1", 75.0, LocalDate.now(), proyectoMock);
    }

    @Test
    void GivenProgresoDTOAndProyecto_whenCrearProgreso_thenReturnProgresoORM() {
        // Act
        ProgresoORM progresoORM = progresoService.crearProgreso(progresoDTO, proyectoMock);

        // Assert
        assertNotNull(progresoORM);
        assertEquals("Etapa 1", progresoORM.getEtapa());
        assertEquals(75.0, progresoORM.getPorcentajeCompletado());
        assertEquals(progresoDTO.fechaActualizacion(), progresoORM.getFechaActualizacion());
        assertEquals(proyectoMock, progresoORM.getProyecto());
    }

    @Test
    void GivenNullProgresoDTO_whenCrearProgreso_thenReturnNull() {
        // Act
        ProgresoORM progresoORM = progresoService.crearProgreso(null, proyectoMock);

        // Assert
        assertNull(progresoORM);
    }

    @Test
    void GivenProgresoORM_whenObtenerProgreso_thenReturnPorcentaje() {
        // Act
        Double porcentaje = progresoService.obtenerProgreso(progresoORM);

        // Assert
        assertEquals(75.0, porcentaje);
    }

    @Test
    void GivenProgresoDTOAndProyecto_whenActualizarProgreso_thenReturnUpdatedProgreso() {
        // Arrange
        ProgresoDTO progresoDTOUpdated = new ProgresoDTO("Etapa 2", 80.0, LocalDate.now(), proyectoMock);
        when(proyectoMock.getProgreso()).thenReturn(progresoORM);
        when(progresoJPA.save(any(ProgresoORM.class))).thenReturn(progresoORM);

        // Act
        ProgresoORM updatedProgreso = progresoService.actualizarProgreso(progresoDTOUpdated, proyectoMock);

        // Assert
        assertNotNull(updatedProgreso);
        assertEquals("Etapa 2", updatedProgreso.getEtapa());
        assertEquals(80.0, updatedProgreso.getPorcentajeCompletado());
        verify(progresoJPA, times(1)).save(any(ProgresoORM.class));
    }

    @Test
    void GivenProgresoDTOAndNullProyecto_whenActualizarProgreso_thenThrowException() {
        // Arrange
        ProgresoDTO progresoDTOUpdated = new ProgresoDTO("Etapa 2", 80.0, LocalDate.now(), null);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> progresoService.actualizarProgreso(progresoDTOUpdated, null));
    }

    @Test
    void GivenNoExistingProgreso_whenActualizarProgreso_thenCreateNewProgreso() {
        // Arrange
        ProgresoDTO progresoDTOUpdated = new ProgresoDTO("Etapa 3", 85.0, LocalDate.now(), proyectoMock);
        when(proyectoMock.getProgreso()).thenReturn(null);  // Simula que no hay progreso previo
        when(progresoJPA.save(any(ProgresoORM.class))).thenReturn(progresoORM);

        // Act
        ProgresoORM nuevoProgreso = progresoService.actualizarProgreso(progresoDTOUpdated, proyectoMock);

        // Assert
        assertNotNull(nuevoProgreso);
        assertEquals("Etapa 3", nuevoProgreso.getEtapa());
        assertEquals(85.0, nuevoProgreso.getPorcentajeCompletado());
        verify(progresoJPA, times(1)).save(any(ProgresoORM.class));
    }
}
