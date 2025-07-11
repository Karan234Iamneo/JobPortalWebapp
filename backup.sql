/*M!999999\- enable the sandbox mode */ 
-- MariaDB dump 10.19  Distrib 10.5.29-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: appdb
-- ------------------------------------------------------
-- Server version	5.6.51

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `appdb`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `appdb` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `appdb`;

--
-- Table structure for table `admin_ban`
--

DROP TABLE IF EXISTS `admin_ban`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin_ban` (
  `ban_id` int(11) NOT NULL AUTO_INCREMENT,
  `banned_by` int(11) NOT NULL,
  `entity_id` int(11) NOT NULL,
  `banned_on` datetime(6) NOT NULL,
  `reason` text NOT NULL,
  `entity_type` enum('company','job','jobseeker') NOT NULL,
  `status` enum('active','revoked') NOT NULL,
  PRIMARY KEY (`ban_id`),
  KEY `FKig7cvwwla25yv86lpdk0079i0` (`banned_by`),
  CONSTRAINT `FKig7cvwwla25yv86lpdk0079i0` FOREIGN KEY (`banned_by`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_ban`
--

LOCK TABLES `admin_ban` WRITE;
/*!40000 ALTER TABLE `admin_ban` DISABLE KEYS */;
/*!40000 ALTER TABLE `admin_ban` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `company` (
  `company_id` int(11) NOT NULL AUTO_INCREMENT,
  `recruiter_id` int(11) NOT NULL,
  `company_location` varchar(100) DEFAULT NULL,
  `company_name` varchar(100) NOT NULL,
  `about` text,
  `status` enum('banned','in_review','published') NOT NULL,
  PRIMARY KEY (`company_id`),
  UNIQUE KEY `UK46jubpbtfae2gfb74a3x6qug7` (`company_name`),
  KEY `FK80qa14g7djyn82qacof9qsh75` (`recruiter_id`),
  CONSTRAINT `FK80qa14g7djyn82qacof9qsh75` FOREIGN KEY (`recruiter_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job_application`
--

DROP TABLE IF EXISTS `job_application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `job_application` (
  `job_application_id` int(11) NOT NULL AUTO_INCREMENT,
  `job_id` int(11) NOT NULL,
  `jobseeker_id` int(11) NOT NULL,
  `status` enum('applied','rejected','selected','shortlisted') NOT NULL,
  PRIMARY KEY (`job_application_id`),
  KEY `FK10hrhnupwe0fiknw4bt4u49fp` (`job_id`),
  KEY `FK526yf8rf5pi88t5cl7vuv377v` (`jobseeker_id`),
  CONSTRAINT `FK10hrhnupwe0fiknw4bt4u49fp` FOREIGN KEY (`job_id`) REFERENCES `jobs` (`job_id`),
  CONSTRAINT `FK526yf8rf5pi88t5cl7vuv377v` FOREIGN KEY (`jobseeker_id`) REFERENCES `job_seeker` (`jobseeker_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_application`
--

LOCK TABLES `job_application` WRITE;
/*!40000 ALTER TABLE `job_application` DISABLE KEYS */;
/*!40000 ALTER TABLE `job_application` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job_application_answers`
--

DROP TABLE IF EXISTS `job_application_answers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `job_application_answers` (
  `answer_id` int(11) NOT NULL AUTO_INCREMENT,
  `job_application_id` int(11) NOT NULL,
  `question_number` int(11) DEFAULT NULL,
  `answer` text,
  PRIMARY KEY (`answer_id`),
  KEY `FK4t1tof8ms6y94xiej4jh9e4km` (`job_application_id`),
  CONSTRAINT `FK4t1tof8ms6y94xiej4jh9e4km` FOREIGN KEY (`job_application_id`) REFERENCES `job_application` (`job_application_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_application_answers`
--

LOCK TABLES `job_application_answers` WRITE;
/*!40000 ALTER TABLE `job_application_answers` DISABLE KEYS */;
/*!40000 ALTER TABLE `job_application_answers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job_questions`
--

DROP TABLE IF EXISTS `job_questions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `job_questions` (
  `job_id` int(11) NOT NULL,
  `question_id` int(11) NOT NULL AUTO_INCREMENT,
  `question` text NOT NULL,
  PRIMARY KEY (`question_id`),
  KEY `FKfi2srtkv10yb5ixlrs13vtygb` (`job_id`),
  CONSTRAINT `FKfi2srtkv10yb5ixlrs13vtygb` FOREIGN KEY (`job_id`) REFERENCES `jobs` (`job_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_questions`
--

LOCK TABLES `job_questions` WRITE;
/*!40000 ALTER TABLE `job_questions` DISABLE KEYS */;
/*!40000 ALTER TABLE `job_questions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job_seeker`
--

DROP TABLE IF EXISTS `job_seeker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `job_seeker` (
  `jobseeker_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `resume_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`jobseeker_id`),
  UNIQUE KEY `UKls9yllllsot92tgfvwdpqsclo` (`user_id`),
  CONSTRAINT `FKt6no6vfq2vtvqbwlqyik9hyef` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_seeker`
--

LOCK TABLES `job_seeker` WRITE;
/*!40000 ALTER TABLE `job_seeker` DISABLE KEYS */;
INSERT INTO `job_seeker` VALUES (1,1,'https://example.com/resume/ritika.pdf');
/*!40000 ALTER TABLE `job_seeker` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job_seeker_job_role_interests`
--

DROP TABLE IF EXISTS `job_seeker_job_role_interests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `job_seeker_job_role_interests` (
  `job_seeker_jobseeker_id` int(11) NOT NULL,
  `job_role_interests` varchar(255) DEFAULT NULL,
  KEY `FKokmw95y598dit8v9hoadt3949` (`job_seeker_jobseeker_id`),
  CONSTRAINT `FKokmw95y598dit8v9hoadt3949` FOREIGN KEY (`job_seeker_jobseeker_id`) REFERENCES `job_seeker` (`jobseeker_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_seeker_job_role_interests`
--

LOCK TABLES `job_seeker_job_role_interests` WRITE;
/*!40000 ALTER TABLE `job_seeker_job_role_interests` DISABLE KEYS */;
INSERT INTO `job_seeker_job_role_interests` VALUES (1,'Backend Developer'),(1,'Full Stack Developer');
/*!40000 ALTER TABLE `job_seeker_job_role_interests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job_seeker_skills`
--

DROP TABLE IF EXISTS `job_seeker_skills`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `job_seeker_skills` (
  `job_seeker_jobseeker_id` int(11) NOT NULL,
  `skills` varchar(255) DEFAULT NULL,
  KEY `FK26axl5t4y1qf4wa9auft983hj` (`job_seeker_jobseeker_id`),
  CONSTRAINT `FK26axl5t4y1qf4wa9auft983hj` FOREIGN KEY (`job_seeker_jobseeker_id`) REFERENCES `job_seeker` (`jobseeker_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_seeker_skills`
--

LOCK TABLES `job_seeker_skills` WRITE;
/*!40000 ALTER TABLE `job_seeker_skills` DISABLE KEYS */;
INSERT INTO `job_seeker_skills` VALUES (1,'Java'),(1,'Spring Boot'),(1,'React');
/*!40000 ALTER TABLE `job_seeker_skills` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jobs`
--

DROP TABLE IF EXISTS `jobs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `jobs` (
  `employer_id` int(11) NOT NULL,
  `job_id` int(11) NOT NULL AUTO_INCREMENT,
  `max_salary` double DEFAULT NULL,
  `min_salary` double DEFAULT NULL,
  `years_of_experience` int(11) DEFAULT NULL,
  `location` varchar(100) DEFAULT NULL,
  `description` text,
  `status` enum('cancelled','closed','draft','published') NOT NULL,
  PRIMARY KEY (`job_id`),
  KEY `FK9ur2hhhufsmctegg8mad7967k` (`employer_id`),
  CONSTRAINT `FK9ur2hhhufsmctegg8mad7967k` FOREIGN KEY (`employer_id`) REFERENCES `company` (`company_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jobs`
--

LOCK TABLES `jobs` WRITE;
/*!40000 ALTER TABLE `jobs` DISABLE KEYS */;
/*!40000 ALTER TABLE `jobs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jobs_skills`
--

DROP TABLE IF EXISTS `jobs_skills`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `jobs_skills` (
  `jobs_job_id` int(11) NOT NULL,
  `skills` varchar(255) DEFAULT NULL,
  KEY `FKh1payune4xf3tkoshw5gys88r` (`jobs_job_id`),
  CONSTRAINT `FKh1payune4xf3tkoshw5gys88r` FOREIGN KEY (`jobs_job_id`) REFERENCES `jobs` (`job_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jobs_skills`
--

LOCK TABLES `jobs_skills` WRITE;
/*!40000 ALTER TABLE `jobs_skills` DISABLE KEYS */;
/*!40000 ALTER TABLE `jobs_skills` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `phone` varchar(15) DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `role` enum('admin','jobseeker','recruiter') NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UKob8kqyqqgmefl0aco34akdtpe` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'9876543210','ritika.sharma@example.com','Ritika Sharma','Bengaluru, Karnataka','securePassword123','jobseeker');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-07-11  5:12:39
