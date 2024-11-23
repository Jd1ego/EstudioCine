package com.jcja.cine_back.bd.jpa;


import com.jcja.cine_back.bd.orm.AuditoriaORM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditoriaJPA extends JpaRepository<AuditoriaORM, Long> {
}
