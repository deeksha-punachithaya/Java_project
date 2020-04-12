-- MySQL dump 10.13  Distrib 5.7.29, for Linux (x86_64)
--
-- Host: localhost    Database: course_credit
-- ------------------------------------------------------
-- Server version	5.7.29-0ubuntu0.16.04.1

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
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course` (
  `code` varchar(10) NOT NULL,
  `name` varchar(60) DEFAULT NULL,
  `semester` int(1) DEFAULT NULL,
  `credits` int(2) DEFAULT NULL,
  `branch` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES ('CS31','Engineering Mathematics-III',3,4,'CSE'),('CS32','Data Structures',3,4,'CSE'),('CS33','Discrete Mathematical Structures',3,4,'CSE'),('CS34','Theory of Computation',3,4,'CSE'),('CS35','Analog and Digital Design',3,4,'CSE'),('CS36','Object Oriented Programming',3,3,'CSE'),('CS41','Engineering Mathematics-IV',4,4,'CSE'),('CS42','Design and Analysis of Algorithms',4,4,'CSE'),('CS43','Microprocessors and Microcontrollers',4,4,'CSE'),('CS44','Data Communication',4,4,'CSE'),('CS45','Computer Organization and Architecture',4,4,'CSE'),('CS46','Software Engineering',4,3,'CSE'),('CS51','Operating Systems',5,4,'CSE'),('CS52','Database Systems',5,4,'CSE'),('CS53','Computer Networks',5,4,'CSE'),('CS54','Java Programming',5,4,'CSE'),('CS55','Intellectual Property Rights',5,2,'CSE'),('CS61','Compiler Design',6,4,'CSE'),('CS62','Software Engineering',6,4,'CSE'),('CS63','Unix System Programming and Web Technologies',6,4,'CSE'),('CS64','Mini Project',6,6,'CSE'),('CS71','Computer Network Security',7,4,'CSE'),('CS72','High Performance Computing',7,4,'CSE'),('CS73','Data Analytics',7,4,'CSE'),('CSE01','Data Mining',5,4,'CSE'),('CSE07','Mobile Computing',6,4,'CSE'),('CSE14','Cloud Computing',7,4,'CSE'),('CSE19','Analysis of Computer Data Networks',7,4,'CSE'),('CSE24','Project management & Engg Economics',7,4,'CSE'),('CSEAC','Extra-Curricular/CoCurricular Activities',8,2,'CSE'),('CSELE','Institutional Elective',8,4,'CSE'),('CSIN','Internship/Departmental Elective',8,4,'CSE'),('CSL37','Object Oriented Programming Laboratory',3,1,'CSE'),('CSL38','Data Structures Laboratory',3,1,'CSE'),('CSL47','Algorithms Laboratory',4,1,'CSE'),('CSL48','Python Programming Laboratory',4,1,'CSE'),('CSL57','Java Programming Laboratory',5,1,'CSE'),('CSL58','Database Systems Laboratory',5,1,'CSE'),('CSL59','Computer Networks Laboratory',5,1,'CSE'),('CSL66','Unix System Programming & Compiler Design Laboratory',6,1,'CSE'),('CSL67','IoT / Embedded Systems Laboratory',6,1,'CSE'),('CSL68','Web Technologies Laboratory',6,1,'CSE'),('CSL74','High Performance Computing Laboratory',7,1,'CSE'),('CSL75','Data Analytics Laboratory',7,1,'CSE'),('CSP','Project Work',8,16,'CSE'),('IS32','Computer Organization and Architecture',3,4,'ISE'),('IS33','Discrete Mathematical Structures',3,4,'ISE'),('IS35','Data Structures',3,4,'ISE'),('IS36','Object Oriented Programming with Java',3,4,'ISE'),('IS41','Engineering Mathematics - IV',4,4,'ISE'),('IS42','Operating Systems',4,3,'ISE'),('IS43','Operations Research',4,4,'ISE'),('IS44','Finite Automata and Formal Languages',4,4,'ISE'),('IS45','Design and Analysis of Algorithms ',4,4,'ISE'),('IS46','Microcontrollers',4,4,'ISE'),('IS51','Computer Networks',5,4,'ISE'),('IS52','Operations Research',5,4,'ISE'),('IS53','Operating Systems',5,3,'ISE'),('IS54','Database Management Systems',5,4,'ISE'),('IS55','Intellectual Property Rights',5,2,'ISE'),('IS61','System Software',6,4,'ISE'),('IS62B','Object Oriented Analysis and Design Patterns using JAVA',6,4,'ISE'),('IS63','Java and J2EE',6,4,'ISE'),('IS71','Data Mining',7,4,'ISE'),('IS72','Distributed Computing',7,4,'ISE'),('IS73','Information Security',7,4,'ISE'),('ISEA1','Digital Image Processing',5,4,'ISE'),('ISEAC','Extra/Co-Curricular Activities',8,2,'ISE'),('ISEB1','Machine Learning',6,4,'ISE'),('ISEC1','Software Testing',7,4,'ISE'),('ISED1','System Simulation and Modeling',7,4,'ISE'),('ISEE1','Data Science',7,4,'ISE'),('ISELE','Open Elective',8,4,'ISE'),('ISIN','Internship',8,4,'ISE'),('ISL37','Data Structures Lab',3,1,'ISE'),('ISL38','Object Oriented Programming with Java Lab',3,1,'ISE'),('ISL47','Design and Analysis of Algorithms Lab',4,1,'ISE'),('ISL48','Microcontrollers Lab ',4,1,'ISE'),('ISL56','Computer Networks Laboratory',5,1,'ISE'),('ISL57','Database Management Systems Laboratory',5,1,'ISE'),('ISL58','Scripting Languages Laboratory',5,2,'ISE'),('ISL65','System Software Laboratory',6,1,'ISE'),('ISL66','Object Oriented Analysis and Design Patterns Laboratory',6,1,'ISE'),('ISL67','Java and J2EE Laboratory',6,1,'ISE'),('ISL74','Data Mining Lab',7,1,'ISE'),('ISL75','Distributed Computing Lab',7,1,'ISE'),('ISP','Senior Project',8,16,'ISE');
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sample`
--

DROP TABLE IF EXISTS `sample`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sample` (
  `num` int(2) NOT NULL,
  PRIMARY KEY (`num`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sample`
--

LOCK TABLES `sample` WRITE;
/*!40000 ALTER TABLE `sample` DISABLE KEYS */;
INSERT INTO `sample` VALUES (5),(6);
/*!40000 ALTER TABLE `sample` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-12 21:57:05
