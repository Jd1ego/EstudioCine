package com.jcja.cine_back.controller.dto;

import java.util.List;

public record EquipoProduccionDTO(Long id,String nombre, String rol, String contacto, List<Long> proyectoIds,List<String> EquiposTitulos) {
}

