/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50539
Source Host           : 127.0.0.1:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50539
File Encoding         : 65001

Date: 2016-07-19 14:25:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sssp_department
-- ----------------------------
DROP TABLE IF EXISTS `sssp_department`;
CREATE TABLE `sssp_department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `department_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sssp_department
-- ----------------------------
INSERT INTO `sssp_department` VALUES ('1', '开发部');
INSERT INTO `sssp_department` VALUES ('2', '事业部');
INSERT INTO `sssp_department` VALUES ('3', '公关部');
INSERT INTO `sssp_department` VALUES ('4', '测试部');
INSERT INTO `sssp_department` VALUES ('5', '销售部');

-- ----------------------------
-- Table structure for sssp_employee
-- ----------------------------
DROP TABLE IF EXISTS `sssp_employee`;
CREATE TABLE `sssp_employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `birth` date DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `department_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_kxamu7gqs4cv7b7vm3hks8h6n` (`department_id`),
  CONSTRAINT `FK_kxamu7gqs4cv7b7vm3hks8h6n` FOREIGN KEY (`department_id`) REFERENCES `sssp_department` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sssp_employee
-- ----------------------------
INSERT INTO `sssp_employee` VALUES ('9', '2016-05-25', '2016-06-18 10:37:24', 'ii@qq.com', '你四哥', '2');
INSERT INTO `sssp_employee` VALUES ('11', '2016-02-12', '2016-07-18 15:21:46', 'adxw@i5018.com', '阿达兮哇', '1');
INSERT INTO `sssp_employee` VALUES ('12', '2011-11-11', '2016-07-18 15:22:58', 'wang@fengzi.com', '王疯子', '2');
INSERT INTO `sssp_employee` VALUES ('13', '1999-12-02', '2016-07-18 17:17:29', 'ngsb@qq.com', '你个萨比', '3');
INSERT INTO `sssp_employee` VALUES ('14', '2016-02-18', '2016-07-19 13:56:16', 'nsgsb@nsgsb.com', '你司个SB', '1');
