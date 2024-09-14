package com.jcja.cine_back.logica;

import com.jcja.cine_back.bd.jpa.GuionJPA;
import com.jcja.cine_back.bd.orm.GuionORM;
import com.jcja.cine_back.bd.orm.ProyectoORM;
import com.jcja.cine_back.controller.dto.GuionDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GuionService {

    private final GuionJPA guionJPA;

    public GuionORM crearGuion(GuionDTO guionDto, ProyectoORM proyecto) {
        if (guionDto != null) {
            return new GuionORM(
                    guionDto.autor(),
                    guionDto.fechaCreacion(),
                    proyecto  // Asignar el proyecto al guion
            );

        }
        return null;
    }
}
