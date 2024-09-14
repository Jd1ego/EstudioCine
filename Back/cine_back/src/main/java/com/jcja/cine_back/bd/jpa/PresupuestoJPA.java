package com.jcja.cine_back.bd.jpa;

import com.jcja.cine_back.bd.orm.PresupuestoORM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PresupuestoJPA extends JpaRepository<PresupuestoORM, Long> {
}
