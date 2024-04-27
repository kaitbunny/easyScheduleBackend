ALTER TABLE `usuario`
ADD COLUMN `turma_id` bigint NULL,
ADD CONSTRAINT `fk_turma` FOREIGN KEY (`turma_id`) REFERENCES `turma` (`id`);
