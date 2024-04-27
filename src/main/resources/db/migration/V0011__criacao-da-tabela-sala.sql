CREATE TABLE sala (
    id BIGINT AUTO_INCREMENT NOT NULL,
    numero VARCHAR(30) NOT NULL,
    andar VARCHAR(30),
    predio VARCHAR(30),
    escola_id BIGINT NOT NULL,
    
    PRIMARY KEY(id),
    FOREIGN KEY(escola_id) REFERENCES escola(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
