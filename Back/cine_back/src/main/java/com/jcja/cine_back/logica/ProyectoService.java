package com.jcja.cine_back.logica;

import com.jcja.cine_back.bd.jpa.EquipoProduccionJPA;
import com.jcja.cine_back.bd.jpa.ProyectoJPA;
import com.jcja.cine_back.bd.orm.EquipoProduccionORM;
import com.jcja.cine_back.bd.orm.GuionORM;
import com.jcja.cine_back.bd.orm.ProyectoORM;
import com.jcja.cine_back.controller.dto.ProyectoDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProyectoService {

    ProyectoJPA proyectoJPA;
    GuionService guionService;
    EquipoProduccionJPA equipoProduccionJPA;


    public boolean adjuntarGuion(GuionORM guionORM,ProyectoORM proyectoORM) {

        if (guionORM != null) {
            proyectoORM.setGuion(guionORM);
            return true;
        }
        return false;
    }
    public boolean asignarEquiposProduccion(ProyectoDTO proyectoDTO,ProyectoORM proyectoORM) {
        List<EquipoProduccionORM> equiposProduccion =
                equipoProduccionJPA.findAllById(proyectoDTO.equipoIds());
        proyectoORM.setEquiposProduccion(equiposProduccion);

        return true;
    }

    public  boolean guardarProyecto(ProyectoDTO proyectoDTO) {

        ProyectoORM proyectoORM = new ProyectoORM();
        proyectoORM.setTitulo(proyectoDTO.titulo());
        asignarEquiposProduccion(proyectoDTO,proyectoORM);
        GuionORM guionORM = guionService.crearGuion(proyectoDTO.guionDTO(),proyectoORM);
        adjuntarGuion(guionORM,proyectoORM);
        proyectoJPA.save(proyectoORM);

        return true;
    }
}
