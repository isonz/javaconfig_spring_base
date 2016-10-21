/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50709
Source Host           : localhost:3306
Source Database       : myssh

Target Server Type    : MYSQL
Target Server Version : 50709
File Encoding         : 65001

Date: 2016-10-21 16:48:37
*/

SET FOREIGN_KEY_CHECKS=0;

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
  `name` varchar(20) DEFAULT NULL,
  `update_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `ip` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('37', '1472724499', '2016-09-01', '100.01', '发生发哈市地方', '哈哈', '2016-09-01 18:08:19', '127.0.0.1');
INSERT INTO `message` VALUES ('84', '1482726919', '2016-09-01', '100.01', 'asdf', 'rr', '2016-09-01 18:48:39', '192.168.75.112');
INSERT INTO `message` VALUES ('85', '1882726919', '2016-09-01', '100.01', 'asdfgg', 'rr', '2016-09-01 18:48:39', '8.8.8.8');
INSERT INTO `message` VALUES ('86', '0', null, '0.00', '????', '????', null, null);
INSERT INTO `message` VALUES ('87', '0', null, '0.00', '?????', '??????????', null, null);
