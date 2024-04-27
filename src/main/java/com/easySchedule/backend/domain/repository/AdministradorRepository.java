package com.easySchedule.backend.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.easySchedule.backend.domain.model.Administrador;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Long> {

}
