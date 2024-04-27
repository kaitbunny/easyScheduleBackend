DELIMITER $$

CREATE TRIGGER check_turma_before_insert
BEFORE INSERT ON `usuario`
FOR EACH ROW
BEGIN
  IF NEW.tipo <> 'ALUNO' THEN
    SET NEW.turma_id = NULL;  -- Garante que apenas alunos tenham turma
  END IF;
END $$

DELIMITER ;
