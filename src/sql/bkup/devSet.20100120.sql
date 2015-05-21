-- MySQL dump 10.11
--
-- Host: localhost    Database: leaderboard
-- ------------------------------------------------------
-- Server version	5.0.51a-24+lenny1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `ADMIN`
--

DROP TABLE IF EXISTS `ADMIN`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `ADMIN` (
  `id` int(10) unsigned NOT NULL,
  `username` varchar(32) NOT NULL default 'default',
  `password` varchar(32) NOT NULL default 'default',
  `is_disabled` enum('default','no','yes') NOT NULL default 'no',
  `is_deleted` enum('default','no','yes') NOT NULL default 'no',
  PRIMARY KEY  (`id`),
  KEY `username` (`username`),
  KEY `enabled_i` (`is_disabled`,`is_deleted`),
  CONSTRAINT `ADMIN_id_fkc` FOREIGN KEY (`id`) REFERENCES `PERSON` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `ADMIN`
--

LOCK TABLES `ADMIN` WRITE;
/*!40000 ALTER TABLE `ADMIN` DISABLE KEYS */;
INSERT INTO `ADMIN` VALUES (70,'josh','9a49c4c534e0eb0237eda2198f5ff985','no','no'),(71,'jackforester','bf51d173c48cfca0774c22cc2911bede','no','no'),(114,'jordan','eb83fca6f80a44c5fa5dac760a0a3e3e','no','no');
/*!40000 ALTER TABLE `ADMIN` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `AUTHORITY`
--

DROP TABLE IF EXISTS `AUTHORITY`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `AUTHORITY` (
  `id` int(10) unsigned NOT NULL,
  `authority` enum('default','ROLE_COMPETITOR','ROLE_EVENTADMIN','ROLE_PROMOTER','ROLE_ROOT') NOT NULL default 'default',
  PRIMARY KEY  (`id`,`authority`),
  CONSTRAINT `AUTHORITY_id_fkc` FOREIGN KEY (`id`) REFERENCES `PERSON` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `AUTHORITY`
--

LOCK TABLES `AUTHORITY` WRITE;
/*!40000 ALTER TABLE `AUTHORITY` DISABLE KEYS */;
INSERT INTO `AUTHORITY` VALUES (70,'ROLE_EVENTADMIN'),(70,'ROLE_PROMOTER'),(70,'ROLE_ROOT'),(71,'ROLE_EVENTADMIN'),(71,'ROLE_PROMOTER'),(71,'ROLE_ROOT'),(114,'ROLE_EVENTADMIN'),(116,'ROLE_COMPETITOR');
/*!40000 ALTER TABLE `AUTHORITY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Access_To_Rel`
--

DROP TABLE IF EXISTS `Access_To_Rel`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `Access_To_Rel` (
  `admin_id` int(10) unsigned NOT NULL,
  `event_id` int(10) unsigned NOT NULL,
  `is_deleted` enum('default','no','yes') NOT NULL default 'no',
  PRIMARY KEY  (`admin_id`,`event_id`),
  KEY `Access_To_Rel_event_id_fkc` (`event_id`),
  CONSTRAINT `Access_To_Rel_admin_id_fkc` FOREIGN KEY (`admin_id`) REFERENCES `ADMIN` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Access_To_Rel_event_id_fkc` FOREIGN KEY (`event_id`) REFERENCES `EVENT` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `Access_To_Rel`
--

LOCK TABLES `Access_To_Rel` WRITE;
/*!40000 ALTER TABLE `Access_To_Rel` DISABLE KEYS */;
/*!40000 ALTER TABLE `Access_To_Rel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `COMPARATOR`
--

DROP TABLE IF EXISTS `COMPARATOR`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `COMPARATOR` (
  `id` int(10) unsigned NOT NULL,
  `course_id` int(10) unsigned NOT NULL,
  `type` enum('default','missedcutoff','unofficial','incomplete','withdrawn','disqualified','didnotstart','mandatorycps','mandatorygroupcps','optionalcps','cpweight','lastvisitedcporder','lastvisitedcporderdeparture','lastvisitedcporderarrival','leastskippedmandatorycps','leastskippedmandatorygroupcps','leastskippedoptionalcps','leastskippedcpweight') NOT NULL default 'default',
  `tie_action` enum('default','continue','rankeven') default 'default',
  `c_order` smallint(2) unsigned NOT NULL default '0',
  `is_deleted` enum('default','no','yes') NOT NULL default 'no',
  PRIMARY KEY  (`id`),
  KEY `COMPARATOR_course_id_fkc` (`course_id`),
  CONSTRAINT `COMPARATOR_course_id_fkc` FOREIGN KEY (`course_id`) REFERENCES `COURSE` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `COMPARATOR_id_fkc` FOREIGN KEY (`id`) REFERENCES `DATA_RESOURCE` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `COMPARATOR`
--

LOCK TABLES `COMPARATOR` WRITE;
/*!40000 ALTER TABLE `COMPARATOR` DISABLE KEYS */;
INSERT INTO `COMPARATOR` VALUES (31,38,'didnotstart','rankeven',1,'no'),(32,38,'disqualified','rankeven',2,'no'),(33,38,'withdrawn','continue',3,'no'),(34,38,'unofficial','continue',4,'no'),(35,38,'lastvisitedcporder','continue',5,'no'),(36,38,'lastvisitedcporderdeparture','continue',6,'no'),(37,38,'lastvisitedcporderarrival','rankeven',7,'no'),(155,95,'leastskippedmandatorycps','continue',1,'no'),(156,95,'leastskippedoptionalcps','rankeven',2,'no');
/*!40000 ALTER TABLE `COMPARATOR` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `COMPETITOR`
--

DROP TABLE IF EXISTS `COMPETITOR`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `COMPETITOR` (
  `id` int(10) unsigned NOT NULL,
  `person_id` int(10) unsigned NOT NULL,
  `emergency_contact_first_name` varchar(32) default 'default',
  `emergency_contact_last_name` varchar(32) default 'default',
  `emergency_contact_relation` enum('default','spouse','sibling','parent','child','other') default 'default',
  `emergency_phone` varchar(24) default 'default',
  `shirt_size` enum('default','x-small','small','medium','large','x-large','xx-large','xxx-large') default 'default',
  `shoe_size` varchar(7) default 'default',
  `is_deleted` enum('default','no','yes') NOT NULL default 'no',
  PRIMARY KEY  (`id`),
  KEY `enabled_i` (`is_deleted`),
  KEY `COMPETITOR_person_id_fkc` (`person_id`),
  CONSTRAINT `COMPETITOR_person_id_fkc` FOREIGN KEY (`person_id`) REFERENCES `PERSON` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `COMPETITOR_id_fkc` FOREIGN KEY (`id`) REFERENCES `DATA_RESOURCE` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `COMPETITOR`
--

LOCK TABLES `COMPETITOR` WRITE;
/*!40000 ALTER TABLE `COMPETITOR` DISABLE KEYS */;
INSERT INTO `COMPETITOR` VALUES (72,70,'Laraleigh','Forester','spouse','770-403-8166','medium','11M','no'),(92,71,'','','spouse','','x-small','default','no'),(93,70,'Laraleigh','Forester','spouse','770-403-8166','medium','11M','no'),(119,114,'Michelle','Emmorey','spouse','','x-small','default','no'),(120,70,'Laraleigh','Forester','spouse','770-403-8166','medium','11M','no'),(123,116,'Michael','Pell','spouse','333-333-3333','x-small','','no');
/*!40000 ALTER TABLE `COMPETITOR` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `COURSE`
--

DROP TABLE IF EXISTS `COURSE`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `COURSE` (
  `id` int(10) unsigned NOT NULL,
  `event_id` int(10) unsigned NOT NULL,
  `name` varchar(64) NOT NULL default 'default',
  `is_deleted` enum('default','no','yes') NOT NULL default 'no',
  PRIMARY KEY  (`id`),
  KEY `event_id` (`event_id`,`name`),
  CONSTRAINT `COURSE_event_id_fkc` FOREIGN KEY (`event_id`) REFERENCES `EVENT` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `COURSE_id_fkc` FOREIGN KEY (`id`) REFERENCES `DATA_RESOURCE` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `COURSE`
--

LOCK TABLES `COURSE` WRITE;
/*!40000 ALTER TABLE `COURSE` DISABLE KEYS */;
INSERT INTO `COURSE` VALUES (38,41,'Race Course','no'),(50,46,'Race Course','no'),(56,55,'Full Course','no'),(95,41,'Test Course','no'),(110,41,'New Course','no');
/*!40000 ALTER TABLE `COURSE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CP`
--

DROP TABLE IF EXISTS `CP`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `CP` (
  `id` int(10) unsigned NOT NULL,
  `course_id` int(10) unsigned NOT NULL,
  `cp_order` smallint(2) unsigned NOT NULL default '0',
  `name` varchar(32) NOT NULL default 'default',
  `ta_name` varchar(32) NOT NULL default 'default',
  `northernly` int(10) unsigned NOT NULL default '0',
  `easternly` int(10) unsigned NOT NULL default '0',
  `zone_number` int(10) unsigned NOT NULL default '0',
  `zone_char` enum('default','C','D','E','F','G','H','J','K','L','M','N','P','Q','S','T','U','V','W','X') default 'default',
  `hint` varchar(256) NOT NULL default 'default',
  `altitude` smallint(6) default '0',
  `cutoff` datetime NOT NULL default '1970-01-01 00:00:00',
  `is_mandatory` enum('default','no','yes') NOT NULL default 'default',
  `is_in_mandatory_group` enum('default','no','yes') NOT NULL default 'default',
  `mandatory_group_requirement` smallint(2) unsigned default '0',
  `mandatory_group_size` smallint(2) unsigned default '0',
  `weight` smallint(5) unsigned NOT NULL default '0',
  `from_discipline` enum('default','running','trekking','mountaineering','coasteering','biking','orienteering','paddling','swimming','ropes','riverboarding','inlineskating','scootering','specialchallenge') default 'default',
  `to_discipline` enum('default','running','trekking','mountaineering','coasteering','biking','orienteering','paddling','swimming','ropes','riverboarding','inlineskating','scootering','specialchallenge') default 'default',
  `is_deleted` enum('default','no','yes') NOT NULL default 'no',
  PRIMARY KEY  (`id`),
  KEY `course_id` (`course_id`,`name`),
  CONSTRAINT `CP_course_id_fkc` FOREIGN KEY (`course_id`) REFERENCES `COURSE` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `CP_id_fkc` FOREIGN KEY (`id`) REFERENCES `DATA_RESOURCE` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `CP`
--

LOCK TABLES `CP` WRITE;
/*!40000 ALTER TABLE `CP` DISABLE KEYS */;
INSERT INTO `CP` VALUES (1,38,1,'CP2','TA1',3828564,752836,16,'S','Upper parking lot',0,'1970-01-01 00:00:00','yes','no',0,0,1,'trekking','biking','no'),(2,38,2,'CP3','TA2',3818382,753242,16,'S','Lindsey Ford campground',0,'1970-01-01 00:00:00','yes','no',0,0,1,'biking','orienteering','no'),(5,38,3,'LF1','',3819155,752980,16,'S','In big felled tree',0,'1970-01-01 00:00:00','no','no',0,0,1,'orienteering','orienteering','no'),(6,38,4,'LF2','',3819896,753290,16,'S','Off ATV trail, on peak. Stay out of cemetery to the east',0,'1970-01-01 00:00:00','no','no',0,0,1,'orienteering','orienteering','no'),(7,38,5,'LF3','',3819338,753821,16,'S','In Rock Cellar ruins',0,'1970-01-01 00:00:00','no','no',0,0,1,'orienteering','orienteering','no'),(8,38,6,'LF4','',3818060,754782,16,'S','Big tree, on downhill side of road.',0,'1970-01-01 00:00:00','no','no',0,0,1,'orienteering','orienteering','no'),(9,38,7,'LF5','',3816960,754120,16,'S','End of road, wood pile',0,'1970-01-01 00:00:00','no','no',0,0,1,'orienteering','orienteering','no'),(10,38,8,'LF6','',3816879,754774,16,'S','Beside rapids/bump upstream from beach.',0,'1970-01-01 00:00:00','no','no',0,0,1,'orienteering','orienteering','no'),(11,38,9,'LF7','',3817441,754741,16,'S','Downstream from rapids/bump.',0,'1970-01-01 00:00:00','no','no',0,0,1,'orienteering','orienteering','no'),(12,38,10,'LF8','',3817959,755441,16,'S','Pit on peak.',0,'1970-01-01 00:00:00','no','no',0,0,1,'orienteering','orienteering','no'),(13,38,11,'CP4','TA3',3818382,753242,16,'S','Lindsey Ford campground.',0,'1970-01-01 00:00:00','yes','no',0,0,1,'orienteering','biking','no'),(14,38,12,'CP5','',3815040,756423,16,'S','Under Steele Bridge.',0,'1970-01-01 00:00:00','no','no',0,0,1,'biking','biking','no'),(15,38,13,'CP6','',3807397,761622,16,'S','Off red trail, reentrant.',0,'1970-01-01 00:00:00','no','no',0,0,1,'biking','biking','no'),(16,38,14,'CP7','',3807020,762550,16,'S','Off red trail, halfbowl reentrant.',0,'1970-01-01 00:00:00','no','no',0,0,1,'biking','biking','no'),(17,38,15,'CP8','TA4',3804721,762767,16,'S','Dawson Forest parking lot.',0,'2009-05-16 01:00:00','yes','no',0,0,1,'biking','running','no'),(18,38,16,'CP9','TA5',3804721,762767,16,'S','Dawson Forest parking lot',0,'1970-01-01 00:00:00','yes','no',0,0,1,'running','trekking','no'),(19,38,17,'CP10','TA6',3805569,765449,16,'S','River Park put-in',0,'1970-01-01 00:00:00','yes','no',0,0,1,'trekking','paddling','no'),(20,38,18,'CP11','',3805326,763019,16,'S','Cool water intake - DANGER! Holes in floor!',0,'1970-01-01 00:00:00','no','no',0,0,1,'paddling','paddling','no'),(21,38,19,'CP12','',3807280,761031,16,'S','Rocks in Shoal Creek',0,'1970-01-01 00:00:00','no','no',0,0,1,'paddling','paddling','no'),(22,38,20,'CP13','TA7',3804797,757017,16,'S','Kelley Bridge takeout',0,'2010-05-16 02:30:00','yes','no',0,0,1,'paddling','trekking','no'),(51,50,1,'Start','',3456789,345678,16,'S','Starting line structure',0,'2010-10-26 08:00:00','yes','no',0,0,0,'running','running','no'),(96,95,1,'CP1','TA1',0,0,0,'default','',0,'1970-01-01 00:00:00','yes','no',0,0,1,'mountaineering','mountaineering','no'),(99,95,2,'CP2','',0,0,0,'default','',0,'1970-01-01 00:00:00','yes','no',0,0,1,'coasteering','mountaineering','no'),(100,95,3,'CP3','',0,0,0,'default','',0,'1970-01-01 00:00:00','yes','no',0,0,1,'mountaineering','mountaineering','no'),(111,110,1,'CP1','TA1',0,0,0,'default','',0,'1970-01-01 00:00:00','no','no',0,0,0,'trekking','trekking','no'),(121,110,2,'CP2','',0,0,0,'default','',0,'1970-01-01 00:00:00','no','no',0,0,0,'specialchallenge','mountaineering','no');
/*!40000 ALTER TABLE `CP` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CP_Visit_Rel`
--

DROP TABLE IF EXISTS `CP_Visit_Rel`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `CP_Visit_Rel` (
  `team_id` int(10) unsigned NOT NULL,
  `cp_id` int(10) unsigned NOT NULL,
  `division_place` smallint(5) unsigned NOT NULL default '0',
  `course_place` smallint(5) unsigned NOT NULL default '0',
  `last_modified` datetime NOT NULL default '1970-01-01 00:00:00',
  `arrival` datetime NOT NULL default '1970-01-01 00:00:00',
  `departure` datetime NOT NULL default '1970-01-01 00:00:00',
  `time_bonus_assessed` datetime NOT NULL default '1970-01-01 00:00:00',
  `time_penalty_assessed` datetime NOT NULL default '1970-01-01 00:00:00',
  `cp_bonus_assessed` tinyint(3) unsigned NOT NULL default '0',
  `cp_penalty_assessed` tinyint(3) unsigned NOT NULL default '0',
  `weight_bonus_assessed` smallint(5) unsigned NOT NULL default '0',
  `weight_penalty_assessed` smallint(5) unsigned NOT NULL default '0',
  `is_acquired` enum('default','no','yes') NOT NULL default 'no',
  `is_skipped` enum('default','no','yes') NOT NULL default 'no',
  `is_missed_cutoff` enum('default','no','yes') NOT NULL default 'no',
  `is_unofficial` enum('default','no','yes') NOT NULL default 'no',
  `is_incomplete` enum('default','no','yes') NOT NULL default 'no',
  `is_withdrawn` enum('default','no','yes') NOT NULL default 'no',
  `is_disqualified` enum('default','no','yes') NOT NULL default 'no',
  `is_deleted` enum('default','no','yes') NOT NULL default 'no',
  `bonus_assessed_reason` varchar(256) NOT NULL default 'default',
  `penalty_assessed_reason` varchar(256) NOT NULL default 'default',
  PRIMARY KEY  (`team_id`,`cp_id`),
  KEY `CP_Visit_Rel_cp_id_fkc` (`cp_id`),
  CONSTRAINT `CP_Visit_Rel_cp_id_fkc` FOREIGN KEY (`cp_id`) REFERENCES `CP` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `CP_Visit_Rel_team_id_fkc` FOREIGN KEY (`team_id`) REFERENCES `TEAM` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `CP_Visit_Rel`
--

LOCK TABLES `CP_Visit_Rel` WRITE;
/*!40000 ALTER TABLE `CP_Visit_Rel` DISABLE KEYS */;
INSERT INTO `CP_Visit_Rel` VALUES (42,1,0,0,'2010-01-12 03:37:34','2010-05-15 09:26:00','2010-05-15 09:32:00','1970-01-01 00:00:00','1970-01-01 00:00:00',0,0,0,0,'no','no','no','no','no','no','no','no','default','default'),(42,2,0,0,'2010-01-14 16:23:41','2010-05-15 09:47:00','2010-05-15 09:50:00','1970-01-01 06:00:00','1970-01-01 00:00:00',0,0,0,0,'no','no','no','no','no','no','no','no','default','default'),(42,5,0,0,'2010-01-12 02:31:15','2010-05-15 10:16:00','2010-05-15 10:21:00','1970-01-01 00:00:00','1970-01-01 00:00:00',0,0,0,0,'no','no','no','no','no','no','no','no','default','default'),(42,6,0,0,'2010-01-12 01:26:43','2010-05-15 10:47:00','2010-05-15 10:51:00','1970-01-01 22:00:00','1970-01-01 00:00:00',0,0,0,0,'no','no','no','no','no','no','no','no','default','default'),(42,7,0,0,'2010-01-06 03:31:59','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00',0,0,0,0,'yes','no','no','no','no','no','no','no','default','default'),(42,8,1,1,'2010-01-21 00:31:51','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00',0,0,0,0,'yes','no','no','no','no','no','no','no','default','default'),(42,9,2,2,'2010-01-21 00:47:52','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00',0,0,0,0,'yes','no','no','no','no','no','no','no','default','default'),(42,10,1,1,'2010-01-21 01:58:00','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00',0,0,0,0,'yes','no','no','no','no','no','no','no','default','default'),(43,1,0,0,'2010-01-12 01:27:08','2010-05-15 09:28:00','2010-05-15 09:35:00','1970-01-01 00:00:00','1970-01-01 00:00:00',0,0,0,0,'no','no','no','no','no','no','no','no','default','default'),(43,2,0,0,'2010-01-12 01:27:08','2010-05-16 09:44:00','2010-05-16 09:45:00','1970-01-01 00:00:00','1970-01-01 00:00:00',0,0,0,0,'no','no','no','no','no','no','no','no','default','default'),(43,5,0,0,'2010-01-12 01:27:09','2010-05-16 10:49:00','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00',0,0,0,0,'no','no','no','no','no','no','no','no','default','default'),(43,6,2,2,'2010-01-20 18:53:12','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00',0,0,0,0,'yes','no','no','no','no','no','no','no','default','default'),(43,7,4,4,'2010-01-21 00:34:45','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00',0,0,0,0,'yes','no','no','no','no','no','no','no','default','default'),(43,8,4,4,'2010-01-21 01:20:16','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00',0,0,0,0,'yes','no','no','no','no','no','no','no','default','default'),(43,9,4,4,'2010-01-21 01:55:35','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00',0,0,0,0,'yes','no','no','no','no','no','no','no','default','default'),(43,10,2,2,'2010-01-21 02:00:05','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00',0,0,0,0,'yes','no','no','no','no','no','no','no','default','default'),(97,96,1,1,'2010-01-18 05:21:28','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00',0,0,0,0,'no','yes','no','no','no','no','no','no','default','default'),(97,99,3,3,'2010-01-18 05:26:15','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00',0,0,0,0,'no','yes','no','no','no','no','no','no','default','default'),(98,99,1,1,'2010-01-20 18:48:43','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00',0,0,0,0,'yes','no','no','no','no','no','no','no','default','default'),(105,1,0,0,'2010-01-16 11:39:09','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00',0,0,0,0,'yes','no','no','no','no','no','no','no','default','default'),(105,2,6,6,'2010-01-21 02:12:51','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00',0,0,0,0,'yes','no','no','no','no','no','no','no','default','default'),(108,1,0,0,'2010-01-17 03:58:26','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00',2,0,0,0,'yes','no','no','no','no','no','no','no','default','default'),(108,2,0,0,'2010-01-17 03:59:34','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00',0,0,0,0,'yes','no','no','no','no','no','no','no','default','default'),(108,5,3,3,'2010-01-20 18:58:32','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00',0,0,0,0,'yes','no','no','no','no','no','no','no','default','default'),(108,6,2,2,'2010-01-21 00:19:08','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00',0,0,0,0,'yes','no','no','no','no','no','no','no','default','default'),(108,7,4,4,'2010-01-21 00:43:26','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00',0,0,0,0,'yes','no','no','no','no','no','no','no','default','default'),(108,8,4,4,'2010-01-21 01:46:06','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00',0,0,0,0,'yes','no','no','no','no','no','no','no','default','default'),(108,9,5,5,'2010-01-21 02:03:24','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00',0,0,0,0,'yes','no','no','no','no','no','no','no','default','default'),(118,111,0,0,'2010-01-07 03:41:24','2009-12-22 00:13:00','2009-12-22 01:01:00','1970-01-01 01:00:00','1970-01-01 00:00:00',0,1,0,0,'no','no','no','no','no','no','no','no','default','default'),(118,121,0,0,'2010-01-06 22:27:35','2010-01-05 17:25:00','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00',0,0,0,0,'no','no','no','no','no','no','no','no','default','default'),(122,111,0,0,'2010-01-07 03:39:59','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00',0,0,0,0,'yes','no','no','yes','no','no','no','no','default','default'),(122,121,0,0,'2010-01-06 20:40:39','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00',0,0,0,0,'no','yes','no','no','no','no','no','no','default','default'),(133,96,1,1,'2010-01-20 18:49:57','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00',0,0,0,0,'yes','no','no','no','no','no','no','no','default','default'),(133,99,2,2,'2010-01-18 05:26:15','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00',0,0,0,0,'yes','no','no','no','no','no','no','no','default','default'),(136,1,0,0,'2010-01-16 11:39:09','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00',0,0,0,0,'yes','no','no','no','no','no','no','no','default','default'),(136,2,0,0,'2010-01-17 03:44:52','2010-05-15 09:47:00','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00',0,0,0,0,'no','no','no','no','no','no','no','no','default','default'),(136,5,2,2,'2010-01-20 18:51:08','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00',0,0,0,0,'yes','no','no','no','no','no','no','no','default','default'),(136,6,3,3,'2010-01-20 18:58:51','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00',0,0,0,0,'yes','no','no','no','no','no','no','no','default','default'),(136,7,2,2,'2010-01-21 00:27:49','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00',0,0,0,0,'yes','no','no','no','no','no','no','no','default','default'),(136,8,1,1,'2010-01-21 00:28:44','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00',0,0,0,0,'yes','no','no','no','no','no','no','no','default','default'),(136,9,2,2,'2010-01-21 00:46:09','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00',0,0,0,0,'yes','no','no','no','no','no','no','no','default','default'),(136,10,1,1,'2010-01-21 00:47:20','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00',0,0,0,0,'yes','no','no','no','no','no','no','no','default','default'),(136,11,1,1,'2010-01-21 02:00:31','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00',0,0,0,0,'yes','no','no','no','no','no','no','no','default','default'),(137,1,0,0,'2010-01-17 03:57:47','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 04:00:00','1970-01-01 00:00:00',0,0,0,0,'yes','no','no','no','no','no','no','no','default','default'),(137,2,0,0,'2010-01-17 03:44:52','2010-05-15 08:47:00','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 04:00:00',0,0,0,0,'no','no','no','no','no','no','no','no','default','Suckas'),(137,5,3,3,'2010-01-20 18:58:20','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00',0,0,0,0,'yes','no','no','no','no','no','no','no','default','default'),(137,6,4,4,'2010-01-21 00:24:17','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00',0,0,0,0,'yes','no','no','no','no','no','no','no','default','default'),(137,7,2,2,'2010-01-21 00:28:16','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00',0,0,0,0,'yes','no','no','no','no','no','no','no','default','default'),(137,8,2,2,'2010-01-21 00:33:30','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00',0,0,0,0,'yes','no','no','no','no','no','no','no','default','default'),(137,9,1,1,'2010-01-21 00:34:23','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00',0,0,0,0,'yes','no','no','no','no','no','no','no','default','default'),(137,10,1,1,'2010-01-21 00:55:28','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00',0,0,0,0,'yes','no','no','no','no','no','no','no','default','default');
/*!40000 ALTER TABLE `CP_Visit_Rel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Competitor_Docs_Rel`
--

DROP TABLE IF EXISTS `Competitor_Docs_Rel`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `Competitor_Docs_Rel` (
  `competitor_id` int(10) unsigned NOT NULL,
  `event_id` int(10) unsigned NOT NULL,
  `usara` enum('default','no','yes') NOT NULL default 'no',
  `media_release` enum('default','no','yes') NOT NULL default 'no',
  `hold_harmless` enum('default','no','yes') NOT NULL default 'no',
  `doc01_name` varchar(64) NOT NULL default 'default',
  `doc01` enum('default','no','yes') NOT NULL default 'no',
  `doc02_name` varchar(64) NOT NULL default 'default',
  `doc02` enum('default','no','yes') NOT NULL default 'no',
  `doc03_name` varchar(64) NOT NULL default 'default',
  `doc03` enum('default','no','yes') NOT NULL default 'no',
  `doc04_name` varchar(64) NOT NULL default 'default',
  `doc04` enum('default','no','yes') NOT NULL default 'no',
  `doc05_name` varchar(64) NOT NULL default 'default',
  `doc05` enum('default','no','yes') NOT NULL default 'no',
  `doc06_name` varchar(64) NOT NULL default 'default',
  `doc06` enum('default','no','yes') NOT NULL default 'no',
  `doc07_name` varchar(64) NOT NULL default 'default',
  `doc07` enum('default','no','yes') NOT NULL default 'no',
  `doc08_name` varchar(64) NOT NULL default 'default',
  `doc08` enum('default','no','yes') NOT NULL default 'no',
  `doc09_name` varchar(64) NOT NULL default 'default',
  `doc09` enum('default','no','yes') NOT NULL default 'no',
  `doc10_name` varchar(64) NOT NULL default 'default',
  `doc10` enum('default','no','yes') NOT NULL default 'no',
  `is_deleted` enum('default','no','yes') NOT NULL default 'no',
  PRIMARY KEY  (`competitor_id`,`event_id`),
  KEY `Competitor_Docs_Rel_event_id_fkc` (`event_id`),
  CONSTRAINT `Competitor_Docs_Rel_competitor_id_fkc` FOREIGN KEY (`competitor_id`) REFERENCES `COMPETITOR` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `Competitor_Docs_Rel_event_id_fkc` FOREIGN KEY (`event_id`) REFERENCES `EVENT` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `Competitor_Docs_Rel`
--

LOCK TABLES `Competitor_Docs_Rel` WRITE;
/*!40000 ALTER TABLE `Competitor_Docs_Rel` DISABLE KEYS */;
/*!40000 ALTER TABLE `Competitor_Docs_Rel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DATA_RESOURCE`
--

DROP TABLE IF EXISTS `DATA_RESOURCE`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `DATA_RESOURCE` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `admin_id` int(10) unsigned default NULL,
  PRIMARY KEY  (`id`),
  KEY `DATA_RESOURCE_admin_id_fkc` (`admin_id`),
  CONSTRAINT `DATA_RESOURCE_admin_id_fkc` FOREIGN KEY (`admin_id`) REFERENCES `ADMIN` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=157 DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `DATA_RESOURCE`
--

LOCK TABLES `DATA_RESOURCE` WRITE;
/*!40000 ALTER TABLE `DATA_RESOURCE` DISABLE KEYS */;
INSERT INTO `DATA_RESOURCE` VALUES (1,70),(2,70),(5,70),(6,70),(7,70),(8,70),(9,70),(10,70),(11,70),(12,70),(13,70),(14,70),(15,70),(16,70),(17,70),(18,70),(19,70),(20,70),(21,70),(22,70),(28,70),(31,70),(32,70),(33,70),(34,70),(35,70),(36,70),(37,70),(38,70),(40,70),(41,70),(42,70),(43,70),(55,70),(56,70),(57,70),(60,70),(70,70),(71,70),(72,70),(73,70),(91,70),(92,70),(93,70),(95,70),(96,70),(97,70),(98,70),(99,70),(100,70),(105,70),(108,70),(110,70),(111,70),(112,70),(114,70),(115,70),(116,70),(117,70),(118,70),(119,70),(120,70),(121,70),(122,70),(123,70),(124,70),(128,70),(132,70),(133,70),(134,70),(135,70),(136,70),(137,70),(138,70),(139,70),(140,70),(141,70),(142,70),(143,70),(144,70),(145,70),(146,70),(147,70),(148,70),(149,70),(150,70),(151,70),(152,70),(155,70),(156,70),(46,71),(47,71),(48,71),(49,71),(50,71),(51,71),(52,71),(53,71),(54,71);
/*!40000 ALTER TABLE `DATA_RESOURCE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DIVISION`
--

DROP TABLE IF EXISTS `DIVISION`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `DIVISION` (
  `id` int(10) unsigned NOT NULL,
  `event_id` int(10) unsigned NOT NULL,
  `name` varchar(64) NOT NULL default 'default',
  `member_count` tinyint(1) unsigned NOT NULL default '0',
  `consistency` enum('default','coed','open','male','female','masters','masterscoed','mastersopen','mastersmale','mastersfemale') NOT NULL default 'default',
  `is_elite` enum('default','no','yes') NOT NULL default 'no',
  `is_deleted` enum('default','no','yes') NOT NULL default 'no',
  PRIMARY KEY  (`id`),
  KEY `event_id` (`event_id`,`name`),
  CONSTRAINT `DIVISION_event_id_fkc` FOREIGN KEY (`event_id`) REFERENCES `EVENT` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `DIVISION_id_fkc` FOREIGN KEY (`id`) REFERENCES `DATA_RESOURCE` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `DIVISION`
--

LOCK TABLES `DIVISION` WRITE;
/*!40000 ALTER TABLE `DIVISION` DISABLE KEYS */;
INSERT INTO `DIVISION` VALUES (40,41,'3-Person Coed (Elite)',3,'coed','yes','no'),(47,46,'Co-Ed Elite',3,'coed','yes','no'),(48,46,'Testosterone',3,'male','no','no'),(49,46,'Estrogen',3,'female','no','no'),(112,41,'Hes',2,'male','no','no'),(128,41,'pukey white',2,'open','no','no');
/*!40000 ALTER TABLE `DIVISION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `EVENT`
--

DROP TABLE IF EXISTS `EVENT`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `EVENT` (
  `id` int(10) unsigned NOT NULL,
  `name` varchar(64) NOT NULL default 'default',
  `start_time` datetime NOT NULL default '1970-01-01 00:00:00',
  `end_time` datetime NOT NULL default '1970-01-01 00:00:00',
  `city` varchar(32) NOT NULL default 'default',
  `region` varchar(32) NOT NULL default 'default',
  `country` varchar(32) NOT NULL default 'default',
  `zip` mediumint(5) unsigned NOT NULL default '0',
  `is_deleted` enum('default','no','yes') NOT NULL default 'no',
  PRIMARY KEY  (`id`),
  KEY `name` (`name`,`start_time`),
  CONSTRAINT `EVENT_id_fkc` FOREIGN KEY (`id`) REFERENCES `DATA_RESOURCE` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `EVENT`
--

LOCK TABLES `EVENT` WRITE;
/*!40000 ALTER TABLE `EVENT` DISABLE KEYS */;
INSERT INTO `EVENT` VALUES (41,'Atomic Adventure Race','2010-05-16 00:00:00','2010-05-17 00:00:00','Dawsonville','GA','US',30354,'no'),(46,'Biohazard AR','2010-10-30 00:00:00','2010-10-31 00:00:00','Dawsonville','GA','US',30534,'no'),(55,'Party City AR','2009-10-01 00:00:00','1970-01-01 00:00:00','Dawsonville','GA','US',30534,'no');
/*!40000 ALTER TABLE `EVENT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `IDENTITY`
--

DROP TABLE IF EXISTS `IDENTITY`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `IDENTITY` (
  `id` int(10) unsigned NOT NULL,
  `is_deleted` enum('default','no','yes') NOT NULL default 'no',
  PRIMARY KEY  (`id`),
  CONSTRAINT `IDENTITY_id_fkc` FOREIGN KEY (`id`) REFERENCES `DATA_RESOURCE` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `IDENTITY`
--

LOCK TABLES `IDENTITY` WRITE;
/*!40000 ALTER TABLE `IDENTITY` DISABLE KEYS */;
INSERT INTO `IDENTITY` VALUES (57,'no'),(60,'no'),(115,'no'),(117,'no');
/*!40000 ALTER TABLE `IDENTITY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PERSON`
--

DROP TABLE IF EXISTS `PERSON`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `PERSON` (
  `id` int(10) unsigned NOT NULL,
  `identity_id` int(10) unsigned NOT NULL,
  `email` varchar(64) NOT NULL default 'default',
  `first_name` varchar(32) NOT NULL default 'default',
  `last_name` varchar(32) NOT NULL default 'default',
  `city` varchar(32) NOT NULL default 'default',
  `region` varchar(32) NOT NULL default 'default',
  `country` varchar(32) NOT NULL default 'default',
  `zip` mediumint(5) unsigned NOT NULL default '0',
  `gender` enum('default','male','female') default 'default',
  `birthday` datetime default '1970-01-01 00:00:00',
  `home_phone` varchar(24) default 'default',
  `cell_phone` varchar(24) default 'default',
  `work_phone` varchar(24) default 'default',
  `default_emergency_contact_first_name` varchar(32) default 'default',
  `default_emergency_contact_last_name` varchar(32) default 'default',
  `default_emergency_contact_relation` enum('default','spouse','sibling','parent','child','other') default 'default',
  `default_emergency_phone` varchar(24) default 'default',
  `default_shirt_size` enum('default','x-small','small','medium','large','x-large','xx-large','xxx-large') default 'default',
  `default_shoe_size` varchar(7) default 'default',
  `occupation` varchar(64) default 'default',
  `self_description` text,
  `hobbies_interests` text,
  `favorite_quote` text,
  `favorite_things` text,
  `website` text,
  `is_disabled` enum('default','no','yes') NOT NULL default 'no',
  `is_deleted` enum('default','no','yes') NOT NULL default 'no',
  PRIMARY KEY  (`id`),
  KEY `email_i` (`email`),
  KEY `zip_i` (`zip`),
  KEY `enabled_i` (`is_disabled`,`is_deleted`),
  KEY `PERSON_identity_id_fkc` (`identity_id`),
  CONSTRAINT `PERSON_id_fkc` FOREIGN KEY (`id`) REFERENCES `DATA_RESOURCE` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `PERSON_identity_id_fkc` FOREIGN KEY (`identity_id`) REFERENCES `IDENTITY` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `PERSON`
--

LOCK TABLES `PERSON` WRITE;
/*!40000 ALTER TABLE `PERSON` DISABLE KEYS */;
INSERT INTO `PERSON` VALUES (70,57,'josh@teamrocgear.com','Josh','Forester','Cumming','GA','US',30041,'male','1982-03-12 00:00:00','678-513-2279','770-403-7846','678-319-7041','Laraleigh','Forester','spouse','770-403-8166','medium','11M','default',NULL,NULL,NULL,NULL,'http://www.teamrocgear.com','no','no'),(71,60,'jack@4lph41337.com','Jack','Forester','Dawsonville','GA','US',30534,'male','1973-05-01 00:00:00','678-455-9048','678-234-8069','678-624-6010','','','spouse','','default','default','default',NULL,NULL,NULL,NULL,'','no','no'),(114,115,'jordan@teamrocgear.com','Jordan','Emmorey','Dawsonville','GA','US',30354,'male','1982-04-04 00:00:00','','678-230-0148','','Michelle','Emmorey','spouse','','default','default','default',NULL,NULL,NULL,NULL,'http://www.teamrocgear.com','no','no'),(116,117,'laura@teamrocgear.com','Laura','Pell','Atlanta','GA','US',30041,'female','1975-01-01 00:00:00','','222-222-2222','','Michael','Pell','spouse','333-333-3333','x-small','','default',NULL,NULL,NULL,NULL,'http://www.teamrocgear.com','no','no');
/*!40000 ALTER TABLE `PERSON` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RANK`
--

DROP TABLE IF EXISTS `RANK`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `RANK` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `team_id` int(10) unsigned NOT NULL,
  `time` datetime NOT NULL default '1970-01-01 00:00:00',
  `division_place` smallint(5) unsigned NOT NULL default '0',
  `course_place` smallint(5) unsigned NOT NULL default '0',
  `is_deleted` enum('default','no','yes') NOT NULL default 'no',
  PRIMARY KEY  (`id`),
  KEY `team_id` (`team_id`,`time`),
  CONSTRAINT `RANK_team_id_fkc` FOREIGN KEY (`team_id`) REFERENCES `TEAM` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=275 DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `RANK`
--

LOCK TABLES `RANK` WRITE;
/*!40000 ALTER TABLE `RANK` DISABLE KEYS */;
INSERT INTO `RANK` VALUES (39,133,'2010-01-20 18:45:47',1,1,'no'),(40,98,'2010-01-20 18:45:47',2,2,'no'),(41,97,'2010-01-20 18:45:47',3,3,'no'),(42,134,'2010-01-20 18:45:47',1,4,'no'),(43,98,'2010-01-20 18:48:43',1,1,'no'),(44,133,'2010-01-20 18:48:43',2,2,'no'),(45,97,'2010-01-20 18:48:43',3,3,'no'),(46,134,'2010-01-20 18:48:43',1,4,'no'),(47,133,'2010-01-20 18:49:57',1,1,'no'),(48,98,'2010-01-20 18:49:57',2,2,'no'),(49,97,'2010-01-20 18:49:57',3,3,'no'),(50,134,'2010-01-20 18:49:57',1,4,'no'),(51,42,'2010-01-20 18:51:08',1,1,'no'),(52,136,'2010-01-20 18:51:08',2,2,'no'),(53,43,'2010-01-20 18:51:08',3,3,'no'),(54,108,'2010-01-20 18:51:08',4,4,'no'),(55,137,'2010-01-20 18:51:08',5,5,'no'),(56,105,'2010-01-20 18:51:08',6,6,'no'),(57,91,'2010-01-20 18:51:08',7,7,'no'),(58,42,'2010-01-20 18:53:12',1,1,'no'),(59,43,'2010-01-20 18:53:12',2,2,'no'),(60,136,'2010-01-20 18:53:12',3,3,'no'),(61,108,'2010-01-20 18:53:12',4,4,'no'),(62,137,'2010-01-20 18:53:12',5,5,'no'),(63,105,'2010-01-20 18:53:12',6,6,'no'),(64,91,'2010-01-20 18:53:12',7,7,'no'),(65,42,'2010-01-20 18:58:20',1,1,'no'),(66,43,'2010-01-20 18:58:20',2,2,'no'),(67,137,'2010-01-20 18:58:20',3,3,'no'),(68,136,'2010-01-20 18:58:20',4,4,'no'),(69,108,'2010-01-20 18:58:20',5,5,'no'),(70,105,'2010-01-20 18:58:20',6,6,'no'),(71,91,'2010-01-20 18:58:20',7,7,'no'),(72,42,'2010-01-20 18:58:32',1,1,'no'),(73,43,'2010-01-20 18:58:32',2,2,'no'),(74,108,'2010-01-20 18:58:32',3,3,'no'),(75,137,'2010-01-20 18:58:32',4,4,'no'),(76,136,'2010-01-20 18:58:32',5,5,'no'),(77,105,'2010-01-20 18:58:32',6,6,'no'),(78,91,'2010-01-20 18:58:32',7,7,'no'),(79,42,'2010-01-20 18:58:51',1,1,'no'),(80,43,'2010-01-20 18:58:51',2,2,'no'),(81,136,'2010-01-20 18:58:51',3,3,'no'),(82,108,'2010-01-20 18:58:51',4,4,'no'),(83,137,'2010-01-20 18:58:51',5,5,'no'),(84,105,'2010-01-20 18:58:51',6,6,'no'),(85,91,'2010-01-20 18:58:51',7,7,'no'),(114,42,'2010-01-21 00:19:08',1,1,'no'),(115,108,'2010-01-21 00:19:08',2,2,'no'),(116,43,'2010-01-21 00:19:08',3,3,'no'),(117,136,'2010-01-21 00:19:08',4,4,'no'),(118,137,'2010-01-21 00:19:08',5,5,'no'),(119,105,'2010-01-21 00:19:08',6,6,'no'),(120,91,'2010-01-21 00:19:08',7,7,'no'),(121,42,'2010-01-21 00:24:17',1,1,'no'),(122,108,'2010-01-21 00:24:17',2,2,'no'),(123,43,'2010-01-21 00:24:17',3,3,'no'),(124,137,'2010-01-21 00:24:17',4,4,'no'),(125,136,'2010-01-21 00:24:17',5,5,'no'),(126,105,'2010-01-21 00:24:17',6,6,'no'),(127,91,'2010-01-21 00:24:17',7,7,'no'),(128,42,'2010-01-21 00:27:49',1,1,'no'),(129,136,'2010-01-21 00:27:49',2,2,'no'),(130,108,'2010-01-21 00:27:49',3,3,'no'),(131,43,'2010-01-21 00:27:49',4,4,'no'),(132,137,'2010-01-21 00:27:49',5,5,'no'),(133,105,'2010-01-21 00:27:49',6,6,'no'),(134,91,'2010-01-21 00:27:49',7,7,'no'),(135,42,'2010-01-21 00:28:16',1,1,'no'),(136,137,'2010-01-21 00:28:16',2,2,'no'),(137,136,'2010-01-21 00:28:16',3,3,'no'),(138,108,'2010-01-21 00:28:16',4,4,'no'),(139,43,'2010-01-21 00:28:16',5,5,'no'),(140,105,'2010-01-21 00:28:16',6,6,'no'),(141,91,'2010-01-21 00:28:16',7,7,'no'),(142,136,'2010-01-21 00:28:45',1,1,'no'),(143,42,'2010-01-21 00:28:45',2,2,'no'),(144,137,'2010-01-21 00:28:45',3,3,'no'),(145,108,'2010-01-21 00:28:45',4,4,'no'),(146,43,'2010-01-21 00:28:45',5,5,'no'),(147,105,'2010-01-21 00:28:45',6,6,'no'),(148,91,'2010-01-21 00:28:45',7,7,'no'),(149,42,'2010-01-21 00:31:51',1,1,'no'),(150,136,'2010-01-21 00:31:51',2,2,'no'),(151,137,'2010-01-21 00:31:51',3,3,'no'),(152,108,'2010-01-21 00:31:51',4,4,'no'),(153,43,'2010-01-21 00:31:51',5,5,'no'),(154,105,'2010-01-21 00:31:51',6,6,'no'),(155,91,'2010-01-21 00:31:51',7,7,'no'),(156,42,'2010-01-21 00:33:30',1,1,'no'),(157,137,'2010-01-21 00:33:30',2,2,'no'),(158,136,'2010-01-21 00:33:30',3,3,'no'),(159,108,'2010-01-21 00:33:30',4,4,'no'),(160,43,'2010-01-21 00:33:30',5,5,'no'),(161,105,'2010-01-21 00:33:30',6,6,'no'),(162,91,'2010-01-21 00:33:30',7,7,'no'),(163,137,'2010-01-21 00:34:23',1,1,'no'),(164,42,'2010-01-21 00:34:23',2,2,'no'),(165,136,'2010-01-21 00:34:23',3,3,'no'),(166,108,'2010-01-21 00:34:23',4,4,'no'),(167,43,'2010-01-21 00:34:23',5,5,'no'),(168,105,'2010-01-21 00:34:23',6,6,'no'),(169,91,'2010-01-21 00:34:23',7,7,'no'),(170,137,'2010-01-21 00:34:45',1,1,'no'),(171,42,'2010-01-21 00:34:45',2,2,'no'),(172,136,'2010-01-21 00:34:45',3,3,'no'),(173,43,'2010-01-21 00:34:45',4,4,'no'),(174,108,'2010-01-21 00:34:45',5,5,'no'),(175,105,'2010-01-21 00:34:45',6,6,'no'),(176,91,'2010-01-21 00:34:45',7,7,'no'),(177,137,'2010-01-21 00:43:26',1,1,'no'),(178,42,'2010-01-21 00:43:26',2,2,'no'),(179,136,'2010-01-21 00:43:26',3,3,'no'),(180,108,'2010-01-21 00:43:26',4,4,'no'),(181,43,'2010-01-21 00:43:26',5,5,'no'),(182,105,'2010-01-21 00:43:26',6,6,'no'),(183,91,'2010-01-21 00:43:26',7,7,'no'),(184,137,'2010-01-21 00:46:09',1,1,'no'),(185,136,'2010-01-21 00:46:09',2,2,'no'),(186,42,'2010-01-21 00:46:09',3,3,'no'),(187,108,'2010-01-21 00:46:09',4,4,'no'),(188,43,'2010-01-21 00:46:09',5,5,'no'),(189,105,'2010-01-21 00:46:09',6,6,'no'),(190,91,'2010-01-21 00:46:09',7,7,'no'),(191,136,'2010-01-21 00:47:20',1,1,'no'),(192,137,'2010-01-21 00:47:20',2,2,'no'),(193,42,'2010-01-21 00:47:20',3,3,'no'),(194,108,'2010-01-21 00:47:20',4,4,'no'),(195,43,'2010-01-21 00:47:20',5,5,'no'),(196,105,'2010-01-21 00:47:20',6,6,'no'),(197,91,'2010-01-21 00:47:20',7,7,'no'),(198,136,'2010-01-21 00:47:52',1,1,'no'),(199,42,'2010-01-21 00:47:52',2,2,'no'),(200,137,'2010-01-21 00:47:52',3,3,'no'),(201,108,'2010-01-21 00:47:52',4,4,'no'),(202,43,'2010-01-21 00:47:52',5,5,'no'),(203,105,'2010-01-21 00:47:52',6,6,'no'),(204,91,'2010-01-21 00:47:52',7,7,'no'),(205,137,'2010-01-21 00:55:28',1,1,'no'),(206,136,'2010-01-21 00:55:28',2,2,'no'),(207,42,'2010-01-21 00:55:28',3,3,'no'),(208,108,'2010-01-21 00:55:28',4,4,'no'),(209,43,'2010-01-21 00:55:28',5,5,'no'),(210,105,'2010-01-21 00:55:28',6,6,'no'),(211,91,'2010-01-21 00:55:28',7,7,'no'),(212,137,'2010-01-21 01:20:16',1,1,'no'),(213,136,'2010-01-21 01:20:16',2,2,'no'),(214,42,'2010-01-21 01:20:16',3,3,'no'),(215,43,'2010-01-21 01:20:16',4,4,'no'),(216,108,'2010-01-21 01:20:16',5,5,'no'),(217,105,'2010-01-21 01:20:16',6,6,'no'),(218,91,'2010-01-21 01:20:16',7,7,'no'),(219,137,'2010-01-21 01:46:06',1,1,'no'),(220,136,'2010-01-21 01:46:06',2,2,'no'),(221,42,'2010-01-21 01:46:06',3,3,'no'),(222,108,'2010-01-21 01:46:06',4,4,'no'),(223,43,'2010-01-21 01:46:06',5,5,'no'),(224,105,'2010-01-21 01:46:06',6,6,'no'),(225,91,'2010-01-21 01:46:06',7,7,'no'),(226,137,'2010-01-21 01:55:35',1,1,'no'),(227,136,'2010-01-21 01:55:35',2,2,'no'),(228,42,'2010-01-21 01:55:35',3,3,'no'),(229,43,'2010-01-21 01:55:35',4,4,'no'),(230,108,'2010-01-21 01:55:35',5,5,'no'),(231,105,'2010-01-21 01:55:35',6,6,'no'),(232,91,'2010-01-21 01:55:35',7,7,'no'),(233,42,'2010-01-21 01:58:00',1,1,'no'),(234,137,'2010-01-21 01:58:00',2,2,'no'),(235,136,'2010-01-21 01:58:00',3,3,'no'),(236,43,'2010-01-21 01:58:00',4,4,'no'),(237,108,'2010-01-21 01:58:00',5,5,'no'),(238,105,'2010-01-21 01:58:00',6,6,'no'),(239,91,'2010-01-21 01:58:00',7,7,'no'),(240,42,'2010-01-21 02:00:05',1,1,'no'),(241,43,'2010-01-21 02:00:05',2,2,'no'),(242,137,'2010-01-21 02:00:05',3,3,'no'),(243,136,'2010-01-21 02:00:05',4,4,'no'),(244,108,'2010-01-21 02:00:05',5,5,'no'),(245,105,'2010-01-21 02:00:05',6,6,'no'),(246,91,'2010-01-21 02:00:05',7,7,'no'),(247,136,'2010-01-21 02:00:31',1,1,'no'),(248,42,'2010-01-21 02:00:31',2,2,'no'),(249,43,'2010-01-21 02:00:31',3,3,'no'),(250,137,'2010-01-21 02:00:31',4,4,'no'),(251,108,'2010-01-21 02:00:31',5,5,'no'),(252,105,'2010-01-21 02:00:31',6,6,'no'),(253,91,'2010-01-21 02:00:31',7,7,'no'),(254,136,'2010-01-21 02:03:24',1,1,'no'),(255,42,'2010-01-21 02:03:24',2,2,'no'),(256,43,'2010-01-21 02:03:24',3,3,'no'),(257,137,'2010-01-21 02:03:24',4,4,'no'),(258,108,'2010-01-21 02:03:24',5,5,'no'),(259,105,'2010-01-21 02:03:24',6,6,'no'),(260,91,'2010-01-21 02:03:24',7,7,'no'),(268,136,'2010-01-21 02:12:52',1,1,'no'),(269,42,'2010-01-21 02:12:52',2,2,'no'),(270,43,'2010-01-21 02:12:52',3,3,'no'),(271,137,'2010-01-21 02:12:52',4,4,'no'),(272,108,'2010-01-21 02:12:52',5,5,'no'),(273,105,'2010-01-21 02:12:52',6,6,'no'),(274,91,'2010-01-21 02:12:52',7,7,'no');
/*!40000 ALTER TABLE `RANK` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TEAM`
--

DROP TABLE IF EXISTS `TEAM`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `TEAM` (
  `id` int(10) unsigned NOT NULL,
  `name` varchar(64) NOT NULL default 'default',
  `course_id` int(10) unsigned NOT NULL,
  `division_id` int(10) unsigned NOT NULL,
  `number` smallint(5) unsigned NOT NULL default '0',
  `registration_time` datetime NOT NULL default '1970-01-01 00:00:00',
  `division_place` smallint(5) unsigned NOT NULL default '0',
  `course_place` smallint(5) unsigned NOT NULL default '0',
  `is_deleted` enum('default','no','yes') NOT NULL default 'no',
  PRIMARY KEY  (`id`),
  KEY `TEAM_course_id_fkc` (`course_id`),
  KEY `TEAM_division_id_fkc` (`division_id`),
  CONSTRAINT `TEAM_course_id_fkc` FOREIGN KEY (`course_id`) REFERENCES `COURSE` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `TEAM_division_id_fkc` FOREIGN KEY (`division_id`) REFERENCES `DIVISION` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `TEAM_id_fkc` FOREIGN KEY (`id`) REFERENCES `DATA_RESOURCE` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `TEAM`
--

LOCK TABLES `TEAM` WRITE;
/*!40000 ALTER TABLE `TEAM` DISABLE KEYS */;
INSERT INTO `TEAM` VALUES (42,'Team ROC Gear/4LPH4 1337 - Tequila',38,40,1,'1970-01-01 00:00:00',0,0,'no'),(43,'Team ROC Gear/4LPH4 1337 - Lime',38,40,2,'1970-01-01 00:00:00',0,0,'no'),(52,'Jack\'s Jills',50,47,1,'1970-01-01 00:00:00',0,0,'no'),(53,'Jack\'s Jacks',50,47,2,'1970-01-01 00:00:00',0,0,'no'),(54,'Jack\'s Sheheits',50,47,1,'1970-01-01 00:00:00',0,0,'no'),(91,'bah',38,40,3,'2009-10-29 00:43:02',0,0,'no'),(97,'Team 3',95,40,1,'1970-01-01 00:00:00',0,0,'no'),(98,'Test Team 3',95,40,2,'1970-01-01 00:00:00',0,0,'no'),(105,'fubar',38,40,4,'1970-01-01 00:00:00',0,0,'no'),(108,'tool',38,40,5,'1970-01-01 00:00:00',0,0,'no'),(118,'He Is It',110,40,1,'2009-12-15 15:37:43',0,0,'no'),(122,'She is It',110,40,2,'2009-12-16 17:48:03',0,0,'no'),(132,'3-Person Coed',110,40,3,'1970-01-01 00:00:00',0,0,'no'),(133,'beaky',95,40,3,'1970-01-01 00:00:00',0,0,'no'),(134,'perky',95,112,4,'1970-01-01 00:00:00',0,0,'no'),(135,'Super Troopers',110,40,4,'1970-01-01 00:00:00',0,0,'no'),(136,'Super Villians',38,40,6,'1970-01-01 00:00:00',0,0,'no'),(137,'Wacky Fun Whitey',38,40,7,'1970-01-01 00:00:00',0,0,'no'),(138,'Santa\'s Elves',110,40,5,'1970-01-01 00:00:00',0,0,'no'),(139,'Hemi Cuda',110,40,6,'1970-01-01 00:00:00',0,0,'no'),(140,'Browsers',110,40,7,'1970-01-01 00:00:00',0,0,'no'),(141,'Tank',110,40,8,'1970-01-01 00:00:00',0,0,'no'),(142,'Onerous',110,40,9,'1970-01-01 00:00:00',0,0,'no'),(143,'Whack-A-Mole',110,40,10,'1970-01-01 00:00:00',0,0,'no'),(144,'Allegion',110,40,11,'1970-01-01 00:00:00',0,0,'no'),(145,'Tuna Fish',110,40,12,'1970-01-01 00:00:00',0,0,'no'),(146,'Grapevine',110,40,13,'1970-01-01 00:00:00',0,0,'no'),(147,'nuun/Feed the Machine',110,40,14,'1970-01-01 00:00:00',0,0,'no'),(148,'Targetted',110,40,15,'1970-01-01 00:00:00',0,0,'no'),(149,'Don\'t Tread on Me',110,40,16,'1970-01-01 00:00:00',0,0,'no'),(150,'Distraught',110,40,17,'1970-01-01 00:00:00',0,0,'no'),(151,'Babbles and Canyd',110,40,18,'1970-01-01 00:00:00',0,0,'no'),(152,'Life as we know it',110,40,19,'1970-01-01 00:00:00',0,0,'no');
/*!40000 ALTER TABLE `TEAM` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Team_Instance_Rel`
--

DROP TABLE IF EXISTS `Team_Instance_Rel`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `Team_Instance_Rel` (
  `team_id` int(10) unsigned NOT NULL,
  `competitor_id` int(10) unsigned NOT NULL,
  `is_captain` enum('default','no','yes') NOT NULL default 'no',
  `is_deleted` enum('default','no','yes') NOT NULL default 'no',
  PRIMARY KEY  (`team_id`,`competitor_id`),
  KEY `Team_Instance_Rel_competitor_id_fkc` (`competitor_id`),
  CONSTRAINT `Team_Instance_Rel_competitor_id_fkc` FOREIGN KEY (`competitor_id`) REFERENCES `COMPETITOR` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Team_Instance_Rel_team_id_fkc` FOREIGN KEY (`team_id`) REFERENCES `TEAM` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `Team_Instance_Rel`
--

LOCK TABLES `Team_Instance_Rel` WRITE;
/*!40000 ALTER TABLE `Team_Instance_Rel` DISABLE KEYS */;
INSERT INTO `Team_Instance_Rel` VALUES (58,7,'yes','no'),(58,8,'no','no'),(58,9,'no','no'),(91,92,'yes','no'),(91,93,'no','no'),(118,119,'yes','no'),(122,120,'no','no'),(122,123,'yes','no');
/*!40000 ALTER TABLE `Team_Instance_Rel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UPD`
--

DROP TABLE IF EXISTS `UPD`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `UPD` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `team_id` int(10) unsigned NOT NULL,
  `time` datetime NOT NULL default '1970-01-01 00:00:00',
  `type` enum('default','CP Visit','Rank') NOT NULL default 'default',
  `message` text,
  `is_deleted` enum('default','no','yes') NOT NULL default 'no',
  PRIMARY KEY  (`id`),
  KEY `UPD_team_id_fkc` (`team_id`),
  CONSTRAINT `UPD_team_id_fkc` FOREIGN KEY (`team_id`) REFERENCES `TEAM` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `UPD`
--

LOCK TABLES `UPD` WRITE;
/*!40000 ALTER TABLE `UPD` DISABLE KEYS */;
INSERT INTO `UPD` VALUES (5,98,'2010-01-20 18:45:47','CP Visit','Team #2 - Test Team 3 at CP2/ updated.','no'),(6,98,'2010-01-20 18:45:47','Rank','Team #2 - Test Team 3 is now ranked at 2 in their division and 2 in the race.','no'),(7,98,'2010-01-20 18:48:43','CP Visit','Team #2 - Test Team 3 at CP2/ updated.','no'),(8,98,'2010-01-20 18:48:43','Rank','Team #2 - Test Team 3 moved up 1 in their division and moved up 1 in the race.','no'),(9,133,'2010-01-20 18:49:57','CP Visit','Team #3 - beaky at CP1/TA1 updated.','no'),(10,133,'2010-01-20 18:49:57','Rank','Team #3 - beaky moved up 1 in their division and moved up 1 in the race.','no'),(11,136,'2010-01-20 18:51:08','CP Visit','Team #6 - Super Villians at LF1/ updated.','no'),(12,136,'2010-01-20 18:51:08','Rank','Team #6 - Super Villians is now ranked at 2 in their division and 2 in the race.','no'),(13,43,'2010-01-20 18:53:12','CP Visit','Team #2 - Team ROC Gear/4LPH4 1337 - Lime at LF2/ updated.','no'),(14,43,'2010-01-20 18:53:12','Rank','Team #2 - Team ROC Gear/4LPH4 1337 - Lime moved up 1 in their division and moved up 1 in the race.','no'),(15,137,'2010-01-20 18:58:20','CP Visit','Team #7 - Wacky Fun Whitey at LF1 updated.','no'),(16,137,'2010-01-20 18:58:20','Rank','Team #7 - Wacky Fun Whitey moved up 2 in their division and moved up 2 in the race.','no'),(17,108,'2010-01-20 18:58:32','CP Visit','Team #5 - tool at LF1 updated.','no'),(18,108,'2010-01-20 18:58:32','Rank','Team #5 - tool moved up 2 in their division and moved up 2 in the race.','no'),(19,136,'2010-01-20 18:58:51','CP Visit','Team #6 - Super Villians at LF2 updated.','no'),(20,136,'2010-01-20 18:58:51','Rank','Team #6 - Super Villians moved up 2 in their division and moved up 2 in the race.','no'),(25,108,'2010-01-21 00:19:08','CP Visit','Team #5 - tool at LF2 updated.','no'),(26,108,'2010-01-21 00:19:08','Rank','Team #5 - tool moved ahead of 1 - Team ROC Gear/4LPH4 1337 - Tequila and moved ahead of 1 - Team ROC Gear/4LPH4 1337 - Tequila.','no'),(27,137,'2010-01-21 00:24:17','CP Visit','Team #7 - Wacky Fun Whitey at LF2 updated.','no'),(28,137,'2010-01-21 00:24:17','Rank','Team #7 - Wacky Fun Whitey moved ahead of 2 - Team ROC Gear/4LPH4 1337 - Lime in their division and moved ahead of 2 - Team ROC Gear/4LPH4 1337 - Lime.','no'),(29,136,'2010-01-21 00:27:49','CP Visit','Team #6 - Super Villians at LF3 updated.','no'),(30,136,'2010-01-21 00:27:49','Rank','Team #6 - Super Villians moved up 3 places in their division and moved up 3 places overall.','no'),(31,137,'2010-01-21 00:28:16','CP Visit','Team #7 - Wacky Fun Whitey at LF3 updated.','no'),(32,137,'2010-01-21 00:28:16','Rank','Team #7 - Wacky Fun Whitey moved up 3 places in their division and moved up 3 places overall.','no'),(33,136,'2010-01-21 00:28:44','CP Visit','Team #6 - Super Villians at LF4 updated.','no'),(34,136,'2010-01-21 00:28:45','Rank','Team #6 - Super Villians moved ahead of  in their division and moved ahead of  overall.','no'),(35,42,'2010-01-21 00:31:51','CP Visit','Team #1 - Team ROC Gear/4LPH4 1337 - Tequila at LF4 updated.','no'),(36,42,'2010-01-21 00:31:51','Rank','Team #1 - Team ROC Gear/4LPH4 1337 - Tequila moved ahead of  in their division and moved ahead of  overall.','no'),(37,137,'2010-01-21 00:33:30','CP Visit','Team #7 - Wacky Fun Whitey at LF4 updated.','no'),(38,137,'2010-01-21 00:33:30','Rank','Team #7 - Wacky Fun Whitey moved ahead of Team #1 - Team ROC Gear/4LPH4 1337 - Tequila in their division and moved ahead of Team #1 - Team ROC Gear/4LPH4 1337 - Tequila overall.','no'),(39,137,'2010-01-21 00:34:23','CP Visit','Team #7 - Wacky Fun Whitey at LF5 updated.','no'),(40,137,'2010-01-21 00:34:23','Rank','Team #7 - Wacky Fun Whitey moved ahead of  in their division and moved ahead of  overall.','no'),(41,43,'2010-01-21 00:34:45','CP Visit','Team #2 - Team ROC Gear/4LPH4 1337 - Lime at LF3 updated.','no'),(42,43,'2010-01-21 00:34:45','Rank','Team #2 - Team ROC Gear/4LPH4 1337 - Lime moved ahead of Team #2 - Team ROC Gear/4LPH4 1337 - Lime in their division and moved ahead of Team #2 - Team ROC Gear/4LPH4 1337 - Lime overall.','no'),(43,108,'2010-01-21 00:43:26','CP Visit','Team #5 - tool at LF3 updated.','no'),(44,108,'2010-01-21 00:43:26','Rank','Team #5 - tool moved ahead of Team #2 - Team ROC Gear/4LPH4 1337 - Lime in their division and moved ahead of Team #2 - Team ROC Gear/4LPH4 1337 - Lime overall.','no'),(45,136,'2010-01-21 00:46:09','CP Visit','Team #6 - Super Villians at LF5 updated.','no'),(46,136,'2010-01-21 00:46:09','Rank','Team #6 - Super Villians moved ahead of Team #1 - Team ROC Gear/4LPH4 1337 - Tequila in their division and moved ahead of Team #1 - Team ROC Gear/4LPH4 1337 - Tequila overall.','no'),(47,136,'2010-01-21 00:47:20','CP Visit','Team #6 - Super Villians at LF6 updated.','no'),(48,136,'2010-01-21 00:47:20','Rank','Team #6 - Super Villians moved ahead of  in their division and moved ahead of  overall.','no'),(49,42,'2010-01-21 00:47:52','CP Visit','Team #1 - Team ROC Gear/4LPH4 1337 - Tequila at LF5 updated.','no'),(50,42,'2010-01-21 00:47:52','Rank','Team #1 - Team ROC Gear/4LPH4 1337 - Tequila moved ahead of Team #1 - Team ROC Gear/4LPH4 1337 - Tequila in their division and moved ahead of Team #1 - Team ROC Gear/4LPH4 1337 - Tequila overall.','no'),(51,137,'2010-01-21 00:55:28','CP Visit','Team #7 - Wacky Fun Whitey at LF6 updated.','no'),(52,137,'2010-01-21 00:55:28','Rank','Team #7 - Wacky Fun Whitey moved ahead of Team #2 - Test Team 3 and Team #2 - Team ROC Gear/4LPH4 1337 - Lime in their division and moved ahead of Team #6 - Super Villians and Team #2 - Team ROC Gear/4LPH4 1337 - Lime overall.','no'),(53,43,'2010-01-21 01:20:16','CP Visit','Team #2 - Team ROC Gear/4LPH4 1337 - Lime at LF4 updated.','no'),(54,43,'2010-01-21 01:20:16','Rank','Team #2 - Team ROC Gear/4LPH4 1337 - Lime moved ahead of Team #7 - Wacky Fun Whitey in their division and moved ahead of Team #7 - Wacky Fun Whitey overall.','no'),(55,108,'2010-01-21 01:46:06','CP Visit','Team #5 - tool at LF4 updated.','no'),(56,108,'2010-01-21 01:46:06','Rank','Team #5 - tool moved ahead of Team #7 - Wacky Fun Whitey in their division and moved ahead of Team #7 - Wacky Fun Whitey overall.','no'),(57,43,'2010-01-21 01:55:35','CP Visit','Team #2 - Team ROC Gear/4LPH4 1337 - Lime at LF5 updated.','no'),(58,43,'2010-01-21 01:55:35','Rank','Team #2 - Team ROC Gear/4LPH4 1337 - Lime moved ahead of Team #5 - tool in their division and moved ahead of Team #5 - tool overall.','no'),(59,42,'2010-01-21 01:58:00','CP Visit','Team #1 - Team ROC Gear/4LPH4 1337 - Tequila at LF6 updated.','no'),(60,42,'2010-01-21 01:58:00','Rank','Team #1 - Team ROC Gear/4LPH4 1337 - Tequila moved ahead of Team #7 - Wacky Fun Whitey and Team #6 - Super Villians in their division and moved ahead of Team #7 - Wacky Fun Whitey and Team #6 - Super Villians overall.','no'),(61,43,'2010-01-21 02:00:05','CP Visit','Team #2 - Team ROC Gear/4LPH4 1337 - Lime at LF6 updated.','no'),(62,43,'2010-01-21 02:00:05','Rank','Team #2 - Team ROC Gear/4LPH4 1337 - Lime moved ahead of Team #7 - Wacky Fun Whitey and Team #6 - Super Villians in their division and moved ahead of Team #7 - Wacky Fun Whitey and Team #6 - Super Villians overall.','no'),(63,136,'2010-01-21 02:00:31','CP Visit','Team #6 - Super Villians at LF7 updated.','no'),(64,136,'2010-01-21 02:00:31','Rank','Team #6 - Super Villians moved up 3 places in their division and moved up 3 places overall.','no'),(65,108,'2010-01-21 02:03:24','CP Visit','Team #5 - tool at LF5 updated.','no'),(66,108,'2010-01-21 02:03:24','Rank','Team #5 - tool.','no'),(68,105,'2010-01-21 02:12:51','CP Visit','Team #4 - fubar at CP3 updated.','no');
/*!40000 ALTER TABLE `UPD` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `authorities`
--

DROP TABLE IF EXISTS `authorities`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `authorities` (
  `USERNAME` varchar(10) NOT NULL,
  `AUTHORITY` varchar(10) NOT NULL,
  KEY `USERNAME` (`USERNAME`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `authorities`
--

LOCK TABLES `authorities` WRITE;
/*!40000 ALTER TABLE `authorities` DISABLE KEYS */;
INSERT INTO `authorities` VALUES ('user','ROLE_USER'),('admin','ROLE_USER'),('admin','ROLE_ADMIN');
/*!40000 ALTER TABLE `authorities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `users` (
  `USERNAME` varchar(10) NOT NULL,
  `PASSWORD` varchar(32) NOT NULL,
  `ENABLED` smallint(6) default NULL,
  PRIMARY KEY  (`USERNAME`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('user','emu',1),('admin','emu',1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2010-01-21  2:21:44
