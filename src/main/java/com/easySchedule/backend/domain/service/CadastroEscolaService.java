package com.easySchedule.backend.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easySchedule.backend.domain.exception.notfound.EscolaNaoEncontradaException;
import com.easySchedule.backend.domain.model.Escola;
import com.easySchedule.backend.domain.repository.EscolaRepository;

@Service
public class CadastroEscolaService {
	
	@Autowired
	private EscolaRepository repository;
	
	public Escola buscarOuFalhar(Long id) {
		return repository.findById(id).orElseThrow(() ->
		new EscolaNaoEncontradaException(id));
	}
}
