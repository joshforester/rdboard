SET FOREIGN_KEY_CHECKS=0;
CREATE TABLE `leaderboard`.`COMPETITOR` (
  `id` INTEGER UNSIGNED NOT NULL,
  `person_id` INTEGER UNSIGNED NOT NULL,
  `emergency_contact_first_name` VARCHAR(32)  DEFAULT 'default',
  `emergency_contact_last_name` VARCHAR(32)  DEFAULT 'default',
  `emergency_contact_relation` ENUM('default','spouse','sibling','parent','child','other')  DEFAULT 'default',
  `emergency_phone` VARCHAR(24)  DEFAULT 'default',
  `shirt_size` ENUM('default','x-small','small','medium','large','x-large','xx-large','xxx-large') DEFAULT 'default',
  `shoe_size` VARCHAR(7)  DEFAULT 'default',
  `is_deleted` ENUM('default','no','yes')  NOT NULL DEFAULT 'no',
  PRIMARY KEY(`id`),
  INDEX `enabled_i`(`is_deleted`),
  CONSTRAINT `COMPETITOR_person_id_fkc` FOREIGN KEY `COMPETITOR_person_id_fkc` (`person_id`)
    REFERENCES `PERSON` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `COMPETITOR_id_fkc` FOREIGN KEY `COMPETITOR_id_fkc` (`id`)
    REFERENCES `DATA_RESOURCE` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE
)
TYPE = InnoDB;
