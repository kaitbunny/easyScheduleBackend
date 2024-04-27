ALTER TABLE administrador
ADD COLUMN escola_id BIGINT NOT NULL,
ADD CONSTRAINT fk_escola_administrador FOREIGN KEY (escola_id) REFERENCES escola(id);