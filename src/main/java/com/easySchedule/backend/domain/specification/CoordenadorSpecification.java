package com.easySchedule.backend.domain.specification;

import org.springframework.data.jpa.domain.Specification;

import com.easySchedule.backend.domain.model.Coordenador;

public class CoordenadorSpecification {

	public static Specification<Coordenador> nomeContains(String nome) {
		return (root, query, criteriaBuilder) -> {
			if(nome == null) {
				return criteriaBuilder.conjunction();
			}
			return criteriaBuilder.like(root.get("nome"), "%" + nome + "%");
		};
	}
	
	public static Specification<Coordenador> emailContains(String email) {
		return (root, query, criteriaBuilder) -> {
			if(email == null) {
				return criteriaBuilder.conjunction();
			}
			return criteriaBuilder.like(root.get("email"), "%" + email + "%");
		};
	}
	
	public static Specification<Coordenador> isAtivo(Boolean ativo) {
		return (root, query, criteriaBuilder) -> {
			if(ativo == null) {
				return criteriaBuilder.conjunction();				
			}
			return criteriaBuilder.equal(root.get("ativo"), ativo);
		};
	}
	
	public static Specification<Coordenador> escolaIdEquals(Long escolaId) {
		return (root, query, criteriaBuilder) -> {
			if(escolaId == null) {
				return criteriaBuilder.conjunction();
			}
			return criteriaBuilder.equal(root.get("escola").get("id"), escolaId);
		};
	}
	
	public static Specification<Coordenador> cursoIdEquals(Long cursoId) {
		return (root, query, criteriaBuilder) -> {
			if(cursoId == null) {
				return criteriaBuilder.conjunction();
			}
			return criteriaBuilder.equal(root.get("curso").get("id"), cursoId);
		};
	}
	
}
