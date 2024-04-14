package com.easySchedule.backend.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

import com.easySchedule.backend.domain.model.enums.Periodo;
import com.easySchedule.backend.utils.JsonConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
public class Curso {
	@EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Convert(converter = JsonConverter.class)
    private List<Periodo> periodos;

    @ManyToOne
    @JoinColumn(name = "escola_id", nullable = false)
    private Escola escola;
}
