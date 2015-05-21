SET FOREIGN_KEY_CHECKS=0;
CREATE TABLE `leaderboard`.`UPD` (
  `id` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `team_id` INTEGER UNSIGNED NOT NULL,
  `time` DATETIME  NOT NULL DEFAULT '1970-01-01 00:00:00',
  `type` ENUM('default','CP Visit','Rank')  NOT NULL DEFAULT 'default',
  `message` text,
  `is_deleted` ENUM('default','no','yes')  NOT NULL DEFAULT 'no',
  PRIMARY KEY(`id`),
  CONSTRAINT `UPD_team_id_fkc` FOREIGN KEY `UPD_team_id_fkc` (`team_id`)
    REFERENCES `TEAM` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
TYPE = InnoDB;