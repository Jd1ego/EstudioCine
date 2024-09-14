package com.jcja.cine_back.bd.orm;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "EquipoProduccion")
@Entity
@Data
@NoArgsConstructor

public class EquipoProduccionORM {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "rol")
    private String rol;

    @Column(name = "contacto")
    private String contacto;

    @ManyToMany(mappedBy = "equiposProduccion")
    private List<ProyectoORM> proyectos;

    public EquipoProduccionORM(String nombre, String rol, String contacto, List<ProyectoORM> proyectos) {
        this.nombre = nombre;
        this.rol = rol;
        this.contacto = contacto;
        this.proyectos = proyectos;
    }

    public EquipoProduccionORM(String nombre, String rol, String contacto) {
        this.nombre = nombre;
        this.rol = rol;
        this.contacto = contacto;
    }
}

