package com.jcja.cine_back.bd.orm;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "proyecto_equipo")
@Entity
@Data
@NoArgsConstructor
public class ProyectoEquipoORM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "proyecto_id", nullable = false)
    private ProyectoORM proyecto;

    @ManyToOne
    @JoinColumn(name = "equipo_id", nullable = false)
    private EquipoProduccionORM equipoProduccion;


}
