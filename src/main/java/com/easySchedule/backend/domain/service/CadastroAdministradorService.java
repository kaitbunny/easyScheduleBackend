package com.easySchedule.backend.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easySchedule.backend.domain.repository.AdministradorRepository;

@Service
public class CadastroAdministradorService {

	@Autowired
	private AdministradorRepository repository;
	
}