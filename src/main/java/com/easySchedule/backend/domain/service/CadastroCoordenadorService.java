package com.easySchedule.backend.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.easySchedule.backend.domain.exception.EntidadeNaoEncontradaException;
import com.easySchedule.backend.domain.model.Coordenador;
import com.easySchedule.backend.domain.repository.CoordenadorRepository;
import com.easySchedule.backend.domain.specification.CoordenadorSpecification;
import com.easySchedule.backend.utils.paginatedresponse.PageableBuilder;
import com.easySchedule.backend.utils.paginatedresponse.PaginatedResponse;
import com.easySchedule.backend.utils.paginatedresponse.ResponseBuilder;

@Service
public class CadastroCoordenadorService {
	
	@Autowired
	private CoordenadorRepository repository;
	
	public PaginatedResponse<Coordenador> listarPorPagina(Integer page, String sortProperty, String sortDirection,
			String nome, String email, Boolean ativo, Long cursoId) {
		
		Pageable pageable = PageableBuilder.build(page, sortProperty, sortDirection);
		
		Specification<Coordenador> spec = Specification.
				where(CoordenadorSpecification.nomeContains(nome)).
					and(CoordenadorSpecification.emailContains(email)).
					and(CoordenadorSpecification.isAtivo(ativo)).
					and(CoordenadorSpecification.cursoIdEquals(cursoId));
		
		Page<Coordenador> result = repository.findAll(spec, pageable);
		
		return ResponseBuilder.build(result, page);
	}
	
	public Coordenador buscarOuFalhar(Long id) throws EntidadeNaoEncontradaException {
		return repository.findById(id).orElseThrow(() -> 
				new EntidadeNaoEncontradaException(new Coordenador(), id));
	}
}
