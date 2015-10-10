-- MySQL dump 10.13  Distrib 5.6.23, for Win64 (x86_64)
--
-- Host: localhost    Database: secopre
-- ------------------------------------------------------
-- Server version	5.6.25-log

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
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ACTIVE` bit(1) DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  `CREATED_BY` varchar(255) DEFAULT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  `UPDATED_BY` varchar(255) DEFAULT NULL,
  `CITY` varchar(50) DEFAULT NULL,
  `NUMBER_INT` varchar(50) DEFAULT NULL,
  `NUMBER` varchar(50) DEFAULT NULL,
  `STATE` varchar(50) DEFAULT NULL,
  `STREET` varchar(150) NOT NULL,
  `ZIP_CODE` varchar(5) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ephmhyidou47sjbf4g79vxxbc` (`CITY`),
  UNIQUE KEY `UK_apy5mwn8cxik8iaeg2dhekllc` (`STATE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `authorization`
--

DROP TABLE IF EXISTS `authorization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `authorization` (
  `ID` bigint(20) NOT NULL,
  `DESCRIPTION` varchar(255) NOT NULL,
  `SUPER_USER_ROLE` bigint(20) NOT NULL,
  `LAST_UPDATE` date DEFAULT NULL,
  `ACTIVE` bit(1) DEFAULT b'1',
  PRIMARY KEY (`ID`),
  KEY `FK_AUTH_ROLE` (`SUPER_USER_ROLE`),
  CONSTRAINT `FK_AUTH_ROLE` FOREIGN KEY (`SUPER_USER_ROLE`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `authorization_config`
--

DROP TABLE IF EXISTS `authorization_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `authorization_config` (
  `ID` bigint(20) NOT NULL,
  `AUTHORIZATION_ID` bigint(20) NOT NULL,
  `STAGE_CONFIG_ID` bigint(20) NOT NULL,
  `EMPLOYMENT_ID` bigint(20) NOT NULL,
  `LAST_UPDATE` date DEFAULT NULL,
  `ACTIVE` bit(1) DEFAULT b'1',
  PRIMARY KEY (`ID`),
  KEY `FK_AUTH_CFG_STG_CFG` (`STAGE_CONFIG_ID`),
  CONSTRAINT `FK_AUTH_CFG_STG_CFG` FOREIGN KEY (`STAGE_CONFIG_ID`) REFERENCES `stage_config` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `chat_conversation`
--

DROP TABLE IF EXISTS `chat_conversation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chat_conversation` (
  `CONVERSATION_ID` bigint(20) NOT NULL,
  `USER_ID` bigint(20) NOT NULL,
  `CREATION_DATE` date DEFAULT NULL,
  `LAST_UPDATE` date DEFAULT NULL,
  `ACTIVE` bit(1) DEFAULT b'1',
  UNIQUE KEY `UDX_CHAT_CONV_USER` (`CONVERSATION_ID`,`USER_ID`),
  KEY `fk_chat_conv_user` (`USER_ID`),
  CONSTRAINT `fk_chat_conv_user` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `chat_message`
--

DROP TABLE IF EXISTS `chat_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chat_message` (
  `MESSAGE_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CONVERSATION_ID` bigint(20) NOT NULL,
  `USER_FROM` bigint(20) NOT NULL,
  `USER_TO` bigint(20) NOT NULL,
  `MESSAGE` varchar(1000) NOT NULL,
  `ESTATUS` int(1) NOT NULL DEFAULT '0',
  `CREATION_DATE` datetime DEFAULT NULL,
  `LAST_UPDATE` datetime DEFAULT NULL,
  `ACTIVE` bit(1) DEFAULT b'1',
  PRIMARY KEY (`MESSAGE_ID`),
  KEY `FK_C_MSG_CONV` (`CONVERSATION_ID`),
  KEY `FK_C_MSG_USR_FROM` (`USER_FROM`),
  KEY `FK_C_MSG_USR_TO` (`USER_TO`),
  CONSTRAINT `FK_C_MSG_CONV` FOREIGN KEY (`CONVERSATION_ID`) REFERENCES `chat_conversation` (`CONVERSATION_ID`),
  CONSTRAINT `FK_C_MSG_USR_FROM` FOREIGN KEY (`USER_FROM`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_C_MSG_USR_TO` FOREIGN KEY (`USER_TO`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=131 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `district`
--

DROP TABLE IF EXISTS `district`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `district` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `NUMBER` varchar(150) NOT NULL,
  `STATE_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_330246qxi65ovqbiqa2wfqr62` (`STATE_ID`),
  CONSTRAINT `FK_330246qxi65ovqbiqa2wfqr62` FOREIGN KEY (`STATE_ID`) REFERENCES `state` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `employment`
--

DROP TABLE IF EXISTS `employment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employment` (
  `EMPLOYMENT_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `DESCRIPTION` varchar(255) NOT NULL,
  `CREATION_DATE` datetime DEFAULT NULL,
  `LAST_UPDATE` datetime DEFAULT NULL,
  `ACTIVE` bit(1) DEFAULT b'1',
  PRIMARY KEY (`EMPLOYMENT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `entry`
--

DROP TABLE IF EXISTS `entry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `entry` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ACTIVE` bit(1) DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  `CREATED_BY` varchar(255) DEFAULT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  `UPDATED_BY` varchar(255) DEFAULT NULL,
  `ACCOUNTING_TYPE` varchar(255) DEFAULT NULL,
  `CODE` int(11) NOT NULL,
  `DESCRIPTION` varchar(150) NOT NULL,
  `NAME` varchar(150) NOT NULL,
  `ENTRY_CONCEPT_ID` bigint(20) DEFAULT NULL,
  `PROGRAMMATIC_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_qw6k8irsi3240tfhpbqookb8v` (`ENTRY_CONCEPT_ID`),
  KEY `FK_3m2vxmpy3fuqgmc4k8lysuwhe` (`PROGRAMMATIC_ID`),
  CONSTRAINT `FK_3m2vxmpy3fuqgmc4k8lysuwhe` FOREIGN KEY (`PROGRAMMATIC_ID`) REFERENCES `programmatic_key` (`id`),
  CONSTRAINT `FK_qw6k8irsi3240tfhpbqookb8v` FOREIGN KEY (`ENTRY_CONCEPT_ID`) REFERENCES `entry` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `entry_district`
--

DROP TABLE IF EXISTS `entry_district`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `entry_district` (
  `id` bigint(20) NOT NULL,
  `ANNUAL_AMOUNT` decimal(10,2) DEFAULT NULL,
  `BUDGET_MONTH1` decimal(10,2) DEFAULT NULL,
  `BUDGET_MONTH10` decimal(10,2) DEFAULT NULL,
  `BUDGET_MONTH11` decimal(10,2) DEFAULT NULL,
  `BUDGET_MONTH12` decimal(10,2) DEFAULT NULL,
  `BUDGET_MONTH2` decimal(10,2) DEFAULT NULL,
  `BUDGET_MONTH3` decimal(10,2) DEFAULT NULL,
  `BUDGET_MONTH4` decimal(10,2) DEFAULT NULL,
  `BUDGET_MONTH5` decimal(10,2) DEFAULT NULL,
  `BUDGET_MONTH6` decimal(10,2) DEFAULT NULL,
  `BUDGET_MONTH7` decimal(10,2) DEFAULT NULL,
  `BUDGET_MONTH8` decimal(10,2) DEFAULT NULL,
  `BUDGET_MONTH9` decimal(10,2) DEFAULT NULL,
  `BUDGET_MONTH_ASSIGN1` decimal(10,2) DEFAULT NULL,
  `BUDGET_MONTH_ASSIGN10` decimal(10,2) DEFAULT NULL,
  `BUDGET_MONTH_ASSIGN11` decimal(10,2) DEFAULT NULL,
  `BUDGET_MONTH_ASSIGN12` decimal(10,2) DEFAULT NULL,
  `BUDGET_MONTH_ASSIGN2` decimal(10,2) DEFAULT NULL,
  `BUDGET_MONTH_ASSIGN3` decimal(10,2) DEFAULT NULL,
  `BUDGET_MONTH_ASSIGN4` decimal(10,2) DEFAULT NULL,
  `BUDGET_MONTH_ASSIGN5` decimal(10,2) DEFAULT NULL,
  `BUDGET_MONTH_ASSIGN6` decimal(10,2) DEFAULT NULL,
  `BUDGET_MONTH_ASSIGN7` decimal(10,2) DEFAULT NULL,
  `BUDGET_MONTH_ASSIGN8` decimal(10,2) DEFAULT NULL,
  `BUDGET_MONTH_ASSIGN9` decimal(10,2) DEFAULT NULL,
  `DISTRICT_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_g470cmkxd30u3x6a006kxmrn8` (`DISTRICT_ID`),
  CONSTRAINT `FK_g470cmkxd30u3x6a006kxmrn8` FOREIGN KEY (`DISTRICT_ID`) REFERENCES `district` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `formality`
--

DROP TABLE IF EXISTS `formality`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `formality` (
  `ID` bigint(20) NOT NULL,
  `DESCRIPTION` varchar(255) NOT NULL,
  `WORKFLOW_ID` bigint(20) NOT NULL,
  `LAST_UPDATE` date DEFAULT NULL,
  `ACTIVE` bit(1) DEFAULT b'1',
  `AUTHORIZATION_ID` bigint(20) NOT NULL,
  `CODE` varchar(50) NOT NULL,
  KEY `FK_FORM_WF` (`WORKFLOW_ID`),
  CONSTRAINT `FK_FORM_WF` FOREIGN KEY (`WORKFLOW_ID`) REFERENCES `workflow` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ACTIVE` bit(1) DEFAULT NULL,
  `CSSCLASS` varchar(255) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `ICON` varchar(255) DEFAULT NULL,
  `JSFUNCTION` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `MENU_ORDER` int(11) DEFAULT NULL,
  `PARENT_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `path`
--

DROP TABLE IF EXISTS `path`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `path` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ACTIVE` bit(1) DEFAULT NULL,
  `PUBLIC_ACCESS` bit(1) DEFAULT NULL,
  `URL` varchar(255) DEFAULT NULL,
  `menu_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_3w4ybluj8r3hyrpdjwf2m11dt` (`URL`),
  KEY `FK_pwccn3jtg46y6oe7nafrxskrn` (`menu_id`),
  CONSTRAINT `FK_pwccn3jtg46y6oe7nafrxskrn` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ACTIVE` bit(1) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `path_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ium5fxjyer4d0eyjklbvuy5yq` (`NAME`),
  KEY `FK_qwmmtwccv10xvycgmignlvcoy` (`path_id`),
  CONSTRAINT `FK_qwmmtwccv10xvycgmignlvcoy` FOREIGN KEY (`path_id`) REFERENCES `path` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `permission_path`
--

DROP TABLE IF EXISTS `permission_path`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permission_path` (
  `permission_id` bigint(20) NOT NULL,
  `path_id` bigint(20) NOT NULL,
  KEY `FK_1hs4k1b75gni8uowgbfaujkkc` (`path_id`),
  KEY `FK_leqyigim7sl3lyiq6cem2asrd` (`permission_id`),
  CONSTRAINT `FK_1hs4k1b75gni8uowgbfaujkkc` FOREIGN KEY (`path_id`) REFERENCES `permission` (`id`),
  CONSTRAINT `FK_leqyigim7sl3lyiq6cem2asrd` FOREIGN KEY (`permission_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `person` (
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
  `personType` varchar(255) DEFAULT NULL,
  `address_id` bigint(20) DEFAULT NULL,
  `TYPE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `rfc_ix` (`RFC`),
  KEY `FK_i3c4vhdh3tc0b9k3970w8siho` (`address_id`),
  CONSTRAINT `FK_i3c4vhdh3tc0b9k3970w8siho` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `person_aud`
--

DROP TABLE IF EXISTS `person_aud`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `person_aud` (
  `id` bigint(20) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `personType` varchar(255) DEFAULT NULL,
  `TYPE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  KEY `FK_5p4rut470hdl6c8v4x2x72ehv` (`REV`),
  CONSTRAINT `FK_5p4rut470hdl6c8v4x2x72ehv` FOREIGN KEY (`REV`) REFERENCES `revinfo` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `position`
--

DROP TABLE IF EXISTS `position`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `position` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ACTIVE` bit(1) DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  `CREATED_BY` varchar(255) DEFAULT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  `UPDATED_BY` varchar(255) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `programmatic_key`
--

DROP TABLE IF EXISTS `programmatic_key`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `programmatic_key` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ACTIVE` bit(1) DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  `CREATED_BY` varchar(255) DEFAULT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  `UPDATED_BY` varchar(255) DEFAULT NULL,
  `CODE` varchar(255) NOT NULL,
  `YEAR` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `rel_user_connection`
--

DROP TABLE IF EXISTS `rel_user_connection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rel_user_connection` (
  `USER_ID` bigint(20) NOT NULL,
  `SOCKET_ID` varchar(255) DEFAULT NULL,
  `CREATION_DATE` datetime DEFAULT NULL,
  `LAST_UPDATE` datetime DEFAULT NULL,
  `ACTIVE` bit(1) DEFAULT b'1',
  PRIMARY KEY (`USER_ID`),
  CONSTRAINT `FK_REL_UC_USR` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `rel_user_employment`
--

DROP TABLE IF EXISTS `rel_user_employment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rel_user_employment` (
  `USER_ID` bigint(20) NOT NULL,
  `EMPLOYMENT_ID` bigint(20) NOT NULL,
  `CREATION_DATE` datetime DEFAULT NULL,
  `LAST_UPDATE` datetime DEFAULT NULL,
  `ACTIVE` bit(1) DEFAULT b'1',
  KEY `FK_UE_USR` (`USER_ID`),
  KEY `FK_UE_EMP` (`EMPLOYMENT_ID`),
  CONSTRAINT `FK_UE_EMP` FOREIGN KEY (`EMPLOYMENT_ID`) REFERENCES `employment` (`EMPLOYMENT_ID`),
  CONSTRAINT `FK_UE_USR` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `request`
--

DROP TABLE IF EXISTS `request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `request` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `FIRST_NAME` varchar(255) DEFAULT NULL,
  `PARENT_LAST_NAME` varchar(255) DEFAULT NULL,
  `MOTHER_LAST_NAME` varchar(255) DEFAULT NULL,
  `LAST_UPDATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `ACTIVE` bit(1) DEFAULT b'1',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `request_config`
--

DROP TABLE IF EXISTS `request_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `request_config` (
  `REQUEST_ID` bigint(20) NOT NULL,
  `FORMALITY_ID` bigint(20) NOT NULL,
  `WORKFLOW_ID` bigint(20) NOT NULL,
  `LAST_UPDATE` date DEFAULT NULL,
  `ACTIVE` bit(1) DEFAULT b'1',
  `authorization_id` bigint(20) NOT NULL,
  KEY `REQ_CFG_WF` (`WORKFLOW_ID`),
  CONSTRAINT `REQ_CFG_WF` FOREIGN KEY (`WORKFLOW_ID`) REFERENCES `workflow` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `request_detail`
--

DROP TABLE IF EXISTS `request_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `request_detail` (
  `REQUEST_ID` bigint(20) NOT NULL,
  `MOVEMENT_NAME` varchar(100) DEFAULT NULL,
  `MOVEMENT_PRICE` decimal(10,0) DEFAULT NULL,
  `LAST_UPDATE` date DEFAULT NULL,
  `ACTIVE` bit(1) DEFAULT b'1',
  PRIMARY KEY (`REQUEST_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `request_history`
--

DROP TABLE IF EXISTS `request_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `request_history` (
  `REQUEST_ID` bigint(20) NOT NULL,
  `CONSECUTIVE` bigint(20) NOT NULL,
  `WORKFLOW_CONFIG_ID` bigint(20) NOT NULL,
  `USER_ID` bigint(20) NOT NULL,
  `LAST_UPDATE` date DEFAULT NULL,
  `ACTIVE` bit(1) DEFAULT b'1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `revinfo`
--

DROP TABLE IF EXISTS `revinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `revinfo` (
  `REV` int(11) NOT NULL AUTO_INCREMENT,
  `REVTSTMP` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ACTIVE` bit(1) DEFAULT NULL,
  `ROLENAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_k3omdq5uvrmirx2ebg1frkhu9` (`ROLENAME`),
  UNIQUE KEY `rolename_ix` (`ROLENAME`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `role_aud`
--

DROP TABLE IF EXISTS `role_aud`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_aud` (
  `id` bigint(20) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `ACTIVE` bit(1) DEFAULT NULL,
  `ROLENAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  KEY `FK_1dxfc1irhkmnp1jp6d09wj46k` (`REV`),
  CONSTRAINT `FK_1dxfc1irhkmnp1jp6d09wj46k` FOREIGN KEY (`REV`) REFERENCES `revinfo` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `role_permission`
--

DROP TABLE IF EXISTS `role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_permission` (
  `ROLE_ID` bigint(20) NOT NULL,
  `PERMISSION_ID` bigint(20) NOT NULL,
  KEY `FK_m2r1sqbtxt5fo39ax55fiq4l0` (`PERMISSION_ID`),
  KEY `FK_fn5mhmaqw72j8387h40mmj8sn` (`ROLE_ID`),
  CONSTRAINT `FK_fn5mhmaqw72j8387h40mmj8sn` FOREIGN KEY (`ROLE_ID`) REFERENCES `role` (`id`),
  CONSTRAINT `FK_m2r1sqbtxt5fo39ax55fiq4l0` FOREIGN KEY (`PERMISSION_ID`) REFERENCES `permission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `stage`
--

DROP TABLE IF EXISTS `stage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stage` (
  `ID` bigint(20) NOT NULL,
  `DESCRIPTION` varchar(255) NOT NULL,
  `LAST_UPDATE` date DEFAULT NULL,
  `ACTIVE` bit(1) DEFAULT b'1',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `stage_config`
--

DROP TABLE IF EXISTS `stage_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stage_config` (
  `ID` bigint(20) NOT NULL,
  `STAGE_ID` bigint(20) NOT NULL,
  `PATH_ID` bigint(20) NOT NULL,
  `IS_CAPTURE` bit(1) NOT NULL,
  `IS_AUTHORIZATION` bit(1) NOT NULL,
  `CAPTURE_FORM` varchar(255) DEFAULT NULL,
  `LAST_UPDATE` date DEFAULT NULL,
  `ACTIVE` bit(1) DEFAULT b'1',
  PRIMARY KEY (`ID`),
  KEY `FK_STG_CFG_STG` (`STAGE_ID`),
  KEY `FK_STG_CFG_PATH` (`PATH_ID`),
  CONSTRAINT `FK_STG_CFG_PATH` FOREIGN KEY (`PATH_ID`) REFERENCES `path` (`id`),
  CONSTRAINT `FK_STG_CFG_STG` FOREIGN KEY (`STAGE_ID`) REFERENCES `stage` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `stage_config_role`
--

DROP TABLE IF EXISTS `stage_config_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stage_config_role` (
  `STAGE_CONFIG_ID` bigint(20) NOT NULL,
  `ROLE_ID` bigint(20) NOT NULL,
  `LAST_UPDATE` date DEFAULT NULL,
  `ACTIVE` bit(1) DEFAULT b'1',
  UNIQUE KEY `IDX_STG_CFG_ROLE` (`ROLE_ID`,`STAGE_CONFIG_ID`),
  KEY `STG_CFG_RL_STGCFG` (`STAGE_CONFIG_ID`),
  CONSTRAINT `STG_CFG_RL_ROLE` FOREIGN KEY (`ROLE_ID`) REFERENCES `role` (`id`),
  CONSTRAINT `STG_CFG_RL_STGCFG` FOREIGN KEY (`STAGE_CONFIG_ID`) REFERENCES `stage_config` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `state`
--

DROP TABLE IF EXISTS `state`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `state` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `CODE` varchar(10) NOT NULL,
  `NAME` varchar(150) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `status` (
  `ID` bigint(20) NOT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `LAST_UPDATE` date DEFAULT NULL,
  `ACTIVE` bit(1) DEFAULT b'1',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `table`
--

DROP TABLE IF EXISTS `table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `table` (
  `CONVERSATION_ID` bigint(20) NOT NULL,
  `USER_ID` bigint(20) NOT NULL,
  `CREATION_DATE` date DEFAULT NULL,
  `LAST_UPDATE` date DEFAULT NULL,
  `ACTIVE` bit(1) DEFAULT b'1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ACTIVE` bit(1) DEFAULT NULL,
  `AVATAR` varchar(255) DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `HAS_CHAT_ACTIVE` bit(1) DEFAULT NULL,
  `NICKNAME` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `USERNAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_q05h8xfsj6spa8dpqh3o9e04l` (`NICKNAME`),
  UNIQUE KEY `UK_lb5yrvw2c22im784wwrpwuq06` (`USERNAME`),
  UNIQUE KEY `username_ix` (`USERNAME`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_access`
--

DROP TABLE IF EXISTS `user_access`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_access` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `LOGIN_DATE` datetime DEFAULT NULL,
  `LOGOUT_DATE` datetime DEFAULT NULL,
  `REMOTE_IP` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `JSESSIONID` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_9kkhln328rvper1343y3qucqn` (`user_id`),
  CONSTRAINT `FK_9kkhln328rvper1343y3qucqn` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_attempts`
--

DROP TABLE IF EXISTS `user_attempts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_attempts` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ATTEMPTS` int(11) DEFAULT NULL,
  `LAST_MODIFIED` datetime DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_o27pxrvekg94vnbumcqbpyx2q` (`user_id`),
  CONSTRAINT `FK_o27pxrvekg94vnbumcqbpyx2q` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_aud`
--

DROP TABLE IF EXISTS `user_aud`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_aud` (
  `id` bigint(20) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `ACTIVE` bit(1) DEFAULT NULL,
  `NICKNAME` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `USERNAME` varchar(255) DEFAULT NULL,
  `HAS_CHAT_MODULE` bit(1) DEFAULT NULL,
  `HAS_CHAT_ACTIVE` bit(1) DEFAULT NULL,
  `AVATAR` varchar(255) DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  KEY `FK_jboq3w0aies9n06aqmd81c8pb` (`REV`),
  CONSTRAINT `FK_jboq3w0aies9n06aqmd81c8pb` FOREIGN KEY (`REV`) REFERENCES `revinfo` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `USER_ID` bigint(20) NOT NULL,
  `ROLE_ID` bigint(20) NOT NULL,
  KEY `FK_oqmdk7xj0ainhxpvi79fkaq3y` (`ROLE_ID`),
  KEY `FK_j2j8kpywaghe8i36swcxv8784` (`USER_ID`),
  CONSTRAINT `FK_j2j8kpywaghe8i36swcxv8784` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_oqmdk7xj0ainhxpvi79fkaq3y` FOREIGN KEY (`ROLE_ID`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `workflow`
--

DROP TABLE IF EXISTS `workflow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `workflow` (
  `ID` bigint(20) NOT NULL,
  `DESCRIPTION` varchar(255) NOT NULL,
  `FIRST_STAGE_CFG` bigint(20) NOT NULL,
  `LAST_UPDATE` date DEFAULT NULL,
  `ACTIVE` bit(1) DEFAULT b'1',
  PRIMARY KEY (`ID`),
  KEY `FK_WF_CFG_STG` (`FIRST_STAGE_CFG`),
  CONSTRAINT `FK_WF_CFG_STG` FOREIGN KEY (`FIRST_STAGE_CFG`) REFERENCES `stage_config` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `workflow_config`
--

DROP TABLE IF EXISTS `workflow_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `workflow_config` (
  `ID` bigint(20) NOT NULL,
  `WORKFLOW_ID` bigint(20) NOT NULL,
  `STAGE_CONFIG_ID` bigint(20) NOT NULL,
  `WF_CFG_CODE` varchar(255) NOT NULL,
  `NEXT_STAGE_CONFIG` bigint(20) NOT NULL,
  `STATUS_ID` bigint(20) NOT NULL,
  `LAST_UPDATE` date DEFAULT NULL,
  `ACTIVE` bit(1) DEFAULT b'1',
  PRIMARY KEY (`ID`),
  KEY `FK_WF_CFG_STG_CFG` (`STAGE_CONFIG_ID`),
  KEY `FK_WF_CFG_NXT_STG` (`NEXT_STAGE_CONFIG`),
  KEY `FK_WF_CFG_CODE` (`WF_CFG_CODE`),
  KEY `FK_WF_CFG_STATUS` (`STATUS_ID`),
  KEY `FK_WF_CFG_WF` (`WORKFLOW_ID`),
  CONSTRAINT `FK_WF_CFG_CODE` FOREIGN KEY (`WF_CFG_CODE`) REFERENCES `workflow_config_code` (`CODE`),
  CONSTRAINT `FK_WF_CFG_NXT_STG` FOREIGN KEY (`NEXT_STAGE_CONFIG`) REFERENCES `stage_config` (`ID`),
  CONSTRAINT `FK_WF_CFG_STATUS` FOREIGN KEY (`STATUS_ID`) REFERENCES `status` (`ID`),
  CONSTRAINT `FK_WF_CFG_STG_CFG` FOREIGN KEY (`STAGE_CONFIG_ID`) REFERENCES `stage_config` (`ID`),
  CONSTRAINT `FK_WF_CFG_WF` FOREIGN KEY (`WORKFLOW_ID`) REFERENCES `workflow` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `workflow_config_code`
--

DROP TABLE IF EXISTS `workflow_config_code`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `workflow_config_code` (
  `ID` bigint(20) NOT NULL,
  `CODE` varchar(255) NOT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `LAST_UPDATE` date DEFAULT NULL,
  `ACTIVE` bit(1) DEFAULT b'1',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `IDX_CODE` (`CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `x`
--

DROP TABLE IF EXISTS `x`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `x` (
  `CONVERSATION_ID` bigint(20) NOT NULL,
  `USER_ID` bigint(20) NOT NULL,
  `CREATION_DATE` date DEFAULT NULL,
  `LAST_UPDATE` date DEFAULT NULL,
  `ACTIVE` bit(1) DEFAULT b'1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-09-17 22:42:09
