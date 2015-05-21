ALTER TABLE `CP` ADD `zone_number` INTEGER UNSIGNED NOT NULL DEFAULT '0';
ALTER TABLE `CP` ADD `zone_char` ENUM('default','C','D','E','F','G','H','J','K','L','M','N','P','Q','S','T','U','V','W','X')  DEFAULT 'default';
