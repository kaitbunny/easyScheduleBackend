package com.easySchedule.backend.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.easySchedule.backend.domain.model.Turma;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {

}
