package com.easySchedule.backend.domain.service;

import com.easySchedule.backend.domain.exception.EntidadeEmUsoException;
import com.easySchedule.backend.domain.exception.EntidadeNaoEncontradaException;
import com.easySchedule.backend.domain.model.Coordenador;
import com.easySchedule.backend.domain.model.Escola;
import com.easySchedule.backend.domain.model.Sala;
import com.easySchedule.backend.domain.specification.SalaSpecification;
import com.easySchedule.backend.utils.paginatedresponse.PageableBuilder;
import com.easySchedule.backend.utils.paginatedresponse.PaginatedResponse;
import com.easySchedule.backend.utils.paginatedresponse.ResponseBuilder;
import org.hibernate.PropertyValueException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.easySchedule.backend.domain.repository.SalaRepository;

@Service
public class CadastroSalaService {

	@Autowired
	private SalaRepository repository;

	@Autowired
	private CadastroEscolaService escolaService;

	public Sala buscarOuFalhar(Long id) throws EntidadeNaoEncontradaException {
		return repository.findById(id).orElseThrow(() ->
				new EntidadeNaoEncontradaException(new Sala(), id));
	}

	public PaginatedResponse<Sala> listarPorPagina(Integer page, String sortProperty, String sortDirection,
												   String numero, String andar, String predio, Long escolaId) {
		Pageable pageable = PageableBuilder.build(page, sortProperty, sortDirection);
		Specification<Sala> spec = Specification.
				where(SalaSpecification.numeroContains(numero)).
				and(SalaSpecification.andarContains(andar)).
				and(SalaSpecification.predioContains(predio)).
				and(SalaSpecification.escolaIdEquals(escolaId));

		Page<Sala> result = repository.findAll(spec, pageable);

		return ResponseBuilder.build(result, page);
	}

	public Sala salvar (Sala sala) throws PropertyValueException {
		if (sala.getEscola().getId() != null) {
			Escola escola = escolaService.buscarOuFalhar(sala.getEscola().getId());
			sala.setEscola(escola);
		}
		try {
			return this.repository.save(sala);
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

	public Sala atualizar(Long id, Sala sala) {
		Sala salaAtual = this.buscarOuFalhar(id);

		BeanUtils.copyProperties(sala, salaAtual, "id");
		return this.salvar(salaAtual);
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
