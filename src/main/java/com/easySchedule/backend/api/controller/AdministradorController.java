package com.easySchedule.backend.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.easySchedule.backend.domain.service.CadastroAdministradorService;

@CrossOrigin("*")
@RestController
@RequestMapping("/administradores")
public class AdministradorController {

	@Autowired
	private CadastroAdministradorService administradorService;
}
