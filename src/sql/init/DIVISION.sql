SET FOREIGN_KEY_CHECKS=0;
CREATE TABLE `leaderboard`.`DIVISION` (
  `id` INTEGER UNSIGNED NOT NULL,
  `event_id` INTEGER UNSIGNED NOT NULL,
  `name` VARCHAR(64)  NOT NULL DEFAULT 'default',
  `member_count` TINYINT(1) UNSIGNED NOT NULL DEFAULT '0',
  `consistency` ENUM('default','coed','open','male','female','masters','masterscoed','mastersopen','mastersmale','mastersfemale')  NOT NULL DEFAULT 'default',
  `is_elite` ENUM('default','no','yes')  NOT NULL DEFAULT 'no',
  `is_deleted` ENUM('default','no','yes')  NOT NULL DEFAULT 'no',
  PRIMARY KEY(`id`),
  KEY(`event_id`,`name`),
  CONSTRAINT `DIVISION_event_id_fkc` FOREIGN KEY `DIVISION_event_id_fkc` (`event_id`)
    REFERENCES `EVENT` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `DIVISION_id_fkc` FOREIGN KEY `DIVISION_id_fkc` (`id`)
    REFERENCES `DATA_RESOURCE` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE
)
TYPE = InnoDB;