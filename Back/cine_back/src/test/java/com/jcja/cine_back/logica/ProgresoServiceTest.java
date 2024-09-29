package com.jcja.cine_back.logica;


import com.jcja.cine_back.bd.orm.ProgresoORM;
import com.jcja.cine_back.bd.orm.ProyectoORM;
import com.jcja.cine_back.controller.dto.ProgresoDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProgresoServiceTest {


    @Mock
    private ProyectoORM proyectoMock;

    @InjectMocks
    private ProgresoService progresoService;


    @Test
    void GivenProgresoDTOAndProyecto_whenCrearProgreso_thenReturnProgresoORM() {
        ProgresoDTO progresoDTO = new ProgresoDTO("Etapa 1", 75.0, LocalDate.now(),proyectoMock);

        ProgresoORM progresoORM = progresoService.crearProgreso(progresoDTO, proyectoMock);

        assertNotNull(progresoORM);
        assertEquals("Etapa 1", progresoORM.getEtapa());
        assertEquals(75.0, progresoORM.getPorcentajeCompletado());
        assertEquals(progresoDTO.fechaActualizacion(), progresoORM.getFechaActualizacion());
        assertEquals(proyectoMock, progresoORM.getProyecto());
    }
    @Test
    void GivenNullProgresoDTO_whenCrearProgreso_thenReturnNull() {
        ProgresoORM progresoORM = progresoService.crearProgreso(null, proyectoMock);

        assertNull(progresoORM);
    }

    @Test
    void GivenProgresoORM_whenObtenerProgreso_thenReturnPorcentaje() {
        ProgresoORM progresoORM = new ProgresoORM("Etapa 1", 75.0, LocalDate.now(), new ProyectoORM());

        Double porcentaje = progresoService.obtenerProgreso(progresoORM);

        assertEquals(75.0, porcentaje);
    }
}
