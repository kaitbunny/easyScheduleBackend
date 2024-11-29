package com.easySchedule.backend.api.controller;

import com.easySchedule.backend.api.dto.SalaDTO;
import com.easySchedule.backend.api.mapper.SalaMapper;
import com.easySchedule.backend.domain.model.Sala;
import com.easySchedule.backend.utils.paginatedresponse.PaginatedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.easySchedule.backend.domain.service.CadastroSalaService;

@CrossOrigin("*")
@RestController
@RequestMapping("/salas")
public class SalaController {

	@Autowired
	private CadastroSalaService salaService;

	@Autowired
	private SalaMapper salaMapper;

	@GetMapping("/{id}")
	public SalaDTO buscar(@PathVariable Long id) {
		var sala = this.salaService.buscarOuFalhar(id);
		return salaMapper.toDTO(sala);
	}

	@GetMapping
	public PaginatedResponse<SalaDTO> listar(
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "sortProperty", defaultValue = "id") String sortProperty,
			@RequestParam(value = "sortDirection", defaultValue = "desc") String sortDirection,
			@RequestParam(value = "numero", required = false) String numero,
			@RequestParam(value = "andar", required = false) String andar,
			@RequestParam(value = "predio", required = false) String predio,
			@RequestParam(value = "escolaId", required = false) Long escolaId
	) {
		PaginatedResponse<Sala> salas = this.salaService.listarPorPagina(page, sortProperty, sortDirection,
				numero, andar, predio, escolaId);

		PaginatedResponse<SalaDTO> salaDTOs = salas.map(sala ->
				salaMapper.toDTO(sala));

		return salaDTOs;
	}
}
