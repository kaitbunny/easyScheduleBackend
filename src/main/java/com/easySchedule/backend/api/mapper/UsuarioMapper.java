package com.easySchedule.backend.api.mapper;

import org.springframework.stereotype.Component;
import com.easySchedule.backend.api.dto.UsuarioDTO;
import com.easySchedule.backend.api.dto.UsuarioDetailDTO;
import com.easySchedule.backend.domain.model.Usuario;

@Component
public class UsuarioMapper {

    public UsuarioDTO toDTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());
        dto.setTipo(usuario.getTipo());
        dto.setAtivo(usuario.isAtivo());
        dto.setEscolaNome(usuario.getEscola().getNome() != null? usuario.getEscola().getNome() : null);

        return dto;
    }

    public UsuarioDetailDTO toDetailDTO(Usuario usuario) {
        UsuarioDetailDTO dto = new UsuarioDetailDTO();
        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());
        dto.setSenha(usuario.getSenha());
        dto.setTipo(usuario.getTipo());
        dto.setAtivo(usuario.isAtivo());
        dto.setEscola(usuario.getEscola() != null ? usuario.getEscola() : null);
        dto.setCurso(usuario.getCurso() != null ? usuario.getCurso() : null);
        dto.setTurma(usuario.getTurma() != null ? usuario.getTurma() : null);

        return dto;
    }

    public Usuario toEntity(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setId(dto.getId());
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setTipo(dto.getTipo());
        usuario.setAtivo(dto.isAtivo());

        return usuario;
    }

    public Usuario toEntity(UsuarioDetailDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setId(dto.getId());
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());
        usuario.setTipo(dto.getTipo());
        usuario.setAtivo(dto.isAtivo());
        usuario.setEscola(dto.getEscola() != null ? dto.getEscola() : null);
        usuario.setCurso(dto.getCurso() != null ? dto.getCurso() : null);
        usuario.setTurma(dto.getTurma() != null ? dto.getTurma() : null);

        return usuario;
    }
}
