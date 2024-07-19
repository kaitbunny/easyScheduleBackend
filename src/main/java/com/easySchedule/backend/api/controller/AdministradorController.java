package com.easySchedule.backend.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.easySchedule.backend.domain.model.Administrador;
import com.easySchedule.backend.domain.model.enums.TipoAdministrador;
import com.easySchedule.backend.domain.service.CadastroAdministradorService;
import com.easySchedule.backend.utils.paginatedresponse.PaginatedResponse;

@CrossOrigin("*")
@RestController
@RequestMapping("/administradores")
public class AdministradorController {

	@Autowired
	private CadastroAdministradorService administradorService;
	
	@GetMapping("/{id}")
	public Administrador buscar(@PathVariable Long id) {
		return administradorService.buscarOuFalhar(id);
	}
	
	@GetMapping
    public PaginatedResponse<Administrador> listar(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "sortProperty", defaultValue = "id") String sortProperty,
            @RequestParam(value = "sortDirection", defaultValue = "desc") String sortDirection,
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "tipo", required = false) TipoAdministrador tipo,
            @RequestParam(value = "ativo", required = false) Boolean ativo,
            @RequestParam(value = "escolaId", required = false) Long escolaId) {
        
        return this.administradorService.listarPorPagina(page, sortProperty, sortDirection, nome, email, tipo, ativo, escolaId);
    }
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Administrador adicionar(@RequestBody Administrador administrador) {
		return this.administradorService.salvar(administrador);
	}
}
