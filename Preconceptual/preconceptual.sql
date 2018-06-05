CREATE DATABASE  IF NOT EXISTS `preconceptual` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `preconceptual`;
-- MySQL dump 10.13  Distrib 5.7.22, for Linux (x86_64)
--
-- Host: localhost    Database: preconceptual
-- ------------------------------------------------------
-- Server version	5.7.22

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
-- Table structure for table `esquemas`
--

DROP TABLE IF EXISTS `esquemas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `esquemas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `esquemas`
--

LOCK TABLES `esquemas` WRITE;
/*!40000 ALTER TABLE `esquemas` DISABLE KEYS */;
INSERT INTO `esquemas` VALUES (1,'Máquina de café');
/*!40000 ALTER TABLE `esquemas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sustantivos`
--

DROP TABLE IF EXISTS `sustantivos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sustantivos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `esquema` int(11) DEFAULT NULL,
  `nombre` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre_UNIQUE` (`nombre`),
  KEY `fk_sustantivos_1_idx` (`esquema`),
  CONSTRAINT `fk_sustantivos_1` FOREIGN KEY (`esquema`) REFERENCES `esquemas` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sustantivos`
--

LOCK TABLES `sustantivos` WRITE;
/*!40000 ALTER TABLE `sustantivos` DISABLE KEYS */;
INSERT INTO `sustantivos` VALUES (1,1,'Maquina'),(2,1,'Cafe'),(4,1,'Devuelta');
/*!40000 ALTER TABLE `sustantivos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `triadas`
--

DROP TABLE IF EXISTS `triadas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `triadas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sustantivo1` int(11) NOT NULL,
  `conector` varchar(2555) NOT NULL,
  `sustantivo2` int(11) NOT NULL,
  `dependencia` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_triadas_1_idx` (`sustantivo1`),
  KEY `fk_triadas_2_idx` (`sustantivo2`),
  KEY `fk_triadas_3_idx` (`dependencia`),
  CONSTRAINT `fk_triadas_1` FOREIGN KEY (`sustantivo1`) REFERENCES `sustantivos` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_triadas_2` FOREIGN KEY (`sustantivo2`) REFERENCES `sustantivos` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_triadas_3` FOREIGN KEY (`dependencia`) REFERENCES `triadas` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `triadas`
--

LOCK TABLES `triadas` WRITE;
/*!40000 ALTER TABLE `triadas` DISABLE KEYS */;
INSERT INTO `triadas` VALUES (1,1,'Entrega',2,NULL),(3,1,'Entrega',4,1);
/*!40000 ALTER TABLE `triadas` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-06-05 10:38:38
