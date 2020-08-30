-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.25-rc-community


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema pms
--

CREATE DATABASE IF NOT EXISTS pms;
USE pms;

--
-- Definition of table `member_type`
--

DROP TABLE IF EXISTS `member_type`;
CREATE TABLE `member_type` (
  `member_type_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `member_type_name` varchar(20) NOT NULL,
  `description` varchar(500) NOT NULL,
  `price` double NOT NULL,
  `percentage_discount` double NOT NULL,
  PRIMARY KEY (`member_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `member_type`
--

/*!40000 ALTER TABLE `member_type` DISABLE KEYS */;
INSERT INTO `member_type` (`member_type_id`,`member_type_name`,`description`,`price`,`percentage_discount`) VALUES 
 (1,'Platinum','kfjdslfsdj',30000,10);
/*!40000 ALTER TABLE `member_type` ENABLE KEYS */;


--
-- Definition of table `membership`
--

DROP TABLE IF EXISTS `membership`;
CREATE TABLE `membership` (
  `member_id` varchar(10) NOT NULL,
  `member_name` varchar(20) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `valid_from` date NOT NULL,
  `valid_to` date NOT NULL,
  `date` date NOT NULL,
  `membership_status_id` int(10) unsigned NOT NULL,
  `member_type_id` varchar(10) NOT NULL,
  `nrc` varchar(30) NOT NULL,
  `address` varchar(45) NOT NULL,
  PRIMARY KEY (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `membership`
--

/*!40000 ALTER TABLE `membership` DISABLE KEYS */;
INSERT INTO `membership` (`member_id`,`member_name`,`phone`,`valid_from`,`valid_to`,`date`,`membership_status_id`,`member_type_id`,`nrc`,`address`) VALUES 
 ('MBS1905001','Zin Ko','5555515','2019-05-01','2020-05-01','2019-05-01',1,'1','fdf','fgfd');
/*!40000 ALTER TABLE `membership` ENABLE KEYS */;


--
-- Definition of table `membership_status`
--

DROP TABLE IF EXISTS `membership_status`;
CREATE TABLE `membership_status` (
  `membership_status_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `membership_status` varchar(10) NOT NULL,
  PRIMARY KEY (`membership_status_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `membership_status`
--

/*!40000 ALTER TABLE `membership_status` DISABLE KEYS */;
/*!40000 ALTER TABLE `membership_status` ENABLE KEYS */;


--
-- Definition of table `plaything`
--

DROP TABLE IF EXISTS `plaything`;
CREATE TABLE `plaything` (
  `plaything_id` varchar(10) NOT NULL,
  `plaything_name` varchar(20) NOT NULL,
  `price` double NOT NULL,
  `description` varchar(500) NOT NULL,
  `created_date` date NOT NULL,
  `play_count` int(10) unsigned NOT NULL,
  `plaything_categories_id` varchar(20) NOT NULL,
  `running_count` int(10) unsigned NOT NULL,
  `maintenance_count` int(10) unsigned NOT NULL,
  `plaything_status_id` int(10) unsigned NOT NULL,
  `service_count` int(10) unsigned NOT NULL,
  PRIMARY KEY (`plaything_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `plaything`
--

/*!40000 ALTER TABLE `plaything` DISABLE KEYS */;
INSERT INTO `plaything` (`plaything_id`,`plaything_name`,`price`,`description`,`created_date`,`play_count`,`plaything_categories_id`,`running_count`,`maintenance_count`,`plaything_status_id`,`service_count`) VALUES 
 ('PLT1905001','a',20000,'acllll','2019-05-04',0,'1',0,0,1,0),
 ('PLT1905002','pp',9,'pp','2019-09-09',0,'1',0,0,1,9),
 ('PLT1905003','a',2000,'ppp','2019-05-16',0,'1',0,0,1,0);
/*!40000 ALTER TABLE `plaything` ENABLE KEYS */;


--
-- Definition of table `plaything_categories`
--

DROP TABLE IF EXISTS `plaything_categories`;
CREATE TABLE `plaything_categories` (
  `plaything_categories_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `plaything_categories_name` varchar(20) NOT NULL,
  PRIMARY KEY (`plaything_categories_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `plaything_categories`
--

/*!40000 ALTER TABLE `plaything_categories` DISABLE KEYS */;
INSERT INTO `plaything_categories` (`plaything_categories_id`,`plaything_categories_name`) VALUES 
 (1,'Horror'),
 (2,'3D'),
 (3,'Excited');
/*!40000 ALTER TABLE `plaything_categories` ENABLE KEYS */;


--
-- Definition of table `plaything_status`
--

DROP TABLE IF EXISTS `plaything_status`;
CREATE TABLE `plaything_status` (
  `plaything_status_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `plaything_status` varchar(10) NOT NULL,
  PRIMARY KEY (`plaything_status_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `plaything_status`
--

/*!40000 ALTER TABLE `plaything_status` DISABLE KEYS */;
INSERT INTO `plaything_status` (`plaything_status_id`,`plaything_status`) VALUES 
 (1,'Active');
/*!40000 ALTER TABLE `plaything_status` ENABLE KEYS */;


--
-- Definition of table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
CREATE TABLE `ticket` (
  `ticket_id` varchar(10) NOT NULL,
  `customer_name` varchar(30) NOT NULL,
  `valid_from` date NOT NULL,
  `valid_to` date NOT NULL,
  `topup_amount` double NOT NULL,
  `balance` double NOT NULL,
  `date` date NOT NULL,
  `ticket_status_id` int(10) unsigned NOT NULL,
  `ticket_type_id` varchar(10) NOT NULL,
  `member_id` varchar(10) NOT NULL,
  `quantity` int(10) unsigned NOT NULL,
  `ticket_price` double NOT NULL,
  `discount` double NOT NULL,
  PRIMARY KEY (`ticket_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ticket`
--

/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
INSERT INTO `ticket` (`ticket_id`,`customer_name`,`valid_from`,`valid_to`,`topup_amount`,`balance`,`date`,`ticket_status_id`,`ticket_type_id`,`member_id`,`quantity`,`ticket_price`,`discount`) VALUES 
 ('TCK1905001','Bobb','2019-09-09','2019-10-02',30000,10000,'2019-10-01',1,'TKT1905001','M001',2,20000,10),
 ('TCK1905002','Yin Nyein Phyo','2019-09-09','2019-10-01',20000,22000,'2019-05-24',3,'TKT1905001','M002',1,2000,0),
 ('TCK1905003','','2019-05-23','2019-05-25',10000,14000,'2019-05-24',3,'TKT1905001','M001',2,2000,0),
 ('TCK1905004','MWM','2019-05-15','2019-05-14',20000,21111,'2019-05-24',1,'TKT1905002','M002',1,1111,0);
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;


--
-- Definition of table `ticket_plaything`
--

DROP TABLE IF EXISTS `ticket_plaything`;
CREATE TABLE `ticket_plaything` (
  `ticket_plaything_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ticket_id` varchar(10) NOT NULL,
  `plaything_id` varchar(10) NOT NULL,
  `date` date NOT NULL,
  `time` varchar(8) NOT NULL,
  PRIMARY KEY (`ticket_plaything_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ticket_plaything`
--

/*!40000 ALTER TABLE `ticket_plaything` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket_plaything` ENABLE KEYS */;


--
-- Definition of table `ticket_plaything_status`
--

DROP TABLE IF EXISTS `ticket_plaything_status`;
CREATE TABLE `ticket_plaything_status` (
  `ticket_plaything_status_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ticket_plaything_status` varchar(10) NOT NULL,
  PRIMARY KEY (`ticket_plaything_status_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ticket_plaything_status`
--

/*!40000 ALTER TABLE `ticket_plaything_status` DISABLE KEYS */;
INSERT INTO `ticket_plaything_status` (`ticket_plaything_status_id`,`ticket_plaything_status`) VALUES 
 (1,'Active');
/*!40000 ALTER TABLE `ticket_plaything_status` ENABLE KEYS */;


--
-- Definition of table `ticket_status`
--

DROP TABLE IF EXISTS `ticket_status`;
CREATE TABLE `ticket_status` (
  `ticket_status_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ticket_status` varchar(10) NOT NULL,
  PRIMARY KEY (`ticket_status_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ticket_status`
--

/*!40000 ALTER TABLE `ticket_status` DISABLE KEYS */;
INSERT INTO `ticket_status` (`ticket_status_id`,`ticket_status`) VALUES 
 (1,'Active'),
 (3,'Active'),
 (5,'Cancel'),
 (6,'Active');
/*!40000 ALTER TABLE `ticket_status` ENABLE KEYS */;


--
-- Definition of table `ticket_type`
--

DROP TABLE IF EXISTS `ticket_type`;
CREATE TABLE `ticket_type` (
  `ticket_type_id` varchar(10) NOT NULL,
  `ticket_type_name` varchar(20) NOT NULL,
  `price` double NOT NULL,
  `valid_from` date NOT NULL,
  `valid_to` date NOT NULL,
  `ticket_type_status_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`ticket_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ticket_type`
--

/*!40000 ALTER TABLE `ticket_type` DISABLE KEYS */;
INSERT INTO `ticket_type` (`ticket_type_id`,`ticket_type_name`,`price`,`valid_from`,`valid_to`,`ticket_type_status_id`) VALUES 
 ('TKT1905001','One Day ',2000,'2019-05-09','2019-05-11',1),
 ('TKT1905002','aaaaaa',1111,'2019-09-09','2019-10-09',1),
 ('TKT1905003','saaa',1111,'2019-09-09','2019-09-09',1);
/*!40000 ALTER TABLE `ticket_type` ENABLE KEYS */;


--
-- Definition of table `ticket_type_plaything`
--

DROP TABLE IF EXISTS `ticket_type_plaything`;
CREATE TABLE `ticket_type_plaything` (
  `ticket_type_plaything_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ticket_type_id` varchar(10) NOT NULL,
  `plaything_id` varchar(10) NOT NULL,
  `count` int(10) unsigned NOT NULL,
  PRIMARY KEY (`ticket_type_plaything_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ticket_type_plaything`
--

/*!40000 ALTER TABLE `ticket_type_plaything` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket_type_plaything` ENABLE KEYS */;


--
-- Definition of table `ticket_type_status`
--

DROP TABLE IF EXISTS `ticket_type_status`;
CREATE TABLE `ticket_type_status` (
  `ticket_type_status_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ticket_type_status` varchar(10) NOT NULL,
  PRIMARY KEY (`ticket_type_status_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ticket_type_status`
--

/*!40000 ALTER TABLE `ticket_type_status` DISABLE KEYS */;
INSERT INTO `ticket_type_status` (`ticket_type_status_id`,`ticket_type_status`) VALUES 
 (1,'Active'),
 (2,'Cancel');
/*!40000 ALTER TABLE `ticket_type_status` ENABLE KEYS */;


--
-- Definition of table `topup_amount`
--

DROP TABLE IF EXISTS `topup_amount`;
CREATE TABLE `topup_amount` (
  `topup_amount_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `topup_amount` double NOT NULL,
  `date` date NOT NULL,
  `time` varchar(20) NOT NULL,
  `ticket_id` varchar(45) NOT NULL,
  `total_balance` double NOT NULL,
  PRIMARY KEY (`topup_amount_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `topup_amount`
--

/*!40000 ALTER TABLE `topup_amount` DISABLE KEYS */;
/*!40000 ALTER TABLE `topup_amount` ENABLE KEYS */;


--
-- Definition of table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` varchar(10) NOT NULL,
  `user_name` varchar(20) NOT NULL,
  `password` varchar(16) NOT NULL,
  `nrc` varchar(15) NOT NULL,
  `address` varchar(30) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `email` varchar(15) NOT NULL,
  `role_id` int(10) unsigned NOT NULL,
  `user_status_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`user_id`,`user_name`,`password`,`nrc`,`address`,`phone`,`email`,`role_id`,`user_status_id`) VALUES 
 ('USR1905001','zkl','123','12/OKM(C)12','Yangon','09131414','zkl@gmail.com',2,0),
 ('USR1905002','YNP','222','12/bahana(N)12','Yangon','09444109735','ynp@gmail.com',1,1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;


--
-- Definition of table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `role_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `role` varchar(15) NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_role`
--

/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` (`role_id`,`role`) VALUES 
 (1,'Admin'),
 (2,'Staff');
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;


--
-- Definition of table `user_status`
--

DROP TABLE IF EXISTS `user_status`;
CREATE TABLE `user_status` (
  `user_status_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_status` varchar(10) NOT NULL,
  PRIMARY KEY (`user_status_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_status`
--

/*!40000 ALTER TABLE `user_status` DISABLE KEYS */;
INSERT INTO `user_status` (`user_status_id`,`user_status`) VALUES 
 (2,'Active'),
 (3,'Active');
/*!40000 ALTER TABLE `user_status` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
