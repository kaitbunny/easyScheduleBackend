package com.easySchedule.backend.domain.specification;

import com.easySchedule.backend.domain.model.Escola;
import org.springframework.data.jpa.domain.Specification;

public class EscolaSpecification {

    public static Specification<Escola> nomeContains(String nome) {
        return (root, query, criteriaBuilder) -> {
            if (nome == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(root.get("nome"), "%" + nome + "%");
        };
    }

}
