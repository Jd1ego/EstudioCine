package com.jcja.cine_back.bd.orm;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "Presupuesto")
@Entity
@Data
@NoArgsConstructor

public class PresupuestoORM {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cantidad")
    private int cantidad;

    @Column(name = "moneda")
    private String moneda;

    @OneToOne
    @JoinColumn(name = "proyecto_id")
    private ProyectoORM proyecto;

    public PresupuestoORM(int cantidad, String moneda, ProyectoORM proyecto) {
        this.cantidad = cantidad;
        this.moneda = moneda;
        this.proyecto = proyecto;
    }
}

