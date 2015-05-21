ALTER TABLE CP_Visit_Rel ADD `division_place` SMALLINT UNSIGNED NOT NULL DEFAULT 0 AFTER `cp_id`;
ALTER TABLE CP_Visit_Rel ADD `course_place` SMALLINT UNSIGNED NOT NULL DEFAULT 0 AFTER `division_place`;
ALTER TABLE COURSE ADD `type` ENUM('default','rogaine','weightedrogaine','modifiedrogaine','pointtopoint','stage')  NOT NULL DEFAULT 'default' AFTER `name`;
ALTER TABLE COURSE ADD `length_hours` SMALLINT UNSIGNED DEFAULT 0 AFTER `type`;
ALTER TABLE COURSE ADD `length_miles` SMALLINT UNSIGNED DEFAULT 0 AFTER `length_hours`;
ALTER TABLE COURSE ADD `description` text AFTER `length_miles`;
ALTER TABLE COURSE ADD `scribblelive_id` VARCHAR(7)  NOT NULL DEFAULT 'default' AFTER `description`;
ALTER TABLE COURSE ADD `coveritlive_id` VARCHAR(10)  NOT NULL DEFAULT 'default' AFTER `scribblelive_id`;