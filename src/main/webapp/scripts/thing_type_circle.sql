/*
Navicat MySQL Data Transfer

Source Server         : 10.1.16.50
Source Server Version : 50627
Source Host           : 10.1.16.50:3306
Source Database       : lims2018bdb

Target Server Type    : MYSQL
Target Server Version : 50627
File Encoding         : 65001

Date: 2019-03-10 14:39:12
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `thing_type_circle`
-- ----------------------------
DROP TABLE IF EXISTS `thing_type_circle`;
CREATE TABLE `thing_type_circle` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `person_title_id` bigint(20) NOT NULL,
  `thing_type_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1dbela98vlo3e0iyjuamvulvj` (`person_title_id`),
  KEY `FKssm8med14m6dsxrdrx28o1qfo` (`thing_type_id`),
  CONSTRAINT `FK1dbela98vlo3e0iyjuamvulvj` FOREIGN KEY (`person_title_id`) REFERENCES `person_title` (`id`),
  CONSTRAINT `FKssm8med14m6dsxrdrx28o1qfo` FOREIGN KEY (`thing_type_id`) REFERENCES `thing_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of thing_type_circle
-- ----------------------------
INSERT INTO `thing_type_circle` VALUES ('1', '0', '2', '2');
INSERT INTO `thing_type_circle` VALUES ('2', '0', '2', '3');
INSERT INTO `thing_type_circle` VALUES ('3', '0', '15', '13');
INSERT INTO `thing_type_circle` VALUES ('4', '0', '17', '11');
INSERT INTO `thing_type_circle` VALUES ('5', '0', '14', '2');
INSERT INTO `thing_type_circle` VALUES ('6', '0', '17', '12');
INSERT INTO `thing_type_circle` VALUES ('7', '0', '2', '18');
INSERT INTO `thing_type_circle` VALUES ('8', '0', '17', '18');
