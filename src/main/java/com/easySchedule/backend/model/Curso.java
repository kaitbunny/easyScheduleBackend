package com.easySchedule.backend.model;

import lombok.Data;

import java.util.List;

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
@Data
public class Curso {

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
