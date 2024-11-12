package com.easySchedule.backend.api.controller;

import com.easySchedule.backend.api.dto.AdministradorDTO;
import com.easySchedule.backend.api.mapper.AdministradorMapper;
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

	@Autowired
	private AdministradorMapper administradorMapper;

	@GetMapping("/{id}")
	public AdministradorDTO buscar(@PathVariable Long id) {
		var administrador = this.administradorService.buscarOuFalhar(id);
		return administradorMapper.toDTO(administrador);
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
	public AdministradorDTO adicionar(@RequestBody AdministradorDTO administradorDTO) {
		var administrador = administradorMapper.toEntity(administradorDTO);
		var savedAdministrador = this.administradorService.salvar(administrador);
		return administradorMapper.toDTO(savedAdministrador);
	}

	@PutMapping("/{id}")
	public AdministradorDTO atualizar(@PathVariable Long id, @RequestBody AdministradorDTO administradorDTO) {
		var administrador = administradorMapper.toEntity(administradorDTO);
		var updatedAdministrador = this.administradorService.atualizar(id, administrador);
		return administradorMapper.toDTO(updatedAdministrador);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long id) {
		this.administradorService.excluir(id);
	}
	
}
