package com.jcja.cine_back.logica;

import com.jcja.cine_back.bd.jpa.EquipoProduccionJPA;
import com.jcja.cine_back.bd.orm.EquipoProduccionORM;
import com.jcja.cine_back.bd.orm.ProyectoORM;
import com.jcja.cine_back.controller.dto.EquipoProduccionDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EquipoProduccionService {

    private EquipoProduccionJPA equipoProduccionJPA;
    private ProyectoService proyectoService;

    public EquipoProduccionORM crearEquipoProduccion(EquipoProduccionDTO equipoProduccionDTO) {

        return new EquipoProduccionORM(
                equipoProduccionDTO.nombre(),
                equipoProduccionDTO.rol(),
                equipoProduccionDTO.contacto()
        )
                ;
    }
    public boolean guardarEquipoProduccion(EquipoProduccionORM equipoProduccionORM) {
        equipoProduccionJPA.save(equipoProduccionORM);
        return true;
    }

    public boolean crearYGuardarEquipoProduccion(EquipoProduccionDTO equipoProduccionDTO) {
        crearEquipoProduccion(equipoProduccionDTO);
        guardarEquipoProduccion(crearEquipoProduccion(equipoProduccionDTO));
        return true;
    }
    public List<EquipoProduccionORM> obtenerTodosEquipos() {
        return equipoProduccionJPA.findAll();
    }



    public List<EquipoProduccionDTO> obtenerEquiposProyectos() {
        return equipoProduccionJPA.findAll().stream()
                .map(equipo -> new EquipoProduccionDTO(
                        equipo.getId(),
                        equipo.getNombre(),
                        equipo.getRol(),
                        equipo.getContacto(),
                        proyectoService.obtenerIdsProyectos(equipo.getProyectos()),   // IDs de los proyectos
                        proyectoService.obtenerListaTitulosProyectos(equipo.getProyectos()) // Títulos de los proyectos
                ))
                .collect(Collectors.toList());
    }
}
