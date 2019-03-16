/*
Navicat MySQL Data Transfer

Source Server         : sample
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : lims2018bdb

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2019-01-11 16:28:45
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `person_title`
-- ----------------------------
DROP TABLE IF EXISTS `person_title`;
CREATE TABLE `person_title` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `up_title_id` bigint(20) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_1so069qcrsvi8y4rqfwh0kdhu` (`name`),
  KEY `FKi6shdjfd8s0hk2fcmo37y9ou9` (`up_title_id`),
  CONSTRAINT `FKi6shdjfd8s0hk2fcmo37y9ou9` FOREIGN KEY (`up_title_id`) REFERENCES `person_title` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of person_title
-- ----------------------------
INSERT INTO `person_title` VALUES ('1', '0', null, '全体人员');
INSERT INTO `person_title` VALUES ('2', '0', '1', '教师');
INSERT INTO `person_title` VALUES ('3', '0', '1', '学生');
INSERT INTO `person_title` VALUES ('4', '0', '2', '教授');
INSERT INTO `person_title` VALUES ('5', '0', '2', '副教授');
INSERT INTO `person_title` VALUES ('6', '0', '2', '讲师');
INSERT INTO `person_title` VALUES ('7', '0', '2', '助教');
INSERT INTO `person_title` VALUES ('8', '0', '2', '教授级高工');
INSERT INTO `person_title` VALUES ('9', '0', '2', '高工');
INSERT INTO `person_title` VALUES ('10', '0', '2', '工程师');
INSERT INTO `person_title` VALUES ('11', '0', '2', '助理工程师');
INSERT INTO `person_title` VALUES ('12', '0', '2', '技术员');
INSERT INTO `person_title` VALUES ('13', '0', '3', '本科生');
INSERT INTO `person_title` VALUES ('14', '0', '3', '研究生');
INSERT INTO `person_title` VALUES ('15', '0', '13', '2015级');
INSERT INTO `person_title` VALUES ('16', '0', '14', '博士');
INSERT INTO `person_title` VALUES ('17', '0', '14', '硕士');
INSERT INTO `person_title` VALUES ('18', '0', '14', '博士后');
