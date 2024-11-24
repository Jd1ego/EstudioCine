package com.jcja.cine_back.controller.dto;

import java.time.LocalDateTime;

import java.util.List;

public class ProgresoActualizadoEvento {
    private String nombreProyecto;
    private Long idProyecto;
    private String estadoAnterior;
    private String nuevoEstado;
    private LocalDateTime fechaHoraCambio;
    private String usuarioCambio;
    private List<String> correosEquipo; // Nuevo campo para los correos electrónicos

    // Constructor
    public ProgresoActualizadoEvento(String nombreProyecto, Long idProyecto, String estadoAnterior,
                                     String nuevoEstado, LocalDateTime fechaHoraCambio,
                                     String usuarioCambio, List<String> correosEquipo) {
        this.nombreProyecto = nombreProyecto;
        this.idProyecto = idProyecto;
        this.estadoAnterior = estadoAnterior;
        this.nuevoEstado = nuevoEstado;
        this.fechaHoraCambio = fechaHoraCambio;
        this.usuarioCambio = usuarioCambio;
        this.correosEquipo = correosEquipo; // Asignar los correos recibidos
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

    public List<String> getCorreosEquipo() {
        return correosEquipo;
    }

    public void setCorreosEquipo(List<String> correosEquipo) {
        this.correosEquipo = correosEquipo;
    }


    @Override
    public String toString() {
        return "ProgresoActualizadoEvento{" +
                "nombreProyecto='" + nombreProyecto + '\'' +
                ", idProyecto=" + idProyecto +
                ", estadoAnterior='" + estadoAnterior + '\'' +
                ", nuevoEstado='" + nuevoEstado + '\'' +
                ", fechaHoraCambio=" + fechaHoraCambio +
                ", usuarioCambio='" + usuarioCambio + '\'' +
                ", correosEquipo=" + correosEquipo +
                '}';
    }
}
