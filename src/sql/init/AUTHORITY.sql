SET FOREIGN_KEY_CHECKS=0;
CREATE TABLE `leaderboard`.`AUTHORITY` (
  `id` INTEGER UNSIGNED NOT NULL,
  `authority` ENUM('default','ROLE_COMPETITOR','ROLE_EVENTADMIN','ROLE_PROMOTER','ROLE_ROOT')  NOT NULL DEFAULT 'default',
  PRIMARY KEY(`id`,`authority`),
  CONSTRAINT `AUTHORITY_id_fkc` FOREIGN KEY `AUTHORITY_id_fkc` (`id`)
    REFERENCES `PERSON` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
TYPE = InnoDB;
