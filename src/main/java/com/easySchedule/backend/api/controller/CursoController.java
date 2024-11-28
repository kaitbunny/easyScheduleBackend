package com.easySchedule.backend.api.controller;

import com.easySchedule.backend.api.dto.CursoDTO;
import com.easySchedule.backend.api.mapper.CursoMapper;
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
	@Autowired
	private CursoMapper cursoMapper;

	@GetMapping("/{id}")
	public CursoDTO buscar(@PathVariable Long id) {
		var curso = this.cursoService.buscarOuFalhar(id);
		return cursoMapper.toDTO(curso);
	}
	
	@GetMapping
	public PaginatedResponse<CursoDTO> listar(
			@RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "sortProperty", defaultValue = "id") String sortProperty,
            @RequestParam(value = "sortDirection", defaultValue = "desc") String sortDirection,
			@RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "periodo", required = false) Periodo periodo,
            @RequestParam(value = "escolaId", required = false) Long escolaId
	) {
		PaginatedResponse<Curso> cursos = this.cursoService.listarPorPagina(
				page, sortProperty, sortDirection, nome, periodo, escolaId);

		PaginatedResponse<CursoDTO> cursoDTOs = cursos.map(curso ->
				cursoMapper.toDTO(curso)
		);

		return cursoDTOs;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CursoDTO adicionar(@RequestBody CursoDTO cursoDTO) {
		var curso = cursoMapper.toEntity(cursoDTO);
		var savedCurso = this.cursoService.salvar(curso);
		return cursoMapper.toDTO(savedCurso);
	}
	
	@PutMapping("/{id}")
	public CursoDTO atualizar(@PathVariable Long id, @RequestBody CursoDTO cursoDTO) {
		Curso curso = cursoMapper.toEntity(cursoDTO);
		return cursoMapper.toDTO(cursoService.atualizar(id, curso));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long id) {
		this.cursoService.excluir(id);
	}
}
