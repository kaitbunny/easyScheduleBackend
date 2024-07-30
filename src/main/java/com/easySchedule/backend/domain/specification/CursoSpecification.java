package com.easySchedule.backend.domain.specification;

import org.springframework.data.jpa.domain.Specification;

import com.easySchedule.backend.domain.model.Curso;
import com.easySchedule.backend.domain.model.enums.Periodo;

public class CursoSpecification {
	
	public static Specification<Curso> nomeContains(String nome) {
		return (root, query, criteriaBuilder) -> {
			if(nome == null) {
				return criteriaBuilder.conjunction();
			}
			return criteriaBuilder.like(root.get("nome"), "%" + nome + "%");
		};
	}
	
	public static Specification<Curso> periodoContains(Periodo periodo) {
		return (root, query, criteriaBuilder) -> {
			if(periodo == null) {
				return criteriaBuilder.conjunction();
			}
			return criteriaBuilder.isTrue(criteriaBuilder.function(
					"JSON_CONTAINS", 
					Boolean.class,
					root.get("periodos"),
					criteriaBuilder.literal("\"" + periodo.name() + "\"")
			));
		};
	}
	
	public static Specification<Curso> escolaIdEquals(Long escolaId) {
		return (root, query, criteriaBuilder) -> {
			if(escolaId == null) {
				return criteriaBuilder.conjunction();
			}
			return criteriaBuilder.equal(root.get("escola").get("id"), escolaId);
		};
	}
	
}
