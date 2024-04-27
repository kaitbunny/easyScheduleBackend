package com.easySchedule.backend.domain.model;

import com.easySchedule.backend.domain.model.enums.TipoAdministrador;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
public class Administrador {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoAdministrador tipo;

    @Column(nullable = false, length = 130)
    private String email;

    @Column(nullable = false, length = 100)
    private String senha;

    @Column(nullable = false)
    private boolean ativo;

    @ManyToOne(optional = true)
    @JoinColumn(name = "escola_id")
    private Escola escola;
}
