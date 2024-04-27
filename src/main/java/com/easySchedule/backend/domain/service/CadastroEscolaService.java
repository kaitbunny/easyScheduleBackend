package com.easySchedule.backend.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easySchedule.backend.domain.repository.EscolaRepository;

@Service
public class CadastroEscolaService {
	
	@Autowired
	private EscolaRepository repository;
}
