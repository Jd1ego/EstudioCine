package com.jcja.cine_back.bd.orm;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Table(name = "Guion")
@Entity
@Data
@NoArgsConstructor

public class GuionORM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "autor")
    private String autor;

    @Column(name = "fechaCreacion")
    private LocalDate fechaCreacion;


    @OneToOne
    @JoinColumn(name = "proyecto_id", nullable = false)
    @JsonBackReference
    private ProyectoORM proyecto;

    public GuionORM(String autor, LocalDate fechaCreacion, ProyectoORM proyecto) {

        this.autor = autor;
        this.fechaCreacion = fechaCreacion;
        this.proyecto = proyecto;
    }
}
