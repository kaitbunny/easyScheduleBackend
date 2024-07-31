package com.easySchedule.backend.domain.service;

import org.hibernate.PropertyValueException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.easySchedule.backend.domain.exception.EntidadeEmUsoException;
import com.easySchedule.backend.domain.exception.EntidadeNaoEncontradaException;
import com.easySchedule.backend.domain.model.Disciplina;
import com.easySchedule.backend.domain.repository.DisciplinaRepository;
import com.easySchedule.backend.domain.specification.DisciplinaSpecification;
import com.easySchedule.backend.utils.paginatedresponse.PageableBuilder;
import com.easySchedule.backend.utils.paginatedresponse.PaginatedResponse;
import com.easySchedule.backend.utils.paginatedresponse.ResponseBuilder;

@Service
public class CadastroDisciplinaService {

	@Autowired
	private DisciplinaRepository repository;
	
	public Disciplina buscarOuFalhar(Long id) {
		return this.repository.findById(id).orElseThrow(() ->
		new EntidadeNaoEncontradaException(new Disciplina(), id));
	}
	
	public PaginatedResponse<Disciplina> listarPorPagina(Integer page, String sortProperty,
			String sortDirection, String nome, Boolean ativo, Long cursoId) {
		
		Pageable pageable = PageableBuilder.build(page, sortProperty, sortDirection);
		
		Specification<Disciplina> spec = Specification.
				where(DisciplinaSpecification.nomeContains(nome)).
					and(DisciplinaSpecification.isAtivo(ativo)).
					and(DisciplinaSpecification.cursoIdEquals(cursoId));
		
		Page<Disciplina> result = this.repository.findAll(spec, pageable);
		
		return ResponseBuilder.build(result, page);
	}
	
	public Disciplina salvar(Disciplina disciplina) throws PropertyValueException {
		try {
			return this.repository.save(disciplina);
		}
		catch(DataIntegrityViolationException e) {
			if(e.getCause() instanceof PropertyValueException) {
				throw (PropertyValueException) e.getCause();
			}
			throw e;
		}
	}
	
	public Disciplina atualizar(Long id, Disciplina disciplina) {
		Disciplina disciplinaAtual = this.buscarOuFalhar(id);
		
		BeanUtils.copyProperties(disciplina, disciplinaAtual, "id");
		
		return this.salvar(disciplinaAtual);
	}
	
	public void excluir(Long id) throws EntidadeEmUsoException {
		try {
			this.repository.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(new Disciplina(), id);
		}
	}
}
