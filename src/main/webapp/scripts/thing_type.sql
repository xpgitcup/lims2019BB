/*
Navicat MySQL Data Transfer

Source Server         : 10.1.16.50
Source Server Version : 50627
Source Host           : 10.1.16.50:3306
Source Database       : lims2018bdb

Target Server Type    : MYSQL
Target Server Version : 50627
File Encoding         : 65001

Date: 2019-03-10 14:39:03
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `thing_type`
-- ----------------------------
DROP TABLE IF EXISTS `thing_type`;
CREATE TABLE `thing_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `up_type_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_gdcrc8efclj9bf72ek4grj09u` (`name`),
  KEY `FKpnengadsd69u0k59x286mr04` (`up_type_id`),
  CONSTRAINT `FKpnengadsd69u0k59x286mr04` FOREIGN KEY (`up_type_id`) REFERENCES `thing_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of thing_type
-- ----------------------------
INSERT INTO `thing_type` VALUES ('1', '0', '全部任务', null);
INSERT INTO `thing_type` VALUES ('2', '0', '科研项目', '1');
INSERT INTO `thing_type` VALUES ('3', '0', '教学任务', '1');
INSERT INTO `thing_type` VALUES ('4', '0', '国家科技重大专项', '2');
INSERT INTO `thing_type` VALUES ('5', '0', '自然基金', '2');
INSERT INTO `thing_type` VALUES ('6', '1', '省级自然基金', '2');
INSERT INTO `thing_type` VALUES ('7', '0', '横向项目', '2');
INSERT INTO `thing_type` VALUES ('8', '0', '课堂教学', '3');
INSERT INTO `thing_type` VALUES ('9', '0', '课程设计', '3');
INSERT INTO `thing_type` VALUES ('10', '0', '软件实训', '9');
INSERT INTO `thing_type` VALUES ('11', '0', '软件概论', '9');
INSERT INTO `thing_type` VALUES ('12', '0', '油气管道SCADA系统与过程控制', '9');
INSERT INTO `thing_type` VALUES ('13', '0', '成品油管道设计', '9');
INSERT INTO `thing_type` VALUES ('16', '0', '本科毕设', '3');
INSERT INTO `thing_type` VALUES ('17', '0', '硕士论文', '3');
INSERT INTO `thing_type` VALUES ('18', '0', '教改项目', '3');
