SET FOREIGN_KEY_CHECKS=0;
CREATE TABLE `leaderboard`.`RANK` (
  `id` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `team_id` INTEGER UNSIGNED NOT NULL,
  `time` DATETIME  NOT NULL DEFAULT '1970-01-01 00:00:00',
  `division_place` SMALLINT UNSIGNED NOT NULL DEFAULT 0,
  `course_place` SMALLINT UNSIGNED NOT NULL DEFAULT 0,
  `is_deleted` ENUM('default','no','yes')  NOT NULL DEFAULT 'no',
  PRIMARY KEY(`id`),
  KEY(`team_id`,`time`),	
  CONSTRAINT `RANK_team_id_fkc` FOREIGN KEY `RANK_team_id_fkc` (`team_id`)
    REFERENCES `TEAM` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
TYPE = InnoDB;