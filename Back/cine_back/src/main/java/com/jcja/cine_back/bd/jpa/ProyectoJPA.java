package com.jcja.cine_back.bd.jpa;

import com.jcja.cine_back.bd.orm.ProyectoORM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProyectoJPA extends JpaRepository<ProyectoORM, Long> {
}

