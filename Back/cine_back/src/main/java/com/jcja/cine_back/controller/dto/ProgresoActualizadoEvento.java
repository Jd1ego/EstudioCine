package com.jcja.cine_back.controller.dto;

import java.time.LocalDateTime;

public class ProgresoActualizadoEvento {
    private String nombreProyecto;
    private Long idProyecto;
    private String estadoAnterior;
    private String nuevoEstado;
    private LocalDateTime fechaHoraCambio;
    private String usuarioCambio;

    // Constructor
    public ProgresoActualizadoEvento(String nombreProyecto, Long idProyecto, String estadoAnterior, String nuevoEstado, LocalDateTime fechaHoraCambio, String usuarioCambio) {
        this.nombreProyecto = nombreProyecto;
        this.idProyecto = idProyecto;
        this.estadoAnterior = estadoAnterior;
        this.nuevoEstado = nuevoEstado;
        this.fechaHoraCambio = fechaHoraCambio;
        this.usuarioCambio = usuarioCambio;
    }

    // Getters y setters
    public Long getIdProyecto() {
        return idProyecto;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
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

    // Sobrescribir el método toString para una representación más legible
    @Override
    public String toString() {
        return "ProgresoActualizadoEvento{" +
                "nombreProyecto='" + nombreProyecto + '\'' +
                ", idProyecto=" + idProyecto +
                ", estadoAnterior='" + estadoAnterior + '\'' +
                ", nuevoEstado='" + nuevoEstado + '\'' +
                ", fechaHoraCambio=" + fechaHoraCambio +
                ", usuarioCambio='" + usuarioCambio + '\'' +
                '}';
    }
}
