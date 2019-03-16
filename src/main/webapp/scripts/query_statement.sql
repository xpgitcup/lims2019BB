/*
Navicat MySQL Data Transfer

Source Server         : sample
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : lims2018bdb

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-03-15 08:50:07
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `query_statement`
-- ----------------------------
DROP TABLE IF EXISTS `query_statement`;
CREATE TABLE `query_statement` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `params_list` varchar(255) DEFAULT NULL,
  `hql` varchar(255) DEFAULT NULL,
  `issql` bit(1) DEFAULT NULL,
  `need_to_query` bit(1) NOT NULL,
  `key_string` varchar(255) NOT NULL,
  `view_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `UK_iejb2adhrl11w1nanxv8r9hql` (`key_string`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=217 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of query_statement
-- ----------------------------
INSERT INTO `query_statement` VALUES ('162', '0', null, 'select count(*) from QueryStatement queryStatement', '\0', '', 'count.operation4QueryStatement.查询配置', null);
INSERT INTO `query_statement` VALUES ('163', '0', null, 'from QueryStatement queryStatement order by keyString', '\0', '', 'list.operation4QueryStatement.查询配置', 'listQueryStatement');
INSERT INTO `query_statement` VALUES ('164', '0', null, 'select count(*) from SystemAttribute systemAttribute where upAttribute is null', '\0', '', 'count.operation4SystemAttribute.系统属性', null);
INSERT INTO `query_statement` VALUES ('165', '0', null, 'from SystemAttribute systemAttribute where upAttribute is null', '\0', '', 'list.operation4SystemAttribute.系统属性', 'listSystemAttribute');
INSERT INTO `query_statement` VALUES ('166', '0', null, 'select count(*) from SystemUser systemUser', '\0', '', 'count.operation4SystemUser.系统用户', null);
INSERT INTO `query_statement` VALUES ('167', '0', null, 'from SystemUser systemUser', '\0', '', 'list.operation4SystemUser.系统用户', 'listSystemUser');
INSERT INTO `query_statement` VALUES ('168', '0', null, 'select count(*) from SystemMenu systemMenu where upMenuItem is null', '\0', '', 'count.operation4SystemMenu.系统菜单', null);
INSERT INTO `query_statement` VALUES ('169', '0', null, 'from SystemMenu systemMenu where upMenuItem is null', '\0', '', 'list.operation4SystemMenu.系统菜单', 'listSystemMenu');
INSERT INTO `query_statement` VALUES ('170', '0', null, 'select count(*) from SystemLog systemLog', '\0', '', 'count.operation4SystemLog.系统日志', null);
INSERT INTO `query_statement` VALUES ('171', '0', null, 'from SystemLog systemLog', '\0', '', 'list.operation4SystemLog.系统日志', 'listSystemLog');
INSERT INTO `query_statement` VALUES ('172', '0', null, 'from Teacher teacher', '\0', '', 'list.operation4Person.教师', 'listTeacher');
INSERT INTO `query_statement` VALUES ('173', '0', null, 'from Student student order by personTitle, supervisor, gradeName,  major, code', '\0', '', 'list.operation4Person.学生', 'listStudent');
INSERT INTO `query_statement` VALUES ('174', '0', null, 'from Major major order by name', '\0', '', 'list.operation4Person.专业', 'listMajor');
INSERT INTO `query_statement` VALUES ('175', '0', null, 'select count(*) from Teacher teacher', '\0', '', 'count.operation4Person.教师', null);
INSERT INTO `query_statement` VALUES ('176', '0', null, 'select count(*) from Student student', '\0', '', 'count.operation4Person.学生', null);
INSERT INTO `query_statement` VALUES ('177', '0', null, 'select count(*) from Major major', '\0', '', 'count.operation4Person.专业', null);
INSERT INTO `query_statement` VALUES ('178', '0', null, 'select count(*) from ThingType thingType where upType is null', '\0', '', 'count.operation4ThingType.项目类型', null);
INSERT INTO `query_statement` VALUES ('179', '0', null, 'select count(*) from PersonTitle personTitle where upTitle is null', '\0', '', 'count.operation4PersonTitle.人员类型', null);
INSERT INTO `query_statement` VALUES ('180', '0', null, 'from Project project order by name', '\0', '', 'list.operation4Thing.科研', 'listProject');
INSERT INTO `query_statement` VALUES ('181', '0', null, 'select count(*) from Project project', '\0', '', 'count.operation4Thing.科研', null);
INSERT INTO `query_statement` VALUES ('182', '0', null, 'from Course course order by name', '\0', '', 'list.operation4Thing.教学', 'listCourse');
INSERT INTO `query_statement` VALUES ('183', '0', null, 'select count(*) from Course course', '\0', '', 'count.operation4Thing.教学', null);
INSERT INTO `query_statement` VALUES ('184', '0', null, 'select count(*) from ThingTypeCircle thingTypeCircle', '\0', '', 'count.operation4ThingTypeCircle.任务分配', null);
INSERT INTO `query_statement` VALUES ('185', '0', null, 'from ThingTypeCircle thingTypeCircle order by thingType', '\0', '', 'list.operation4ThingTypeCircle.任务分配', 'listThingTypeCircle');
INSERT INTO `query_statement` VALUES ('186', '0', 'thingTypeList', 'from Thing thing where thing.thingType in :thingTypeList', '\0', '', 'list.operation4Team.可选题目.thingTypeList', 'listThing');
INSERT INTO `query_statement` VALUES ('187', '0', 'thingTypeList', 'select count(*) from Thing thing where thing.thingType in :thingTypeList', '\0', '', 'count.operation4Team.可选题目.thingTypeList', null);
INSERT INTO `query_statement` VALUES ('188', '0', null, 'select count(*) from QueryStatement queryStatement where queryStatement.hql is null', '\0', '', 'count.operation4QueryStatement.filter.查询配置', null);
INSERT INTO `query_statement` VALUES ('189', '0', null, 'from QueryStatement queryStatement where queryStatement.hql is null  order by keyString', '\0', '', 'list.operation4QueryStatement.filter.查询配置', 'listQueryStatement');
INSERT INTO `query_statement` VALUES ('190', '0', 'currentThing', 'from Team team where team.thing=:currentThing', '\0', '', 'list.operation4Team.currentThing.相关团队', 'listTeam');
INSERT INTO `query_statement` VALUES ('191', '0', 'currentThing', 'select count(*) from Team team where team.thing=:currentThing', '\0', '', 'count.operation4Team.currentThing.相关团队', null);
INSERT INTO `query_statement` VALUES ('192', '0', 'myself', 'from Team team where team.leader=:myself', '\0', '', 'list.operation4Progress.我领导的.myself', 'listTeam');
INSERT INTO `query_statement` VALUES ('193', '0', 'myself', 'select count(*) from Team team where team.leader=:myself', '\0', '', 'count.operation4Progress.我领导的.myself', 'listTeam');
INSERT INTO `query_statement` VALUES ('194', '0', 'myself', 'select team_members_id  from team_person where person_id=:myself', '', '', 'list.operation4Progress.我参与的.myself', 'listTeam');
INSERT INTO `query_statement` VALUES ('195', '0', 'myself', 'select count(*) from team_person where person_id=:myself', '', '', 'count.operation4Progress.我参与的.myself', null);
INSERT INTO `query_statement` VALUES ('196', '0', 'currentTeam', 'from Progress progress where progress.team=:currentTeam order by progress.regDate desc', '\0', '', 'list.operation4Progress.currentTeam.进度查看', 'listProgress');
INSERT INTO `query_statement` VALUES ('197', '0', 'currentTeam', 'select count(*) from Progress progress where progress.team=:currentTeam', '\0', '', 'count.operation4Progress.currentTeam.进度查看', 'listProgress');
INSERT INTO `query_statement` VALUES ('198', '0', 'currentProgress', 'from Evaluate evaluate where evaluate.progress=:currentProgress', '\0', '', 'list.operation4Progress.currentProgress.反馈信息', 'listEvaluate');
INSERT INTO `query_statement` VALUES ('199', '0', 'currentProgress', 'select count(*) from Evaluate evaluate where evaluate.progress=:currentProgress', '\0', '', 'count.operation4Progress.currentProgress.反馈信息', null);
INSERT INTO `query_statement` VALUES ('200', '0', null, 'SELECT Count(DISTINCT person.grade_name) FROM person WHERE person.grade_name IS NOT NULL', '', '', 'count.operation4Person.年级', null);
INSERT INTO `query_statement` VALUES ('201', '0', null, 'SELECT DISTINCT person.grade_name FROM person WHERE person.grade_name IS NOT NULL limit %d,%d', '', '', 'list.operation4Person.年级', 'listGradeName');
INSERT INTO `query_statement` VALUES ('204', '0', 'thingTypeList', 'from Team team where team.thing in :thingTypeList order by team.thing', '\0', '', 'list.operation4Progress.我管理的.thingTypeList', 'listTeam');
INSERT INTO `query_statement` VALUES ('205', '0', 'thingTypeList', 'select count(*) from Team team where team.thing in :thingTypeList', '\0', '', 'count.operation4Progress.我管理的.thingTypeList', null);
INSERT INTO `query_statement` VALUES ('206', '0', null, null, '\0', '\0', 'list.operation4Team.队员列表', null);
INSERT INTO `query_statement` VALUES ('208', '0', null, null, '\0', '\0', 'list.operation4Team.currentTeam.队员列表', null);
INSERT INTO `query_statement` VALUES ('210', '0', 'keyString', 'select count(*) from QueryStatement queryStatement where queryStatement.keyString like :keyString', '\0', '', 'count.operation4QueryStatement.查询配置.keyString', null);
INSERT INTO `query_statement` VALUES ('211', '0', 'keyString', 'from QueryStatement queryStatement where queryStatement.keyString like :keyString', '\0', '', 'list.operation4QueryStatement.查询配置.keyString', 'listQueryStatement');
INSERT INTO `query_statement` VALUES ('216', '2', 'thingOrTypeId', 'from Plan plan  where plan.thingOrTypeId=:thingOrTypeId', '\0', '', 'list.operation4Plan.通用计划.thingOrTypeId', 'listPlan');
