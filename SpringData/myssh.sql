/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50524
Source Host           : localhost:3306
Source Database       : myssh

Target Server Type    : MYSQL
Target Server Version : 50524
File Encoding         : 65001

Date: 2016-10-26 00:05:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for dept
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `parent_id` int(10) unsigned DEFAULT NULL COMMENT '父级ID',
  PRIMARY KEY (`id`),
  KEY `FKpo40eieof9vp6499qcs7ep9et` (`parent_id`),
  CONSTRAINT `FKpo40eieof9vp6499qcs7ep9et` FOREIGN KEY (`parent_id`) REFERENCES `dept` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dept
-- ----------------------------
INSERT INTO `dept` VALUES ('1', '电商部', null);

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `create_at` int(10) unsigned DEFAULT NULL COMMENT '时间戳',
  `date` date DEFAULT NULL,
  `days` float(10,2) DEFAULT NULL,
  `msg` longtext,
  `name` varchar(20) NOT NULL,
  `update_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `ip` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=96 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('37', '1472724499', '2016-09-01', '100.01', '发生发哈市地方', '哈哈', '2016-09-01 18:08:19', '127.0.0.1');
INSERT INTO `message` VALUES ('84', '1482726919', '2016-09-01', '100.01', 'asdf', 'ytybbbbbbbbbbfffffff', '2016-09-01 18:48:39', '192.168.75.112');
INSERT INTO `message` VALUES ('90', '0', null, '0.00', '杭州起码科技有限公司杭州起码科技有限公司', '杭州起码科技有限公司', null, null);
INSERT INTO `message` VALUES ('91', '0', null, '0.00', 'uuuuurrrrffff', '杭州起码fff', null, null);
INSERT INTO `message` VALUES ('92', '0', null, '0.00', 'ff', 'uuuuu让人啊啊啊', null, null);
INSERT INTO `message` VALUES ('95', '0', null, '0.00', 'fffffffffffffffff', 'サンヤ (三亜)を体験しよう!', '2016-10-25 22:55:13', null);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `code` varchar(20) DEFAULT NULL COMMENT '角色代码',
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'dept_manger', '部门经理');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `openid` varchar(30) DEFAULT NULL COMMENT '微信OPENID',
  `username` varchar(20) DEFAULT NULL,
  `userid` varchar(20) DEFAULT NULL COMMENT '微信企业号里的userid',
  `valid` bit(1) DEFAULT NULL COMMENT '用户是否有效T,F',
  `dept_id` int(10) unsigned DEFAULT NULL COMMENT '部门ID，外键',
  PRIMARY KEY (`id`),
  KEY `FK5rwmryny6jthaaxkogownknqp` (`dept_id`),
  CONSTRAINT `FK5rwmryny6jthaaxkogownknqp` FOREIGN KEY (`dept_id`) REFERENCES `dept` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'openid_ison', 'username_ison', 'userid_ison', '', '1');
INSERT INTO `user` VALUES ('2', 'katie', '13632252275', 'katie', '', null);
INSERT INTO `user` VALUES ('3', '张三丰', '张三丰', '张无忌', '', null);
INSERT INTO `user` VALUES ('4', '张三丰', '张三丰不能重复', '张无忌', '', null);
INSERT INTO `user` VALUES ('5', '张三丰', '张三丰还是不能重复', '张无忌', '', null);
INSERT INTO `user` VALUES ('6', 'katie', '也是不能重复', 'katie', '', null);
INSERT INTO `user` VALUES ('7', 'katie', '什么？不可以？', 'katie', '', null);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `users_id` int(10) unsigned NOT NULL,
  `roles_id` int(10) unsigned NOT NULL,
  KEY `FKeog8p06nu33ihk13roqnrp1y6` (`roles_id`),
  KEY `FKip2yfnw7nx55paaa4i18j7moj` (`users_id`),
  CONSTRAINT `FKip2yfnw7nx55paaa4i18j7moj` FOREIGN KEY (`users_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKeog8p06nu33ihk13roqnrp1y6` FOREIGN KEY (`roles_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1');
