package com.easySchedule.backend.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;


import com.easySchedule.backend.domain.model.enums.Periodo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
public class Turma {
	@EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(nullable = false)
    private boolean ativo;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
    private Periodo periodo;

	@Min(1)
    @Max(16)
	@Column(nullable = false)
	private Integer semestre;
	
	@ManyToOne(optional = false)
    private Curso curso;
}
