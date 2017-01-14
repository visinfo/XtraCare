-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: xtracare
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `xt_config_master`
--

DROP TABLE IF EXISTS `xt_config_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `xt_config_master` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(45) NOT NULL,
  `value` json DEFAULT NULL,
  `status` enum('ACTIVE','INACTIVE') NOT NULL,
  PRIMARY KEY (`id`,`code`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xt_config_master`
--

LOCK TABLES `xt_config_master` WRITE;
/*!40000 ALTER TABLE `xt_config_master` DISABLE KEYS */;
INSERT INTO `xt_config_master` VALUES (1,'ownerDetails','{\"email\": \"contact@gmail.com\", \"address\": \"Preet Vihar,New Delhi\", \"companyName\": \"Xtra Care Services\", \"contactNumber\": \"8882747169\"}','ACTIVE'),(3,'carModels','[\"I20\", \"Other\", \"I10\", \"BMW\"]','ACTIVE'),(4,'serviceType','[\"Free\", \"Paid\", \"Other\"]','ACTIVE'),(5,'maxUserCount','0','ACTIVE'),(6,'maxServiceBookPerDay','10','ACTIVE');
/*!40000 ALTER TABLE `xt_config_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xt_service_booking`
--

DROP TABLE IF EXISTS `xt_service_booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `xt_service_booking` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `bookingDetails` json NOT NULL,
  `bookingId` varchar(20) NOT NULL,
  `bookDate` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `bookingId_UNIQUE` (`bookingId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xt_service_booking`
--

LOCK TABLES `xt_service_booking` WRITE;
/*!40000 ALTER TABLE `xt_service_booking` DISABLE KEYS */;
INSERT INTO `xt_service_booking` VALUES (1,'{\"userId\": \"1001\", \"carModel\": \"I20\", \"jsession\": \"JsessionId\", \"mobileNbr\": \"8882747169\", \"isPickNDrop\": \"YES\", \"serviceDate\": \"yyyymmdd\", \"serviceDesc\": \"Normal service with wash\", \"serviceType\": \"PaidService\"}','45671000','0000-00-00 00:00:00');
/*!40000 ALTER TABLE `xt_service_booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xt_user_master`
--

DROP TABLE IF EXISTS `xt_user_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `xt_user_master` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `fname` varchar(50) DEFAULT NULL,
  `lname` varchar(45) DEFAULT NULL,
  `mobile_number` bigint(11) unsigned NOT NULL,
  `email` varchar(200) NOT NULL,
  `reg_date` datetime DEFAULT NULL,
  `isVerified` enum('Y','N') NOT NULL DEFAULT 'N',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1002 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xt_user_master`
--

LOCK TABLES `xt_user_master` WRITE;
/*!40000 ALTER TABLE `xt_user_master` DISABLE KEYS */;
INSERT INTO `xt_user_master` VALUES (1,'Neeraj','Jain',88882747169,'neera@gmail.com',NULL,'N'),(1000,'Neeraj','Jain',88882747169,'neera@gmail.com',NULL,'N'),(1001,'neeraj','jaiva',8883774759,'neeraj.jain@skilrock.com','2017-01-14 18:13:42','N');
/*!40000 ALTER TABLE `xt_user_master` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-01-15  0:23:12
