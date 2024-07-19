package com.easySchedule.backend.domain.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.easySchedule.backend.domain.exception.CorpoDeRequisicaoInvalidoException;
import com.easySchedule.backend.domain.exception.emuso.EntidadeEmUsoException;
import com.easySchedule.backend.domain.exception.emuso.EscolaEmUsoException;
import com.easySchedule.backend.domain.exception.notfound.EntidadeNaoEncontradaException;
import com.easySchedule.backend.domain.exception.notfound.EscolaNaoEncontradaException;
import com.easySchedule.backend.domain.model.Escola;
import com.easySchedule.backend.domain.repository.EscolaRepository;
import com.easySchedule.backend.domain.specification.EscolaSpecification;
import com.easySchedule.backend.utils.paginatedresponse.PageableBuilder;
import com.easySchedule.backend.utils.paginatedresponse.PaginatedResponse;
import com.easySchedule.backend.utils.paginatedresponse.ResponseBuilder;

@Service
public class CadastroEscolaService {
	
	@Autowired
	private EscolaRepository repository;
	
	public Escola buscarOuFalhar(Long id) throws EntidadeNaoEncontradaException {
		return repository.findById(id).orElseThrow(() ->
		new EscolaNaoEncontradaException(id));
	}
	
	public PaginatedResponse<Escola> listarPorPagina(Integer page, String sortProperty, String sortDirection, String nome) {
        Pageable pageable = PageableBuilder.build(page, sortProperty, sortDirection);
        Specification<Escola> spec = Specification.where(EscolaSpecification.nomeContains(nome));
        Page<Escola> result = repository.findAll(spec, pageable);
        
        return ResponseBuilder.build(result, page);
    }
	
	public Escola salvar(Escola escola) throws CorpoDeRequisicaoInvalidoException {
		try {
			return this.repository.save(escola);						
		}
		catch(DataIntegrityViolationException e) {
			throw new CorpoDeRequisicaoInvalidoException(e.getMessage());
		}
	} 
	
	public Escola atualizar(Long id, Escola escola) {
		Escola escolaAtual = this.buscarOuFalhar(id);
		
		BeanUtils.copyProperties(escola, escolaAtual, "id");
		
		return this.salvar(escolaAtual);
	}
	
	public void excluir(Long id) throws EntidadeEmUsoException {
		try {
			this.repository.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new EscolaEmUsoException(id);
		}
	}
}
