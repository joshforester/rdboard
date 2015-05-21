ALTER TABLE EVENT ADD `event_url` VARCHAR(2048)  NOT NULL DEFAULT 'default' AFTER `zip`;
ALTER TABLE EVENT ADD `event_caption` VARCHAR(255)  NOT NULL DEFAULT 'default' AFTER `event_url`;
ALTER TABLE EVENT ADD `sponsor_url` VARCHAR(2048)  NOT NULL DEFAULT 'default' AFTER `event_description`;
ALTER TABLE EVENT ADD `sponsor_caption` VARCHAR(255)  NOT NULL DEFAULT 'default' AFTER `sponsor_url`;
ALTER TABLE EVENT ADD `sponsor_description` text AFTER `sponsor_caption`;
