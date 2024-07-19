package com.easySchedule.backend.domain.service;

import org.hibernate.PropertyValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

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

	public Administrador buscarOuFalhar(Long id) throws EntidadeNaoEncontradaException {
		return repository.findById(id).orElseThrow(() ->
		new EntidadeNaoEncontradaException(new Administrador(), id));
	}
	
	public PaginatedResponse<Administrador> listarPorPagina(Integer page, String sortProperty, String sortDirection,
            String nome, String email, TipoAdministrador tipo, Boolean ativo, Long escolaId) {
        
        Pageable pageable = PageableBuilder.build(page, sortProperty, sortDirection);
        
        Specification<Administrador> spec = Specification.
                where(AdministradorSpecification.nomeContains(nome)).
                    and(AdministradorSpecification.emailContains(email)).
                    and(AdministradorSpecification.tipoEquals(tipo)).
                    and(AdministradorSpecification.isAtivo(ativo)).
                    and(AdministradorSpecification.escolaIdEquals(escolaId));
        
        Page<Administrador> result = repository.findAll(spec, pageable);
        
        return ResponseBuilder.build(result, page);
    }
	
	public Administrador salvar(Administrador administrador) throws PropertyValueException {
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
	
}
