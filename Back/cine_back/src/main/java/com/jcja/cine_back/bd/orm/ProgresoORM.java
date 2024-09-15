package com.jcja.cine_back.bd.orm;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Table(name = "Progreso")
@Entity
@Data
@NoArgsConstructor
public class ProgresoORM {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "etapa")
    private String etapa;

    @Column (name = "porcentaje_completado")
    private double porcentajeCompletado;

    @Column (name = "fecha_actualizacion")
    private LocalDate fechaActualizacion;

    @OneToOne
    @JoinColumn(name = "proyecto_id",nullable = false)
    @JsonBackReference
    private ProyectoORM proyecto;

    public ProgresoORM(String etapa, double porcentajeCompletado, LocalDate fechaActualizacion, ProyectoORM proyecto) {
        this.etapa = etapa;
        this.porcentajeCompletado = porcentajeCompletado;
        this.fechaActualizacion = fechaActualizacion;
        this.proyecto = proyecto;
    }
}

