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
-- Table structure for table `CP_Visit_Rel`
--

DROP TABLE IF EXISTS `CP_Visit_Rel`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `CP_Visit_Rel` (
  `team_id` int(10) unsigned NOT NULL,
  `cp_id` int(10) unsigned NOT NULL,
  `arrival` datetime NOT NULL default '1970-01-01 00:00:00',
  `departure` datetime NOT NULL default '1970-01-01 00:00:00',
  `time_bonus_assessed` datetime NOT NULL default '1970-01-01 00:00:00',
  `time_penalty_assessed` datetime NOT NULL default '1970-01-01 00:00:00',
  `cp_bonus_assessed` tinyint(3) unsigned NOT NULL default '0',
  `cp_penalty_assessed` tinyint(3) unsigned NOT NULL default '0',
  `weight_bonus_assessed` smallint(5) unsigned NOT NULL default '0',
  `weight_penalty_assessed` smallint(5) unsigned NOT NULL default '0',
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
INSERT INTO `CP_Visit_Rel` VALUES (42,1,'2010-05-15 09:25:00','2010-05-15 09:30:00','1970-01-01 00:00:00','1970-01-01 00:00:00',0,0,0,0,'no','no','no','no','no','no','no','default','default'),(42,2,'2010-05-15 09:45:00','2010-05-15 09:50:00','1970-01-01 06:00:00','1970-01-01 00:00:00',0,0,0,0,'no','no','no','no','no','no','no','default','default'),(42,5,'2010-05-15 10:15:00','2010-05-15 10:20:00','1970-01-01 00:00:00','1970-01-01 00:00:00',0,0,0,0,'no','no','no','no','no','no','no','default','default'),(42,6,'2010-05-15 10:45:00','2010-05-15 10:50:00','1970-01-01 22:00:00','1970-01-01 00:00:00',0,0,0,0,'no','no','no','no','no','no','no','default','default'),(42,7,'1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00',0,0,0,0,'yes','no','no','no','no','no','no','default','default'),(43,1,'2010-05-15 09:29:00','2010-05-15 09:35:00','1970-01-01 00:00:00','1970-01-01 00:00:00',0,0,0,0,'no','no','no','no','no','no','no','default','default'),(43,2,'2010-05-16 09:45:00','1970-01-01 00:00:00','1970-01-01 00:00:00','1970-01-01 00:00:00',0,0,0,0,'no','no','no','no','no','no','no','default','default');
/*!40000 ALTER TABLE `CP_Visit_Rel` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2009-12-03 21:03:48
