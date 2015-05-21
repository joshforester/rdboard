ALTER TABLE COURSE ADD `feedburner_all_id` VARCHAR(64)  NOT NULL DEFAULT 'default' AFTER `coveritlive_id`;
ALTER TABLE COURSE ADD `feedburner_blogs_id` VARCHAR(64)  NOT NULL DEFAULT 'default' AFTER `feedburner_all_id`;
ALTER TABLE COURSE ADD `feedburner_photos_id` VARCHAR(64)  NOT NULL DEFAULT 'default' AFTER `feedburner_blogs_id`;
ALTER TABLE COURSE ADD `feedburner_videos_id` VARCHAR(64)  NOT NULL DEFAULT 'default' AFTER `feedburner_photos_id`;
ALTER TABLE COURSE ADD `feedburner_audio_id` VARCHAR(64)  NOT NULL DEFAULT 'default' AFTER `feedburner_videos_id`;
