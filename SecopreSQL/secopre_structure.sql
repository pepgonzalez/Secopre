-- MySQL dump 10.13  Distrib 5.6.27, for Linux (x86_64)
--
-- Host: localhost    Database: secopre
-- ------------------------------------------------------
-- Server version	5.6.27

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
-- Create schema `secopre`
--
create schema secopre;

use secopre;

--
-- Table structure for table `ADDRESS`
--

DROP TABLE IF EXISTS `ADDRESS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ADDRESS` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ACTIVE` bit(1) DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  `CREATED_BY` varchar(255) DEFAULT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  `UPDATED_BY` varchar(255) DEFAULT NULL,
  `CITY` varchar(50) DEFAULT NULL,
  `COLONY` varchar(50) DEFAULT NULL,
  `NUMBER` varchar(50) DEFAULT NULL,
  `NUMBER_INT` varchar(50) DEFAULT NULL,
  `STREET` varchar(150) NOT NULL,
  `ZIP_CODE` varchar(5) NOT NULL,
  `stateDTO_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_d3qxvpugdqh8t572h5dynbf5n` (`stateDTO_id`),
  CONSTRAINT `FK_d3qxvpugdqh8t572h5dynbf5n` FOREIGN KEY (`stateDTO_id`) REFERENCES `STATE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ADDRESS`
--

LOCK TABLES `ADDRESS` WRITE;
/*!40000 ALTER TABLE `ADDRESS` DISABLE KEYS */;
/*!40000 ALTER TABLE `ADDRESS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DASHBOARD`
--

DROP TABLE IF EXISTS `DASHBOARD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DASHBOARD` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ACTIVE` bit(1) DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  `CREATED_BY` varchar(255) DEFAULT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  `UPDATED_BY` varchar(255) DEFAULT NULL,
  `NAME` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DASHBOARD`
--

LOCK TABLES `DASHBOARD` WRITE;
/*!40000 ALTER TABLE `DASHBOARD` DISABLE KEYS */;
/*!40000 ALTER TABLE `DASHBOARD` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DASHBOARD_AUD`
--

