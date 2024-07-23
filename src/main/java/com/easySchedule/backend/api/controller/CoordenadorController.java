package com.easySchedule.backend.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@GetMapping
	public PaginatedResponse<Coordenador> listar(
			@RequestParam(name = "page", defaultValue = "1") Integer page,
			@RequestParam(name = "sortProperty", defaultValue = "id") String sortProperty,
			@RequestParam(name = "sortDirection", defaultValue = "desc") String sortDirection,
			@RequestParam(name = "nome", required = false) String nome,
			@RequestParam(name = "email", required = false) String email,
			@RequestParam(name = "ativo", required = false) Boolean ativo,
			@RequestParam(name = "escolaId", required = false) Long escolaId,
			@RequestParam(name = "cursoId", required = false) Long cursoId
			) {
		
		return coordenadorService.listarPorPagina(page, sortProperty, sortDirection, nome, email, ativo, escolaId, cursoId);
	}
}
