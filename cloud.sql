/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 80025
Source Host           : localhost:3306
Source Database       : cloud

Target Server Type    : MYSQL
Target Server Version : 80025
File Encoding         : 65001

Date: 2022-03-05 20:08:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `c_acount`
-- ----------------------------
DROP TABLE IF EXISTS `c_acount`;
CREATE TABLE `c_acount` (
  `id` int NOT NULL,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `id_role` int NOT NULL,
  PRIMARY KEY (`id`,`id_role`),
  KEY `rol` (`id_role`),
  CONSTRAINT `rol` FOREIGN KEY (`id_role`) REFERENCES `c_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of c_acount
-- ----------------------------

-- ----------------------------
-- Table structure for `c_activity`
-- ----------------------------
DROP TABLE IF EXISTS `c_activity`;
CREATE TABLE `c_activity` (
  `id` int NOT NULL AUTO_INCREMENT,
  `activity_name` varchar(20) DEFAULT NULL,
  `begin_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `max_number` int DEFAULT NULL,
  `score` int DEFAULT NULL,
  `location` varchar(40) DEFAULT NULL,
  `create_member_id` int DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `status` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cre_mem` (`create_member_id`),
  CONSTRAINT `cre_mem` FOREIGN KEY (`create_member_id`) REFERENCES `c_member` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of c_activity
-- ----------------------------

-- ----------------------------
-- Table structure for `c_activity_member`
-- ----------------------------
DROP TABLE IF EXISTS `c_activity_member`;
CREATE TABLE `c_activity_member` (
  `id_activity` int NOT NULL,
  `id_member` int NOT NULL,
  PRIMARY KEY (`id_activity`,`id_member`),
  KEY `memb` (`id_member`),
  CONSTRAINT `activity` FOREIGN KEY (`id_activity`) REFERENCES `c_activity` (`id`),
  CONSTRAINT `memb` FOREIGN KEY (`id_member`) REFERENCES `c_member` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of c_activity_member
-- ----------------------------

-- ----------------------------
-- Table structure for `c_bookresource`
-- ----------------------------
DROP TABLE IF EXISTS `c_bookresource`;
CREATE TABLE `c_bookresource` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(40) DEFAULT NULL,
  `join_time` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `author` varchar(40) DEFAULT NULL,
  `status` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of c_bookresource
-- ----------------------------

-- ----------------------------
-- Table structure for `c_integral`
-- ----------------------------
DROP TABLE IF EXISTS `c_integral`;
CREATE TABLE `c_integral` (
  `id` int NOT NULL AUTO_INCREMENT,
  `award` int DEFAULT NULL,
  `punish` int DEFAULT NULL,
  `cause` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of c_integral
-- ----------------------------

-- ----------------------------
-- Table structure for `c_member`
-- ----------------------------
DROP TABLE IF EXISTS `c_member`;
CREATE TABLE `c_member` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `class` varchar(20) DEFAULT NULL,
  `grade` int DEFAULT NULL,
  `in_time` datetime DEFAULT NULL,
  `score` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of c_member
-- ----------------------------

-- ----------------------------
-- Table structure for `c_member_bookresource`
-- ----------------------------
DROP TABLE IF EXISTS `c_member_bookresource`;
CREATE TABLE `c_member_bookresource` (
  `id_member` int NOT NULL,
  `id_bookResource` int NOT NULL,
  PRIMARY KEY (`id_member`,`id_bookResource`),
  KEY `bookResource` (`id_bookResource`),
  CONSTRAINT `bookResource` FOREIGN KEY (`id_bookResource`) REFERENCES `c_bookresource` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `membe` FOREIGN KEY (`id_member`) REFERENCES `c_member` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of c_member_bookresource
-- ----------------------------

-- ----------------------------
-- Table structure for `c_member_integral`
-- ----------------------------
DROP TABLE IF EXISTS `c_member_integral`;
CREATE TABLE `c_member_integral` (
  `id_member` int NOT NULL,
  `id_integral` int NOT NULL,
  PRIMARY KEY (`id_member`,`id_integral`),
  KEY `integral` (`id_integral`),
  CONSTRAINT `integral` FOREIGN KEY (`id_integral`) REFERENCES `c_integral` (`id`),
  CONSTRAINT `mem` FOREIGN KEY (`id_member`) REFERENCES `c_member` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of c_member_integral
-- ----------------------------

-- ----------------------------
-- Table structure for `c_member_netresource`
-- ----------------------------
DROP TABLE IF EXISTS `c_member_netresource`;
CREATE TABLE `c_member_netresource` (
  `id_member` int NOT NULL,
  `id_netResource` int NOT NULL,
  PRIMARY KEY (`id_member`,`id_netResource`),
  KEY `netResource` (`id_netResource`),
  CONSTRAINT `me` FOREIGN KEY (`id_member`) REFERENCES `c_member` (`id`),
  CONSTRAINT `netResource` FOREIGN KEY (`id_netResource`) REFERENCES `c_netresource` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of c_member_netresource
-- ----------------------------

-- ----------------------------
-- Table structure for `c_netresource`
-- ----------------------------
DROP TABLE IF EXISTS `c_netresource`;
CREATE TABLE `c_netresource` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `status` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of c_netresource
-- ----------------------------

-- ----------------------------
-- Table structure for `c_role`
-- ----------------------------
DROP TABLE IF EXISTS `c_role`;
CREATE TABLE `c_role` (
  `id` int NOT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of c_role
-- ----------------------------
INSERT INTO `c_role` VALUES ('0', '系统管理员');
INSERT INTO `c_role` VALUES ('1', '管理员');
INSERT INTO `c_role` VALUES ('2', '成员');
INSERT INTO `c_role` VALUES ('3', '游客');

-- ----------------------------
-- Table structure for `c_role_member`
-- ----------------------------
DROP TABLE IF EXISTS `c_role_member`;
CREATE TABLE `c_role_member` (
  `role_id` int NOT NULL,
  `member_id` int NOT NULL,
  PRIMARY KEY (`role_id`,`member_id`),
  KEY `member` (`member_id`),
  CONSTRAINT `member` FOREIGN KEY (`member_id`) REFERENCES `c_member` (`id`),
  CONSTRAINT `role` FOREIGN KEY (`role_id`) REFERENCES `c_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of c_role_member
-- ----------------------------