DROP TABLE IF EXISTS `DASHBOARD_AUD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DASHBOARD_AUD` (
  `id` bigint(20) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `NAME` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  KEY `FK_ijbw7am0yia1v2a1yqjrpt7lm` (`REV`),
  CONSTRAINT `FK_ijbw7am0yia1v2a1yqjrpt7lm` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DASHBOARD_AUD`
--

LOCK TABLES `DASHBOARD_AUD` WRITE;
/*!40000 ALTER TABLE `DASHBOARD_AUD` DISABLE KEYS */;
/*!40000 ALTER TABLE `DASHBOARD_AUD` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DASHBOARD_INDICATOR`
--

DROP TABLE IF EXISTS `DASHBOARD_INDICATOR`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DASHBOARD_INDICATOR` (
  `DASHBOARD_ID` bigint(20) NOT NULL,
  `INDICATOR_ID` bigint(20) NOT NULL,
  KEY `FK_12idoy875p8i1681wwvays5ox` (`INDICATOR_ID`),
  KEY `FK_5jhqglm0jgo82xir692m4v69m` (`DASHBOARD_ID`),
  CONSTRAINT `FK_12idoy875p8i1681wwvays5ox` FOREIGN KEY (`INDICATOR_ID`) REFERENCES `INDICATOR` (`id`),
  CONSTRAINT `FK_5jhqglm0jgo82xir692m4v69m` FOREIGN KEY (`DASHBOARD_ID`) REFERENCES `DASHBOARD` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DASHBOARD_INDICATOR`
--

LOCK TABLES `DASHBOARD_INDICATOR` WRITE;
/*!40000 ALTER TABLE `DASHBOARD_INDICATOR` DISABLE KEYS */;
/*!40000 ALTER TABLE `DASHBOARD_INDICATOR` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DISTRICT`
--

DROP TABLE IF EXISTS `DISTRICT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DISTRICT` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ACTIVE` bit(1) DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  `CREATED_BY` varchar(255) DEFAULT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  `UPDATED_BY` varchar(255) DEFAULT NULL,
  `EMAIL` varchar(50) NOT NULL,
  `NUMBER` varchar(150) NOT NULL,
  `TELEPHONE` varchar(50) NOT NULL,
  `ADDRESS_ID` bigint(20) DEFAULT NULL,
  `state_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_q3drby5yq0lqqdg7aav07dc0n` (`ADDRESS_ID`),
  KEY `FK_pxq49959051v31uafc8eavr6` (`state_id`),
  CONSTRAINT `FK_pxq49959051v31uafc8eavr6` FOREIGN KEY (`state_id`) REFERENCES `STATE` (`id`),
  CONSTRAINT `FK_q3drby5yq0lqqdg7aav07dc0n` FOREIGN KEY (`ADDRESS_ID`) REFERENCES `ADDRESS` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DISTRICT`
--

LOCK TABLES `DISTRICT` WRITE;
/*!40000 ALTER TABLE `DISTRICT` DISABLE KEYS */;
/*!40000 ALTER TABLE `DISTRICT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DISTRICT_USER`
--

DROP TABLE IF EXISTS `DISTRICT_USER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DISTRICT_USER` (
  `USER_ID` bigint(20) NOT NULL,
  `DISTRICT_ID` bigint(20) NOT NULL,
  KEY `FK_7ksrsadgdxtwm1mrhq27rdjl0` (`DISTRICT_ID`),
  KEY `FK_580e29l7wcsi9vd3u2r13ij9c` (`USER_ID`),
  CONSTRAINT `FK_580e29l7wcsi9vd3u2r13ij9c` FOREIGN KEY (`USER_ID`) REFERENCES `USER` (`id`),
  CONSTRAINT `FK_7ksrsadgdxtwm1mrhq27rdjl0` FOREIGN KEY (`DISTRICT_ID`) REFERENCES `DISTRICT` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DISTRICT_USER`
--

LOCK TABLES `DISTRICT_USER` WRITE;
/*!40000 ALTER TABLE `DISTRICT_USER` DISABLE KEYS */;
/*!40000 ALTER TABLE `DISTRICT_USER` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DUE_DATE`
--

DROP TABLE IF EXISTS `DUE_DATE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DUE_DATE` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ACTIVE` bit(1) DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  `CREATED_BY` varchar(255) DEFAULT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  `UPDATED_BY` varchar(255) DEFAULT NULL,
  `DUE_DATE` date NOT NULL,
  `MAX_BLOCK_DATE` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DUE_DATE`
--

LOCK TABLES `DUE_DATE` WRITE;
/*!40000 ALTER TABLE `DUE_DATE` DISABLE KEYS */;
/*!40000 ALTER TABLE `DUE_DATE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ENTRY`
--

DROP TABLE IF EXISTS `ENTRY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ENTRY` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ACTIVE` bit(1) DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  `CREATED_BY` varchar(255) DEFAULT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  `UPDATED_BY` varchar(255) DEFAULT NULL,
  `ACCOUNTING_TYPE` varchar(255) DEFAULT NULL,
  `CODE` int(11) NOT NULL,
  `DESCRIPTION` varchar(250) NOT NULL,
  `NAME` varchar(250) NOT NULL,
  `STATUS` varchar(255) DEFAULT NULL,
  `ENTRY_CONCEPT_ID` bigint(20) DEFAULT NULL,
  `PROGRAMMATIC_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_qw6k8irsi3240tfhpbqookb8v` (`ENTRY_CONCEPT_ID`),
  KEY `FK_3m2vxmpy3fuqgmc4k8lysuwhe` (`PROGRAMMATIC_ID`),
  CONSTRAINT `FK_3m2vxmpy3fuqgmc4k8lysuwhe` FOREIGN KEY (`PROGRAMMATIC_ID`) REFERENCES `PROGRAMMATIC_KEY` (`id`),
  CONSTRAINT `FK_qw6k8irsi3240tfhpbqookb8v` FOREIGN KEY (`ENTRY_CONCEPT_ID`) REFERENCES `ENTRY` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ENTRY`
--

LOCK TABLES `ENTRY` WRITE;
/*!40000 ALTER TABLE `ENTRY` DISABLE KEYS */;
/*!40000 ALTER TABLE `ENTRY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ENTRYDISTRICT`
--

DROP TABLE IF EXISTS `ENTRYDISTRICT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ENTRYDISTRICT` (
  `id` bigint(20) NOT NULL,
  `ACTIVE` bit(1) DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  `CREATED_BY` varchar(255) DEFAULT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  `UPDATED_BY` varchar(255) DEFAULT NULL,
  `ANNUAL_AMOUNT` decimal(10,2) DEFAULT NULL,
  `BUDGET_AMOUNT` decimal(10,2) DEFAULT NULL,
  `BUDGET_AMOUNT_ASSIGN` decimal(10,2) DEFAULT NULL,
  `COMMITTED_AMOUNT` decimal(10,2) DEFAULT NULL,
  `MONTH` bigint(20) DEFAULT NULL,
  `DISTRICT_ID` bigint(20) NOT NULL,
  `ENTRY_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_n4oa21bombym4mlmu5qgvetdm` (`DISTRICT_ID`),
  KEY `FK_3alw09js75pxj605ggybewd7y` (`ENTRY_ID`),
  CONSTRAINT `FK_3alw09js75pxj605ggybewd7y` FOREIGN KEY (`ENTRY_ID`) REFERENCES `ENTRY` (`id`),
  CONSTRAINT `FK_n4oa21bombym4mlmu5qgvetdm` FOREIGN KEY (`DISTRICT_ID`) REFERENCES `DISTRICT` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ENTRYDISTRICT`
--

LOCK TABLES `ENTRYDISTRICT` WRITE;
/*!40000 ALTER TABLE `ENTRYDISTRICT` DISABLE KEYS */;
/*!40000 ALTER TABLE `ENTRYDISTRICT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `INDICATOR`
--

DROP TABLE IF EXISTS `INDICATOR`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `INDICATOR` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ALIGNMENT_SIGNUS` varchar(255) NOT NULL,
  `ICON` varchar(100) NOT NULL,
  `INDICATOR_TYPE` varchar(255) NOT NULL,
  `INDICATOR_SIGNUS` varchar(255) NOT NULL,
  `SQL_SENTENCE` longtext NOT NULL,
  `TITLE` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `INDICATOR`
--

LOCK TABLES `INDICATOR` WRITE;
/*!40000 ALTER TABLE `INDICATOR` DISABLE KEYS */;
/*!40000 ALTER TABLE `INDICATOR` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `INDICATOR_PARAMETER`
--

DROP TABLE IF EXISTS `INDICATOR_PARAMETER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `INDICATOR_PARAMETER` (
  `INDICATOR_ID` bigint(20) NOT NULL,
  `PARAMETER_ID` bigint(20) NOT NULL,
  KEY `FK_tb7jnc9nu29yv801h4ijdcmtr` (`PARAMETER_ID`),
  KEY `FK_hu0e7aekdqlfrf1uyrvrjhfp3` (`INDICATOR_ID`),
  CONSTRAINT `FK_hu0e7aekdqlfrf1uyrvrjhfp3` FOREIGN KEY (`INDICATOR_ID`) REFERENCES `INDICATOR` (`id`),
  CONSTRAINT `FK_tb7jnc9nu29yv801h4ijdcmtr` FOREIGN KEY (`PARAMETER_ID`) REFERENCES `PARAMETER` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `INDICATOR_PARAMETER`
--

LOCK TABLES `INDICATOR_PARAMETER` WRITE;
/*!40000 ALTER TABLE `INDICATOR_PARAMETER` DISABLE KEYS */;
/*!40000 ALTER TABLE `INDICATOR_PARAMETER` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MENU`
--

DROP TABLE IF EXISTS `MENU`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MENU` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ACTIVE` bit(1) DEFAULT NULL,
  `CSSCLASS` varchar(255) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `ICON` varchar(255) DEFAULT NULL,
  `JSFUNCTION` varchar(255) DEFAULT NULL,
  `JSID` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `MENU_ORDER` int(11) DEFAULT NULL,
  `PARENT_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MENU`
--

LOCK TABLES `MENU` WRITE;
/*!40000 ALTER TABLE `MENU` DISABLE KEYS */;
/*!40000 ALTER TABLE `MENU` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MOVEMENT_TYPE`
--

DROP TABLE IF EXISTS `MOVEMENT_TYPE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MOVEMENT_TYPE` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `CODE` varchar(50) NOT NULL,
  `DESCRIPTION` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MOVEMENT_TYPE`
--

LOCK TABLES `MOVEMENT_TYPE` WRITE;
/*!40000 ALTER TABLE `MOVEMENT_TYPE` DISABLE KEYS */;
/*!40000 ALTER TABLE `MOVEMENT_TYPE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `NOTICE`
--

DROP TABLE IF EXISTS `NOTICE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `NOTICE` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ACTIVE` bit(1) DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  `CREATED_BY` varchar(255) DEFAULT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  `UPDATED_BY` varchar(255) DEFAULT NULL,
  `DISPLAY_DATE` date NOT NULL,
  `NOTICE` varchar(255) NOT NULL,
  `REGISTER_DATE` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `NOTICE`
--

LOCK TABLES `NOTICE` WRITE;
/*!40000 ALTER TABLE `NOTICE` DISABLE KEYS */;
/*!40000 ALTER TABLE `NOTICE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `NOTICE_DISTRICT`
--

DROP TABLE IF EXISTS `NOTICE_DISTRICT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `NOTICE_DISTRICT` (
  `NOTICE_ID` bigint(20) NOT NULL,
  `DISTRICT_ID` bigint(20) NOT NULL,
  KEY `FK_a1yjg1cfjd44wlbx0c5jidj90` (`DISTRICT_ID`),
  KEY `FK_buuk9nr14vgjqnoms2t9vqy7m` (`NOTICE_ID`),
  CONSTRAINT `FK_a1yjg1cfjd44wlbx0c5jidj90` FOREIGN KEY (`DISTRICT_ID`) REFERENCES `DISTRICT` (`id`),
  CONSTRAINT `FK_buuk9nr14vgjqnoms2t9vqy7m` FOREIGN KEY (`NOTICE_ID`) REFERENCES `NOTICE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `NOTICE_DISTRICT`
--

LOCK TABLES `NOTICE_DISTRICT` WRITE;
/*!40000 ALTER TABLE `NOTICE_DISTRICT` DISABLE KEYS */;
/*!40000 ALTER TABLE `NOTICE_DISTRICT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PARAMETER`
--

DROP TABLE IF EXISTS `PARAMETER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PARAMETER` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PARAMETER`
--

LOCK TABLES `PARAMETER` WRITE;
/*!40000 ALTER TABLE `PARAMETER` DISABLE KEYS */;
/*!40000 ALTER TABLE `PARAMETER` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PATH`
--

DROP TABLE IF EXISTS `PATH`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PATH` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ACTIVE` bit(1) DEFAULT NULL,
  `PUBLIC_ACCESS` bit(1) DEFAULT NULL,
  `URL` varchar(255) DEFAULT NULL,
  `menu_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_3w4ybluj8r3hyrpdjwf2m11dt` (`URL`),
  KEY `FK_pwccn3jtg46y6oe7nafrxskrn` (`menu_id`),
  CONSTRAINT `FK_pwccn3jtg46y6oe7nafrxskrn` FOREIGN KEY (`menu_id`) REFERENCES `MENU` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PATH`
--

LOCK TABLES `PATH` WRITE;
/*!40000 ALTER TABLE `PATH` DISABLE KEYS */;
/*!40000 ALTER TABLE `PATH` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PERMISSION`
--

DROP TABLE IF EXISTS `PERMISSION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PERMISSION` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ACTIVE` bit(1) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `path_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ium5fxjyer4d0eyjklbvuy5yq` (`NAME`),
  KEY `FK_qwmmtwccv10xvycgmignlvcoy` (`path_id`),
  CONSTRAINT `FK_qwmmtwccv10xvycgmignlvcoy` FOREIGN KEY (`path_id`) REFERENCES `PATH` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PERMISSION`
--

LOCK TABLES `PERMISSION` WRITE;
/*!40000 ALTER TABLE `PERMISSION` DISABLE KEYS */;
/*!40000 ALTER TABLE `PERMISSION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PERSON`
--

DROP TABLE IF EXISTS `PERSON`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PERSON` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ACTIVE` bit(1) DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  `CREATED_BY` varchar(255) DEFAULT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  `UPDATED_BY` varchar(255) DEFAULT NULL,
  `CURP` varchar(19) DEFAULT NULL,
  `FACEBOOK` varchar(50) DEFAULT NULL,
  `FATHER_LASTNAME` varchar(30) NOT NULL,
  `GENDER` varchar(255) DEFAULT NULL,
  `MOBILE_TELEPHONE` varchar(30) DEFAULT NULL,
  `MOTHER_LASTNAME` varchar(30) DEFAULT NULL,
  `NAME` varchar(30) NOT NULL,
  `RFC` varchar(13) DEFAULT NULL,
  `SECOND_NAME` varchar(30) DEFAULT NULL,
  `TELEPHONE` varchar(30) DEFAULT NULL,
  `TWITTER` varchar(50) DEFAULT NULL,
  `WEB_SITE` longtext,
  `TYPE` varchar(255) DEFAULT NULL,
  `address_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `rfc_ix` (`RFC`),
  KEY `FK_i3c4vhdh3tc0b9k3970w8siho` (`address_id`),
  CONSTRAINT `FK_i3c4vhdh3tc0b9k3970w8siho` FOREIGN KEY (`address_id`) REFERENCES `ADDRESS` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PERSON`
--

LOCK TABLES `PERSON` WRITE;
/*!40000 ALTER TABLE `PERSON` DISABLE KEYS */;
/*!40000 ALTER TABLE `PERSON` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PERSON_AUD`
--

DROP TABLE IF EXISTS `PERSON_AUD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PERSON_AUD` (
  `id` bigint(20) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `TYPE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  KEY `FK_5p4rut470hdl6c8v4x2x72ehv` (`REV`),
  CONSTRAINT `FK_5p4rut470hdl6c8v4x2x72ehv` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PERSON_AUD`
--

LOCK TABLES `PERSON_AUD` WRITE;
/*!40000 ALTER TABLE `PERSON_AUD` DISABLE KEYS */;
/*!40000 ALTER TABLE `PERSON_AUD` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `POSITION`
--

DROP TABLE IF EXISTS `POSITION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `POSITION` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ACTIVE` bit(1) DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  `CREATED_BY` varchar(255) DEFAULT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  `UPDATED_BY` varchar(255) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `POSITION`
--

LOCK TABLES `POSITION` WRITE;
/*!40000 ALTER TABLE `POSITION` DISABLE KEYS */;
/*!40000 ALTER TABLE `POSITION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PROGRAMMATIC_KEY`
--

DROP TABLE IF EXISTS `PROGRAMMATIC_KEY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PROGRAMMATIC_KEY` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ACTIVE` bit(1) DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  `CREATED_BY` varchar(255) DEFAULT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  `UPDATED_BY` varchar(255) DEFAULT NULL,
  `ACTIVITY` varchar(255) DEFAULT NULL,
  `CODE` varchar(255) NOT NULL,
  `FINALITY` varchar(255) DEFAULT NULL,
  `FUNCTION` varchar(255) DEFAULT NULL,
  `PROGRAM_BUDGET` varchar(255) DEFAULT NULL,
  `SUBFUNCTION` varchar(255) DEFAULT NULL,
  `UNIT_RESPONSABLE` varchar(255) DEFAULT NULL,
  `YEAR` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PROGRAMMATIC_KEY`
--

LOCK TABLES `PROGRAMMATIC_KEY` WRITE;
/*!40000 ALTER TABLE `PROGRAMMATIC_KEY` DISABLE KEYS */;
/*!40000 ALTER TABLE `PROGRAMMATIC_KEY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `REVINFO`
--

DROP TABLE IF EXISTS `REVINFO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `REVINFO` (
  `REV` int(11) NOT NULL AUTO_INCREMENT,
  `REVTSTMP` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `REVINFO`
--

LOCK TABLES `REVINFO` WRITE;
/*!40000 ALTER TABLE `REVINFO` DISABLE KEYS */;
/*!40000 ALTER TABLE `REVINFO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ROLE`
--

DROP TABLE IF EXISTS `ROLE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ROLE` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ACTIVE` bit(1) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `ROLENAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_k3omdq5uvrmirx2ebg1frkhu9` (`ROLENAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ROLE`
--

LOCK TABLES `ROLE` WRITE;
/*!40000 ALTER TABLE `ROLE` DISABLE KEYS */;
/*!40000 ALTER TABLE `ROLE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ROLE_AUD`
--

DROP TABLE IF EXISTS `ROLE_AUD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ROLE_AUD` (
  `id` bigint(20) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `ACTIVE` bit(1) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `ROLENAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  KEY `FK_1dxfc1irhkmnp1jp6d09wj46k` (`REV`),
  CONSTRAINT `FK_1dxfc1irhkmnp1jp6d09wj46k` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ROLE_AUD`
--

LOCK TABLES `ROLE_AUD` WRITE;
/*!40000 ALTER TABLE `ROLE_AUD` DISABLE KEYS */;
/*!40000 ALTER TABLE `ROLE_AUD` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ROLE_PERMISSION`
--

DROP TABLE IF EXISTS `ROLE_PERMISSION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ROLE_PERMISSION` (
  `ROLE_ID` bigint(20) NOT NULL,
  `PERMISSION_ID` bigint(20) NOT NULL,
  KEY `FK_m2r1sqbtxt5fo39ax55fiq4l0` (`PERMISSION_ID`),
  KEY `FK_fn5mhmaqw72j8387h40mmj8sn` (`ROLE_ID`),
  CONSTRAINT `FK_fn5mhmaqw72j8387h40mmj8sn` FOREIGN KEY (`ROLE_ID`) REFERENCES `ROLE` (`id`),
  CONSTRAINT `FK_m2r1sqbtxt5fo39ax55fiq4l0` FOREIGN KEY (`PERMISSION_ID`) REFERENCES `PERMISSION` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ROLE_PERMISSION`
--

LOCK TABLES `ROLE_PERMISSION` WRITE;
/*!40000 ALTER TABLE `ROLE_PERMISSION` DISABLE KEYS */;
/*!40000 ALTER TABLE `ROLE_PERMISSION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `STATE`
--

DROP TABLE IF EXISTS `STATE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `STATE` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `CODE` varchar(10) NOT NULL,
  `NAME` varchar(150) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `STATE`
--

LOCK TABLES `STATE` WRITE;
/*!40000 ALTER TABLE `STATE` DISABLE KEYS */;
/*!40000 ALTER TABLE `STATE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USER`
--

DROP TABLE IF EXISTS `USER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USER` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ACTIVE` bit(1) DEFAULT NULL,
  `AVATAR` varchar(255) DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `HAS_CHAT_ACTIVE` bit(1) DEFAULT NULL,
  `INFORMATION` varchar(255) DEFAULT NULL,
  `NICKNAME` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `USERNAME` varchar(255) DEFAULT NULL,
  `DASHBOARD_ID` bigint(20) DEFAULT NULL,
  `PERSON_ID` bigint(20) DEFAULT NULL,
  `POSITION_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_q05h8xfsj6spa8dpqh3o9e04l` (`NICKNAME`),
  UNIQUE KEY `UK_lb5yrvw2c22im784wwrpwuq06` (`USERNAME`),
  KEY `FK_f4u77slikxky7xvsfypswo1rn` (`DASHBOARD_ID`),
  KEY `FK_53sghx07ywuru49yirrni62hk` (`PERSON_ID`),
  KEY `FK_fks22q460vmdgfn89f34f42ty` (`POSITION_ID`),
  CONSTRAINT `FK_53sghx07ywuru49yirrni62hk` FOREIGN KEY (`PERSON_ID`) REFERENCES `PERSON` (`id`),
  CONSTRAINT `FK_f4u77slikxky7xvsfypswo1rn` FOREIGN KEY (`DASHBOARD_ID`) REFERENCES `DASHBOARD` (`id`),
  CONSTRAINT `FK_fks22q460vmdgfn89f34f42ty` FOREIGN KEY (`POSITION_ID`) REFERENCES `POSITION` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USER`
--

LOCK TABLES `USER` WRITE;
/*!40000 ALTER TABLE `USER` DISABLE KEYS */;
/*!40000 ALTER TABLE `USER` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USER_ACCESS`
--

DROP TABLE IF EXISTS `USER_ACCESS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USER_ACCESS` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `JSESSIONID` varchar(255) DEFAULT NULL,
  `LOGIN_DATE` datetime DEFAULT NULL,
  `LOGOUT_DATE` datetime DEFAULT NULL,
  `REMOTE_IP` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_9kkhln328rvper1343y3qucqn` (`user_id`),
  CONSTRAINT `FK_9kkhln328rvper1343y3qucqn` FOREIGN KEY (`user_id`) REFERENCES `USER` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USER_ACCESS`
--

LOCK TABLES `USER_ACCESS` WRITE;
/*!40000 ALTER TABLE `USER_ACCESS` DISABLE KEYS */;
/*!40000 ALTER TABLE `USER_ACCESS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USER_ATTEMPTS`
--

DROP TABLE IF EXISTS `USER_ATTEMPTS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USER_ATTEMPTS` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ATTEMPTS` int(11) DEFAULT NULL,
  `LAST_MODIFIED` datetime DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_o27pxrvekg94vnbumcqbpyx2q` (`user_id`),
  CONSTRAINT `FK_o27pxrvekg94vnbumcqbpyx2q` FOREIGN KEY (`user_id`) REFERENCES `USER` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USER_ATTEMPTS`
--

LOCK TABLES `USER_ATTEMPTS` WRITE;
/*!40000 ALTER TABLE `USER_ATTEMPTS` DISABLE KEYS */;
/*!40000 ALTER TABLE `USER_ATTEMPTS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USER_AUD`
--

DROP TABLE IF EXISTS `USER_AUD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USER_AUD` (
  `id` bigint(20) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `ACTIVE` bit(1) DEFAULT NULL,
  `AVATAR` varchar(255) DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `HAS_CHAT_ACTIVE` bit(1) DEFAULT NULL,
  `INFORMATION` varchar(255) DEFAULT NULL,
  `NICKNAME` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `USERNAME` varchar(255) DEFAULT NULL,
  `DASHBOARD_ID` bigint(20) DEFAULT NULL,
  `PERSON_ID` bigint(20) DEFAULT NULL,
  `POSITION_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  KEY `FK_jboq3w0aies9n06aqmd81c8pb` (`REV`),
  CONSTRAINT `FK_jboq3w0aies9n06aqmd81c8pb` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USER_AUD`
--

LOCK TABLES `USER_AUD` WRITE;
/*!40000 ALTER TABLE `USER_AUD` DISABLE KEYS */;
/*!40000 ALTER TABLE `USER_AUD` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USER_ROLE`
--

DROP TABLE IF EXISTS `USER_ROLE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USER_ROLE` (
  `USER_ID` bigint(20) NOT NULL,
  `ROLE_ID` bigint(20) NOT NULL,
  KEY `FK_oqmdk7xj0ainhxpvi79fkaq3y` (`ROLE_ID`),
  KEY `FK_j2j8kpywaghe8i36swcxv8784` (`USER_ID`),
  CONSTRAINT `FK_j2j8kpywaghe8i36swcxv8784` FOREIGN KEY (`USER_ID`) REFERENCES `USER` (`id`),
  CONSTRAINT `FK_oqmdk7xj0ainhxpvi79fkaq3y` FOREIGN KEY (`ROLE_ID`) REFERENCES `ROLE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USER_ROLE`
--

LOCK TABLES `USER_ROLE` WRITE;
/*!40000 ALTER TABLE `USER_ROLE` DISABLE KEYS */;
/*!40000 ALTER TABLE `USER_ROLE` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-11-22 20:19:31
