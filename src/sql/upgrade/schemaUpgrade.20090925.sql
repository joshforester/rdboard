ALTER TABLE `Team_Competing_In_Rel` ADD `number` SMALLINT UNSIGNED NOT NULL DEFAULT '0';
ALTER TABLE `TEAM` DROP COLUMN `number`;
