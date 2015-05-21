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
INSERT INTO `COMPARATOR` VALUES (31,38,'didnotstart','rankeven',1,'no'),(32,38,'disqualified','rankeven',2,'no'),(33,38,'withdrawn','continue',3,'no'),(34,38,'unofficial','continue',4,'no'),(35,38,'lastvisitedcporder','continue',5,'no'),(36,38,'lastvisitedcporderdeparture','continue',6,'no'),(37,38,'lastvisitedcporderarrival','rankeven',7,'no');
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
INSERT INTO `CP_Visit_Rel` VALUES (42,1,'2010-01-12 03:37:34','2010-05-15 09:26:00','2010-05-15 09:32:00','1970-01-01 00:00:00','1970-01-01 00:00:00',0,0,0,0,'no','no','no','no','no','no','no','no','default','default'),(42,2,'2010-01-14 16:23:41','2010-05-15 09:47:00','2010-05-15 09:50:00','1970-01-01 06:00:00','1970-01-01 00:00:00',0,0,0,0,'no','no','no','no','no','no','no','no','default','default'),(42,5,'2010-01-12 02:31:15','2010-05-15 10:16:00','2010-05-15 10:21:00','1970-01-01 00:00:00','1970-01-01 00:00:00',0,0,0,0,'no','no','no','no','no','no','no','no','default','default'),(42,6,'2010-01-12 01:26:43','2010-05-15 10:47:00','2010-05-15 10:51:00','1970-01-01 22:00:00','1970-01-01 00:00:00',0,0,0,0,'no','no','no','no','no','no','no','no','default','default'),(42,7,'2010-01-06 03:31:59','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00',0,0,0,0,'yes','no','no','no','no','no','no','no','default','default'),(43,1,'2010-01-12 01:27:08','2010-05-15 09:28:00','2010-05-15 09:35:00','1970-01-01 00:00:00','1970-01-01 00:00:00',0,0,0,0,'no','no','no','no','no','no','no','no','default','default'),(43,2,'2010-01-12 01:27:08','2010-05-16 09:44:00','2010-05-16 09:45:00','1970-01-01 00:00:00','1970-01-01 00:00:00',0,0,0,0,'no','no','no','no','no','no','no','no','default','default'),(43,5,'2010-01-12 01:27:09','2010-05-16 10:49:00','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00',0,0,0,0,'no','no','no','no','no','no','no','no','default','default'),(97,96,'2010-01-06 03:01:12','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00',0,0,0,0,'yes','no','no','no','no','no','no','no','default','default'),(105,1,'2010-01-16 11:39:09','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00',0,0,0,0,'yes','no','no','no','no','no','no','no','default','default'),(108,1,'2010-01-17 03:58:26','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00',2,0,0,0,'yes','no','no','no','no','no','no','no','default','default'),(108,2,'2010-01-17 03:59:34','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00',0,0,0,0,'yes','no','no','no','no','no','no','no','default','default'),(118,111,'2010-01-07 03:41:24','2009-12-22 00:13:00','2009-12-22 01:01:00','1970-01-01 01:00:00','1970-01-01 00:00:00',0,1,0,0,'no','no','no','no','no','no','no','no','default','default'),(118,121,'2010-01-06 22:27:35','2010-01-05 17:25:00','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00',0,0,0,0,'no','no','no','no','no','no','no','no','default','default'),(122,111,'2010-01-07 03:39:59','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00',0,0,0,0,'yes','no','no','yes','no','no','no','no','default','default'),(122,121,'2010-01-06 20:40:39','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00',0,0,0,0,'no','yes','no','no','no','no','no','no','default','default'),(136,1,'2010-01-16 11:39:09','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00',0,0,0,0,'yes','no','no','no','no','no','no','no','default','default'),(136,2,'2010-01-17 03:44:52','2010-05-15 09:47:00','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00',0,0,0,0,'no','no','no','no','no','no','no','no','default','default'),(137,1,'2010-01-17 03:57:47','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 04:00:00','1970-01-01 00:00:00',0,0,0,0,'yes','no','no','no','no','no','no','no','default','default'),(137,2,'2010-01-17 03:44:52','2010-05-15 08:47:00','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 04:00:00',0,0,0,0,'no','no','no','no','no','no','no','no','default','Suckas');
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
) ENGINE=InnoDB AUTO_INCREMENT=153 DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Dumping data for table `DATA_RESOURCE`
--

LOCK TABLES `DATA_RESOURCE` WRITE;
/*!40000 ALTER TABLE `DATA_RESOURCE` DISABLE KEYS */;
INSERT INTO `DATA_RESOURCE` VALUES (1,70),(2,70),(5,70),(6,70),(7,70),(8,70),(9,70),(10,70),(11,70),(12,70),(13,70),(14,70),(15,70),(16,70),(17,70),(18,70),(19,70),(20,70),(21,70),(22,70),(28,70),(31,70),(32,70),(33,70),(34,70),(35,70),(36,70),(37,70),(38,70),(40,70),(41,70),(42,70),(43,70),(55,70),(56,70),(57,70),(60,70),(70,70),(71,70),(72,70),(73,70),(91,70),(92,70),(93,70),(95,70),(96,70),(97,70),(98,70),(99,70),(100,70),(105,70),(108,70),(110,70),(111,70),(112,70),(114,70),(115,70),(116,70),(117,70),(118,70),(119,70),(120,70),(121,70),(122,70),(123,70),(124,70),(128,70),(132,70),(133,70),(134,70),(135,70),(136,70),(137,70),(138,70),(139,70),(140,70),(141,70),(142,70),(143,70),(144,70),(145,70),(146,70),(147,70),(148,70),(149,70),(150,70),(151,70),(152,70),(46,71),(47,71),(48,71),(49,71),(50,71),(51,71),(52,71),(53,71),(54,71);
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

-- Dump completed on 2010-01-18  2:22:09
