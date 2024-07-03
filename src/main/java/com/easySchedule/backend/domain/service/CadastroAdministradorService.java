package com.easySchedule.backend.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

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
}
