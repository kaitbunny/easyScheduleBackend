package com.easySchedule.backend.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.easySchedule.backend.domain.model.Coordenador;
import com.easySchedule.backend.domain.service.CadastroCoordenadorService;
import com.easySchedule.backend.utils.paginatedresponse.PaginatedResponse;

@CrossOrigin("*")
@RestController
@RequestMapping("/coordenadores")
public class CoordenadorController {
	
	@Autowired
	private CadastroCoordenadorService coordenadorService;
	
	@GetMapping("/{id}")
	public Coordenador buscar(@PathVariable Long id) {
		return this.coordenadorService.buscarOuFalhar(id);
	}
	
	@GetMapping
	public PaginatedResponse<Coordenador> listar(
			@RequestParam(name = "page", defaultValue = "1") Integer page,
			@RequestParam(name = "sortProperty", defaultValue = "id") String sortProperty,
			@RequestParam(name = "sortDirection", defaultValue = "desc") String sortDirection,
			@RequestParam(name = "nome", required = false) String nome,
			@RequestParam(name = "email", required = false) String email,
			@RequestParam(name = "ativo", required = false) Boolean ativo,
			@RequestParam(name = "cursoId", required = false) Long cursoId
			) {
		
		return this.coordenadorService.listarPorPagina(page, sortProperty, sortDirection, nome, email, ativo, cursoId);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Coordenador adicionar(@RequestBody Coordenador coordenador) {
		return this.coordenadorService.salvar(coordenador);
	}
	
	@PutMapping("/{id}")
	public Coordenador atualizar(@PathVariable Long id, @RequestBody Coordenador coordenador) {
		return this.coordenadorService.atualizar(id, coordenador);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long id) {
		this.coordenadorService.excluir(id);
	}
}
