SET FOREIGN_KEY_CHECKS=0;
CREATE TABLE `leaderboard`.`Access_To_Rel` (
  `admin_id` INTEGER UNSIGNED NOT NULL,
  `event_id` INTEGER UNSIGNED NOT NULL,
  `is_deleted` ENUM('default','no','yes')  NOT NULL DEFAULT 'no',
  PRIMARY KEY(`admin_id`, `event_id`),
  CONSTRAINT `Access_To_Rel_admin_id_fkc` FOREIGN KEY `Access_To_Rel_admin_id_fkc` (`admin_id`)
    REFERENCES `ADMIN` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `Access_To_Rel_event_id_fkc` FOREIGN KEY `Access_To_Rel_event_id_fkc` (`event_id`)
    REFERENCES `EVENT` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
TYPE = InnoDB;