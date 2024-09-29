package com.jcja.cine_back.logica;

import com.jcja.cine_back.bd.jpa.ProyectoJPA;
import com.jcja.cine_back.bd.jpa.EquipoProduccionJPA;
import com.jcja.cine_back.bd.orm.*;
import com.jcja.cine_back.controller.dto.ProyectoDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class ProyectoServiceTest {



    @Mock
    private PresupuestoService presupuestoService;

    @Mock
    private ProgresoService progresoService;

    @Mock
    private ProyectoJPA proyectoJPA;

    @Mock
    private GuionService guionService;

    @Mock
    private EquipoProduccionJPA equipoProduccionJPA;

    @InjectMocks
    private ProyectoService proyectoService;


    @Test
    void GivenProyectoDTO_whenCrearProyecto_thenReturnProyectoORM() {
        ProyectoDTO proyectoDTO = new ProyectoDTO("Titulo", null, null, null, null, null, null, null);
        ProyectoORM proyectoORM = proyectoService.crearProyecto(proyectoDTO);

        assertNotNull(proyectoORM);
        assertEquals("Titulo", proyectoORM.getTitulo());
    }

    @Test
    void GivenProyectoORM_whenGuardarProyecto_thenReturnTrue() {
        ProyectoORM proyectoORM = new ProyectoORM("Titulo");

        boolean result = proyectoService.guardarProyecto(proyectoORM);

        verify(proyectoJPA, times(1)).save(proyectoORM);
        assertTrue(result);
    }

    @Test
    void GivenProyectoDTO_whenCrearYGuardarProyectoCompleto_thenReturnTrue() {
        ProyectoDTO proyectoDTO = new ProyectoDTO("Titulo", null, null, null, null, null, null, null);
        GuionORM guionORM = new GuionORM();
        PresupuestoORM presupuestoORM = new PresupuestoORM();
        ProgresoORM progresoORM = new ProgresoORM();

        when(guionService.crearGuion(any(), any())).thenReturn(guionORM);
        when(presupuestoService.crearPresupuesto(any(), any())).thenReturn(presupuestoORM);
        when(progresoService.crearProgreso(any(), any())).thenReturn(progresoORM);

        boolean result = proyectoService.crearYGuardarProyectoCompleto(proyectoDTO);

        verify(proyectoJPA, times(1)).save(any(ProyectoORM.class));
        assertTrue(result);
    }

    @Test
    void WhenObtenerTitulosProyectos_thenReturnMap() {
        ProyectoORM proyecto = new ProyectoORM(1L, "Titulo");
        when(proyectoJPA.findAll()).thenReturn(Collections.singletonList(proyecto));

        Map<Long, String> titulos = proyectoService.obtenerTitulosProyectos();

        assertEquals(1, titulos.size());
        assertEquals("Titulo", titulos.get(1L));
    }

    @Test
    void WhenObtenerProyectos_thenReturnListOfProyectoORM() {
        ProyectoORM proyecto = new ProyectoORM(1L, "Titulo");
        when(proyectoJPA.findAll()).thenReturn(Collections.singletonList(proyecto));

        List<ProyectoORM> proyectos = proyectoService.obtenerProyectos();

        assertEquals(1, proyectos.size());
        assertEquals("Titulo", proyectos.get(0).getTitulo());
    }

    @Test
    public void GivenProyectos_whenObtenerProyectoDetallado_thenReturnProyectoDTOList() {
        ProyectoORM proyectoORM = new ProyectoORM();
        proyectoORM.setTitulo("Proyecto 1");

        when(proyectoJPA.findAll()).thenReturn(Collections.singletonList(proyectoORM));
        when(guionService.obtenerAutor(proyectoORM.getGuion())).thenReturn("Autor 1");
        when(presupuestoService.obtenerPresupuesto(proyectoORM.getPresupuesto())).thenReturn(1000);
        when(progresoService.obtenerProgreso(proyectoORM.getProgreso())).thenReturn(50.0);

        List<ProyectoDTO> result = proyectoService.obtenerProyectoDetallado();

        assertEquals(1, result.size());
        assertEquals("Proyecto 1", result.get(0).titulo());
        assertEquals("Autor 1",guionService.obtenerAutor(proyectoORM.getGuion()));
        assertEquals(1000, presupuestoService.obtenerPresupuesto(proyectoORM.getPresupuesto()));
        assertEquals(50.0, progresoService.obtenerProgreso(proyectoORM.getProgreso()));
    }
    @Test
    void GivenNullGuion_whenAsignarGuion_thenReturnFalse() {
        ProyectoORM proyectoORM = new ProyectoORM();
        boolean result = proyectoService.asignarGuion(null, proyectoORM);

        assertFalse(result);
        assertNull(proyectoORM.getGuion());
    }
    @Test
    void GivenNullPresupuesto_whenAsignarPresupuesto_thenReturnFalse() {
        ProyectoORM proyectoORM = new ProyectoORM();
        boolean result = proyectoService.asignarPresupuesto(null, proyectoORM);

        assertFalse(result);
        assertNull(proyectoORM.getPresupuesto());
    }
    @Test
    void GivenNullProgreso_whenAsignarProgreso_thenReturnFalse() {
        ProyectoORM proyectoORM = new ProyectoORM();
        boolean result = proyectoService.asignarProgreso(null, proyectoORM);

        assertFalse(result);
        assertNull(proyectoORM.getProgreso());
    }
}

