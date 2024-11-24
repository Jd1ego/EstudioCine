package com.jcja.cine_back.bd.jpa;

import com.jcja.cine_back.bd.orm.ProyectoEquipoORM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProyectoEquipoJPA extends JpaRepository<ProyectoEquipoORM, Long> {
    List<ProyectoEquipoORM> findByProyectoId(Long proyectoId);
}