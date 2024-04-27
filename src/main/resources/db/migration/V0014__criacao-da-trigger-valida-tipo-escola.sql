DELIMITER //
CREATE TRIGGER valida_tipo_escola
BEFORE INSERT ON administrador
FOR EACH ROW
BEGIN
    IF NEW.tipo != 'ESCOLA' THEN
        SET NEW.escola_id = NULL;
    END IF;
END;
//
DELIMITER ;
