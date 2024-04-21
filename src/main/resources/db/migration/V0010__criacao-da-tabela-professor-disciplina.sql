CREATE TABLE professor_disciplina (
    id BIGINT AUTO_INCREMENT NOT NULL,
    periodo ENUM('MANHA', 'TARDE', 'NOITE') NOT NULL,
    professor_id BIGINT NOT NULL,
    disciplina_id BIGINT NOT NULL,
    
    PRIMARY KEY(id),
    FOREIGN KEY(professor_id) REFERENCES usuario(id),
    FOREIGN KEY(disciplina_id) REFERENCES disciplina(id),
    CONSTRAINT fk_usuario_professor FOREIGN KEY (professor_id) REFERENCES usuario(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
