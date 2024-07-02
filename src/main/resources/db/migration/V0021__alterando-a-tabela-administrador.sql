DROP TABLE `administrador`;

CREATE TABLE `administrador` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `tipo` enum('MASTER','ESCOLA') NOT NULL,
  `email` varchar(130) NOT NULL,
  `senha` varchar(100) NOT NULL,
  `ativo` tinyint(1) NOT NULL DEFAULT '1',
  `escola_id` bigint NULL,
  PRIMARY KEY (`id`),
  KEY `fk_escola_administrador` (`escola_id`),
  CONSTRAINT `fk_escola_administrador` FOREIGN KEY (`escola_id`) REFERENCES `escola` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;