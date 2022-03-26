/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 80025
Source Host           : localhost:3306
Source Database       : cloud

Target Server Type    : MYSQL
Target Server Version : 80025
File Encoding         : 65001

Date: 2022-03-23 15:34:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `c_activity`
-- ----------------------------
DROP TABLE IF EXISTS `c_activity`;
CREATE TABLE `c_activity` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `describe` varchar(255) DEFAULT NULL,
  `activityMan` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `participationConditions` varchar(255) DEFAULT NULL,
  `price` varchar(255) DEFAULT NULL,
  `manNums` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of c_activity
-- ----------------------------

-- ----------------------------
-- Table structure for `c_activity_type`
-- ----------------------------
DROP TABLE IF EXISTS `c_activity_type`;
CREATE TABLE `c_activity_type` (
  `aid` bigint NOT NULL,
  `tid` int NOT NULL,
  PRIMARY KEY (`aid`,`tid`),
  KEY `typid` (`tid`),
  CONSTRAINT `activityId` FOREIGN KEY (`aid`) REFERENCES `c_activity` (`id`),
  CONSTRAINT `typid` FOREIGN KEY (`tid`) REFERENCES `c_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of c_activity_type
-- ----------------------------

-- ----------------------------
-- Table structure for `c_resource`
-- ----------------------------
DROP TABLE IF EXISTS `c_resource`;
CREATE TABLE `c_resource` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `describe` varchar(255) DEFAULT NULL,
  `resourceMan` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of c_resource
-- ----------------------------

-- ----------------------------
-- Table structure for `c_resource_type`
-- ----------------------------
DROP TABLE IF EXISTS `c_resource_type`;
CREATE TABLE `c_resource_type` (
  `rid` bigint NOT NULL,
  `tid` int NOT NULL,
  PRIMARY KEY (`rid`,`tid`),
  KEY `typeId` (`tid`),
  CONSTRAINT `resourceId` FOREIGN KEY (`rid`) REFERENCES `c_resource` (`id`),
  CONSTRAINT `typeId` FOREIGN KEY (`tid`) REFERENCES `c_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of c_resource_type
-- ----------------------------

-- ----------------------------
-- Table structure for `c_type`
-- ----------------------------
DROP TABLE IF EXISTS `c_type`;
CREATE TABLE `c_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of c_type
-- ----------------------------
