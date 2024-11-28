package com.easySchedule.backend.api.controller;

import com.easySchedule.backend.api.dto.EscolaDTO;
import com.easySchedule.backend.api.mapper.EscolaMapper;
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

import com.easySchedule.backend.domain.model.Escola;
import com.easySchedule.backend.domain.service.CadastroEscolaService;
import com.easySchedule.backend.utils.paginatedresponse.PaginatedResponse;

@CrossOrigin("*")
@RestController
@RequestMapping("/escolas")
public class EscolaController {

	@Autowired
	private CadastroEscolaService escolaService;

	@Autowired
	private EscolaMapper escolaMapper;
	
	@GetMapping("/{id}")
	public EscolaDTO buscar(@PathVariable Long id) {
		var escola = this.escolaService.buscarOuFalhar(id);
		return escolaMapper.toDTO(escola);
	}
	
	@GetMapping
    public PaginatedResponse<EscolaDTO> listar(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "sortProperty", defaultValue = "id") String sortProperty,
            @RequestParam(value = "sortDirection", defaultValue = "desc") String sortDirection,
            @RequestParam(value = "nome", required = false) String nome
	) {
        PaginatedResponse<Escola> escolas = this.escolaService.listarPorPagina(page, sortProperty, sortDirection, nome);

		PaginatedResponse<EscolaDTO> escolaDTOs = escolas.map(escola -> escolaMapper.toDTO(escola));
        return escolaDTOs;
    }

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EscolaDTO adicionar(@RequestBody EscolaDTO escolaDTO) {
		var escola = escolaMapper.toEntity(escolaDTO);
		var savedEscola = this.escolaService.salvar(escola);
		return escolaMapper.toDTO(savedEscola);
	}
	
	@PutMapping("/{id}")
	public EscolaDTO atualizar(@PathVariable Long id, @RequestBody EscolaDTO escolaDTO) {
		var escola = escolaMapper.toEntity(escolaDTO);
		var updatedEscola = this.escolaService.atualizar(id, escola);
		return escolaMapper.toDTO(updatedEscola);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long id) {
		this.escolaService.excluir(id);
	}
}
