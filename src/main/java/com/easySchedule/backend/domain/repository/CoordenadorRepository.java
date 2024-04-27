package com.easySchedule.backend.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.easySchedule.backend.domain.model.Coordenador;

@Repository
public interface CoordenadorRepository extends JpaRepository<Coordenador, Long> {

}
