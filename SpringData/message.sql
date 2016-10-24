/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50709
Source Host           : localhost:3306
Source Database       : myssh

Target Server Type    : MYSQL
Target Server Version : 50709
File Encoding         : 65001

Date: 2016-10-24 11:31:48
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
  `name` varchar(20) NOT NULL,
  `update_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `ip` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('37', '1472724499', '2016-09-01', '100.01', '发生发哈市地方', '哈哈', '2016-09-01 18:08:19', '127.0.0.1');
INSERT INTO `message` VALUES ('84', '1482726919', '2016-09-01', '100.01', 'asdf', 'yty', '2016-09-01 18:48:39', '192.168.75.112');
INSERT INTO `message` VALUES ('85', '1882726919', '2016-09-01', '100.01', 'asdfgg', 'rr', '2016-09-01 18:48:39', '8.8.8.8');
INSERT INTO `message` VALUES ('90', '0', null, '0.00', '杭州起码科技有限公司杭州起码科技有限公司', '杭州起码科技有限公司', null, null);
INSERT INTO `message` VALUES ('91', '0', null, '0.00', '杭州起码科技有限公司杭州起码科技有限公司杭州起码科技有限公司杭州起码科技有限公司杭州起码科技有限公司杭州起码科技有限公司杭州起码科技有限公司杭州起码科技有限公司杭州起码科技有限公司杭州起码科技有限公司杭州起码科技有限公司杭州起码科技有限公司', '杭州起码科技有限公司杭州起码科技有限公司', null, null);
INSERT INTO `message` VALUES ('92', '1882726919', '2016-10-24', '1111.00', 'fasdfsadf', 'dduu888', '2016-10-24 11:29:19', '10.0.0.');
