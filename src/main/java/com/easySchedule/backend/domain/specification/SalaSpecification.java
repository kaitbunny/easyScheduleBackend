package com.easySchedule.backend.domain.specification;

import com.easySchedule.backend.domain.model.Sala;
import org.springframework.data.jpa.domain.Specification;

public class SalaSpecification {
    public static Specification<Sala> numeroContains(String numero) {
        return (root, query, criteriaBuilder) -> {
            if(numero == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(root.get("numero"), "%" + numero + "%");
        };
    }

    public static Specification<Sala> andarContains(String andar) {
        return (root, query, criteriaBuilder) -> {
            if(andar == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(root.get("andar"), "%" + andar + "%");
        };
    }

    public static Specification<Sala> predioContains(String predio) {
        return (root, query, criteriaBuilder) -> {
            if(predio == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(root.get("predio"), "%" + predio + "%");
        };
    }

    public static Specification<Sala> escolaIdEquals(Long escolaId) {
        return (root, query, criteriaBuilder) -> {
            if(escolaId == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("escola").get("id"), escolaId);
        };
    }
}
