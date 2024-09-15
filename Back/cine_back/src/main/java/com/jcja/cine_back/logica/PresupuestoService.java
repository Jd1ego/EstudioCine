package com.jcja.cine_back.logica;

import com.jcja.cine_back.bd.jpa.PresupuestoJPA;
import com.jcja.cine_back.bd.orm.PresupuestoORM;
import com.jcja.cine_back.bd.orm.ProyectoORM;
import com.jcja.cine_back.controller.dto.PresupuestoDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PresupuestoService {

    private PresupuestoJPA presupuestoJPA;

    public PresupuestoORM crearPresupuesto(PresupuestoDTO presupuestoDTO, ProyectoORM proyecto) {
        if (presupuestoDTO != null) {
            return new PresupuestoORM(
                    presupuestoDTO.cantidad(),
                    presupuestoDTO.moneda(),
                    proyecto
            );

        }
        return null;
    }
}

