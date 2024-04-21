CREATE TABLE turma_aluno (
    turma_id BIGINT NOT NULL,
    aluno_id BIGINT NOT NULL,
    
    PRIMARY KEY(turma_id, aluno_id),
    FOREIGN KEY(turma_id) REFERENCES turma(id),
    FOREIGN KEY(aluno_id) REFERENCES usuario(id),
    UNIQUE KEY(turma_id, aluno_id),
    CONSTRAINT fk_usuario_aluno FOREIGN KEY (aluno_id) REFERENCES usuario(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;