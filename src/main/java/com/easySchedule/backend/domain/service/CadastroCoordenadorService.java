package com.easySchedule.backend.domain.service;

import com.easySchedule.backend.domain.model.Curso;
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
	@Autowired
	private CadastroCursoService cursoService;
	
	public PaginatedResponse<Coordenador> listarPorPagina(Integer page, String sortProperty, String sortDirection,
			String nome, String email, Boolean ativo, Long cursoId, Long cursoNome) {
		
		Pageable pageable = PageableBuilder.build(page, sortProperty, sortDirection);
		
		Specification<Coordenador> spec = Specification.
				where(CoordenadorSpecification.nomeContains(nome)).
					and(CoordenadorSpecification.emailContains(email)).
					and(CoordenadorSpecification.isAtivo(ativo)).
					and(CoordenadorSpecification.cursoIdEquals(cursoId)).
					and(CoordenadorSpecification.cursoIdEquals(cursoNome));
		
		Page<Coordenador> result = repository.findAll(spec, pageable);
		
		return ResponseBuilder.build(result, page);
	}
	
	public Coordenador buscarOuFalhar(Long id) throws EntidadeNaoEncontradaException {
		return repository.findById(id).orElseThrow(() -> 
				new EntidadeNaoEncontradaException(new Coordenador(), id));
	}
	
	public Coordenador salvar(Coordenador coordenador) throws PropertyValueException {
		if (coordenador.getCurso().getId() != null) {
			Curso curso = cursoService.buscarOuFalhar(coordenador.getCurso().getId());
			coordenador.setCurso(curso);
		}
		try {
			return this.repository.save(coordenador);
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
	
	public Coordenador atualizar(Long id, Coordenador coordenador) {
		Coordenador coordenadorAtual = this.buscarOuFalhar(id);
		
		BeanUtils.copyProperties(coordenador, coordenadorAtual, "id");
		return this.salvar(coordenadorAtual);
	}
	
	public void excluir(Long id) throws EntidadeEmUsoException {
		try {
			this.repository.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(new Coordenador(), id);
		}
	}
}
