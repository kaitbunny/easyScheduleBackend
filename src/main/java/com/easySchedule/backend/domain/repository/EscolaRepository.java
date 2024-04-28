package com.easySchedule.backend.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.easySchedule.backend.domain.model.Escola;

@Repository
public interface EscolaRepository extends JpaRepository<Escola, Long> {
	
	Page<Escola> findByNomeContaining(String nome, Pageable pageable);
	
}
