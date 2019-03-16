/*
Navicat MySQL Data Transfer

Source Server         : 10.1.16.50
Source Server Version : 50627
Source Host           : 10.1.16.50:3306
Source Database       : lims2018bdb

Target Server Type    : MYSQL
Target Server Version : 50627
File Encoding         : 65001

Date: 2019-03-10 14:37:01
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `progress`
-- ----------------------------
DROP TABLE IF EXISTS `progress`;
CREATE TABLE `progress` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `current_status` varchar(255) NOT NULL,
  `team_id` bigint(20) NOT NULL,
  `prev_progress_id` bigint(20) DEFAULT NULL,
  `problem_encounter` varchar(255) DEFAULT NULL,
  `support_file_name` varchar(255) DEFAULT NULL,
  `contributor_id` bigint(20) NOT NULL,
  `reg_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9rdflrh05eu15ao9mldbr2eqg` (`team_id`),
  KEY `FKaegt6dto4gjcsiy0xhfy491dx` (`prev_progress_id`),
  KEY `FK3gfg3qw93o00sgybjd3nuy1v0` (`contributor_id`),
  CONSTRAINT `FK3gfg3qw93o00sgybjd3nuy1v0` FOREIGN KEY (`contributor_id`) REFERENCES `person` (`id`),
  CONSTRAINT `FK9rdflrh05eu15ao9mldbr2eqg` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`),
  CONSTRAINT `FKaegt6dto4gjcsiy0xhfy491dx` FOREIGN KEY (`prev_progress_id`) REFERENCES `progress` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of progress
-- ----------------------------
INSERT INTO `progress` VALUES ('1', '0', '已完成布站，且在TLNET内完成了三种算例的建模，今晚进行模拟校核。', '34', null, null, null, '114', '2019-03-04 10:04:00');
INSERT INTO `progress` VALUES ('2', '0', '已完成基础数据计算，选择管径，确定流量，校核流态，已经计算出水力坡降，现在正在完成泵的选型进而确定布站位置', '32', null, '可能需要在已有站场之间加中间站', '12组课程设计计算数据.xlsx', '120', '2019-03-04 10:03:00');
INSERT INTO `progress` VALUES ('3', '0', 'word版（未整理）', '34', '1', null, '课程设计111.docx', '114', '2019-03-04 10:06:00');
INSERT INTO `progress` VALUES ('4', '0', '对所用软件进行调研', '42', null, null, null, '247', '2019-03-04 10:25:00');
INSERT INTO `progress` VALUES ('5', '0', '已完成管径及壁厚计算，并已分组，三名成员各自进行水力计算、泵的选择及泵站布置，进行混油计算及SPS搭建讨论。', '30', null, '主要为开始阶段的变管径问题困扰，目前已解决', 'CA6A6EF4-F594-4031-86E7-5F061364AD0F.jpeg', '131', '2019-03-04 10:20:00');
INSERT INTO `progress` VALUES ('6', '0', '已经根据雷诺数选取管径，计算出水力坡降，正在选泵和布站', '37', null, '暂无', null, '105', '2019-03-04 10:26:00');
INSERT INTO `progress` VALUES ('7', '0', '已完成对大部分软件的调研，选择了LPS及TLNET软件来完成对工况的模拟', '17', null, '软件学习及安装具有一定的难度', null, '260', '2019-03-04 10:27:00');
INSERT INTO `progress` VALUES ('8', '0', '已完成三种方案（管径、壁厚、变径）建立、混油情况、水力计算、成本计算、罐容计算、建立LPS和SPS模型对计算结果进行校核', '28', null, '一、泵型号凭经验选取，我组的解决办法是利用软件进行校核；二、LPS软件高程参数总自动变化，无法更改', '长输管道课程设计兰敏9组.xlsx', '107', '2019-03-04 09:54:00');
INSERT INTO `progress` VALUES ('9', '0', '已完成管径的选取，摩阻的计算，正在进行选泵和布站，以及经济必选', '43', null, null, null, '125', '2019-03-04 10:34:00');
INSERT INTO `progress` VALUES ('10', '0', '已完成三种方案（管径、壁厚、变径）建立、混油情况、水力计算、成本计算、罐容计算、建立LPS和SPS模型对计算结果进行校核', '28', '8', '一、泵型号凭经验选取，我组的解决办法是利用软件进行校核；二、LPS软件高程参数总自动变化，无法更改', '长输管道课程设计兰敏9组.docx', '107', '2019-03-04 10:35:00');
INSERT INTO `progress` VALUES ('11', '0', '学习了LPS的使用，并按照说明书自己模拟了一遍，调研了一部分软件的信息，设置了自己组的一些站点数据', '20', null, 'LPS的瞬态模拟掌握的还不熟练，需要进一步学习', '地温高程站点总传热系数.rar', '265', '2019-03-04 10:29:00');
INSERT INTO `progress` VALUES ('12', '0', '准备使用LPS和TLNET软件做模拟比较，模拟基础数据基本准备完毕。。', '33', null, null, null, '258', '2019-03-04 10:35:00');
INSERT INTO `progress` VALUES ('13', '0', '已经进行了管径和壁厚的计算和选择，校核流态，进行了水力计算，正在做泵的选择以及泵站位置的 选定', '45', null, null, '二.xlsx', '119', '2019-03-04 10:32:00');
INSERT INTO `progress` VALUES ('14', '0', '管道模拟软件的安装和学习', '18', null, null, null, '95', '2019-03-04 10:38:00');
INSERT INTO `progress` VALUES ('15', '0', '完成了基础计算，并用matlab进行了水力计算，正在进行选泵和布站操作', '49', null, null, null, '133', '2019-03-04 10:40:00');
INSERT INTO `progress` VALUES ('16', '0', '管径已经确定，站址位置已布好，目前正在三个方案用汽油进行校核', '22', null, null, '初稿.docx', '116', '2019-03-04 09:42:00');
INSERT INTO `progress` VALUES ('17', '0', '测试1', '6', null, null, '华南项目工作周报-任亮20171019.docx', '224', '2019-03-04 10:42:00');
INSERT INTO `progress` VALUES ('18', '0', '已经为完成三种方案的管径与壁厚选取，并分别进行了沿程摩阻计算，强度校核与稳定性校核，正在准备布站与选泵', '48', null, null, null, '102', '2019-03-04 10:49:00');
INSERT INTO `progress` VALUES ('19', '0', '根据教程学习了LPS和realpipe 软件，并且建立了一条管线，确定了相关数据，并用LPS建立了模型。', '25', null, null, null, '90', '2019-03-04 10:48:00');
INSERT INTO `progress` VALUES ('20', '0', '软件综述调研完成60%完成率，LPS的安装与学习，在进行数据的调试。', '24', null, '其他软件的学习不会', null, '252', '2019-03-04 10:49:00');
INSERT INTO `progress` VALUES ('21', '0', '安装调试SPS和realpipe软件，同时进行综述整理工作', '26', null, null, null, '238', '2019-03-04 10:53:00');
INSERT INTO `progress` VALUES ('22', '0', '初步了解软件调研，暂定使用LPS及TLNET进行工况模拟，进行软件的安装和学习', '27', null, null, null, '244', '2019-03-04 10:55:00');
INSERT INTO `progress` VALUES ('23', '0', '已经为完成三种方案的管径与壁厚选取，三个队员对选取三种管径分别进行了沿程摩阻计算以及翻越点验证，正在进行布站', '46', null, null, '张  悦.docx', '136', '2019-03-04 10:59:00');
INSERT INTO `progress` VALUES ('24', '0', '根据LPS教程学习并模拟克乌管线流程，也开始调研关于LPS软件的一些综述性材料', '19', null, '一位同学的LPS文件保存出现一点问题', null, '240', '2019-03-04 11:02:00');
INSERT INTO `progress` VALUES ('25', '0', '完成基础数据计算，确定变径方案，校核流态，完成水力坡降计算，lps基础数据已录入，现在正在进行布站', '36', null, null, '水力计算.xlsx', '109', '2019-03-04 11:10:00');
INSERT INTO `progress` VALUES ('26', '0', '基础数据准备，数字孪生软件的调研，以及LPS，pipephase软件的学习中', '31', null, null, null, '257', '2019-03-04 11:15:00');
INSERT INTO `progress` VALUES ('27', '0', '中期汇报PPT', '3', null, null, '4_长治单管项目中期汇报12.5.pptx', '48', '2019-03-04 17:02:00');
INSERT INTO `progress` VALUES ('28', '0', '投标文件', '8', null, null, '管道积液模拟试验投标文件.docx', '48', '2019-03-04 17:04:00');
INSERT INTO `progress` VALUES ('29', '0', '会议纪要', '4', null, null, '会议纪要.docx', '48', '2019-03-04 17:09:00');
INSERT INTO `progress` VALUES ('30', '0', '第一周作业', '52', null, null, '第一周作业.docx', '84', '2019-03-04 18:24:00');
INSERT INTO `progress` VALUES ('31', '0', '汇报2018.5.15', '4', null, null, '汇报5.15.pptx', '61', '2019-03-04 18:26:00');
INSERT INTO `progress` VALUES ('32', '0', '汇报2018.5.22', '4', null, null, '汇报5.22.pptx', '61', '2019-03-04 18:30:00');
INSERT INTO `progress` VALUES ('33', '0', '汇报2018.6.22', '4', null, null, '汇报6.22.pptx', '61', '2019-03-04 18:30:00');
INSERT INTO `progress` VALUES ('34', '0', '汇报2018.7.25', '4', null, null, '汇报7.25.pptx', '61', '2019-03-04 18:31:00');
INSERT INTO `progress` VALUES ('35', '0', '汇报2018.8.15', '4', null, null, '汇报8.15.pptx', '61', '2019-03-04 18:31:00');
INSERT INTO `progress` VALUES ('36', '0', '汇报2018.11.12', '4', null, null, '汇报11.12.pptx', '61', '2019-03-04 18:32:00');
INSERT INTO `progress` VALUES ('37', '0', '汇报2018.11.19', '4', null, null, '汇报11.19.pptx', '61', '2019-03-04 18:32:00');
INSERT INTO `progress` VALUES ('38', '0', '汇报2018.11.19', '4', null, null, '汇报11.19.pptx', '61', '2019-03-04 18:32:00');
INSERT INTO `progress` VALUES ('39', '0', '汇报2018.11.19', '4', null, null, '汇报11.19.pptx', '61', '2019-03-04 18:32:00');
INSERT INTO `progress` VALUES ('40', '0', '汇报2018.11.26', '4', null, null, '汇报11.26.pptx', '61', '2019-03-04 18:35:00');
INSERT INTO `progress` VALUES ('41', '0', '汇报2018.12.3', '4', null, null, '汇报12.3.pptx', '61', '2019-03-04 18:36:00');
INSERT INTO `progress` VALUES ('42', '0', '汇报2019.1.7', '4', null, null, '汇报2019.1.7.pptx', '61', '2019-03-04 18:36:00');
INSERT INTO `progress` VALUES ('43', '0', '汇报2019.1.14', '4', null, null, '汇报2019.1.14.pptx', '61', '2019-03-04 18:36:00');
INSERT INTO `progress` VALUES ('44', '0', '管网项目中期汇报12.11', '4', null, null, '长治集输管网项目中期汇报12.11.pptx', '61', '2019-03-04 18:38:00');
INSERT INTO `progress` VALUES ('45', '0', '长治管网项目中期总结12.5', '4', null, null, '长治管网项目中期总结12.5.docx', '61', '2018-12-05 20:32:00');
INSERT INTO `progress` VALUES ('46', '0', '完成SCADA第一次作业--设想大数据的应用场景', '54', null, null, '刘胜男 崔雪萌 周建伟--大数据应用场景.pptx', '97', '2019-03-04 23:31:00');
INSERT INTO `progress` VALUES ('47', '0', '汇报2019.3.5', '4', null, null, '汇报2019.3.5.pptx', '61', '2019-03-05 10:13:00');
INSERT INTO `progress` VALUES ('48', '0', '2018.1.7研究记录册', '4', null, null, '1.7研究记录册.docx', '61', '2018-01-07 10:14:00');
INSERT INTO `progress` VALUES ('49', '0', '华南软件源代码', '6', null, null, 'EasyPipe2017B-华南-2.6.zip', '263', '2019-03-05 13:08:00');
INSERT INTO `progress` VALUES ('50', '0', '温老师程序', '6', null, null, 'XSHN_Data_Transfer.zip', '49', '2019-03-05 15:51:00');
INSERT INTO `progress` VALUES ('51', '0', '华南软件源代码', '6', null, null, 'EasyPipe2017B-华南-2.6.zip', '49', '2019-03-05 15:54:00');
INSERT INTO `progress` VALUES ('52', '0', '已完成汇报ppt', '41', null, null, 'SCADA第一次汇报.pptx', '260', '2019-03-05 19:01:00');
INSERT INTO `progress` VALUES ('53', '0', '完成', '53', null, null, '第一次作业.rar', '264', '2019-03-05 19:37:00');
INSERT INTO `progress` VALUES ('54', '0', '完成第一周大数据应用设想作业', '51', null, null, '大数据智慧化生活设想.docx', '82', '2019-03-05 21:08:00');
INSERT INTO `progress` VALUES ('55', '0', '完成 智慧管道设想', '50', null, null, '智慧管道.pptx', '95', '2019-03-06 15:26:00');
INSERT INTO `progress` VALUES ('56', '0', '继续完善各阶段所用软件的工作简介', '42', '4', null, null, '247', '2019-03-06 16:51:00');
INSERT INTO `progress` VALUES ('57', '0', '数字双生所涉及的软件综述文献查阅中，LPS软件进一步学习中', '31', null, null, null, '257', '2019-03-06 17:00:00');
INSERT INTO `progress` VALUES ('58', '0', 'Lps模拟完毕，正在学习新软件。', '33', null, null, null, '258', '2019-03-06 16:56:00');
INSERT INTO `progress` VALUES ('59', '0', '数字双生相关软件文献查阅进行中，前两个阶段基本完成，LPS软件进一步学习中', '31', null, null, null, '257', '2019-03-06 16:57:00');
INSERT INTO `progress` VALUES ('60', '0', '学习了TLNET和LPS的运用，成员完成分工，确定了流程模拟的基础数据', '27', null, null, null, '243', '2019-03-06 17:06:00');
INSERT INTO `progress` VALUES ('61', '0', '检索油气储运生产设计全过程相关软件', '18', null, null, '勘察.wps', '95', '2019-03-06 17:07:00');
INSERT INTO `progress` VALUES ('62', '0', '已完成LPS软件模型的建立和稳态计算', '17', null, '无', null, '260', '2019-03-06 17:12:00');
INSERT INTO `progress` VALUES ('63', '0', '使用LPS进行了稳态和瞬态的一种工况计算，查阅了相关文献，写了部分综述。', '25', null, null, null, '90', '2019-03-06 17:15:00');
INSERT INTO `progress` VALUES ('64', '0', '综述完成度60%；已确定建模数据，模拟实验收集数据', '26', null, null, null, '238', '2019-03-06 19:31:00');
INSERT INTO `progress` VALUES ('65', '0', '查阅了有关勘察类软件和设计类的软件，并总结了一些常用软件的介绍、使用特点、应用场合、使用技巧。并进行了这些软件的对比。', '27', null, null, null, '243', '2019-03-08 11:41:00');
INSERT INTO `progress` VALUES ('66', '0', '已完成', '22', null, null, '3组长输管道课程设计.docx', '116', '2019-03-09 18:37:00');
INSERT INTO `progress` VALUES ('67', '0', '已完成', '22', null, null, '3组课设答辩.pptx', '116', '2019-03-09 18:42:00');
INSERT INTO `progress` VALUES ('68', '0', '已完成', '22', '67', null, '茂名站工艺流程图.dwg', '116', '2019-03-09 19:27:00');
INSERT INTO `progress` VALUES ('69', '0', '报告终版及PPT', '34', '3', null, '长距离成品油管道工艺课程设计答辩（第四组）.ppt', '114', '2019-03-09 23:49:00');
INSERT INTO `progress` VALUES ('70', '0', '报告终版（计算文档过多 不方便上传）', '34', '69', null, '长输管道课程设计 储运15-1班 4组.docx', '114', '2019-03-09 23:58:00');
INSERT INTO `progress` VALUES ('71', '0', '已完成报告', '37', null, '无', '课设报告', '105', '2019-03-09 06:44:00');
INSERT INTO `progress` VALUES ('72', '0', '已完成首站流程图', '37', null, '无', '二组茂名站工艺流程图.dwg', '105', '2019-03-09 06:46:00');
INSERT INTO `progress` VALUES ('73', '0', '已完成计算', '37', null, '无', '二组课设计算文档.xlsx', '105', '2019-03-10 06:47:00');
INSERT INTO `progress` VALUES ('74', '0', '已完成汇报PPT', '37', null, '无', '二组报告.pptx', '105', '2019-03-09 06:49:00');
INSERT INTO `progress` VALUES ('75', '0', '修改ppt', '37', '74', '字体不够清晰', '二组报告.pptx', '105', '2019-03-10 07:15:00');
INSERT INTO `progress` VALUES ('76', '0', '完成报告撰写 基本完成任务', '28', null, 'SPS无法生成报告 校核与初始计算情况差异较大', '第九组报告终极版.docx', '107', '2019-03-10 08:19:00');
INSERT INTO `progress` VALUES ('77', '0', '汇报PPT', '28', '8', 'SPS无法生成报告 校核与初始计算情况差异较大', '输油答辩.pptx', '107', '2019-03-10 08:23:00');
INSERT INTO `progress` VALUES ('78', '0', '整体计算文档', '28', null, null, '九组长输管道课程设计.xlsx', '107', '2019-03-10 08:25:00');
INSERT INTO `progress` VALUES ('79', '0', '最终结果', '49', null, null, '2019长输管道设计-15-1第八组.zip', '133', '2019-03-10 08:44:00');
INSERT INTO `progress` VALUES ('80', '0', '完成设计', '36', null, '不能熟练运用编程软件', '5组.ppt', '110', '2019-03-10 09:24:00');
INSERT INTO `progress` VALUES ('81', '0', '完成设计', '36', '80', '不能熟练运用编程软件', '成品油计算表格.xlsx', '110', '2019-03-10 09:25:00');
INSERT INTO `progress` VALUES ('82', '0', '完成设计', '36', '81', '不能熟练运用编程软件', '成品油首站流程图2010.dwg', '110', '2019-03-10 09:26:00');
INSERT INTO `progress` VALUES ('83', '0', '完成设计', '36', '82', '不能熟练运用编程软件', '成品油长输管道工艺方案设计报告.pdf', '110', '2019-03-10 09:27:00');
INSERT INTO `progress` VALUES ('84', '0', '更正格式', '37', '71', null, '第二组报告.docx', '105', '2019-03-10 07:03:00');
INSERT INTO `progress` VALUES ('85', '0', '完成软件校核，混油计算，经济比选，首站工艺设计', '48', '18', 'LPS软件模拟存在一点超压和欠压的情况', '包括报告、ppt、计算、cad画图.rar', '102', '2019-03-10 10:06:00');
INSERT INTO `progress` VALUES ('86', '0', '完成', '43', null, null, '第7组油气储运工程长输管道课程设计.dwg', '125', '2019-03-10 10:10:00');
INSERT INTO `progress` VALUES ('87', '0', '完成', '43', '86', null, '第7组油气储运工程长输管道课程设计.docx', '125', '2019-03-10 10:12:00');
INSERT INTO `progress` VALUES ('88', '0', '第二组CAD首站图', '37', null, null, '二组茂名站工艺流程图.dwg', '105', '2019-03-10 10:13:00');
INSERT INTO `progress` VALUES ('89', '0', '完成', '43', null, null, '第7组油气储运工程长输管道课程设计.ppt', '125', '2019-03-10 10:13:00');
INSERT INTO `progress` VALUES ('90', '0', '报告文档', '28', null, null, '第九组报告终极版.docx', '107', '2019-03-10 10:14:00');
INSERT INTO `progress` VALUES ('91', '0', '流程图', '28', null, null, '第九组流程图.pdf', '107', '2019-03-10 10:14:00');
INSERT INTO `progress` VALUES ('92', '0', '对三种管径进行了布站，小组成员对泵站进行了泵型的选择，对三种管径进行了混油段的计算，通过经营成本，建设成本，流动资金成本进行了经济比选，软件校核进出站压力，静水压力校核，动水压力校核，绘制首站工艺流程图，制作PPT', '46', null, null, '第10组课程设计.zip', '136', '2019-03-10 11:00:00');
