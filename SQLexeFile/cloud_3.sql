/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 80025
Source Host           : localhost:3306
Source Database       : cloud

Target Server Type    : MYSQL
Target Server Version : 80025
File Encoding         : 65001

Date: 2022-03-24 15:17:18
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
-- Table structure for `c_member`
-- ----------------------------
DROP TABLE IF EXISTS `c_member`;
CREATE TABLE `c_member` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `sex` bit(1) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `admissionTime` date DEFAULT NULL,
  `integral` int DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `telPhone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of c_member
-- ----------------------------

-- ----------------------------
-- Table structure for `c_member_role`
-- ----------------------------
DROP TABLE IF EXISTS `c_member_role`;
CREATE TABLE `c_member_role` (
  `mid` int NOT NULL,
  `rid` int NOT NULL,
  PRIMARY KEY (`mid`),
  KEY `roleId` (`rid`),
  CONSTRAINT `memberId` FOREIGN KEY (`mid`) REFERENCES `c_member` (`id`),
  CONSTRAINT `roleId` FOREIGN KEY (`rid`) REFERENCES `c_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of c_member_role
-- ----------------------------

-- ----------------------------
-- Table structure for `c_menu`
-- ----------------------------
DROP TABLE IF EXISTS `c_menu`;
CREATE TABLE `c_menu` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `linkUrl` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `priority` int DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `parentMenuId` int DEFAULT NULL,
  `level` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `parentMenuId` (`parentMenuId`),
  CONSTRAINT `parentMenuId` FOREIGN KEY (`parentMenuId`) REFERENCES `c_menu` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of c_menu
-- ----------------------------

-- ----------------------------
-- Table structure for `c_permission`
-- ----------------------------
DROP TABLE IF EXISTS `c_permission`;
CREATE TABLE `c_permission` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `keyword` varchar(64) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of c_permission
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
-- Table structure for `c_role`
-- ----------------------------
DROP TABLE IF EXISTS `c_role`;
CREATE TABLE `c_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `keyword` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `decription` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of c_role
-- ----------------------------
INSERT INTO `c_role` VALUES ('1', '管理员', null, null);
INSERT INTO `c_role` VALUES ('2', '成员', null, null);
INSERT INTO `c_role` VALUES ('3', '游客', null, null);

-- ----------------------------
-- Table structure for `c_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `c_role_menu`;
CREATE TABLE `c_role_menu` (
  `rid` int NOT NULL,
  `mid` int NOT NULL,
  PRIMARY KEY (`rid`,`mid`),
  KEY `mid` (`mid`),
  CONSTRAINT `mid` FOREIGN KEY (`mid`) REFERENCES `c_menu` (`id`),
  CONSTRAINT `rid` FOREIGN KEY (`rid`) REFERENCES `c_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of c_role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for `c_role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `c_role_permission`;
CREATE TABLE `c_role_permission` (
  `rid` int NOT NULL,
  `pid` int DEFAULT NULL,
  PRIMARY KEY (`rid`),
  KEY `permissionId` (`pid`),
  CONSTRAINT `permissionId` FOREIGN KEY (`pid`) REFERENCES `c_permission` (`id`),
  CONSTRAINT `roId` FOREIGN KEY (`rid`) REFERENCES `c_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of c_role_permission
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
