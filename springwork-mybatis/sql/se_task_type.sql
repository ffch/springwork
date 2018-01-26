/*
Navicat MySQL Data Transfer

Source Server         : docker
Source Server Version : 50173
Source Host           : 192.168.99.100:3306
Source Database       : bussi

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2017-05-13 21:43:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cff_task
-- ----------------------------
DROP TABLE IF EXISTS `se_task_type`;
CREATE TABLE `se_task_type` (
  `tasktype` varchar(2) DEFAULT NULL COMMENT '01:基础服务 02:技术支持 03:产品申请 04:会议申请 05:其他',
  `name` varchar(10) DEFAULT NULL,
  `type` varchar(2) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cff_task
-- ----------------------------
