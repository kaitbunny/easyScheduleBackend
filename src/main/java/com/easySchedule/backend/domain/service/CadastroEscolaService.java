package com.easySchedule.backend.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.easySchedule.backend.domain.exception.notfound.EscolaNaoEncontradaException;
import com.easySchedule.backend.domain.model.Escola;
import com.easySchedule.backend.domain.repository.EscolaRepository;
import com.easySchedule.backend.utils.paginatedresponse.PageableBuilder;
import com.easySchedule.backend.utils.paginatedresponse.PaginatedResponse;
import com.easySchedule.backend.utils.paginatedresponse.ResponseBuilder;

@Service
public class CadastroEscolaService {
	
	@Autowired
	private EscolaRepository repository;
	
	public Escola buscarOuFalhar(Long id) {
		return repository.findById(id).orElseThrow(() ->
		new EscolaNaoEncontradaException(id));
	}
	
	public PaginatedResponse<Escola> listarPorPagina(Integer page, String sortProperty, String sortDirection) {
		Pageable pageable = PageableBuilder.build(page, sortProperty, sortDirection);
		Page<Escola> result = repository.findAll(pageable);
		
		return ResponseBuilder.build(result, page);
	}

	public PaginatedResponse<Escola> buscarPorNome(String nome, Integer page, String sortProperty, String sortDirection) {
		Pageable pageable = PageableBuilder.build(page, sortProperty, sortDirection);
		Page<Escola> result = repository.findByNomeContaining(nome, pageable);
		
		return ResponseBuilder.build(result, page);
	}
}
