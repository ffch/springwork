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
DROP TABLE IF EXISTS `se_user_task`;
CREATE TABLE `se_user_task` (
  `taskid` varchar(64) DEFAULT NULL,
  `type` varchar(2) NOT NULL DEFAULT '1' COMMENT '01:建议',
  `tasktype` varchar(2) DEFAULT NULL COMMENT '01:基础服务 02:技术支持 03:产品申请 04:会议申请 05:其他',
  `content` varchar(255) DEFAULT NULL,
  `name` varchar(10) DEFAULT NULL,
  `email` varchar(64) DEFAULT NULL,
  `mobile` varchar(11) DEFAULT NULL,
  `userid` varchar(11) DEFAULT NULL COMMENT '发起人',
  `title` varchar(30) DEFAULT NULL,
  `curviewer` varchar(11) DEFAULT NULL,
  `status` varchar(1) DEFAULT '0' COMMENT '0:初始化 1:进行中 2：已完成'
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cff_task
-- ----------------------------
