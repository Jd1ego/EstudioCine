package com.jcja.cine_back.controller.dto;

import java.util.List;

public record EquipoProduccionDTO(String nombre, String rol, String contacto, List<Long> proyectoIds) {
}

