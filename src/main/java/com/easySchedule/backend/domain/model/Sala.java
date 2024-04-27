package com.easySchedule.backend.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.persistence.*;

@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
public class Sala {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String numero;

    @Column(length = 30)
    private String andar;

    @Column(length = 30)
    private String predio;

    @ManyToOne(optional = false)
    @JoinColumn(name = "escola_id")
    private Escola escola;
}
