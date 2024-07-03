package com.easySchedule.backend.domain.specification;

import org.springframework.data.jpa.domain.Specification;

import com.easySchedule.backend.domain.model.Administrador;
import com.easySchedule.backend.domain.model.enums.TipoAdministrador;

public class AdministradorSpecification {

	public static Specification<Administrador> nomeContains(String nome) {
		return (root, query, criteriaBuilder) -> {
			if(nome == null) {
				return criteriaBuilder.conjunction();
			}
			return criteriaBuilder.like(root.get("nome"), "%" + nome + "%");
		};
	}
	
	public static Specification<Administrador> tipoEquals(TipoAdministrador tipo) {
		return (root, query, criteriaBuilder) -> {
			if(tipo == null) {
				return criteriaBuilder.conjunction();
			}
			return criteriaBuilder.equal(root.get("tipo"), tipo);
		};
	}
	
	public static Specification<Administrador> emailContains(String email) {
		return (root, query, criteriaBuilder) -> {
			if(email == null) {
				return criteriaBuilder.conjunction();
			}
			return criteriaBuilder.like(root.get("email"), "%" + email + "%");
		};
	}
	
	public static Specification<Administrador> isAtivo(Boolean ativo) {
		return (root, query, criteriaBuilder) -> {
			if(ativo == null) {
				return criteriaBuilder.conjunction();
			}
			return criteriaBuilder.equal(root.get("ativo"), ativo);
		};
	}
	
	public static Specification<Administrador> escolaIdEquals(Long escolaId) {
        return (root, query, criteriaBuilder) -> {
            if(escolaId == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("escola").get("id"), escolaId);
        };
    }
	
}
