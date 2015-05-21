SET FOREIGN_KEY_CHECKS=0;
CREATE TABLE `leaderboard`.`CP` (
  `id` INTEGER UNSIGNED NOT NULL,
  `course_id` INTEGER UNSIGNED NOT NULL,
  `cp_order` SMALLINT(2) UNSIGNED NOT NULL DEFAULT '0',
  `name` VARCHAR(32)  NOT NULL DEFAULT 'default',
  `ta_name` VARCHAR(32)  NOT NULL DEFAULT 'default',
  `northernly` INTEGER UNSIGNED NOT NULL DEFAULT '0',
  `easternly` INTEGER UNSIGNED NOT NULL DEFAULT '0',
  `zone_number` INTEGER UNSIGNED NOT NULL DEFAULT '0',
  `zone_char` ENUM('default','C','D','E','F','G','H','J','K','L','M','N','P','Q','S','T','U','V','W','X')  DEFAULT 'default',
  `hint` VARCHAR(256)  NOT NULL DEFAULT 'default',
  `altitude` SMALLINT  DEFAULT '0',
  `cutoff` DATETIME  NOT NULL DEFAULT '1970-01-01 00:00:00',
  `is_mandatory` ENUM('default','no','yes')  NOT NULL DEFAULT 'default',
  `is_in_mandatory_group` ENUM('default','no','yes')  NOT NULL DEFAULT 'default',
  `mandatory_group_requirement` SMALLINT(2) UNSIGNED DEFAULT '0',
  `mandatory_group_size` SMALLINT(2) UNSIGNED DEFAULT '0',
  `weight` SMALLINT UNSIGNED NOT NULL DEFAULT '0',
  `from_discipline` ENUM('default','running','trekking','mountaineering','coasteering','biking','orienteering','paddling','swimming','ropes','riverboarding','inlineskating','scootering','specialchallenge')  DEFAULT 'default',
  `to_discipline` ENUM('default','running','trekking','mountaineering','coasteering','biking','orienteering','paddling','swimming','ropes','riverboarding','inlineskating','scootering','specialchallenge')  DEFAULT 'default',
  `heywhatsthat_id` VARCHAR(8)  DEFAULT 'default',
  `is_deleted` ENUM('default','no','yes')  NOT NULL DEFAULT 'no',
  PRIMARY KEY(`id`),
  KEY(`course_id`,`name`),
  CONSTRAINT `CP_course_id_fkc` FOREIGN KEY `CP_course_id_fkc` (`course_id`)
    REFERENCES `COURSE` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `CP_id_fkc` FOREIGN KEY `CP_id_fkc` (`id`)
    REFERENCES `DATA_RESOURCE` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE
)
TYPE = InnoDB;
