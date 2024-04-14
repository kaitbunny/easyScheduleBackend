CREATE TABLE administrador_escola (
    id BIGINT AUTO_INCREMENT NOT NULL,
    administrador_id BIGINT NOT NULL,
    escola_id BIGINT NOT NULL,
    
    PRIMARY KEY(id),
    FOREIGN KEY(administrador_id) REFERENCES administrador(id),
    FOREIGN KEY(escola_id) REFERENCES escola(id),
    CONSTRAINT administrador_escola UNIQUE(administrador_id, escola_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
