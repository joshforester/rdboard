SET FOREIGN_KEY_CHECKS=0;
CREATE TABLE `leaderboard`.`COURSE` (
  `id` INTEGER UNSIGNED NOT NULL,
  `event_id` INTEGER UNSIGNED NOT NULL,
  `name` VARCHAR(64)  NOT NULL DEFAULT 'default',
  `type` ENUM('default','rogaine','weightedrogaine','modifiedrogaine','pointtopoint','stage')  NOT NULL DEFAULT 'default',
  `length_hours` SMALLINT UNSIGNED DEFAULT 0,
  `length_miles` SMALLINT UNSIGNED DEFAULT 0,
  `description` text,
  `scribblelive_id` VARCHAR(7)  NOT NULL DEFAULT `default`,
  `coveritlive_id` VARCHAR(10)  NOT NULL DEFAULT `default`,
  `feedburner_all_id` VARCHAR(64)  NOT NULL DEFAULT `default`,
  `feedburner_blogs_id` VARCHAR(64)  NOT NULL DEFAULT `default`,
  `feedburner_photos_id` VARCHAR(64)  NOT NULL DEFAULT `default`,
  `feedburner_videos_id` VARCHAR(64)  NOT NULL DEFAULT `default`,
  `feedburner_audio_id` VARCHAR(64)  NOT NULL DEFAULT `default`,
  `competitor_gmap_suffix` VARCHAR(1024)  NOT NULL DEFAULT `default`,
  `is_deleted` ENUM('default','no','yes')  NOT NULL DEFAULT 'no',
  PRIMARY KEY(`id`),
  KEY(`event_id`,`name`),
  CONSTRAINT `COURSE_event_id_fkc` FOREIGN KEY `COURSE_event_id_fkc` (`event_id`)
    REFERENCES `EVENT` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `COURSE_id_fkc` FOREIGN KEY `COURSE_id_fkc` (`id`)
    REFERENCES `DATA_RESOURCE` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE
)
TYPE = InnoDB;
