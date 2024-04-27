package com.easySchedule.backend.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easySchedule.backend.domain.repository.CursoRepository;

@Service
public class CadastroCursoService {
	
	@Autowired
	private CursoRepository repository;
}
