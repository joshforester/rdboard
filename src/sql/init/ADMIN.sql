SET FOREIGN_KEY_CHECKS=0;
CREATE TABLE `leaderboard`.`ADMIN` (
  `id` INTEGER UNSIGNED NOT NULL,
  `username` VARCHAR(32)  NOT NULL DEFAULT 'default',
  `password` VARCHAR(32)  NOT NULL DEFAULT 'default',
  `is_disabled` ENUM('default','no','yes')  NOT NULL DEFAULT 'no',
  `is_deleted` ENUM('default','no','yes')  NOT NULL DEFAULT 'no',
  PRIMARY KEY(`id`),
  KEY(`username`),
  INDEX `enabled_i`(`is_disabled`, `is_deleted`),
  CONSTRAINT `ADMIN_id_fkc` FOREIGN KEY `ADMIN_id_fkc` (`id`)
    REFERENCES `PERSON` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
TYPE = InnoDB;
