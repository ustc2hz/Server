/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50067
Source Host           : localhost:3306
Source Database       : parktable

Target Server Type    : MYSQL
Target Server Version : 50067
File Encoding         : 65001

Date: 2015-02-01 21:18:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `admin_id` int(11) NOT NULL auto_increment,
  `admin_name` varchar(50) NOT NULL,
  `admin_password` varchar(30) NOT NULL,
  `park_phone` varchar(50) default NULL,
  PRIMARY KEY  (`admin_id`),
  UNIQUE KEY `a` (`admin_name`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'hq', '123456', 'null');
INSERT INTO `admin` VALUES ('2', 'zjx', '666666', null);
INSERT INTO `admin` VALUES ('4', 'han', '666666', null);
INSERT INTO `admin` VALUES ('7', 'zf', '123456', null);
INSERT INTO `admin` VALUES ('10', 'Lll', '6666666', null);

-- ----------------------------
-- Table structure for `driver`
-- ----------------------------
DROP TABLE IF EXISTS `driver`;
CREATE TABLE `driver` (
  `driver_id` int(11) NOT NULL auto_increment,
  `driver_name` varchar(50) NOT NULL,
  `driver_password` varchar(30) NOT NULL,
  `driver_phone` varchar(50) default NULL,
  PRIMARY KEY  (`driver_id`),
  UNIQUE KEY `d` (`driver_name`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of driver
-- ----------------------------
INSERT INTO `driver` VALUES ('1', 'zjx', '666666', 'null');
INSERT INTO `driver` VALUES ('5', 'hq', '666666', null);
INSERT INTO `driver` VALUES ('7', 'han', '666666', null);
INSERT INTO `driver` VALUES ('8', 'admin', '123456', null);
INSERT INTO `driver` VALUES ('9', 'manager', '123456', null);
INSERT INTO `driver` VALUES ('10', 'bb', '555555', null);
INSERT INTO `driver` VALUES ('11', 'hhh', '555555', null);

-- ----------------------------
-- Table structure for `order`
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `order_id` int(11) NOT NULL auto_increment,
  `admin_id` int(11) NOT NULL,
  `park_name` varchar(50) NOT NULL,
  `park_address` varchar(100) NOT NULL,
  `driver_id` int(11) NOT NULL,
  `driver_num` int(11) NOT NULL,
  `order_date` varchar(30) NOT NULL default 'N',
  `driver_phone` varchar(50) NOT NULL,
  `order_info` varchar(100) NOT NULL,
  `order_price` varchar(10) NOT NULL,
  `order_status` int(11) NOT NULL,
  PRIMARY KEY  (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order
-- ----------------------------

-- ----------------------------
-- Table structure for `park`
-- ----------------------------
DROP TABLE IF EXISTS `park`;
CREATE TABLE `park` (
  `admin_name` varchar(50) NOT NULL,
  `park_name` varchar(30) default NULL,
  `park_address` varchar(50) NOT NULL,
  `park_phone` varchar(20) default NULL,
  PRIMARY KEY  (`admin_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of park
-- ----------------------------
INSERT INTO `park` VALUES ('hq', 'hq', 'hq', '1223444');

-- ----------------------------
-- Table structure for `parkinfo`
-- ----------------------------
DROP TABLE IF EXISTS `parkinfo`;
CREATE TABLE `parkinfo` (
  `admin_name` varchar(50) NOT NULL,
  `park_sum` varchar(10) default NULL,
  `park_remainsum` varchar(10) default NULL,
  `park_ten` varchar(10) default NULL,
  `park_tri` varchar(10) default NULL,
  `park_twe` varchar(10) default NULL,
  `park_halpay` varchar(10) default NULL,
  `park_morepay` varchar(10) default NULL,
  `park_onepay` varchar(10) default NULL,
  PRIMARY KEY  (`admin_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of parkinfo
-- ----------------------------
