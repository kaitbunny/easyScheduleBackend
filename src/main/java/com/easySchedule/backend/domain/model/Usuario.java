package com.easySchedule.backend.domain.model;

import java.util.HashSet;
import java.util.Set;

import com.easySchedule.backend.domain.model.enums.TipoUsuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
public class Usuario {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 130)
    private String email;

    @Column(nullable = false, length = 100)
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoUsuario tipo;
    
    @Column(nullable = false)
    private boolean ativo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "escola_id")
    private Escola escola;
    
    @ManyToOne(optional = true)
    @JoinColumn(name = "curso_id")
    private Curso curso;
    
    @ManyToOne
    @JoinColumn(name = "turma_id", nullable = true)
    private Turma turma;
    
    @PrePersist
    @PreUpdate
    public void validateUsuario() {
    	if(!this.validateProfessor()) {
    		throw new IllegalArgumentException("Usuarios do tipo Professor nao podem ter turmas");
    	}
    	if(!this.validateAluno()) {
    		throw new IllegalArgumentException("Usuarios do tipo Aluno devem ter uma turma e um curso");
    	}
    }
    
    public boolean validateProfessor() {
        if (this.getTipo().equals(TipoUsuario.PROFESSOR)) {
            if (this.getTurma() != null) {
                return false;
            }
        }
        return true;
    }
    
    public boolean validateAluno() {
        if (this.getTipo().equals(TipoUsuario.ALUNO)) {
            if (this.getTurma() == null || this.getClass() == null) {
                return false;
            }
        }
        return true;
    }
}
