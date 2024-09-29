package com.jcja.cine_back.logica;

import com.jcja.cine_back.bd.orm.PresupuestoORM;
import com.jcja.cine_back.bd.orm.ProyectoORM;
import com.jcja.cine_back.controller.dto.PresupuestoDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PresupuestoServiceTest {

    @Mock
    private ProyectoORM proyectoMock;

    @InjectMocks
    private PresupuestoService presupuestoService;




    @Test
    void GivenPresupuestoDTOAndProyecto_whenCrearPresupuesto_thenReturnPresupuestoORM() {

        PresupuestoDTO presupuestoDTO = new PresupuestoDTO(10000, "USD", proyectoMock);


        PresupuestoORM presupuestoORM = presupuestoService.crearPresupuesto(presupuestoDTO, proyectoMock);


        assertNotNull(presupuestoORM);
        assertEquals(10000, presupuestoORM.getCantidad());
        assertEquals("USD", presupuestoORM.getMoneda());
        assertEquals(proyectoMock, presupuestoORM.getProyecto());
    }
    @Test
    void GivenNullPresupuestoDTO_whenPresupuesto_thenReturnNull() {
        PresupuestoORM presupuestoORM = presupuestoService.crearPresupuesto(null, proyectoMock);

        assertNull(presupuestoORM);
    }

    @Test
    void GivenPresupuestoORM_whenObtenerPresupuesto_thenReturnCantidad() {

        PresupuestoORM presupuestoORM = new PresupuestoORM(10000, "USD", new ProyectoORM());


        Integer cantidad = presupuestoService.obtenerPresupuesto(presupuestoORM);


        assertEquals(10000, cantidad);
    }
}
