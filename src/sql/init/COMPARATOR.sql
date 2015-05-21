SET FOREIGN_KEY_CHECKS=0;
CREATE TABLE `leaderboard`.`COMPARATOR` (
  `id` INTEGER UNSIGNED NOT NULL,
  `course_id` INTEGER UNSIGNED NOT NULL,
  `type` ENUM('default','missedcutoff','unofficial','incomplete','withdrawn','disqualified','didnotstart','mandatorycps','mandatorygroupcps','optionalcps','cpweight','lastvisitedcporder','lastvisitedcporderdeparture','lastvisitedcporderarrival','leastskippedmandatorycps','leastskippedmandatorygroupcps','leastskippedoptionalcps','leastskippedcpweight')  NOT NULL DEFAULT 'default',
  `tie_action` ENUM('default','continue','rankeven')  DEFAULT 'default',
  `c_order` SMALLINT(2) UNSIGNED NOT NULL DEFAULT '0',
  `is_deleted` ENUM('default','no','yes')  NOT NULL DEFAULT 'no',
  PRIMARY KEY(`id`),
  CONSTRAINT `COMPARATOR_course_id_fkc` FOREIGN KEY `COMPARATOR_course_id_fkc` (`course_id`)
    REFERENCES `COURSE` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `COMPARATOR_id_fkc` FOREIGN KEY `COMPARATOR_id_fkc` (`id`)
    REFERENCES `DATA_RESOURCE` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE
)
TYPE = InnoDB;
