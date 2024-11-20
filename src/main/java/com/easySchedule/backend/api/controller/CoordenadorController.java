package com.easySchedule.backend.api.controller;

import com.easySchedule.backend.domain.model.Coordenador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.easySchedule.backend.api.dto.CoordenadorDTO;
import com.easySchedule.backend.api.mapper.CoordenadorMapper;
import com.easySchedule.backend.domain.service.CadastroCoordenadorService;
import com.easySchedule.backend.utils.paginatedresponse.PaginatedResponse;

@CrossOrigin("*")
@RestController
@RequestMapping("/coordenadores")
public class CoordenadorController {

	@Autowired
	private CadastroCoordenadorService coordenadorService;

	@Autowired
	private CoordenadorMapper coordenadorMapper;

	@GetMapping("/{id}")
	public CoordenadorDTO buscar(@PathVariable Long id) {
		return coordenadorMapper.toDTO(coordenadorService.buscarOuFalhar(id));
	}

	@GetMapping
	public PaginatedResponse<CoordenadorDTO> listar(
			@RequestParam(name = "page", defaultValue = "1") Integer page,
			@RequestParam(name = "sortProperty", defaultValue = "id") String sortProperty,
			@RequestParam(name = "sortDirection", defaultValue = "desc") String sortDirection,
			@RequestParam(name = "nome", required = false) String nome,
			@RequestParam(name = "email", required = false) String email,
			@RequestParam(name = "ativo", required = false) Boolean ativo,
			@RequestParam(name = "cursoId", required = false) Long cursoId
	) {
		PaginatedResponse<Coordenador> coordenadores = this.coordenadorService.listarPorPagina(
				page, sortProperty, sortDirection, nome, email, ativo, cursoId);

		// Usando o map para converter Coordenador para CoordenadorDTO
		PaginatedResponse<CoordenadorDTO> coordenadorDTOs = coordenadores.map(coordenador ->
				coordenadorMapper.toDTO(coordenador)
		);

		return coordenadorDTOs;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CoordenadorDTO adicionar(@RequestBody CoordenadorDTO coordenadorDTO) {
		var coordenador = coordenadorMapper.toEntity(coordenadorDTO);
		var savedCoordenador = this.coordenadorService.salvar(coordenador);
		return coordenadorMapper.toDTO(savedCoordenador);
	}

	@PutMapping("/{id}")
	public CoordenadorDTO atualizar(@PathVariable Long id, @RequestBody CoordenadorDTO coordenadorDTO) {
		Coordenador coordenador = coordenadorMapper.toEntity(coordenadorDTO);
		return coordenadorMapper.toDTO(coordenadorService.atualizar(id, coordenador));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long id) {
		coordenadorService.excluir(id);
	}
}
