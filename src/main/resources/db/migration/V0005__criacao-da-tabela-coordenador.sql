CREATE TABLE coordenador (
    id BIGINT AUTO_INCREMENT NOT NULL,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(130) NOT NULL,
    senha VARCHAR(100) NOT NULL,
    ativo TINYINT(1) NOT NULL DEFAULT 1,
    escola_id BIGINT NOT NULL,
    curso_id BIGINT NOT NULL,
    
    PRIMARY KEY(id),
    FOREIGN KEY(escola_id) REFERENCES escola(id),
    FOREIGN KEY(curso_id) REFERENCES curso(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
