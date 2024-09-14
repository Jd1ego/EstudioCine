package com.jcja.cine_back.controller.dto;

import com.jcja.cine_back.bd.orm.ProyectoORM;

public record PresupuestoDTO(int cantidad, String moneda, ProyectoORM proyecto) {
}
