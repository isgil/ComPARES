-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: compares
-- ------------------------------------------------------
-- Server version	5.5.14

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
-- Table structure for table `par`
--

DROP TABLE IF EXISTS `par`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `par` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TEST_FILE_SOURCE1` int(11) DEFAULT NULL,
  `TEST_FILE_SOURCE2` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=171 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `par`
--

LOCK TABLES `par` WRITE;
/*!40000 ALTER TABLE `par` DISABLE KEYS */;
/*!40000 ALTER TABLE `par` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rel_par_title`
--

DROP TABLE IF EXISTS `rel_par_title`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rel_par_title` (
  `PAR_ID` int(11) NOT NULL,
  `TITLE_ID` int(11) NOT NULL,
  PRIMARY KEY (`PAR_ID`,`TITLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rel_par_title`
--

LOCK TABLES `rel_par_title` WRITE;
/*!40000 ALTER TABLE `rel_par_title` DISABLE KEYS */;
/*!40000 ALTER TABLE `rel_par_title` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rel_test_par`
--

DROP TABLE IF EXISTS `rel_test_par`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rel_test_par` (
  `TEST_ID` int(11) NOT NULL,
  `PAR_ID` int(11) NOT NULL,
  PRIMARY KEY (`TEST_ID`,`PAR_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rel_test_par`
--

LOCK TABLES `rel_test_par` WRITE;
/*!40000 ALTER TABLE `rel_test_par` DISABLE KEYS */;
/*!40000 ALTER TABLE `rel_test_par` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `settings`
--

DROP TABLE IF EXISTS `settings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `settings` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SETTINGS` longblob,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `settings`
--

LOCK TABLES `settings` WRITE;
/*!40000 ALTER TABLE `settings` DISABLE KEYS */;
INSERT INTO `settings` VALUES (1,'¨\Ì\0sr\0java.util.HashMap\⁄¡\√`\—\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0topssr\0java.util.LinkedList)S]J`à\"\0\0xpw\0\0\0sr\0java.lang.Integer‚†§˜Åá8\0I\0valuexr\0java.lang.NumberÜ¨ïî\‡ã\0\0xp\0\0\0\nsq\0~\0\0\0\02sq\0~\0\0\0\0dsq\0~\0\0\0\0\»sq\0~\0\0\0,sq\0~\0\0\0êsq\0~\0\0\0Ùsq\0~\0\0\0\Ósq\0~\0\0\0\Ësq\0~\0\0\0\‹sq\0~\0\0\0\–sq\0~\0\0\0	\ƒsq\0~\0\0\0∏sq\0~\0\0\0\r¨sq\0~\0\0\0†sq\0~\0\0\0îsq\0~\0\0\0àsq\0~\0\0\0|sq\0~\0\0\0psq\0~\0\0\0dsq\0~\0\0\0Xsq\0~\0\0\0Lsq\0~\0\0\0@sq\0~\0\0\0!4sq\0~\0\0\0#(sq\0~\0\0\0%sq\0~\0\0\0\'xx');
/*!40000 ALTER TABLE `settings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `source`
--

DROP TABLE IF EXISTS `source`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `source` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `source`
--

LOCK TABLES `source` WRITE;
/*!40000 ALTER TABLE `source` DISABLE KEYS */;
/*!40000 ALTER TABLE `source` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test`
--

DROP TABLE IF EXISTS `test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) DEFAULT NULL,
  `TITLE_MARK1` varchar(255) DEFAULT NULL,
  `TITLE_MARK2` varchar(255) DEFAULT NULL,
  `SOURCE1_ID` int(11) DEFAULT NULL,
  `SOURCE2_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test`
--

LOCK TABLES `test` WRITE;
/*!40000 ALTER TABLE `test` DISABLE KEYS */;
/*!40000 ALTER TABLE `test` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test_file`
--

DROP TABLE IF EXISTS `test_file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test_file` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `EXTENSION` varchar(255) DEFAULT NULL,
  `FILE_NAME` varchar(255) DEFAULT NULL,
  `PATH` varchar(255) DEFAULT NULL,
  `PHYSICAL_FILE_NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=341 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test_file`
--

LOCK TABLES `test_file` WRITE;
/*!40000 ALTER TABLE `test_file` DISABLE KEYS */;
/*!40000 ALTER TABLE `test_file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `title`
--

DROP TABLE IF EXISTS `title`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `title` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `POSITION_SOURCE1` int(11) DEFAULT NULL,
  `POSITION_SOURCE2` int(11) DEFAULT NULL,
  `TITLE` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=146738 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `title`
--

LOCK TABLES `title` WRITE;
/*!40000 ALTER TABLE `title` DISABLE KEYS */;
/*!40000 ALTER TABLE `title` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-03-20 19:42:16
