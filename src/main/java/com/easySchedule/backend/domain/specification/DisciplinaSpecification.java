package com.easySchedule.backend.domain.specification;

import org.springframework.data.jpa.domain.Specification;

import com.easySchedule.backend.domain.model.Disciplina;

public class DisciplinaSpecification {
	
	public static Specification<Disciplina> nomeContains(String nome) {
		return (root, query, criteriaBuilder) ->{
			if(nome == null) {
				return criteriaBuilder.conjunction();
			}
			return criteriaBuilder.like(root.get("nome"), "%" + nome + "%");
		};
	}
	
	public static Specification<Disciplina> isAtivo(Boolean ativo) {
		return (root, query, criteriaBuilder) -> {
			if (ativo == null) {
				return criteriaBuilder.conjunction();
			}
			return criteriaBuilder.equal(root.get("ativo"), ativo);
		};
	}
	
	public static Specification<Disciplina> cursoIdEquals(Long cursoId) {
		return (root, query, criteriaBuilder) -> {
			if(cursoId == null) {
				return criteriaBuilder.conjunction();
			}
			return criteriaBuilder.equal(root.get("curso").get("id"), cursoId);
		};
	}
	
}
