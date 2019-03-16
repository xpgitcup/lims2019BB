/*
Navicat MySQL Data Transfer

Source Server         : 10.1.16.50
Source Server Version : 50627
Source Host           : 10.1.16.50:3306
Source Database       : lims2018bdb

Target Server Type    : MYSQL
Target Server Version : 50627
File Encoding         : 65001

Date: 2019-03-10 14:38:29
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `team`
-- ----------------------------
DROP TABLE IF EXISTS `team`;
CREATE TABLE `team` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `thing_id` bigint(20) NOT NULL,
  `leader_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKakp3oh8ul8hjcycd0aq8x9836` (`thing_id`),
  KEY `FK4lrv984nrutn7xe5bwap2p1le` (`leader_id`),
  CONSTRAINT `FK4lrv984nrutn7xe5bwap2p1le` FOREIGN KEY (`leader_id`) REFERENCES `person` (`id`),
  CONSTRAINT `FKakp3oh8ul8hjcycd0aq8x9836` FOREIGN KEY (`thing_id`) REFERENCES `thing` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of team
-- ----------------------------
INSERT INTO `team` VALUES ('3', '3', '10', '2');
INSERT INTO `team` VALUES ('4', '4', '11', '2');
INSERT INTO `team` VALUES ('5', '6', '7', '1');
INSERT INTO `team` VALUES ('6', '8', '8', '1');
INSERT INTO `team` VALUES ('7', '5', '9', '1');
INSERT INTO `team` VALUES ('8', '2', '12', '2');
INSERT INTO `team` VALUES ('9', '1', '13', '2');
INSERT INTO `team` VALUES ('10', '1', '14', '2');
INSERT INTO `team` VALUES ('11', '1', '15', '2');
INSERT INTO `team` VALUES ('12', '1', '16', '2');
INSERT INTO `team` VALUES ('13', '1', '17', '2');
INSERT INTO `team` VALUES ('14', '1', '18', '2');
INSERT INTO `team` VALUES ('15', '1', '19', '2');
INSERT INTO `team` VALUES ('17', '2', '4', '261');
INSERT INTO `team` VALUES ('18', '2', '4', '95');
INSERT INTO `team` VALUES ('19', '2', '4', '240');
INSERT INTO `team` VALUES ('20', '3', '4', '265');
INSERT INTO `team` VALUES ('22', '2', '6', '116');
INSERT INTO `team` VALUES ('23', '2', '7', '252');
INSERT INTO `team` VALUES ('24', '3', '4', '252');
INSERT INTO `team` VALUES ('25', '4', '4', '90');
INSERT INTO `team` VALUES ('26', '2', '4', '238');
INSERT INTO `team` VALUES ('27', '2', '4', '243');
INSERT INTO `team` VALUES ('28', '3', '6', '107');
INSERT INTO `team` VALUES ('30', '2', '6', '131');
INSERT INTO `team` VALUES ('31', '3', '4', '257');
INSERT INTO `team` VALUES ('32', '3', '6', '120');
INSERT INTO `team` VALUES ('33', '2', '4', '258');
INSERT INTO `team` VALUES ('34', '2', '6', '114');
INSERT INTO `team` VALUES ('36', '3', '6', '110');
INSERT INTO `team` VALUES ('37', '12', '6', '105');
INSERT INTO `team` VALUES ('39', '0', '6', '127');
INSERT INTO `team` VALUES ('41', '2', '5', '260');
INSERT INTO `team` VALUES ('42', '2', '4', '247');
INSERT INTO `team` VALUES ('43', '6', '6', '125');
INSERT INTO `team` VALUES ('45', '2', '6', '119');
INSERT INTO `team` VALUES ('46', '4', '6', '136');
INSERT INTO `team` VALUES ('48', '2', '6', '102');
INSERT INTO `team` VALUES ('49', '2', '6', '133');
INSERT INTO `team` VALUES ('50', '1', '5', '95');
INSERT INTO `team` VALUES ('51', '3', '5', '82');
INSERT INTO `team` VALUES ('52', '2', '5', '84');
INSERT INTO `team` VALUES ('53', '4', '5', '264');
INSERT INTO `team` VALUES ('54', '2', '5', '97');
