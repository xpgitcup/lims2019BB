/*
Navicat MySQL Data Transfer

Source Server         : 10.1.16.50
Source Server Version : 50627
Source Host           : 10.1.16.50:3306
Source Database       : lims2018bdb

Target Server Type    : MYSQL
Target Server Version : 50627
File Encoding         : 65001

Date: 2019-03-10 14:38:55
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `thing_progress`
-- ----------------------------
DROP TABLE IF EXISTS `thing_progress`;
CREATE TABLE `thing_progress` (
  `thing_progresses_id` bigint(20) NOT NULL,
  `progress_id` bigint(20) DEFAULT NULL,
  KEY `FKobt92l4fslq51a7s9smgvq74b` (`progress_id`),
  KEY `FKqqdsiyq8l68a7m27rkdgpiwk1` (`thing_progresses_id`),
  CONSTRAINT `FKobt92l4fslq51a7s9smgvq74b` FOREIGN KEY (`progress_id`) REFERENCES `progress` (`id`),
  CONSTRAINT `FKqqdsiyq8l68a7m27rkdgpiwk1` FOREIGN KEY (`thing_progresses_id`) REFERENCES `thing` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of thing_progress
-- ----------------------------
