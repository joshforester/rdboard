SET FOREIGN_KEY_CHECKS=0;
CREATE TABLE `leaderboard`.`PERSON` (
  `id` INTEGER UNSIGNED NOT NULL,
  `identity_id` INTEGER UNSIGNED NOT NULL,
  `email` VARCHAR(64)  NOT NULL DEFAULT 'default',
  `first_name` VARCHAR(32)  NOT NULL DEFAULT 'default',
  `last_name` VARCHAR(32)  NOT NULL DEFAULT 'default',
  `city` VARCHAR(32)  NOT NULL DEFAULT 'default',
  `region` VARCHAR(32)  NOT NULL DEFAULT 'default',
  `country` VARCHAR(32)  NOT NULL DEFAULT 'default',
  `zip` MEDIUMINT(5) UNSIGNED NOT NULL DEFAULT 0,
  `gender` ENUM('default','male','female')  DEFAULT 'default',
  `birthday` DATETIME  DEFAULT '1970-01-01 00:00:00',
  `home_phone` VARCHAR(24)  DEFAULT 'default',
  `cell_phone` VARCHAR(24)  DEFAULT 'default',
  `work_phone` VARCHAR(24)  DEFAULT 'default',
  `default_emergency_contact_first_name` VARCHAR(32)  DEFAULT 'default',
  `default_emergency_contact_last_name` VARCHAR(32)  DEFAULT 'default',
  `default_emergency_contact_relation` ENUM('default','spouse','sibling','parent','child','other')  DEFAULT 'default',
  `default_emergency_phone` VARCHAR(24)  DEFAULT 'default',
  `default_shirt_size` ENUM('default','x-small','small','medium','large','x-large','xx-large','xxx-large') DEFAULT 'default',
  `default_shoe_size` VARCHAR(7)  DEFAULT 'default',
  `occupation` VARCHAR(64)  DEFAULT 'default',
  `self_description` TEXT ,
  `hobbies_interests` TEXT ,
  `favorite_quote` TEXT ,
  `favorite_things` TEXT ,
  `website` TEXT ,
  `is_disabled` ENUM('default','no','yes')  NOT NULL DEFAULT 'no',
  `is_deleted` ENUM('default','no','yes')  NOT NULL DEFAULT 'no',
  PRIMARY KEY(`id`),
  INDEX `email_i`(`email`),
  INDEX `zip_i`(`zip`),
  INDEX `enabled_i`(`is_disabled`, `is_deleted`),
  CONSTRAINT `PERSON_id_fkc` FOREIGN KEY `PERSON_id_fkc` (`id`)
    REFERENCES `DATA_RESOURCE` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `PERSON_identity_id_fkc` FOREIGN KEY `PERSON_identity_id_fkc` (`identity_id`)
    REFERENCES `IDENTITY` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE
)
TYPE = InnoDB;
