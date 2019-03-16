/*
Navicat MySQL Data Transfer

Source Server         : sample
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : lims2018bdb

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-03-14 10:40:39
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `system_attribute`
-- ----------------------------
DROP TABLE IF EXISTS `system_attribute`;
CREATE TABLE `system_attribute` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `up_attribute_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKa8ygck6uvyk4a6bb7ywe573xp` (`up_attribute_id`),
  CONSTRAINT `FKa8ygck6uvyk4a6bb7ywe573xp` FOREIGN KEY (`up_attribute_id`) REFERENCES `system_attribute` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_attribute
-- ----------------------------
INSERT INTO `system_attribute` VALUES ('1', '0', '系统操作权限', null);
INSERT INTO `system_attribute` VALUES ('2', '0', '系统维护', '1');
INSERT INTO `system_attribute` VALUES ('3', '0', '基础维护', '1');
INSERT INTO `system_attribute` VALUES ('4', '0', '公共服务', '1');
INSERT INTO `system_attribute` VALUES ('5', '0', '教师服务', '1');
INSERT INTO `system_attribute` VALUES ('6', '0', '学生服务', '1');
INSERT INTO `system_attribute` VALUES ('7', '0', '底层管理', '1');
INSERT INTO `system_attribute` VALUES ('8', '0', '教师权限', null);
INSERT INTO `system_attribute` VALUES ('9', '0', '学生权限', null);
INSERT INTO `system_attribute` VALUES ('10', '0', '基础维护', '8');
INSERT INTO `system_attribute` VALUES ('11', '1', '用户服务', '8');
INSERT INTO `system_attribute` VALUES ('12', '1', '用户服务', '9');
INSERT INTO `system_attribute` VALUES ('14', '0', '系统维护', '8');
INSERT INTO `system_attribute` VALUES ('15', '0', '底层管理', '8');
INSERT INTO `system_attribute` VALUES ('16', '0', '教师服务', '8');
