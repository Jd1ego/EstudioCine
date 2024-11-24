package com.jcja.cine_back.logica;


import com.jcja.cine_back.bd.jpa.AuditoriaJPA;
import com.jcja.cine_back.bd.orm.AuditoriaORM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuditoriaService {

    @Autowired
    private AuditoriaJPA auditoriaJPA;


    public void registrarAuditoria(String nombreProyecto, Long idProyecto, String estadoAnterior, String nuevoEstado, LocalDateTime fechaHoraCambio, String usuarioCambio) {
        AuditoriaORM auditoria = new AuditoriaORM(nombreProyecto, idProyecto, estadoAnterior, nuevoEstado, fechaHoraCambio, usuarioCambio);
        auditoriaJPA.save(auditoria);
    }


    public List<AuditoriaORM> obtenerAuditorias() {
        return auditoriaJPA.findAll();
    }
    public void guardarAuditoria(String nombreProyecto, Long idProyecto, String estadoAnterior, String nuevoEstado, String usuarioCambio) {
        // Crear un nuevo objeto AuditoriaProgreso
        AuditoriaORM auditoria = new AuditoriaORM();
        auditoria.setNombreProyecto(nombreProyecto);
        auditoria.setIdProyecto(idProyecto);
        auditoria.setEstadoAnterior(estadoAnterior);
        auditoria.setNuevoEstado(nuevoEstado);
        auditoria.setFechaHoraCambio(LocalDateTime.now());
        auditoria.setUsuarioCambio(usuarioCambio);

        // Guardar el registro en la base de datos
        auditoriaJPA.save(auditoria);
    }

}