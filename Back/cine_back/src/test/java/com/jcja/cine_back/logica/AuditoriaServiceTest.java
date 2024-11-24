package com.jcja.cine_back.logica;

import com.jcja.cine_back.bd.jpa.AuditoriaJPA;
import com.jcja.cine_back.bd.orm.AuditoriaORM;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AuditoriaServiceTest {

    @Mock
    private AuditoriaJPA auditoriaJPA;

    @InjectMocks
    private AuditoriaService auditoriaService;

    private AuditoriaORM auditoriaORM;

    @BeforeEach
    void setUp() {
        // Inicializamos el objeto AuditoriaORM que usaremos en las pruebas
        auditoriaORM = new AuditoriaORM("Proyecto X", 1L, "Estado Anterior", "Nuevo Estado", LocalDateTime.now(), "Usuario");
    }

    @Test
    void testRegistrarAuditoria() {
        // Arrange
        String nombreProyecto = "Proyecto A";
        Long idProyecto = 1L;
        String estadoAnterior = "Estado Inicial";
        String nuevoEstado = "Estado Final";
        LocalDateTime fechaHoraCambio = LocalDateTime.now();
        String usuarioCambio = "admin";

        // Act
        auditoriaService.registrarAuditoria(nombreProyecto, idProyecto, estadoAnterior, nuevoEstado, fechaHoraCambio, usuarioCambio);

        // Assert
        // Verificamos que se haya creado un objeto AuditoriaORM y se haya guardado
        verify(auditoriaJPA, times(1)).save(argThat(auditoria ->
                auditoria.getNombreProyecto().equals(nombreProyecto) &&
                        auditoria.getIdProyecto().equals(idProyecto) &&
                        auditoria.getEstadoAnterior().equals(estadoAnterior) &&
                        auditoria.getNuevoEstado().equals(nuevoEstado) &&
                        auditoria.getFechaHoraCambio().equals(fechaHoraCambio) &&
                        auditoria.getUsuarioCambio().equals(usuarioCambio)
        ));
    }


    @Test
    void testObtenerAuditorias() {
        // Arrange
        List<AuditoriaORM> auditorias = Arrays.asList(auditoriaORM);
        when(auditoriaJPA.findAll()).thenReturn(auditorias);

        // Act
        List<AuditoriaORM> result = auditoriaService.obtenerAuditorias();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(auditoriaORM, result.get(0));
        verify(auditoriaJPA, times(1)).findAll();
    }

    @Test
    void testGuardarAuditoria() {
        // Arrange
        String nombreProyecto = "Proyecto B";
        Long idProyecto = 2L;
        String estadoAnterior = "En progreso";
        String nuevoEstado = "Terminado";
        String usuarioCambio = "usuario1";

        // Act
        auditoriaService.guardarAuditoria(nombreProyecto, idProyecto, estadoAnterior, nuevoEstado, usuarioCambio);

        // Assert
        verify(auditoriaJPA, times(1)).save(any(AuditoriaORM.class)); // Verificamos que save se haya llamado una vez
    }
}
