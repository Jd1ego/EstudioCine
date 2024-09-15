package com.jcja.cine_back.bd.orm;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.web.JsonPath;

import java.util.List;

@Table(name = "Proyecto")
@Entity
@Data
@NoArgsConstructor

public class ProyectoORM {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String titulo;

    @OneToOne(mappedBy = "proyecto", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private GuionORM guion;

    @OneToOne(mappedBy = "proyecto", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private ProgresoORM progreso;

    @OneToOne(mappedBy = "proyecto", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private PresupuestoORM presupuesto;

    @ManyToMany
    @JoinTable(name = "proyecto_equipo",
            joinColumns = @JoinColumn(name = "proyecto_id"),
            inverseJoinColumns = @JoinColumn(name = "equipo_id"))
    private List<EquipoProduccionORM> equiposProduccion;

    public ProyectoORM(String titulo, GuionORM guion, ProgresoORM progreso, PresupuestoORM presupuesto, List<EquipoProduccionORM> equiposProduccion) {
        this.titulo = titulo;
        this.guion = guion;
        this.progreso = progreso;
        this.presupuesto = presupuesto;
        this.equiposProduccion = equiposProduccion;
    }

    public ProyectoORM(String titulo) {
        this.titulo = titulo;
    }

    public ProyectoORM(Long id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }
}
