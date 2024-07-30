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

import com.easySchedule.backend.domain.model.Curso;
import com.easySchedule.backend.domain.model.enums.Periodo;
import com.easySchedule.backend.domain.service.CadastroCursoService;
import com.easySchedule.backend.utils.paginatedresponse.PaginatedResponse;

@CrossOrigin("*")
@RestController
@RequestMapping("/cursos")
public class CursoController {

	@Autowired
	private CadastroCursoService cursoService;
	
	@GetMapping("/{id}")
	public Curso buscar(@PathVariable Long id) {
		return this.cursoService.buscarOuFalhar(id);
	}
	
	@GetMapping
	public PaginatedResponse<Curso> listar(
			@RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "sortProperty", defaultValue = "id") String sortProperty,
            @RequestParam(value = "sortDirection", defaultValue = "desc") String sortDirection,
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "periodo", required = false) Periodo periodo,
            @RequestParam(value = "escolaId", required = false) Long escolaId) {
		
		return this.cursoService.listarPorPagina(page, sortProperty, sortDirection, nome, periodo, escolaId);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Curso adicionar(@RequestBody Curso curso) {
		return this.cursoService.salvar(curso);
	}
	
	@PutMapping("/{id}")
	public Curso atualizar(@PathVariable Long id, @RequestBody Curso curso) {
		return this.cursoService.atualizar(id, curso);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long id) {
		this.cursoService.excluir(id);
	}
}
