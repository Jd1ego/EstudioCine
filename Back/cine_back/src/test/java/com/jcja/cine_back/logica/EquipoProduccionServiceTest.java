package com.jcja.cine_back.logica;


import com.jcja.cine_back.bd.jpa.EquipoProduccionJPA;
import com.jcja.cine_back.bd.orm.EquipoProduccionORM;
import com.jcja.cine_back.controller.dto.EquipoProduccionDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class EquipoProduccionServiceTest {


    @Mock
    private EquipoProduccionJPA equipoProduccionJPA;

    @Mock
    private ProyectoService proyectoService;

    @InjectMocks
    private EquipoProduccionService equipoProduccionService;




    @Test
    void GivenEquipoProduccionDTO_whenCrearEquipoProduccion_thenReturnEquipoProduccionORM() {
        EquipoProduccionDTO equipoProduccionDTO = new EquipoProduccionDTO(1L,"Nombre", "Rol", "Contacto", null, null);

        EquipoProduccionORM equipoProduccionORM = equipoProduccionService.crearEquipoProduccion(equipoProduccionDTO);

        assertNotNull(equipoProduccionORM);
        assertEquals("Nombre", equipoProduccionORM.getNombre());
        assertEquals("Rol", equipoProduccionORM.getRol());
        assertEquals("Contacto", equipoProduccionORM.getContacto());
    }

    @Test
    void GivenEquipoProduccionORM_whenGuardarEquipoProduccion_thenReturnTrue() {
        EquipoProduccionORM equipoProduccionORM = new EquipoProduccionORM("Nombre", "Rol", "Contacto");

        boolean result = equipoProduccionService.guardarEquipoProduccion(equipoProduccionORM);

        verify(equipoProduccionJPA, times(1)).save(equipoProduccionORM);
        assertTrue(result);
    }

    @Test
    void GivenEquipoProduccionDTO_whenCrearYGuardarEquipoProduccion_thenReturnTrue() {
        EquipoProduccionDTO equipoProduccionDTO = new EquipoProduccionDTO(1L,"Nombre", "Rol", "Contacto", null, null);
        when(equipoProduccionJPA.save(any(EquipoProduccionORM.class))).thenReturn(new EquipoProduccionORM("Nombre", "Rol", "Contacto"));

        boolean result = equipoProduccionService.crearYGuardarEquipoProduccion(equipoProduccionDTO);

        verify(equipoProduccionJPA, times(1)).save(any(EquipoProduccionORM.class));
        assertTrue(result);
    }

    @Test
    void WhenObtenerTodosEquipos_thenReturnListOfEquipoProduccionORM() {
        when(equipoProduccionJPA.findAll()).thenReturn(Collections.singletonList(new EquipoProduccionORM("Nombre", "Rol", "Contacto")));

        List<EquipoProduccionORM> equipos = equipoProduccionService.obtenerTodosEquipos();

        assertEquals(1, equipos.size());
    }

    @Test
    void WhenObtenerEquiposProyectos_thenReturnListOfEquipoProduccionDTO() {
        EquipoProduccionORM equipo = new EquipoProduccionORM("Nombre", "Rol", "Contacto");
        when(equipoProduccionJPA.findAll()).thenReturn(Collections.singletonList(equipo));
        when(proyectoService.obtenerIdsProyectos(any())).thenReturn(Collections.emptyList());
        when(proyectoService.obtenerListaTitulosProyectos(any())).thenReturn(Collections.emptyList());

        List<EquipoProduccionDTO> equiposDTO = equipoProduccionService.obtenerEquiposProyectos();

        assertEquals(1, equiposDTO.size());
        assertEquals("Nombre", equiposDTO.get(0).nombre());
    }
}

