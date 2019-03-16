/*
Navicat MySQL Data Transfer

Source Server         : 10.1.16.50
Source Server Version : 50627
Source Host           : 10.1.16.50:3306
Source Database       : lims2018bdb

Target Server Type    : MYSQL
Target Server Version : 50627
File Encoding         : 65001

Date: 2019-03-10 14:38:38
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `team_person`
-- ----------------------------
DROP TABLE IF EXISTS `team_person`;
CREATE TABLE `team_person` (
  `team_members_id` bigint(20) NOT NULL,
  `person_id` bigint(20) DEFAULT NULL,
  KEY `FK849p1qabcqamcqtn57f2h8v8p` (`person_id`),
  KEY `FKt0ne9eoxa1hptfkq4vo8xhd53` (`team_members_id`),
  CONSTRAINT `FK849p1qabcqamcqtn57f2h8v8p` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`),
  CONSTRAINT `FKt0ne9eoxa1hptfkq4vo8xhd53` FOREIGN KEY (`team_members_id`) REFERENCES `team` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of team_person
-- ----------------------------
INSERT INTO `team_person` VALUES ('3', '48');
INSERT INTO `team_person` VALUES ('3', '61');
INSERT INTO `team_person` VALUES ('5', '2');
INSERT INTO `team_person` VALUES ('5', '15');
INSERT INTO `team_person` VALUES ('5', '43');
INSERT INTO `team_person` VALUES ('6', '2');
INSERT INTO `team_person` VALUES ('6', '224');
INSERT INTO `team_person` VALUES ('6', '6');
INSERT INTO `team_person` VALUES ('6', null);
INSERT INTO `team_person` VALUES ('6', null);
INSERT INTO `team_person` VALUES ('6', '49');
INSERT INTO `team_person` VALUES ('6', '34');
INSERT INTO `team_person` VALUES ('5', '57');
INSERT INTO `team_person` VALUES ('5', '48');
INSERT INTO `team_person` VALUES ('5', '59');
INSERT INTO `team_person` VALUES ('6', '263');
INSERT INTO `team_person` VALUES ('7', '2');
INSERT INTO `team_person` VALUES ('7', '15');
INSERT INTO `team_person` VALUES ('7', '75');
INSERT INTO `team_person` VALUES ('7', '69');
INSERT INTO `team_person` VALUES ('7', '64');
INSERT INTO `team_person` VALUES ('3', '1');
INSERT INTO `team_person` VALUES ('4', '1');
INSERT INTO `team_person` VALUES ('4', '48');
INSERT INTO `team_person` VALUES ('4', '15');
INSERT INTO `team_person` VALUES ('4', '61');
INSERT INTO `team_person` VALUES ('8', '48');
INSERT INTO `team_person` VALUES ('8', '61');
INSERT INTO `team_person` VALUES ('9', '29');
INSERT INTO `team_person` VALUES ('10', '43');
INSERT INTO `team_person` VALUES ('11', '57');
INSERT INTO `team_person` VALUES ('12', '263');
INSERT INTO `team_person` VALUES ('13', '34');
INSERT INTO `team_person` VALUES ('14', '49');
INSERT INTO `team_person` VALUES ('15', '29');
INSERT INTO `team_person` VALUES ('17', '260');
INSERT INTO `team_person` VALUES ('17', '91');
INSERT INTO `team_person` VALUES ('19', '241');
INSERT INTO `team_person` VALUES ('20', null);
INSERT INTO `team_person` VALUES ('19', '266');
INSERT INTO `team_person` VALUES ('22', '118');
INSERT INTO `team_person` VALUES ('22', '115');
INSERT INTO `team_person` VALUES ('20', '256');
INSERT INTO `team_person` VALUES ('24', '253');
INSERT INTO `team_person` VALUES ('20', '255');
INSERT INTO `team_person` VALUES ('25', null);
INSERT INTO `team_person` VALUES ('24', '254');
INSERT INTO `team_person` VALUES ('26', '68');
INSERT INTO `team_person` VALUES ('26', '33');
INSERT INTO `team_person` VALUES ('25', '249');
INSERT INTO `team_person` VALUES ('18', '245');
INSERT INTO `team_person` VALUES ('27', '244');
INSERT INTO `team_person` VALUES ('18', '94');
INSERT INTO `team_person` VALUES ('27', '93');
INSERT INTO `team_person` VALUES ('30', '135');
INSERT INTO `team_person` VALUES ('28', '124');
INSERT INTO `team_person` VALUES ('28', '127');
INSERT INTO `team_person` VALUES ('30', '128');
INSERT INTO `team_person` VALUES ('25', null);
INSERT INTO `team_person` VALUES ('25', '239');
INSERT INTO `team_person` VALUES ('31', '259');
INSERT INTO `team_person` VALUES ('31', '242');
INSERT INTO `team_person` VALUES ('31', null);
INSERT INTO `team_person` VALUES ('32', null);
INSERT INTO `team_person` VALUES ('32', '100');
INSERT INTO `team_person` VALUES ('32', '129');
INSERT INTO `team_person` VALUES ('33', '251');
INSERT INTO `team_person` VALUES ('34', '113');
INSERT INTO `team_person` VALUES ('33', '250');
INSERT INTO `team_person` VALUES ('34', '117');
INSERT INTO `team_person` VALUES ('36', null);
INSERT INTO `team_person` VALUES ('37', null);
INSERT INTO `team_person` VALUES ('37', null);
INSERT INTO `team_person` VALUES ('37', null);
INSERT INTO `team_person` VALUES ('37', null);
INSERT INTO `team_person` VALUES ('37', null);
INSERT INTO `team_person` VALUES ('37', null);
INSERT INTO `team_person` VALUES ('37', null);
INSERT INTO `team_person` VALUES ('37', null);
INSERT INTO `team_person` VALUES ('37', '106');
INSERT INTO `team_person` VALUES ('37', null);
INSERT INTO `team_person` VALUES ('37', '103');
INSERT INTO `team_person` VALUES ('37', null);
INSERT INTO `team_person` VALUES ('41', '256');
INSERT INTO `team_person` VALUES ('41', '255');
INSERT INTO `team_person` VALUES ('42', '246');
INSERT INTO `team_person` VALUES ('42', '56');
INSERT INTO `team_person` VALUES ('45', '123');
INSERT INTO `team_person` VALUES ('43', null);
INSERT INTO `team_person` VALUES ('45', '104');
INSERT INTO `team_person` VALUES ('46', null);
INSERT INTO `team_person` VALUES ('43', null);
INSERT INTO `team_person` VALUES ('43', null);
INSERT INTO `team_person` VALUES ('43', null);
INSERT INTO `team_person` VALUES ('43', '121');
INSERT INTO `team_person` VALUES ('43', '130');
INSERT INTO `team_person` VALUES ('46', null);
INSERT INTO `team_person` VALUES ('46', '108');
INSERT INTO `team_person` VALUES ('46', '126');
INSERT INTO `team_person` VALUES ('49', '134');
INSERT INTO `team_person` VALUES ('49', '132');
INSERT INTO `team_person` VALUES ('48', '111');
INSERT INTO `team_person` VALUES ('48', '101');
INSERT INTO `team_person` VALUES ('24', null);
INSERT INTO `team_person` VALUES ('36', '109');
INSERT INTO `team_person` VALUES ('36', '112');
INSERT INTO `team_person` VALUES ('51', '81');
INSERT INTO `team_person` VALUES ('51', null);
INSERT INTO `team_person` VALUES ('51', '92');
INSERT INTO `team_person` VALUES ('50', '94');
INSERT INTO `team_person` VALUES ('52', '83');
INSERT INTO `team_person` VALUES ('52', '68');
INSERT INTO `team_person` VALUES ('53', null);
INSERT INTO `team_person` VALUES ('53', null);
INSERT INTO `team_person` VALUES ('54', '93');
INSERT INTO `team_person` VALUES ('54', '96');
INSERT INTO `team_person` VALUES ('5', '80');
INSERT INTO `team_person` VALUES ('53', '66');
INSERT INTO `team_person` VALUES ('53', '265');
INSERT INTO `team_person` VALUES ('28', null);
