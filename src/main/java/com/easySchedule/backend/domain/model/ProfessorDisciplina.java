package com.easySchedule.backend.domain.model;

import com.easySchedule.backend.domain.model.enums.Periodo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "professor_disciplina")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
public class ProfessorDisciplina {
	@EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable = false)
	private Periodo periodo;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "professor_id")
	private Usuario professor;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "disciplina_id")
	private Disciplina disciplina;
}
