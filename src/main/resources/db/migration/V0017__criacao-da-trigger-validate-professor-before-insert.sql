DELIMITER $$

CREATE TRIGGER validate_professor_before_insert
BEFORE INSERT ON `professor_disciplina`
FOR EACH ROW
BEGIN
  DECLARE professor_type ENUM('PROFESSOR', 'ALUNO');
  
  -- Obtemos o tipo de usuário do professor_id
  SELECT `tipo` INTO professor_type
  FROM `usuario`
  WHERE `id` = NEW.professor_id;
  
  -- Se o tipo for 'ALUNO', geramos um erro para impedir a inserção
  IF professor_type = 'ALUNO' THEN
    SIGNAL SQLSTATE '45000'
    SET MESSAGE_TEXT = 'Invalid insert: professor_id refers to a user with type ALUNO';
  END IF;
END $$

DELIMITER ;
