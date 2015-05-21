SET FOREIGN_KEY_CHECKS=0;
CREATE TABLE `leaderboard`.`Team_Instance_Rel` (
  `team_id` INTEGER UNSIGNED NOT NULL,
  `competitor_id` INTEGER UNSIGNED NOT NULL,
  `is_captain` ENUM('default','no','yes')  NOT NULL DEFAULT 'no',
  `is_deleted` ENUM('default','no','yes')  NOT NULL DEFAULT 'no',
  PRIMARY KEY(`team_id`, `competitor_id`),
  CONSTRAINT `Team_Instance_Rel_team_id_fkc` FOREIGN KEY `Team_Instance_Rel_team_id_fkc` (`team_id`)
    REFERENCES `TEAM` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `Team_Instance_Rel_competitor_id_fkc` FOREIGN KEY `Team_Instance_Rel_competitor_id_fkc` (`competitor_id`)
    REFERENCES `COMPETITOR` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
TYPE = InnoDB;