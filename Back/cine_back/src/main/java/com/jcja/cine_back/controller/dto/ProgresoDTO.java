package com.jcja.cine_back.controller.dto;

import com.jcja.cine_back.bd.orm.ProyectoORM;

import java.time.LocalDate;

public record ProgresoDTO(String etapa, double porcentajeCompletado, LocalDate fechaActualizacion, ProyectoORM proyecto) {
}
