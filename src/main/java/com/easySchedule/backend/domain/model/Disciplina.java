package com.easySchedule.backend.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.persistence.*;

@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
public class Disciplina {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;
    
    @Column(nullable = false)
    private boolean ativo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "curso_id")
    private Curso curso;
}
