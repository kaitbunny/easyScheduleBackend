package com.easySchedule.backend.api.controller;

import com.easySchedule.backend.api.dto.DisciplinaDTO;
import com.easySchedule.backend.api.mapper.DisciplinaMapper;
import com.easySchedule.backend.domain.model.Curso;
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

import com.easySchedule.backend.domain.model.Disciplina;
import com.easySchedule.backend.domain.service.CadastroDisciplinaService;
import com.easySchedule.backend.utils.paginatedresponse.PaginatedResponse;

@CrossOrigin("*")
@RestController
@RequestMapping("/disciplinas")
public class DisciplinaController {
	
	@Autowired
	private CadastroDisciplinaService disciplinaService;
	@Autowired
	private DisciplinaMapper disciplinaMapper;
	
	@GetMapping("/{id}")
	public DisciplinaDTO buscar(@PathVariable Long id) {
		var disciplina = this.disciplinaService.buscarOuFalhar(id);
		return disciplinaMapper.toDTO(disciplina);
	}
	
	@GetMapping
	public PaginatedResponse<DisciplinaDTO> listar (
			@RequestParam(name = "page", defaultValue = "1") Integer page,
			@RequestParam(name = "sortProperty", defaultValue = "id") String sortProperty,
			@RequestParam(name = "sortDirection", defaultValue = "desc") String sortDirection,
			@RequestParam(name = "nome", required = false) String nome,
			@RequestParam(name = "ativo", required = false) Boolean ativo,
			@RequestParam(name = "cursoId", required = false) Long cursoId) {

		PaginatedResponse<Disciplina> disciplinas = this.disciplinaService.listarPorPagina(
				page, sortProperty, sortDirection, nome, ativo, cursoId);

		PaginatedResponse<DisciplinaDTO> disciplinaDTOs = disciplinas.map(disciplina ->
				disciplinaMapper.toDTO(disciplina)
		);

		return disciplinaDTOs;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public DisciplinaDTO adicionar(@RequestBody DisciplinaDTO disciplinaDTO) {
		var curso = disciplinaMapper.toEntity(disciplinaDTO);
		var savedDisciplina = this.disciplinaService.salvar(curso);
		return disciplinaMapper.toDTO(savedDisciplina);
	}
	
	@PutMapping("/{id}")
	public DisciplinaDTO atualizar(@PathVariable Long id, @RequestBody DisciplinaDTO disciplinaDTO) {
		Disciplina disciplina = disciplinaMapper.toEntity(disciplinaDTO);
		return disciplinaMapper.toDTO(disciplinaService.atualizar(id, disciplina));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long id) {
		this.disciplinaService.excluir(id);
	}
}
