SET FOREIGN_KEY_CHECKS=0;
CREATE TABLE `leaderboard`.`Competitor_Docs_Rel` (
  `competitor_id` INTEGER UNSIGNED NOT NULL,
  `event_id` INTEGER UNSIGNED NOT NULL,
  `usara` ENUM('default','no','yes')  NOT NULL DEFAULT 'no',
  `media_release` ENUM('default','no','yes')  NOT NULL DEFAULT 'no',
  `hold_harmless` ENUM('default','no','yes')  NOT NULL DEFAULT 'no',
  `doc01_name` VARCHAR(64)  NOT NULL DEFAULT 'default',
  `doc01` ENUM('default','no','yes')  NOT NULL DEFAULT 'no',
  `doc02_name` VARCHAR(64)  NOT NULL DEFAULT 'default',
  `doc02` ENUM('default','no','yes')  NOT NULL DEFAULT 'no',
  `doc03_name` VARCHAR(64)  NOT NULL DEFAULT 'default',
  `doc03` ENUM('default','no','yes')  NOT NULL DEFAULT 'no',
  `doc04_name` VARCHAR(64)  NOT NULL DEFAULT 'default',
  `doc04` ENUM('default','no','yes')  NOT NULL DEFAULT 'no',
  `doc05_name` VARCHAR(64)  NOT NULL DEFAULT 'default',
  `doc05` ENUM('default','no','yes')  NOT NULL DEFAULT 'no',
  `doc06_name` VARCHAR(64)  NOT NULL DEFAULT 'default',
  `doc06` ENUM('default','no','yes')  NOT NULL DEFAULT 'no',
  `doc07_name` VARCHAR(64)  NOT NULL DEFAULT 'default',
  `doc07` ENUM('default','no','yes')  NOT NULL DEFAULT 'no',
  `doc08_name` VARCHAR(64)  NOT NULL DEFAULT 'default',
  `doc08` ENUM('default','no','yes')  NOT NULL DEFAULT 'no',
  `doc09_name` VARCHAR(64)  NOT NULL DEFAULT 'default',
  `doc09` ENUM('default','no','yes')  NOT NULL DEFAULT 'no',
  `doc10_name` VARCHAR(64)  NOT NULL DEFAULT 'default',
  `doc10` ENUM('default','no','yes')  NOT NULL DEFAULT 'no',
  `is_deleted` ENUM('default','no','yes')  NOT NULL DEFAULT 'no',
  PRIMARY KEY(`competitor_id`, `event_id`),
  CONSTRAINT `Competitor_Docs_Rel_competitor_id_fkc` FOREIGN KEY `Competitor_Docs_Rel_competitor_id_fkc` (`competitor_id`)
    REFERENCES `COMPETITOR` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `Competitor_Docs_Rel_event_id_fkc` FOREIGN KEY `Competitor_Docs_Rel_event_id_fkc` (`event_id`)
    REFERENCES `EVENT` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
TYPE = InnoDB;