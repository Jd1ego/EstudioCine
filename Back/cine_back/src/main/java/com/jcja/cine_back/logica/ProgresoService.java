package com.jcja.cine_back.logica;


import com.jcja.cine_back.bd.jpa.ProgresoJPA;
import com.jcja.cine_back.bd.orm.ProgresoORM;
import com.jcja.cine_back.bd.orm.ProyectoORM;
import com.jcja.cine_back.controller.dto.ProgresoDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProgresoService {

    private ProgresoJPA progresoJPA;

    public ProgresoORM crearProgreso(ProgresoDTO progresoDTO, ProyectoORM proyecto) {
        if (progresoDTO!=null){
            return new ProgresoORM(
                    progresoDTO.etapa(),
                    progresoDTO.porcentajeCompletado(),
                    progresoDTO.fechaActualizacion(),
                    proyecto
            );
        }
        return null;

    }
    public Double obtenerProgreso(ProgresoORM progresoORM){

        return progresoORM.getPorcentajeCompletado();
    }
}
