package com.easySchedule.backend.domain.service;

import com.easySchedule.backend.domain.model.Escola;
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
import com.easySchedule.backend.domain.model.Administrador;
import com.easySchedule.backend.domain.model.enums.TipoAdministrador;
import com.easySchedule.backend.domain.repository.AdministradorRepository;
import com.easySchedule.backend.domain.specification.AdministradorSpecification;
import com.easySchedule.backend.utils.paginatedresponse.PageableBuilder;
import com.easySchedule.backend.utils.paginatedresponse.PaginatedResponse;
import com.easySchedule.backend.utils.paginatedresponse.ResponseBuilder;

@Service
public class CadastroAdministradorService {

	@Autowired
	private AdministradorRepository repository;

	@Autowired
	private CadastroEscolaService escolaService;

	public Administrador buscarOuFalhar(Long id) throws EntidadeNaoEncontradaException {
		return repository.findById(id).orElseThrow(() ->
		new EntidadeNaoEncontradaException(new Administrador(), id));
	}
	
	public PaginatedResponse<Administrador> listarPorPagina(Integer page, String sortProperty, String sortDirection,
															String nome, String email, TipoAdministrador tipo, Boolean ativo, Long escolaId, Long escolaNome) {
        
        Pageable pageable = PageableBuilder.build(page, sortProperty, sortDirection);
        
        Specification<Administrador> spec = Specification.
                where(AdministradorSpecification.nomeContains(nome)).
                    and(AdministradorSpecification.emailContains(email)).
                    and(AdministradorSpecification.tipoEquals(tipo)).
                    and(AdministradorSpecification.isAtivo(ativo)).
                    and(AdministradorSpecification.escolaIdEquals(escolaId)).
					and(AdministradorSpecification.escolaIdEquals(escolaNome));
        
        Page<Administrador> result = repository.findAll(spec, pageable);
        
        return ResponseBuilder.build(result, page);
    }
	
	public Administrador salvar(Administrador administrador) throws PropertyValueException {
		if (administrador.getEscola().getId() != null) {
			Escola escola = escolaService.buscarOuFalhar(administrador.getEscola().getId());
			administrador.setEscola(escola);
		}
		try {
			return this.repository.save(administrador);
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
	
	public Administrador atualizar(Long id, Administrador administrador) {
		Administrador administradorAtual = this.buscarOuFalhar(id);
		
		BeanUtils.copyProperties(administrador, administradorAtual, "id");
		
		return this.salvar(administradorAtual);
	}
	
	public void excluir(Long id) throws EntidadeEmUsoException {
		try {
			this.repository.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(new Administrador(), id);
		}
	}
}
