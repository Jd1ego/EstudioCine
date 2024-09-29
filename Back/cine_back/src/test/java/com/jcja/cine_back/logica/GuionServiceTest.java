package com.jcja.cine_back.logica;

import com.jcja.cine_back.bd.orm.GuionORM;
import com.jcja.cine_back.bd.orm.ProyectoORM;
import com.jcja.cine_back.controller.dto.GuionDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;

import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class GuionServiceTest {

    @InjectMocks
    GuionService guionService;


    @Test
    void GivenGuionDTOAndProyecto_whenCrearGuion_thenReturnNewGuionORM() {

        ProyectoORM proyecto = new ProyectoORM();
        LocalDate fechaCreacion = LocalDate.of(2024, 9, 29);
        GuionDTO guionDto = new GuionDTO("Juan Pérez", fechaCreacion);


        GuionORM guionORM = guionService.crearGuion(guionDto, proyecto);


        assertNotNull(guionORM);
        assertEquals("Juan Pérez", guionORM.getAutor());
        assertEquals(fechaCreacion, guionORM.getFechaCreacion());
        assertEquals(proyecto, guionORM.getProyecto());
    }
    @Test
    void GivenNullGuionDTO_whenCrearGuion_thenReturnNull() {
        ProyectoORM proyecto = new ProyectoORM();
        GuionORM guionORM = guionService.crearGuion(null,proyecto);

        assertNull(guionORM);
    }

    @Test
    void GivenGuionORM_whenObtenerAutor_thenReturnAutor() {

        LocalDate fechaCreacion = LocalDate.of(2024, 9, 29);
        GuionORM guionORM = new GuionORM("Juan Pérez", fechaCreacion, new ProyectoORM());


        String autor = guionService.obtenerAutor(guionORM);


        assertEquals("Juan Pérez", autor);
    }
}
