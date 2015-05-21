SET FOREIGN_KEY_CHECKS=0;
CREATE TABLE `leaderboard`.`EVENT` (
  `id` INTEGER UNSIGNED NOT NULL,
  `name` VARCHAR(64)  NOT NULL DEFAULT 'default',
  `start_time` DATETIME  NOT NULL DEFAULT '1970-01-01 00:00:00',
  `end_time` DATETIME  NOT NULL DEFAULT '1970-01-01 00:00:00',
  `city` VARCHAR(32)  NOT NULL DEFAULT 'default',
  `region` VARCHAR(32)  NOT NULL DEFAULT 'default',
  `country` VARCHAR(32)  NOT NULL DEFAULT 'default',
  `zip` MEDIUMINT(5) UNSIGNED NOT NULL DEFAULT '0',
  `event_url` VARCHAR(2048)  NOT NULL DEFAULT 'default',
  `event_caption` VARCHAR(255)  NOT NULL DEFAULT 'default',
  `sponsor_website` VARCHAR(2048)  NOT NULL DEFAULT 'default',
  `sponsor_url` VARCHAR(2048)  NOT NULL DEFAULT 'default',
  `sponsor_title` VARCHAR(32)  NOT NULL DEFAULT 'default',
  `sponsor_caption` VARCHAR(255)  NOT NULL DEFAULT 'default',
  `sponsor_description` text,
  `is_deleted` ENUM('default','no','yes')  NOT NULL DEFAULT 'no',
  PRIMARY KEY(`id`),
  KEY(`name`,`start_time`),
  CONSTRAINT `EVENT_id_fkc` FOREIGN KEY `EVENT_id_fkc` (`id`)
    REFERENCES `DATA_RESOURCE` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE
)
TYPE = InnoDB;