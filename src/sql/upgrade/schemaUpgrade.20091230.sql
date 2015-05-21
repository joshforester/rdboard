ALTER TABLE CP_Visit_Rel ADD `is_acquired` ENUM('default','no','yes') NOT NULL DEFAULT 'no'  AFTER `weight_penalty_assessed`;

