CREATE TABLE curso (
    id BIGINT AUTO_INCREMENT NOT NULL,
    nome VARCHAR(100) NOT NULL,
    periodos JSON NOT NULL,
    escola_id BIGINT NOT NULL,
    
    PRIMARY KEY(id),
    FOREIGN KEY(escola_id) REFERENCES escola(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
