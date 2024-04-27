DELIMITER $$

CREATE TRIGGER check_curso_id_before_insert
BEFORE INSERT ON `usuario`
FOR EACH ROW
BEGIN
  IF NEW.tipo = 'ALUNO' AND NEW.curso_id IS NULL THEN
    SIGNAL SQLSTATE '45000'
    SET MESSAGE_TEXT = 'curso_id cannot be NULL for ALUNO';
  END IF;
END $$

DELIMITER ;
