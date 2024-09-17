package com.jcja.cine_back.controller.dto;

import java.util.List;

public record ProyectoDTO(String titulo, List<Long> equipoIds, GuionDTO guionDTO,PresupuestoDTO presupuestoDTO,ProgresoDTO progresoDTO,String Autor, Integer Presupuesto, Double Progreso) {
}
