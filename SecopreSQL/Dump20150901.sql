CREATE DATABASE  IF NOT EXISTS `secopre` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `secopre`;
-- MySQL dump 10.13  Distrib 5.6.23, for Win64 (x86_64)
--
-- Host: localhost    Database: secopre
-- ------------------------------------------------------
-- Server version	5.5.43-log

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES (1,'','icon-home','Dashboard Home por Usuario',NULL,'alert(\'al dashboar por usuario\');','Dashboard',1,NULL),(2,'','icon-settings','Modulo De Administracion',NULL,'javascript: void(0);','Administracion',2,NULL),(3,'','icon-home','Modulo Configuracion de Usuarios',NULL,'sendRequestJQ(\'auth/adm/usr/list\',\'dashboard\',\'initUserPage()\');','Usuarios',3,2),(4,'','icon-settings','Modulo Configuracion de Roles',NULL,'sendRequestJQ(\'auth/adm/role/list\',\'dashboard\',\'initRolePage()\');','Roles',4,2),(5,'','icon-settings','Modulo Configuracion de Permisos',NULL,'sendRequestJQ(\'auth/adm/perm/list\',\'dashboard\',\'initPermPage()\');','Permisos',5,2);
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `path`
--

LOCK TABLES `path` WRITE;
/*!40000 ALTER TABLE `path` DISABLE KEYS */;
INSERT INTO `path` VALUES (1,'','\0','auth/adm/usr/list',3),(2,'','\0','auth/adm/role/list',4),(3,'','\0','auth/adm/usr',2),(4,'','\0','auth/adm/perm/list',5);
/*!40000 ALTER TABLE `path` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` VALUES (1,'','USR_LIST',1),(2,'','ROLE_LIST',2),(3,'','USR_CONF',3),(4,'','PERM_LIST',4),(5,'','SU#',NULL);
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (-1,'','ROLE_USER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `role_permission`
--

LOCK TABLES `role_permission` WRITE;
/*!40000 ALTER TABLE `role_permission` DISABLE KEYS */;
INSERT INTO `role_permission` VALUES (-1,1),(-1,2),(-1,3),(-1,4);
/*!40000 ALTER TABLE `role_permission` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (-1,'','','jorge.canoc@gmail.com','','jorgekno','$2a$10$wvXkMdLjP3k.XFUeo9zHXORpU7DLCjeqgNGUuEIrcOsfEq5ZJmCvC','admin');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (-1,-1);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-09-01 20:49:08



-- nuevas tablas

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*------------------------------------------------------------------------*/

CREATE TABLE SECOPRE.STAGE(
	ID BIGINT(20) NOT NULL,
	DESCRIPTION VARCHAR(255) NOT NULL,
	LAST_UPDATE DATE,
	ACTIVE BIT(1) DEFAULT 1
);

CREATE TABLE SECOPRE.STAGE_CONFIG(
	ID BIGINT(20) NOT NULL,
	STAGE_ID BIGINT(20) NOT NULL,
	PATH_ID BIGINT(20) NOT NULL,
	IS_CAPTURE BIT(1) NOT NULL,
	IS_AUTHORIZATION BIT(1) NOT NULL,
	CAPTURE_FORM VARCHAR(255),
	LAST_UPDATE DATE,
	ACTIVE BIT(1) DEFAULT 1
);

CREATE TABLE SECOPRE.WORKFLOW(
	ID BIGINT(20) NOT NULL,
	DESCRIPTION VARCHAR(255) NOT NULL,
	FIRST_CFG_STAGE BIGINT(20) NOT NULL,
	LAST_UPDATE DATE,
	ACTIVE BIT(1) DEFAULT 1
);

CREATE TABLE SECOPRE.WORKFLOW_CONFIG(
	ID BIGINT(20) NOT NULL,
	STAGE_ID BIGINT(20) NOT NULL,
	PATH_ID BIGINT(20) NOT NULL,
	IS_CAPTURE BIT(1) NOT NULL,
	IS_AUTHORIZATION BIT(1) NOT NULL,
	CAPTURE_FORM VARCHAR(255),
	LAST_UPDATE DATE,
	ACTIVE BIT(1) DEFAULT 1
);

CREATE TABLE SECOPRE.FORMALITY(
	ID BIGINT(20) NOT NULL,
	DESCRIPTION VARCHAR(255) NOT NULL,
	WORKFLOW_ID BIGINT(20) NOT NULL,
	LAST_UPDATE DATE,
	ACTIVE BIT(1) DEFAULT 1
);

CREATE TABLE SECOPRE.REQUEST(
	ID BIGINT(20) NOT NULL,
	FIRST_NAME VARCHAR(255),
	PARENT_LAST_NAME VARCHAR(255),
	MOTHER_LAST_NAME VARCHAR(255),
	LAST_UPDATE DATE,
	ACTIVE BIT(1) DEFAULT 1
);

CREATE TABLE SECOPRE.REQUEST_CONFIG(
	ID BIGINT(20) NOT NULL,
	FORMALITY_ID BIGINT(20) NOT NULL,
	WORKFLOW_ID BIGINT(20) NOT NULL,
	LAST_UPDATE DATE,
	ACTIVE BIT(1) DEFAULT 1
);

CREATE TABLE SECOPRE.STAGE_CONFIG_ROLE(
	STAGE_CONFIG_ID BIGINT(20) NOT NULL,
	ROLE_ID BIGINT(20) NOT NULL,
	LAST_UPDATE DATE,
	ACTIVE BIT(1) DEFAULT 1
);


--etapas

INSERT INTO SECOPRE.STAGE
(ID, DESCRIPTION, LAST_UPDATE)
VALUES
(1, 'Captura Basica', SYSDATE());

INSERT INTO SECOPRE.STAGE
(ID, DESCRIPTION, LAST_UPDATE)
VALUES
(2, 'Captura Completa', SYSDATE());

INSERT INTO SECOPRE.STAGE
(ID, DESCRIPTION, LAST_UPDATE)
VALUES
(3, 'Autorizacion', SYSDATE());

-- configuracion de etapas


INSERT INTO SECOPRE.WORKFLOW
(ID, DESCRIPTION, FIRST_CFG_STAGE, LAST_UPDATE, ACTIVE)
VALUES
(1, 'Flujo de Movimientos', 1, SYSDATE, 1);

INSERT INTO SECOPRE.FORMALITY
(ID, DESCRIPTION, WORKFLOW_ID, LAST_UPDATE)
VALUES
(1, 'Tramite de Movimientos', sysdate());