package com.easySchedule.backend.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.easySchedule.backend.domain.model.Escola;
import com.easySchedule.backend.domain.service.CadastroEscolaService;
import com.easySchedule.backend.utils.paginatedresponse.PaginatedResponse;

@CrossOrigin("*")
@RestController
@RequestMapping("/escolas")
public class EscolaController {

	@Autowired
	private CadastroEscolaService escolaService;
	
	@GetMapping
	public PaginatedResponse<Escola> listar(
			@RequestParam("page") Integer page,
			@RequestParam("sortProperty") String sortProperty,
			@RequestParam("sortDirection") String sortDirection) {
		
		return this.escolaService.listarPorPagina(page, sortProperty, sortDirection);
	}
	
	@GetMapping("/{id}")
	public Escola buscar(@PathVariable Long id) {
		return this.escolaService.buscarOuFalhar(id);
	}
	
	@GetMapping("/por-nome")
	public PaginatedResponse<Escola> buscarPorNome(
			@RequestParam("nome") String nome,
			@RequestParam("page") Integer page,
			@RequestParam("sortProperty") String sortProperty,
			@RequestParam("sortDirection") String sortDirection) {
		
		return this.escolaService.buscarPorNome(nome, page, sortProperty, sortDirection);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Escola adicionar(@RequestBody Escola escola) {
		return this.escolaService.salvar(escola);
	}
	
	@PutMapping("/{id}")
	public Escola atualizar(@PathVariable Long id, @RequestBody Escola escola) {
		return this.escolaService.atualizar(id, escola);
	}
}
