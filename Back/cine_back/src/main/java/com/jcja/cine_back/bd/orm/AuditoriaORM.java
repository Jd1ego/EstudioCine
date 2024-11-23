package com.jcja.cine_back.bd.orm;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "auditoria_progreso")
public class AuditoriaORM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreProyecto;
    private Long idProyecto;
    private String estadoAnterior;
    private String nuevoEstado;
    private LocalDateTime fechaHoraCambio;
    private String usuarioCambio;

    // Constructor
    public AuditoriaORM(String nombreProyecto, Long idProyecto, String estadoAnterior, String nuevoEstado, LocalDateTime fechaHoraCambio, String usuarioCambio) {
        this.nombreProyecto = nombreProyecto;
        this.idProyecto = idProyecto;
        this.estadoAnterior = estadoAnterior;
        this.nuevoEstado = nuevoEstado;
        this.fechaHoraCambio = fechaHoraCambio;
        this.usuarioCambio = usuarioCambio;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public Long getIdProyecto() {
        return idProyecto;
    }

    public String getEstadoAnterior() {
        return estadoAnterior;
    }

    public String getNuevoEstado() {
        return nuevoEstado;
    }

    public LocalDateTime getFechaHoraCambio() {
        return fechaHoraCambio;
    }

    public String getUsuarioCambio() {
        return usuarioCambio;
    }
}
