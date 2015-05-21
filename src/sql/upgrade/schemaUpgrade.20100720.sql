ALTER TABLE EVENT ADD `sponsor_website` VARCHAR(2048)  NOT NULL DEFAULT 'default' AFTER `event_caption`;
ALTER TABLE EVENT ADD `sponsor_title` VARCHAR(32)  NOT NULL DEFAULT 'default' AFTER `sponsor_url`;
