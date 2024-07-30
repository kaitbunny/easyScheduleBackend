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
import com.easySchedule.backend.domain.model.Curso;
import com.easySchedule.backend.domain.model.enums.Periodo;
import com.easySchedule.backend.domain.repository.CursoRepository;
import com.easySchedule.backend.domain.specification.CursoSpecification;
import com.easySchedule.backend.utils.paginatedresponse.PageableBuilder;
import com.easySchedule.backend.utils.paginatedresponse.PaginatedResponse;
import com.easySchedule.backend.utils.paginatedresponse.ResponseBuilder;

@Service
public class CadastroCursoService {
	
	@Autowired
	private CursoRepository repository;
	
	public Curso buscarOuFalhar(Long id) throws EntidadeNaoEncontradaException {
		return this.repository.findById(id).orElseThrow(() ->
		new EntidadeNaoEncontradaException(new Curso(), id));
	}
	
	public PaginatedResponse<Curso> listarPorPagina(Integer page, String sortProperty, String sortDirection,
			String nome, Periodo periodo, Long escolaId) {
		
		Pageable pageable = PageableBuilder.build(page, sortProperty, sortDirection);
		
		Specification<Curso> spec = Specification.
				where(CursoSpecification.nomeContains(nome)).
					and(CursoSpecification.periodoContains(periodo)).
					and(CursoSpecification.escolaIdEquals(escolaId));
		
		Page<Curso> result = repository.findAll(spec, pageable);
		
		return ResponseBuilder.build(result, page);
	}
	
	public Curso salvar(Curso curso) throws PropertyValueException {
		try {
			return this.repository.save(curso);
		}
		catch(DataIntegrityViolationException e) {
			if(e.getCause() instanceof PropertyValueException) {
				throw (PropertyValueException) e.getCause();
			}
			else {
				throw e;
			}
		}
	}
	
	public Curso atualizar(Long id, Curso curso) {
		Curso cursoAtual = this.buscarOuFalhar(id);
		
		BeanUtils.copyProperties(curso, cursoAtual, "id");
		
		return this.salvar(cursoAtual);
	}
	
	public void excluir(Long id) throws EntidadeEmUsoException {
		try {
			this.repository.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(new Curso(), id);
		}
	}
}
