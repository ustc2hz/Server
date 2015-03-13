-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.6.21 - MySQL Community Server (GPL)
-- Server OS:                    Win32
-- HeidiSQL version:             7.0.0.4053
-- Date/time:                    2015-03-08 14:21:12
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;

-- Dumping database structure for lbs_server
DROP DATABASE IF EXISTS `lbs_server`;
CREATE DATABASE IF NOT EXISTS `lbs_server` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `lbs_server`;


-- Dumping structure for table lbs_server.lbs_admin
DROP TABLE IF EXISTS `lbs_admin`;
CREATE TABLE IF NOT EXISTS `lbs_admin` (
  `admin_id` int(10) NOT NULL AUTO_INCREMENT,
  `admin_name` varchar(50) NOT NULL,
  `admin_password` varchar(50) NOT NULL,
  `admin_phone` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`admin_id`),
  UNIQUE KEY `admin_name` (`admin_name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Dumping data for table lbs_server.lbs_admin: ~0 rows (approximately)
/*!40000 ALTER TABLE `lbs_admin` DISABLE KEYS */;
INSERT INTO `lbs_admin` (`admin_id`, `admin_name`, `admin_password`, `admin_phone`) VALUES
	(1, 'zjx', '666666', '123456789');
/*!40000 ALTER TABLE `lbs_admin` ENABLE KEYS */;


-- Dumping structure for table lbs_server.lbs_driver
DROP TABLE IF EXISTS `lbs_driver`;
CREATE TABLE IF NOT EXISTS `lbs_driver` (
  `driver_id` int(10) NOT NULL AUTO_INCREMENT,
  `driver_name` varchar(50) NOT NULL,
  `driver_password` varchar(50) NOT NULL,
  `driver_phone` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`driver_id`),
  UNIQUE KEY `driver_name` (`driver_name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Dumping data for table lbs_server.lbs_driver: ~0 rows (approximately)
/*!40000 ALTER TABLE `lbs_driver` DISABLE KEYS */;
INSERT INTO `lbs_driver` (`driver_id`, `driver_name`, `driver_password`, `driver_phone`) VALUES
	(1, '苏888888', '888888', '987654321');
/*!40000 ALTER TABLE `lbs_driver` ENABLE KEYS */;


-- Dumping structure for table lbs_server.lbs_order
DROP TABLE IF EXISTS `lbs_order`;
CREATE TABLE IF NOT EXISTS `lbs_order` (
  `order_id` int(10) NOT NULL AUTO_INCREMENT,
  `order_uuid` varchar(100) NOT NULL,
  `park_name` varchar(100) NOT NULL,
  `park_address` varchar(100) NOT NULL,
  `driver_number` int(10) NOT NULL,
  `order_date` varchar(50) NOT NULL,
  `order_info` varchar(100) NOT NULL,
  `order_price` varchar(50) NOT NULL,
  `order_status` int(10) NOT NULL,
  `admin_id` int(10) NOT NULL,
  `driver_id` int(10) NOT NULL,
  PRIMARY KEY (`order_id`),
  KEY `FK_fgxee5kpoc16brjv5dn7911pp` (`admin_id`),
  KEY `FK_sq04fd2bggg625osrtsna68lf` (`driver_id`),
  CONSTRAINT `FK_fgxee5kpoc16brjv5dn7911pp` FOREIGN KEY (`admin_id`) REFERENCES `lbs_admin` (`admin_id`),
  CONSTRAINT `FK_sq04fd2bggg625osrtsna68lf` FOREIGN KEY (`driver_id`) REFERENCES `lbs_driver` (`driver_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Dumping data for table lbs_server.lbs_order: ~2 rows (approximately)
/*!40000 ALTER TABLE `lbs_order` DISABLE KEYS */;
INSERT INTO `lbs_order` (`order_id`, `order_uuid`, `park_name`, `park_address`, `driver_number`, `order_date`, `order_info`, `order_price`, `order_status`, `admin_id`, `driver_id`) VALUES
	(2, '', '测试停车场', '测试停车地址', 2, '2015-03-07', '提前10分钟预定收取：5元', '10', 1, 1, 1);
/*!40000 ALTER TABLE `lbs_order` ENABLE KEYS */;
/*!40014 SET FOREIGN_KEY_CHECKS=1 */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
