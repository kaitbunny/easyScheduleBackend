package com.easySchedule.backend.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.easySchedule.backend.domain.model.Escola;
import com.easySchedule.backend.domain.service.CadastroEscolaService;

@CrossOrigin("*")
@RestController
@RequestMapping("/escolas")
public class EscolaController {

	@Autowired
	private CadastroEscolaService escolaService;
	
	@GetMapping("/{id}")
	public Escola buscar(@PathVariable Long id) {
		return this.escolaService.buscarOuFalhar(id);
	}
}