/*
Navicat MySQL Data Transfer

Source Server         : 阿里云mysql
Source Server Version : 50718
Source Host           : rm-uf664wu2lfctsrtczo.mysql.rds.aliyuncs.com:3306
Source Database       : cff

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2017-12-26 13:44:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sequence
-- ----------------------------
DROP TABLE IF EXISTS `sequence`;
CREATE TABLE `sequence` (
  `sequence_name` varchar(64) NOT NULL COMMENT '序列名称',
  `value` int(11) DEFAULT NULL COMMENT '当前值',
  PRIMARY KEY (`sequence_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
