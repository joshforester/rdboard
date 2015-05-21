SET FOREIGN_KEY_CHECKS=0;
CREATE TABLE `leaderboard`.`CP_Visit_Rel` (
  `team_id` INTEGER UNSIGNED NOT NULL,
  `cp_id` INTEGER UNSIGNED NOT NULL,
  `division_place` SMALLINT UNSIGNED NOT NULL DEFAULT 0,
  `course_place` SMALLINT UNSIGNED NOT NULL DEFAULT 0,
  `last_modified` DATETIME  NOT NULL DEFAULT '1970-01-01 00:00:00',
  `arrival` DATETIME  NOT NULL DEFAULT '1970-01-01 00:00:00',
  `departure` DATETIME  NOT NULL DEFAULT '1970-01-01 00:00:00',
  `time_bonus_assessed` DATETIME  NOT NULL DEFAULT '1970-01-01 00:00:00',
  `time_penalty_assessed` DATETIME  NOT NULL DEFAULT '1970-01-01 00:00:00',
  `cp_bonus_assessed` TINYINT UNSIGNED  NOT NULL DEFAULT 0,
  `cp_penalty_assessed` TINYINT UNSIGNED  NOT NULL DEFAULT 0,
  `weight_bonus_assessed` SMALLINT UNSIGNED  NOT NULL DEFAULT 0,
  `weight_penalty_assessed` SMALLINT UNSIGNED  NOT NULL DEFAULT 0,
  `bonus_assessed_reason` VARCHAR(256)  NOT NULL DEFAULT 'default',
  `penalty_assessed_reason` VARCHAR(256)  NOT NULL DEFAULT 'default',
  `is_acquired` ENUM('default','no','yes') NOT NULL DEFAULT 'no',
  `is_skipped` ENUM('default','no','yes') NOT NULL DEFAULT 'no',
  `is_missed_cutoff` ENUM('default','no','yes')  NOT NULL DEFAULT 'no',
  `is_unofficial` ENUM('default','no','yes')  NOT NULL DEFAULT 'no',
  `is_incomplete` ENUM('default','no','yes')  NOT NULL DEFAULT 'no',
  `is_withdrawn` ENUM('default','no','yes')  NOT NULL DEFAULT 'no',
  `is_disqualified` ENUM('default','no','yes')  NOT NULL DEFAULT 'no',
  `is_deleted` ENUM('default','no','yes')  NOT NULL DEFAULT 'no',
  PRIMARY KEY(`team_id`, `cp_id`),
  CONSTRAINT `CP_Visit_Rel_team_id_fkc` FOREIGN KEY `CP_Visit_Rel_team_id_fkc` (`team_id`)
    REFERENCES `TEAM` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `CP_Visit_Rel_cp_id_fkc` FOREIGN KEY `CP_Visit_Rel_cp_id_fkc` (`cp_id`)
    REFERENCES `CP` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
TYPE = InnoDB;