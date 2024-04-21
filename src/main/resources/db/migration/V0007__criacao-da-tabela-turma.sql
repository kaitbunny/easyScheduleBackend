CREATE TABLE turma (
    id BIGINT AUTO_INCREMENT NOT NULL,
    ativo TINYINT(1) NOT NULL DEFAULT 1,
    periodo ENUM('MANHA', 'TARDE', 'NOITE') NOT NULL,
    semestre INT NOT NULL,
    curso_id BIGINT NOT NULL,
    
    PRIMARY KEY(id),
    FOREIGN KEY(curso_id) REFERENCES curso(id),
    CONSTRAINT chk_intervalo CHECK (semestre >= 1 AND semestre <= 16)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
