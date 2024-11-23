package com.jcja.cine_back.bd.jpa;

import com.jcja.cine_back.bd.orm.ProgresoORM;
import com.jcja.cine_back.bd.orm.ProyectoORM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgresoJPA extends JpaRepository<ProgresoORM, Long> {
    ProgresoORM findByProyecto(ProyectoORM proyecto);
}